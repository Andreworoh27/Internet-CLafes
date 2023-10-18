package models;

public class Pc {
    private String pcId;
    private String pcCondition;

    public Pc() {

    }

    public Pc(String pcId, String pcCondition) {
        this.pcId = pcId;
        this.pcCondition = pcCondition;
    }

    public void setPcCondition(String pcCondition) {
        this.pcCondition = pcCondition;
    }

    public void setPcId(String pcId) {
        this.pcId = pcId;
    }

    public String getPcCondition() {
        return pcCondition;
    }

    public String getPcId() {
        return pcId;
    }
}
