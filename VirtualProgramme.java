import java.util.*;

public class VirtualProgramme  
{
	private String category;	
	private Boolean pdStatus;
	private Integer quota;
	private Integer meritListIndex;
	private Integer seatsFilled = new Integer(0);
	private ArrayList<Candidate> waitList;
	private ArrayList<Candidate> waitListForeign;
	private ArrayList<Candidate> waitListDS;
	private ArrayList<Candidate> tempList;

	public VirtualProgramme(String category_ , Boolean pdStatus_ , Integer quota_)
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
	}

	public void receiveApplication(Candidate c)
	{
		/*if(seatsFilled < quota)
		{
			seatsFilled++;
			/*if(c.getNationality() == true)
			{
				waitListForeign.add(c);
			}*/
			/*if(c.DSStatus() == true)
			{
				waitListDS.add(c);
			}
			else if(c.getNationality() == false)
			{
				waitList.add(c);
			}
		}
		else if(seatsFilled >= quota)
		{
			if(quota != 0)
			{
				if(wa)
			}
		}*/
		if()				//check if the candidate is present in the merit list, which is available in gale-sharpley class.
		{
			tempList.add(c);
		}
		else
		{
			rejectionList.add(c);	//otherwise add the candidate to the rejection list for that iteration of the gale sharpley algorithm.
		}

	}

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
	public ArrayList<Candidate> filter()
	{
			SelectionSort(tempList);		
			if(quota>1)
			{
				waitList.addAll(tempList.subList(0, quota));
				tempList.removeRange(0, quota);
				while( tempList.size()!=0  && waitList.get(waitList.size()-1).getRank() == tempList.get(0).getRank())
				{
					waitList.add(tempList.get(0));
					tempList.remove(0);
				}
			}

			rejectionList.addAll(tempList);
			return rejectionList;
	}

}