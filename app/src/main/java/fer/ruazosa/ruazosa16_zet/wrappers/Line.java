package fer.ruazosa.ruazosa16_zet.wrappers;

public class Line {

    private String brojLinije;
    private String nazivLinije;

    public Line(String brojLinije, String nazivLinije) {
        this.brojLinije = brojLinije;
        this.nazivLinije = nazivLinije;
    }

    public String getBrojLinije() {
        return brojLinije;
    }

    public String getNazivLinije() {
        return nazivLinije;
    }

}
