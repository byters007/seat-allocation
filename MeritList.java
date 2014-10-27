import java.io.*;
import java.util.*;
//I have no clue what to do here in this class please suggest
public class MeritList{
	
	//data members
	private HashMap<String, Integer> rankList;

	//constructor
	public MeritList(){
		rankList = new HashMap<String, Integer>();
	}

	//copy constructor
	public MeritList(MeritList temp){
		rankList = new HashMap<String, Integer>(temp.rankList);
	}

	//Function for adding candidate
	public void addCandidate(String newID, Integer rank){
		rankList.put(newID,rank);
	}

	//Function for getting rank
	public int getRank(String candidateID){
		if(rankList.containsKey(candidateID)==false){
			return -1;
		}
		return rankList.get(candidateID);
	}

	//Function for comparing ranks
	public Integer compareRank(Candidate p1, Candidate p2, Integer case_){
		if(case_==0 || case_==2 || case_==3){

			if(rankList.get(p1.getUniqueID())<rankList.get(p2.getUniqueID())) return 0;
			else if(rankList.get(p1.getUniqueID())==rankList.get(p2.getUniqueID())) return 2;
			else return 1;
		}
		else if(case_==1 || case_==4 || case_==5){
			if(p1.getCategory()=="GE" && p2.getCategory()!="GE"){
				return 1;
			}
			else if(p1.getCategory()!="GE" && p2.getCategory()=="GE"){
				return 0;
			}
			else{

				if(rankList.get(p1.getUniqueID())<rankList.get(p2.getUniqueID())) return 0;
				else if(rankList.get(p1.getUniqueID())==rankList.get(p2.getUniqueID())) return 2;
				else return 1;
			}
		}
		else if(case_==6 || case_==7){
			if(p1.getPDStatus()==true && p2.getPDStatus()==false){
				return 0;
			}
			else if(p1.getPDStatus()==false && p2.getPDStatus()==true){
				return 1;
			}
			else{

				if(rankList.get(p1.getUniqueID())<rankList.get(p2.getUniqueID())) return 0;
				else if(rankList.get(p1.getUniqueID())==rankList.get(p2.getUniqueID())) return 2;
				else return 1;
			}	
		}
		else return -1;
	}
	/**Add the sort function here. Use comparator. For example for meritList[1]{OBC and non-PD}, first comparision:on category. 
	oBC student should be higher up. Second comparision(which will be done when first compariosion gives equals(that is if both 
	candidates belong to the same category)), then compare by their rank. 
	This way the merit list will automatically place all OBC students above GE students.
	@Pranjal: Note that now we don't need to increment the rank of GE students by "Number of OBC Students" .This is much cleaner solution. 
	Also note that the we need 8 diferent sort functions for the 8 different merit lists.
	Regarding the design of function: 
		return type: void
		paramter: int index (from 0 to 7)
		body: based on the value of "index" implement the corresponding sorting. 

	*/
	void print_list() {
		for (Map.Entry<String , Integer> entry : rankList.entrySet())
			{
					System.out.println(entry.getKey() + " " + entry.getValue());
			}
	}
}