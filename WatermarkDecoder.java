


import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author doulat
 */
public class WatermarkDecoder {

    public static void main(String[] args) throws IOException {

        Gson gson = new Gson();
        Reader jsonReader = new FileReader("C:\\Users\\doula\\Desktop\\DecoderInput\\DecodingInfo.json");
        DecodeInputJsonSetup setup = gson.fromJson(jsonReader, DecodeInputJsonSetup.class);

        int i;
        int numSections = setup.getNumSections();

        // Sorting key initializations
        SecureRandom secure_random = new SecureRandom();
        BigInteger mySecretKey = new BigInteger(160, secure_random);
        mySecretKey = setup.getMySecretKey();

        // Hashing inintializations
        MessageDigest md = null;

        try {
            md = MessageDigest.getInstance("SHA-1");
            md.reset(); // reseting the hashing algorithm
        } catch (NoSuchAlgorithmException exc) {
            System.out.println("Algorithm not found");
        }

        // Create the random set of data and populate the Tuples vector
        Tuples mytuples = new Tuples();
        String line = null;
        Stats statistics = new Stats();
        double c = setup.getC();
        double T = setup.getT(); // updated for the gausian case..
        String myWatermark = setup.getMyWatermark();
        int WLength = myWatermark.length();
        if (args.length > 0) {
            T = Double.parseDouble(args[0]);
            System.out.println("T = " + T);
        }
        String inputPath = setup.getInput_path();
        File inFile = new File(inputPath);  // read from first file specified
        try (BufferedReader inDataFile = new BufferedReader(new FileReader(inFile))) {
            while ((line = inDataFile.readLine()) != null) {
                int comma = line.lastIndexOf(',');
                int primarykey = Integer.parseInt(line.substring(0, comma));
                double data = Double.parseDouble(line.substring(comma + 1, line.length()));
                System.out.println(primarykey + "," + data);
                mytuples.add(new Tuple(primarykey, md, mySecretKey, data));
            }
        }
        int[] onesCount = new int[WLength];
        int[] onesCountInd = new int[WLength];
        mytuples.get_sections_step1(numSections);
        mytuples.sort();
        mytuples.get_sections_step2();
        for (i = 0;i < WLength;i++) {
            onesCount[i] = 0;
            onesCountInd[i] = -1;
        }
        int minSec = setup.getMinSec();
        String embededW = "";
        int numEmbeded = 0;
        for (i = 0;i < mytuples.sections.length;i++) {
            int j;
            if (mytuples.sections[i].length >= minSec) {
                double[] s = new double[mytuples.sections[i].length];
                for (j = 0; j < s.length; j++) {
                    Tuple t = (Tuple) mytuples.get(mytuples.sections[i].start + j);
                    s[j] = t.data;
                }

                int embededBit = statistics.getBit(s, c, T);
                embededW = embededW + embededBit;

                onesCount[embededW.length() - 1] = onesCount[embededW.length() - 1] + embededBit;

                onesCountInd[embededW.length() - 1] = 1;
            } else {
                embededW = embededW + "e";
            }

            if (embededW.length() >= myWatermark.length()) {
                embededW = "";
                numEmbeded++;
            }
        }
        line = "";
        for (i = 0;i < WLength;i++) {
            if (onesCountInd[i] == 1) {
                if (((double) onesCount[i]) / ((double) numEmbeded) >= 0.5) {
                    line = line + "1";
                } else {
                    line = line + "0";
                }
            } else {
                line = line + "e";
            }
        }
        JsonDecoderOutputHelper df = new JsonDecoderOutputHelper(line);
        String Private_output = setup.getoutput_path();
        try (FileWriter privateDecodeInfo = new FileWriter(Private_output)) {
            gson.toJson(df, privateDecodeInfo);
        }
        System.out.println("The embeded watermark is:  " + line);
    }
}
