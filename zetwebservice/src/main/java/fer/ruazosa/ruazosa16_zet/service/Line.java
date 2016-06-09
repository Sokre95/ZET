package fer.ruazosa.ruazosa16_zet.service;

public class Line {

    private String lineNumber;
    private String lineName;

    public Line(String lineNumber, String lineName) {
        this.lineNumber = lineNumber;
        this.lineName = lineName;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public String getLineName() {
        return lineName;
    }

}
