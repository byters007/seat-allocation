import java.util.*;
import java.io.*;

public class VirtualProgramme  
{
	private String programID;
	private String category;	
	private Boolean pdStatus;
	private int quota;
	private int meritListIndex;
	private int seatsFilled;
	private ArrayList<Candidate> waitList;
	private ArrayList<Candidate> waitListForeign;
	private ArrayList<Candidate> waitListDS;
	private ArrayList<Candidate> tempList;
	private MeritList meritList;

	public VirtualProgramme(VirtualProgramme prog) {
		programID = prog.programID ;
		category = prog.category ;
		pdStatus = prog.pdStatus ;
		quota = prog.quota ;
		meritListIndex = prog.meritListIndex ;
		seatsFilled = prog.seatsFilled ;
		waitList = new ArrayList<Candidate>(prog.waitList) ;
		waitListForeign = new ArrayList<Candidate>(prog.waitListForeign) ;
		waitListDS = new ArrayList<Candidate>(prog.waitListDS) ;
		tempList = new ArrayList<Candidate>(prog.tempList) ;
		meritList = new MeritList(prog.meritList) ;
	}
	public VirtualProgramme(String category_ , Boolean pdStatus_ , int quota_, MeritList[] recievedList, String programID_)
	{
		category = category_;
		pdStatus = pdStatus_;
		programID = programID_;
		if(pdStatus == false)
		{
			if(category == "GE")
			{
				meritListIndex = 0;
			}
			if(category == "OBC")
			{
				meritListIndex = 1;
			}
			if(category == "SC")
			{
				meritListIndex = 2;
			}
			if(category == "ST")
			{
				meritListIndex = 3;
			}
		}
		else
		{
			if(category == "GE_PD")
			{
				meritListIndex = 4;
			}
			if(category == "OBC_PD")
			{
				meritListIndex = 5;
			}
			if(category == "SC_PD")
			{
				meritListIndex = 6;
			}
			if(category == "ST_PD")
			{
				meritListIndex = 7;
			}
		}

		quota = quota_;
		//list of candidates who have applied for that programme in 1 iteration
		tempList = new ArrayList<Candidate>();
		//list of candidates who have been wait listed after filtering
		waitList = new ArrayList<Candidate>();
		waitListDS = new ArrayList<Candidate>();
		waitListForeign = new ArrayList<Candidate>();

		//meritList = new MeritList(recievedList[meritListIndex]);
		meritList = recievedList[meritListIndex];
		seatsFilled = 0 ;
	}

	public String getProgramID(){
		return programID;
	}
	public String getCategory() {
		return category ;
	}

	public int getMeritListIndex(){
		return meritListIndex;
	}

	/** @debug: maybe you can pass tempId(string) ,instead of Candidate*/
	public void receiveApplication(Candidate newCandidate, HashMap<String , Candidate> rejectionList)	
	{	//check if the candidate is present in the merit list, which is available in gale-shapley class.
		if(meritList.getRank(newCandidate.getUniqueID())!=-1)
		{
			tempList.add(newCandidate);
		}
		else
		{
			rejectionList.put(newCandidate.getUniqueID(), newCandidate);	//otherwise add the candidate to the rejection list for that iteration of the gale sharpley algorithm.
		}
		//newCandidate.setAppliedUpto(5);

	}
	//We can implement treeMap too
	public void SelectionSort ( ArrayList<Candidate> num)
	{
	     int i, j, first;
	     for (i = num.size() - 1; i >= 0; i--)  
	     { 
	          first = 0;   //initialize to subscript of first element
	          for(j = 0; j <= i; j++)   //locate smallest element between positions 0 and i.
	          {
	               if( meritList.compareRank(num.get(j), num.get(first),meritListIndex) == 1 )         	//here write the actual name of the hashmap, rank is the 
	                 first = j;
	          }
	          Candidate temp = new Candidate(num.get(first));   //swap smallest found with element in position i.
	          num.set(first,num.get(i)) ;
	          num.set(i,temp); 
	      }
	}
	public HashMap<String , Candidate> filter(HashMap<String , Candidate> rejectionList)
	{
		/*
			//those who have applied for that programme, sort them in the increasing order of rank
			SelectionSort(tempList);	
			if(quota>0)
			{
				waitList.clear();
				waitList.addAll(tempList.subList(0, Math.min(quota , tempList.size()  ) ) ) ;
				/*for(int i=0; i<waitList.size(); i++){
					waitList.get(i).setWaitListedForBool(true);
				}*/
				/*	
				tempList.subList(0,  Math.min(quota , tempList.size() )).clear();
				while( tempList.size()!=0  && (meritList.compareRank(waitList.get(waitList.size()-1), tempList.get(0), meritListIndex)==2)) /**While the candidate at the end of the waitList has same rank as the 
																																candidate on top of the the remaining list, Add the candidate from the the tempList to the waitList.
				*/
				/*																												This is done so as to ensure that the candidates of same rank are all selected, even if it exceeds the quota*/
//				{	// Does tempList.get(0) returns value or reference?
//					waitList.add(tempList.get(0)));
//					//waitList.get(waitList.size()-1).setWaitListedForBool(true);
//					tempList.remove(0);
//				}
//			}
//			for(int i=0; i<tempList.size(); i++){
//				//tempList.get(i).setWaitListedForBool(false);
//				rejectionList.put(tempList.get(i).getUniqueID(), tempList.get(i));
//			}
//			tempList.clear();
//			return rejectionList;*/
//////////////////////////////////////////////////////////////////////////////////////////////////////////
		//System.out.println(tempList.size()) ;
		SelectionSort(tempList) ;
		if(quota > 0) {
			waitList.clear() ;
			int max_size = Math.min(quota , tempList.size()) ;
			waitList.addAll(tempList.subList(0, max_size)) ;
			int i ;
			for(i = max_size ; i < tempList.size() ; i++) {
				if(meritList.compareRank(waitList.get(waitList.size()-1), tempList.get(i), meritListIndex)==2) {
					waitList.add(tempList.get(i)) ;
				}
				else {
					break ;
				}
			}
			for( ; i < tempList.size() ; i++) {
				rejectionList.put(tempList.get(i).getUniqueID(), tempList.get(i));
			}
		}
		else {
			for(int i=0; i<tempList.size(); i++){
				rejectionList.put(tempList.get(i).getUniqueID(), tempList.get(i));
			}
		}

		tempList.clear();
		return rejectionList;
	}
	void print_program() {
		System.out.println(programID + " " + quota + " " + category) ;
	}
}