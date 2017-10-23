

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author doulat
 */
public class JsonDecoderOutputHelper {
    private String embededWatermark;

    public JsonDecoderOutputHelper(String embededWatermark) {
        this.embededWatermark = embededWatermark;
    }

    public String getEmbededWatermark() {
        return embededWatermark;
    }

    public void setEmbededWatermark(String embededWatermark) {
        this.embededWatermark = embededWatermark;
    }
    
}
