
import java.math.BigInteger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author doula
 */
public class DecodeInputJsonSetup {

    private int numinter;
    private int datasize;
    private int avgsection_size;
    private int avgnum_sections;
    private int numSections;
    private int section_min_size;
    private double c;
    private double T; // updated for the gausian case..
    private String myWatermark;
    private BigInteger mySecretKey;
    private int minSec;
    private String input_path;
    private String output_path;

    public String getInput_path() {
        return input_path;
    }

    public void setInput_path(String input_path) {
        this.input_path = input_path;
    }

    public String getoutput_path() {
        return output_path;
    }

    public void setoutput_path(String output_path) {
        this.output_path = output_path;
    }

    public int getMinSec() {
        return minSec;
    }

    public void setMinSec(int minSec) {
        this.minSec = minSec;
    }

    public int getNuminter() {
        return numinter;
    }

    public void setNuminter(int numinter) {
        this.numinter = numinter;
    }

    public int getDatasize() {
        return datasize;
    }

    public void setDatasize(int datasize) {
        this.datasize = datasize;
    }

    public int getAvgsection_size() {
        return avgsection_size;
    }

    public void setAvgsection_size(int avgsection_size) {
        this.avgsection_size = avgsection_size;
    }

    public int getAvgnum_sections() {
        return avgnum_sections;
    }

    public void setAvgnum_sections(int avgnum_sections) {
        this.avgnum_sections = avgnum_sections;
    }

    public int getNumSections() {
        return numSections;
    }

    public void setNumSections(int numSections) {
        this.numSections = numSections;
    }

    public int getSection_min_size() {
        return section_min_size;
    }

    public void setSection_min_size(int section_min_size) {
        this.section_min_size = section_min_size;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public double getT() {
        return T;
    }

    public void setT(double T) {
        this.T = T;
    }

    public String getMyWatermark() {
        return myWatermark;
    }

    public void setMyWatermark(String myWatermark) {
        this.myWatermark = myWatermark;
    }

    public BigInteger getMySecretKey() {
        return mySecretKey;
    }

    public void setMySecretKey(BigInteger mySecretKey) {
        this.mySecretKey = mySecretKey;
    }
}
