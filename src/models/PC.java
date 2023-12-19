package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import connection.Connect;

/*
 * Represent PC entity in the system.
 * Encapsulate information related to a PC. 
 * Interact with database to perform CRUD operations on PC data.
 */

public class PC {
	
	private Connect db = Connect.getConnection();
	
    private String pcId, pcCondition;

    public PC() {}

    public PC(String pcId, String pcCondition) {
        this.pcId = pcId;
        this.pcCondition = pcCondition;
    }
    
    public String getPcId() {
        return pcId;
    }
    
    public void setPcId(String pcId) {
        this.pcId = pcId;
    }

    public String getPcCondition() {
        return pcCondition;
    }
    
    public void setPcCondition(String pcCondition) {
        this.pcCondition = pcCondition;
    }
    
    public List<PC> getAllPCData() {
    	String query = "SELECT * FROM PC";
    	Vector<PC> pcs = new Vector<>();
    	try {
    		PreparedStatement ps = db.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String pcId = rs.getString("PcID");
				String pcCondition = rs.getString("PCCondition");
				pcs.add(new PC(pcId, pcCondition));
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch pc data");
			e.printStackTrace();
		}
		return pcs;
	}
	
	public void updatePCCondition(String pcId, String condition) {
		String query = "UPDATE PC SET PCCondition = ? WHERE PcID = ?";
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, condition);
			ps.setString(2, pcId);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Failed to update pc condition");
			e.printStackTrace();
		}
	}
	
	public void deletePC(String pcId) {
		String query = "DELETE FROM PC WHERE PcID = ?";
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, pcId);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Failed to delete pc");
			e.printStackTrace();
		}
	}
	
	public void addNewPC(String pcId) {
		String query = "INSERT INTO PC VALUES (?, ?)";
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, pcId);
			ps.setString(2, "Usable");
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Failed to add new pc");
			e.printStackTrace();
		}
	}
	
	public PC getPcDetail(String pcId) {
		String query = "SELECT * FROM PC WHERE PcID = ?";
		try {
			PreparedStatement ps = db.prepareStatement(query);
			ps.setString(1, pcId);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String pcCondition = rs.getString("PCCondition");
				return new PC(pcId, pcCondition);
			}
		} catch (SQLException e) {
			System.out.println("Failed to fetch pc detail");
			e.printStackTrace();
		}
		return null;
	}
    
}
