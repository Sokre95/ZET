package fer.ruazosa.ruazosa16_zet.data;

import java.util.ArrayList;
import java.util.List;

import fer.ruazosa.ruazosa16_zet.wrappers.InfoDisplay;
import fer.ruazosa.ruazosa16_zet.wrappers.Line;

/**
 * Klasa koja implementira staticke metode dohvata reprezentativnih podataka.
 * Podaci koji se dohvacaju nisu stvarni podaci vec sluze kao pomoc za razvoj aplikacije.
 */
public class ExampleData {

    /**
        Vraca reprezentativne podatke dnevnih linija buseva.
     */
    public static List<Line> getDnevniBus() {
        List<Line> linije = new ArrayList<>();
        linije.add(new Line("231", "BORONGAJ-DUBEC"));
        linije.add(new Line("269", "BORONGAJ-SESVETSKI KRALJEVEC"));
        linije.add(new Line("101", "BRITANSKI TRG-GORNJE PREKRIŽJE"));
        linije.add(new Line("102", "BRITANSKI TRG-MIHALJEVAC"));
        linije.add(new Line("103", "BRITANSKI TRG-KRALJEVEC"));
        linije.add(new Line("105", "KAPTOL-BRITANSKI TRG"));
        linije.add(new Line("138", "BRITANSKI TRG-ZELENGAJ-BRITANSKI TRG"));
        linije.add(new Line("109", "ČRNOMEREC-DUGAVE"));
        linije.add(new Line("119", "ČRNOMEREC-PODSUSED MOST"));
        linije.add(new Line("120", "ČRNOMEREC-GAJNICE"));
        return linije;
    }

    /**
        Vraca reprezentativne podatke nocnih linija buseva.
     */
    public static List<Line> getBusNocni() {
        List<Line> linije = new ArrayList<>();
        linije.add(new Line("116", "LJUBLJANICA-PODSUSED MOST"));
        linije.add(new Line("172", "ZAGREB(ČRNOMEREC)-ZAPREŠIĆ"));
        linije.add(new Line("212", "DUBEC-SESVETE"));
        linije.add(new Line("268", "ZAGREB(GLAVNI KOLODVOR)-VELIKA GORICA"));
        return linije;
    }

    /**
        Vraca reprezentativne podatke dnevnih tramvajskih linija.
     */
    public static List<Line> getTramDnevni() {
        List<Line> linije = new ArrayList<>();
        linije.add(new Line("1", "ZAPADNI KOLODVOR-BORONGAJ"));
        linije.add(new Line("2", "ČRNOMEREC-SAVIŠĆE"));
        linije.add(new Line("3", "LJUBLJANICA-SAVIŠĆE"));
        linije.add(new Line("4", "SAVSKI MOST-DUBEC"));
        linije.add(new Line("5", "PREČKO-MAKSIMIR"));
        linije.add(new Line("6", "ČRNOMEREC-SOPOT"));
        linije.add(new Line("7", "SAVSKI MOST-DUBRAVA"));
        linije.add(new Line("8", "MIHALJEVEC-ZAPRUĐE"));
        linije.add(new Line("9", "LJUBLJANICA-BORONGAJ"));
        linije.add(new Line("11", "ČRNOMEREC-DUBEC"));
        linije.add(new Line("12", "LJUBLJANICA-DUBRAVA"));
        linije.add(new Line("13", "ŽITNJAK-KVATERNIKOV TRG"));
        linije.add(new Line("14", "MIHALJEVEC-ZAPRUĐE"));
        linije.add(new Line("15", "MIHALJEVEC-DOLJE"));
        linije.add(new Line("17", "PREČKO-BORONGAJ"));
        return linije;
    }

    /**
        Vraca reprezentativne podatke nocnih tramvajskih linija.
     */
    public static List<Line> getTramNocni() {
        List<Line> linije = new ArrayList<>();
        linije.add(new Line("31", "ČRNOMEREC-SAVSKI MOST"));
        linije.add(new Line("32", "PREČKO-BORONGAJ"));
        linije.add(new Line("33", "DOLJE-SAVIŠĆE"));
        linije.add(new Line("34", "LJUBLJANICA-DUBEC"));
        return linije;
    }

    public static List<InfoDisplay> getRouteInfo() {
        List<InfoDisplay> displays = new ArrayList<>();
        displays.add(new InfoDisplay("07:33:00", "DUBRAVA", "7"));
        displays.add(new InfoDisplay("07:33:00", "DUBRAVA", "12"));
        displays.add(new InfoDisplay("07:38:30", "LJUBIJSKA", "11"));
        displays.add(new InfoDisplay("07:42:00", "LJUBIJSKA", "4"));
        displays.add(new InfoDisplay("07:45:00", "DUBRAVA", "4"));
        return displays;
    }

    public static List<InfoDisplay> getFavouritesInfo() {
        List<InfoDisplay> displays = new ArrayList<>();
        displays.add(new InfoDisplay("09:15:00", "TRNAVA", "215"));
        displays.add(new InfoDisplay("09:20:00", "KVATERNIKOV TRG", "215"));
        return displays;
    }

}
