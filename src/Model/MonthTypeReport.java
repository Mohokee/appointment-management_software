package Model;

/** This class is for the Report by Month and Type */
public class MonthTypeReport {

    private String month;
    private String type;
    private int total;

    public MonthTypeReport(String month, String type, int total) {
        this.month = month;
        this.type = type;
        this.total = total;
    }

    public String getMonth() {
        return month;
    }

    public String getType() {
        return type;
    }

    public int getTotal() {
        return total;
    }



}
