import java.io.*;
import java.util.*;

public class Candidate{
	
	//Personal Information
	private String uniqueID;
	private String category;
	private boolean pdStatus;
	private boolean dsStatus;
	private boolean nationality;

	//Information regarding preferences
	private ArrayList<VirtualProgramme> preferenceList;
	private int appliedUpto;
	private VirtualProgramme waitListedFor;

	//Functions to manipulate data members
	public String getUniqueID(){
		return uniqueID;
	}

	public String getCategory(){
		return category;
	}

	public boolean getPDStatus(){
		return pdStatus;
	}

	public boolean getDSStatus(){
		return dsStatus;
	}

	public boolean getNationality(){
		return nationality;
	}

	public int getAppliedUpto(){
		return appliedUpto;
	}

	public void setAppliedUpto(int x){
		appliedUpto = x;
	}

	public VirtualProgramme getWaitListedFor(){
		return waitListedFor;
	}

	public void setWaitListedFor(VirtualProgramme x){
		waitListedFor = x;
	}

	//Constructor
	public Candidate(String uniqueID, String category, boolean pdStatus, boolean dsStatus, boolean nationality, String choices){
		this.uniqueID=uniqueID;
		this.category=category;
		this.pdStatus=pdStatus;
		this.dsStatus=dsStatus;
		this.nationality=nationality;

		preferenceList = new ArrayList();
		appliedUpto = 0;
		waitListedFor = NULL;
	}

	//Function for adding preferences to the preference list
	public void addPreference(VirtualProgramme[] choice){
		switch(category){

			case "GE": if(!pdStatus){
				preferenceList.add(choice[0]);
				preferenceList.add(choice[1]);
				preferenceList.add(choice[4]);
				preferenceList.add(choice[5]);
			}
			else{
				preferenceList.add(choice[0]);
				preferenceList.add(choice[4]);
				preferenceList.add(choice[1]);
				preferenceList.add(choice[5]);
			}break;

			case "OBC": if(!pdStatus){
				preferenceList.add(choice[0]);
				preferenceList.add(choice[1]);
				preferenceList.add(choice[4]);
				preferenceList.add(choice[5]);
			}
			else{
				preferenceList.add(choice[0]);
				preferenceList.add(choice[4]);
				preferenceList.add(choice[1]);
				preferenceList.add(choice[5]);
			}break;

			case "SC": if(!pdStatus){
				preferenceList.add(choice[0]);
				preferenceList.add(choice[2]);
				preferenceList.add(choice[1]);
				preferenceList.add(choice[4]);
				preferenceList.add(choice[5]);
				preferenceList.add(choice[6]);
			}
			else{
				preferenceList.add(choice[0]);
				preferenceList.add(choice[2]);
				preferenceList.add(choice[4]);
				preferenceList.add(choice[6]);
				preferenceList.add(choice[1]);
				preferenceList.add(choice[5]);
			}break;

			case "ST": if(!pdStatus){
				preferenceList.add(choice[0]);
				preferenceList.add(choice[3]);
				preferenceList.add(choice[1]);
				preferenceList.add(choice[4]);
				preferenceList.add(choice[5]);
				preferenceList.add(choice[7]);
			}
			else{
				preferenceList.add(choice[0]);
				preferenceList.add(choice[3]);
				preferenceList.add(choice[4]);
				preferenceList.add(choice[7]);
				preferenceList.add(choice[1]);
				preferenceList.add(choice[5]);
			}break;
		}
	}

	//Function for finding the next Virtual Programme
	public VirtualProgramme nextVirtualProgramme(){
		appliedUpto++;
		return preferenceList.get(appliedUpto);
	}
}