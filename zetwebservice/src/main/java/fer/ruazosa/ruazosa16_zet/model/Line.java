package fer.ruazosa.ruazosa16_zet.model;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (!lineNumber.equals(line.lineNumber)) return false;
        return lineName.equals(line.lineName);

    }

    @Override
    public int hashCode() {
        int result = lineNumber.hashCode();
        result = 31 * result + lineName.hashCode();
        return result;
    }
}
