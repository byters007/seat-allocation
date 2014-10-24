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

		preferenceList = new ArrayList<VirtualProgramme>();
		appliedUpto = 0;
		waitListedFor = NULL;

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Added loop for adding preferencelist but not sure if it will work as i am using its member function inside its constructor
		//@anmol: maybe we can do this in galeshapley
		String[] tempChoice = choices.split("_");
		for(int i=0;i<tempChoice.length(),i++){
			//VirtualProgramme tempProg = new VirtualProgramme(tempChoice,pdStatus,/*@anmol: I need qouta over here. You have not read it in GaleShapley currently*/);
			VirtualProgramme[] tempProg = programMap.get(tempChoice[i]); 
			this.addPreference(tempProg);
		}
		//I think we should read the programme file before student choice file then we can directly get it from programme map
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	}

	//Copy Constructor
	public Candidate(Candidate x){
		uniqueID=x.uniqueID;
		category=x.category;
		pdStatus=x.pdStatus;
		dsStatus=x.dsStatus;
		nationality=x.nationality;
		preferenceList=x.preferenceList    //I am not sure whether u need preferencelist, appliedupto or waitlistedfor too and whether this will work
		appliedUpto=x.appliedUpto;
		waitListedFor=x.waitListedFor;
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

	public VirtualProgramme currentVirtualProgramme(){
		return preferenceList.get(appliedUpto);
	}
	//Function for finding the next Virtual Programme
	public void nextVirtualProgramme(){
		appliedUpto++;												/** @note to Pranjal: Maybe you should call the function "setWaitListedFor( preferenceList.get(appliedUpto))"
													so that when this function is called from galeShapley class, the current waitListed Programme also gets updated automatically*/
		//setWaitListedFor(preferenceList.get(appliedUpto));		//@anmol: I think this should be in VirttualProgramme as when applying to the next programme we can get rejected so only the
																	//programme knows we are waitlisted or rejected
		if(appliedUpto==preferenceList.length()){
			appliedupto=-1;
		}
	}
}