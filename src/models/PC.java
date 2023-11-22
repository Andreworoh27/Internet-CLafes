package models;

import java.util.List;

public class PC {
	
    private Integer pcId;
    private String pcCondition;

    public PC() {}

    public PC(Integer pcId, String pcCondition) {
        this.pcId = pcId;
        this.pcCondition = pcCondition;
    }
    
    public Integer getPcId() {
        return pcId;
    }
    
    public void setPcId(Integer pcId) {
        this.pcId = pcId;
    }

    public String getPcCondition() {
        return pcCondition;
    }
    
    public void setPcCondition(String pcCondition) {
        this.pcCondition = pcCondition;
    }
    
    public List<PC> getAllPCData() {
		return null;
	}
	
	public void updatePCCondition(Integer PcId, String condition) {
		
	}
	
	public void deletePC(Integer pcId) {
		
	}
	
	public void addNewPC(Integer pcId) {
		
	}
	
	public PC getPcDetail(Integer pcId) {
		return null;
	}
    
}
