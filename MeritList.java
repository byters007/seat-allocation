import java.io.*;
import java.util.*;

public class MeritList{
	
	//data members
	private HashMap rankList;
	private 

	//constructor
	public MeritList(){
		rankList = new HashMap();
	}

	//Function for adding candidate
	public void addCandidate(Candidate x){
		
	}
	/**Add the sort function here. Use comparator. For example for meritList[1]{OBC and non-PD}, first comparision:on category. oBC student should be higher up. Second comparision(which will be done when first compariosion gives equals(that is if both candidates belong to the same category)), then compare by their rank. 
	This way the merit list will automatically place all OBC students above GE students.
	@Pranjal: Note that now we don't need to increment the rank of GE students by "Number of OBC Students" .This is much cleaner solution. 
	Also note that the we need 8 diferent sort functions for the 8 different merit lists.
	Regarding the design of function: 
		return type: void
		paramter: int index (from 0 to 7)
		body: based on the value of "index" implement the corresponding sorting. 

	*/

}