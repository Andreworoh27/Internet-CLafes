package controller;

import java.util.List;

import models.PC;

public class PCController {

	private PC pc = new PC();

	public List<PC> getAllPCData() {
		return pc.getAllPCData();
	}

	public PC getPcDetail(String pcId) {
		return pc.getPcDetail(pcId);
	}

	public String addNewPC(String pcId) {
		if (pcId.isEmpty())
			return "PC ID must not be empty";
		else if (getPcDetail(pcId) != null)
			return "PC ID must be unique";

		pc.addNewPC(pcId);
		return "Successfully add a new PC";
	}

	public String updatePCCondition(String pcId, String condition) {
		String errorMessage = "";
		if (getPcDetail(pcId) == null)
			return "PC must be chosen";
		else if (!condition.equals("Usable") && !condition.equals("Maintenance") && !condition.equals("Broken"))
			errorMessage = "PC condition must be either Usable, Maintenance, or Broken";

		if (errorMessage.isEmpty())
			pc.updatePCCondition(pcId, condition);

		return errorMessage;
	}

	public String deletePC(String pcId) {
		String errorMessage = "";
		if (getPcDetail(pcId) == null)
			errorMessage = "PC must be chosen";

		if (errorMessage.isEmpty())
			pc.deletePC(pcId);

		return errorMessage;
	}

}
