package fer.ruazosa.ruazosa16_zet.wrappers;

public class Linija {

    private String brojLinije;
    private String nazivLinije;

    public Linija(String brojLinije, String nazivLinije) {
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
