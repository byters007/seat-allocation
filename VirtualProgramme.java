import java.util.*;

public class VirtualProgramme  
{
	private String programID;
	private String category;	
	private Boolean pdStatus;
	private Integer quota;
	private Integer meritListIndex;
	private Integer seatsFilled = new Integer(0);
	private ArrayList<Candidate> waitList;
	private ArrayList<Candidate> waitListForeign;
	private ArrayList<Candidate> waitListDS;
	private ArrayList<Candidate> tempList;
	private MeritList meritList;

	public VirtualProgramme(String category_ , Boolean pdStatus_ , Integer quota_, MeritList[] recievedList, String programID_)
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
			if(category == "GE")
			{
				meritListIndex = 4;
			}
			if(category == "OBC")
			{
				meritListIndex = 5;
			}
			if(category == "SC")
			{
				meritListIndex = 6;
			}
			if(category == "ST")
			{
				meritListIndex = 7;
			}
		}

		quota = quota_;
		tempList = new ArrayList<Candidate>();
		waitList = new ArrayList<Candidate>();
		waitListDS = new ArrayList<Candidate>();
		waitListForeign = new ArrayList<Candidate>();

		meritList = recievedList[meritListIndex];
	}

	public String getProgramID(){
		return programID;
	}

	/** @debug: maybe you can pass tempId(string) ,instead of Candidate*/
	public void receiveApplication(Candidate newCandidate, HashMap<String , Candidate> rejectionList)	
	{
		if(meritList.getRank(newCandidate.getUniqueID())!=-1)				//check if the candidate is present in the merit list, which is available in gale-sharpley class.
		{
			tempList.add(newCandidate);
		}
		else
		{
			rejectionList.put(newCandidate.getUniqueID(), newCandidate);	//otherwise add the candidate to the rejection list for that iteration of the gale sharpley algorithm.
		}

	}
	//We can implement treeMap took
	public void SelectionSort ( ArrayList<Candidate> num)
	{
	     int i, j, first;
	     Candidate temp;  
	     for ( i = num.size() - 1; i > 0; i--)  
	     {
	          first = 0;   //initialize to subscript of first element
	          for(j = 1; j <= i; j++)   //locate smallest element between positions 1 and i.
	          {
	               if( meritList.compareRank(num.get(j), num.get(first),meritListIndex)==0 )         	//here write the actual name of the hashmap, rank is the 
	                 first = j;
	          }
	          temp = new Candidate(num.get(first));   //swap smallest found with element in position i.
	          num.set(first,num.get(i));
	          num.set(i,temp); 
	      }           
	}
	public HashMap<String , Candidate> filter(HashMap<String , Candidate> rejectionList)
	{
			SelectionSort(tempList);		
			if(quota>0)
			{
				waitList.addAll(tempList.subList(0, quota));
				tempList.subList(0, quota).clear();
				while( tempList.size()!=0  && (meritList.compareRank(waitList.get(waitList.size()-1), tempList.get(0), meritListIndex)==2)) /**While the candidate at the end of the waitList has same rank as the 
																																candidate on top of the the remaining list, Add the candidate from the the tempList to the waitList.
																																This is done so as to ensure that the candidates of same rank are all selected, even if it exceeds the quota*/
				{
					waitList.add(new Candidate(tempList.get(0)));	/** Does tempList.get(0) returns value or reference?*/
					tempList.remove(0);
				}
			}

			for(int i=0; i<tempList.size(); i++){
				rejectionList.put(tempList.get(i).getUniqueID(), tempList.get(i));
			}
			return rejectionList;
	}

}