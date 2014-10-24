import java.util.*;

public class VirtualProgramme  
{
	private String category;	
	private Boolean pdStatus;
	private Integer quota;
	private Integer meritListIndex;
	private Integer seatsFilled = new Integer(0);
	private ArrayList<String> waitList;
	private ArrayList<String> waitListForeign;
	private ArrayList<String> waitListDS;
	private ArrayList<String> tempList;
	private MeritList meritList;

	public VirtualProgramme(String category_ , Boolean pdStatus_ , Integer quota_, MeritList[] recievedList)
	{
		category = category_;
		pdStatus = pdStatus_;
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

	/** @debug: maybe you can pass tempId(string) ,instead of Candidate*/
	public void receiveApplication(String candidateID, Map<String , Candidate> rejectionList)	
	{
		if(meritList.getRank(candidateID)!=-1)				//check if the candidate is present in the merit list, which is available in gale-sharpley class.
		{
			tempList.add(candidateID);
		}
		else
		{
			rejectionList.add(candidateID);	//otherwise add the candidate to the rejection list for that iteration of the gale sharpley algorithm.
		}

	}
	//We can implement treeMap too
	public static void SelectionSort ( ArrayList<Candidate> num)
	{
	     int i, j, first, temp;  
	     for ( i = num.length - 1; i > 0; i - - )  
	     {
	          first = 0;   //initialize to subscript of first element
	          for(j = 1; j <= i; j ++)   //locate smallest element between positions 1 and i.
	          {
	               if( hashmap.get(num.get(j)).rank< hashmap.get(num.get(first)).rank )         	//here write the actual name of the hashmap, rank is the 
	                 first = j;
	          }
	          temp = num.get(first);   //swap smallest found with element in position i.
	          num.set(first,num.get(i));
	          num.set(i,temp); 
	      }           
	}
	public Hashmap<String , Candidate> filter(Hashmap<String , Candidate> rejectionList)
	{
			SelectionSort(tempList);		
			if(quota>0)
			{
				waitList.addAll(tempList.subList(0, quota));
				tempList.removeRange(0, quota);
				while( tempList.size()!=0  && ((waitList.get(waitList.size()-1)).getRank()).equals((tempList.get(0)).getRank())) /**While the candidate at the end of the waitList has same rank as the 
																																candidate on top of the the remaining list, Add the candidate from the the tempList to the waitList.
																																This is done so as to ensure that the candidates of same rank are all selected, even if it exceeds the quota*/
				{
					waitList.add(new Candidate(tempList.get(0));	/** Does tempList.get(0) returns value or reference?*/
					tempList.remove(0);
				}
			}

			rejectionList.addAll(tempList);
			return rejectionList;
	}

}