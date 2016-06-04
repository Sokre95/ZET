package fer.ruazosa.ruazosa16_zet;

import java.util.ArrayList;
import java.util.List;

/**
 * Klasa koja implementira staticke metode dohvata reprezentativnih podataka.
 * Podaci koji se dohvacaju nisu stvarni podaci vec sluze kao pomoc za razvoj aplikacije.
 */
public class ExampleData {

    /**
        Vraca reprezentativne podatke dnevnih linija buseva.
     */
    public static List<Linija> getDnevniBus() {
        List<Linija> linije = new ArrayList<>();
        linije.add(new Linija("231", "BORONGAJ-DUBEC"));
        linije.add(new Linija("269", "BORONGAJ-SESVETSKI KRALJEVEC"));
        linije.add(new Linija("101", "BRITANSKI TRG-GORNJE PREKRIŽJE"));
        linije.add(new Linija("102", "BRITANSKI TRG-MIHALJEVAC"));
        linije.add(new Linija("103", "BRITANSKI TRG-KRALJEVEC"));
        linije.add(new Linija("105", "KAPTOL-BRITANSKI TRG"));
        linije.add(new Linija("138", "BRITANSKI TRG-ZELENGAJ-BRITANSKI TRG"));
        linije.add(new Linija("109", "ČRNOMEREC-DUGAVE"));
        linije.add(new Linija("119", "ČRNOMEREC-PODSUSED MOST"));
        linije.add(new Linija("120", "ČRNOMEREC-GAJNICE"));
        return linije;
    }

    /**
        Vraca reprezentativne podatke nocnih linija buseva.
     */
    public static List<Linija> getBusNocni() {
        List<Linija> linije = new ArrayList<>();
        linije.add(new Linija("116", "LJUBLJANICA-PODSUSED MOST"));
        linije.add(new Linija("172", "ZAGREB(ČRNOMEREC)-ZAPREŠIĆ"));
        linije.add(new Linija("212", "DUBEC-SESVETE"));
        linije.add(new Linija("268", "ZAGREB(GLAVNI KOLODVOR)-VELIKA GORICA"));
        return linije;
    }

    /**
        Vraca reprezentativne podatke dnevnih tramvajskih linija.
     */
    public static List<Linija> getTramDnevni() {
        List<Linija> linije = new ArrayList<>();
        linije.add(new Linija("1", "ZAPADNI KOLODVOR-BORONGAJ"));
        linije.add(new Linija("2", "ČRNOMEREC-SAVIŠĆE"));
        linije.add(new Linija("3", "LJUBLJANICA-SAVIŠĆE"));
        linije.add(new Linija("4", "SAVSKI MOST-DUBEC"));
        linije.add(new Linija("5", "PREČKO-MAKSIMIR"));
        linije.add(new Linija("6", "ČRNOMEREC-SOPOT"));
        linije.add(new Linija("7", "SAVSKI MOST-DUBRAVA"));
        linije.add(new Linija("8", "MIHALJEVEC-ZAPRUĐE"));
        linije.add(new Linija("9", "LJUBLJANICA-BORONGAJ"));
        linije.add(new Linija("11", "ČRNOMEREC-DUBEC"));
        linije.add(new Linija("12", "LJUBLJANICA-DUBRAVA"));
        linije.add(new Linija("13", "ŽITNJAK-KVATERNIKOV TRG"));
        linije.add(new Linija("14", "MIHALJEVEC-ZAPRUĐE"));
        linije.add(new Linija("15", "MIHALJEVEC-DOLJE"));
        linije.add(new Linija("17", "PREČKO-BORONGAJ"));
        return linije;
    }

    /**
        Vraca reprezentativne podatke nocnih tramvajskih linija.
     */
    public static List<Linija> getTramNocni() {
        List<Linija> linije = new ArrayList<>();
        linije.add(new Linija("31", "ČRNOMEREC-SAVSKI MOST"));
        linije.add(new Linija("32", "PREČKO-BORONGAJ"));
        linije.add(new Linija("33", "DOLJE-SAVIŠĆE"));
        linije.add(new Linija("34", "LJUBLJANICA-DUBEC"));
        return linije;
    }

}
