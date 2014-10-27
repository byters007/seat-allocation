import java.util.*;
import java.io.*;

public class GaleShapleyAdmission
{
	private Map<String,Candidate> candidateMap = new HashMap<String,Candidate>();
	private Map<String , ArrayList<VirtualProgramme> > programMap = new HashMap<String , ArrayList<VirtualProgramme> >();						//the program map contains the program code as the key and the arrayList of virtual program as its key value
	//private ArrayList< ArrayList<VirtualProgramme> > Programs;
	//private ArrayList<VirtualProgramme> virtualProgrammeList;
	private MeritList[] meritList = new MeritList[8];

	//Candidate tempCandidate;
	String tempId;
	String tempCategory;
	String tempPDStatus;
	String tempChoices;
	String garbage;
	boolean booleanTempPDStatus;

	public GaleShapleyAdmission(){
		for(int i=0;i<8;i++){
			meritList[i] = new MeritList();
		}
	}
	
	public Candidate getCandidate(String uniqueID){
		return candidateMap.get(uniqueID);
	}

	public ArrayList<VirtualProgramme> getProgram(String programCode){
		return programMap.get(programCode);
	}

	public void startAlgorithm()
	{

/** To read in the rank list of candidates and create the merit lists of different categories */
		try{
			Scanner sd = new Scanner(new File("ranklist.csv")).useDelimiter(",|\n");
		
		//fstream inRankList("ranklist.csv" , ios::in);
		String tempId;
		int tempGender,tempCML,tempGE,tempOBC,tempSC,tempST,tempCML_PD,tempGE_PD,tempOBC_PD,tempSC_PD,tempST_PD;
		//inRankList>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage;
		garbage = sd.next();garbage = sd.next();garbage = sd.next();garbage = sd.next();garbage = sd.next();
		garbage = sd.next();garbage = sd.next();garbage = sd.next();garbage = sd.next();garbage = sd.next();
		garbage = sd.next();garbage = sd.next();
		while(sd.hasNext())					/** @debug Write proper syntax */
		{
			tempId = sd.next();
	  		tempGender = sd.nextInt();
	  		tempCML = sd.nextInt();
	  		tempGE = sd.nextInt();
	  		tempOBC = sd.nextInt();
	  		tempSC = sd.nextInt();
	  		tempST = sd.nextInt();
	  		tempCML_PD = sd.nextInt();
	  		tempGE_PD = sd.nextInt();
	  		tempOBC_PD = sd.nextInt();
	  		tempSC_PD = sd.nextInt();
	  		tempST_PD = sd.nextInt();


			//inRankList>>tempId>>tempGender>>tempCML>>tempGE>>tempOBC>>tempSC>>tempST>>tempCML_PD>>tempGE_PD>>tempOBC_PD>>tempSC_PD>>tempST_PD;
																			/** @debug Make sure that proper copy constructors are present to enable copy by value */
			/*if(candidateMap.get(tempId).getCategory.equals("GE") && candidateMap.get(tempId).getPDStatus.equals(false))
			{
				meritList[0].addCandidate(tempId , int rank);
										/** @debug Make sure a proper constructor with these fewer arguements is present 
				meritList[1].addCandidate(tempId , int rank);
				meritList[4].addCandidate(tempId , int rank);
				meritList[5].addCandidate(tempId , int rank);
			}
			if(candidateMap.get(tempId).getCategory.equals("OBC") && candidateMap.get(tempId).getPDStatus.equals(false))
			{
				meritList[1].addCandidate(tempId , int rank);
			}
			if(candidateMap.get(tempId).getCategory.equals("SC") && candidateMap.get(tempId).getPDStatus.equals(false))
			{
				meritList[2].addCandidate(tempId , int rank);
			}
			if(candidateMap.get(tempId).getCategory.equals("ST") && candidateMap.get(tempId).getPDStatus.equals(false))
			{
				meritList[3].addCandidate(tempId , int rank);
			}
			if(candidateMap.get(tempId).getCategory.equals("GE") && candidateMap.get(tempId).getPDStatus.equals(true))
			{
				meritList[0].addCandidate(tempId , int rank);
										/** @debug Make sure a proper constructor with these fewer arguements is present 
				meritList[1].addCandidate(tempId , int rank);
				meritList[4].addCandidate(tempId , int rank);
				meritList[5].addCandidate(tempId , int rank);
			}																
			*/
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//Use addCandidate function in meritlist
			///////////////////////////////////////////////////////////////////////////////////////////////////////////////
			if(tempGE != 0)
			{
				meritList[0].addCandidate(tempId , tempGE);
				meritList[1].addCandidate(tempId , tempGE);
				meritList[4].addCandidate(tempId , tempGE);
				meritList[5].addCandidate(tempId , tempGE);
			}
			
			if(tempOBC != 0)
			{
				meritList[1].addCandidate(tempId , tempOBC);
			}

			if(tempSC != 0)
			{
				meritList[2].addCandidate(tempId , tempSC);
				meritList[6].addCandidate(tempId , tempSC);
			}							

			if(tempST != 0)
			{
				meritList[3].addCandidate(tempId , tempST);
				meritList[7].addCandidate(tempId , tempST);
			}				

			if(tempGE_PD != 0)
			{
				meritList[4].addCandidate(tempId , tempGE_PD);
			}

			if(tempOBC_PD != 0)
			{
				meritList[5].addCandidate(tempId , tempOBC_PD);
			}

			if(tempSC_PD != 0)
			{
				meritList[6].addCandidate(tempId , tempSC_PD);
			}
			if(tempST_PD != 0)
			{
				meritList[7].addCandidate(tempId , tempST_PD);
			}
		}
		/*for(int i=0;i<8;i++)
		{
			meritList[i].sort(i);*/				/** @debug create function inside the MeritList class to sort it on the basis of the given code */
		//}
		
		sd.close();
		} catch(FileNotFoundException e){
			System.exit(1);
		}
			// Till this step we have created all 8 merit lists and sorted them in proper order.
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		/** To read in all available programs, create their respective virtual programmes*/
		String programCode;
		String programName;
		int ge,obc,sc,st,ge_pd,obc_pd,sc_pd,st_pd;
		//ArrayList<VirtualProgramme> tempVirtualProgrammeList;
		try{
		 Scanner sb = new Scanner(new File("programs.csv")).useDelimiter(",|\n");
		//fstream inProgrammes("programs.csv" , ios::in);	/** create proper file stream object */
		//inProgrammes>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage;
		garbage = sb.next();garbage = sb.next();garbage = sb.next();garbage = sb.next();garbage = sb.next();garbage = sb.next();
		garbage = sb.next();garbage = sb.next();garbage = sb.next();garbage = sb.next();garbage = sb.next();
		while(sb.hasNext())
		{
	  	//	inProgrammes>>garbage>>programCode>>programName>>ge>>obc>>sc>>st>>ge_pd>>obc_pd>>sc_pd>>st_pd;
	  		garbage = sb.next();
	  		programCode = sb.next();
	  		programName = sb.next();
	  		ge = sb.nextInt();
	  		obc = sb.nextInt();
	  		sc = sb.nextInt();
	  		st = sb.nextInt();
	  		ge_pd = sb.nextInt();
	  		obc_pd = sb.nextInt();
	  		sc_pd = sb.nextInt();
	  		st_pd = sb.nextInt();
			programMap.put(programCode , new ArrayList<VirtualProgramme>());
			programMap.get(programCode).add(new VirtualProgramme("GE",false,ge,meritList,programCode));
			programMap.get(programCode).add(new VirtualProgramme("OBC",false,obc,meritList,programCode));
			programMap.get(programCode).add(new VirtualProgramme("SC",false,sc,meritList,programCode));
			programMap.get(programCode).add(new VirtualProgramme("ST",false,st,meritList,programCode));
			programMap.get(programCode).add(new VirtualProgramme("GE_PD",true,ge_pd,meritList,programCode));
			programMap.get(programCode).add(new VirtualProgramme("OBC_PD",true,obc_pd,meritList,programCode));
			programMap.get(programCode).add(new VirtualProgramme("SC_PD",true,sc_pd,meritList,programCode));
			programMap.get(programCode).add(new VirtualProgramme("ST_PD",true,st_pd,meritList,programCode));
			//programMap.put(programCode);

		}
		sb.close();
		} catch(FileNotFoundException e){
			System.exit(1);
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/** To Read in the candidate list and their choices */
		try{
		 Scanner s = new Scanner(new File("choices.csv")).useDelimiter(",|\n");
		//fstream inChoices("choices.csv" , ios::in); /** create proper file stream object */
		//inChoices>>garbage>>garbage>>garbage>>garbage;	/** reading in the first line which contains the field names */
		 garbage = s.next();garbage = s.next();garbage = s.next();garbage = s.next();
		while(s.hasNext())				/** Write the correct syntax for reading in from the files */
		{
			//inChoices>>tempId>>tempCategory>>tempPDStatus>>tempChoices;
			tempId = s.next();
			tempCategory = s.next();
			tempPDStatus = s.next();
			tempChoices = s.next();
			if(tempPDStatus == "Y"){booleanTempPDStatus = true;}
			else {booleanTempPDStatus = false;}
			Candidate tempCandidate = new Candidate(tempId,tempCategory,booleanTempPDStatus,false,true);
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//Added loop for adding preferencelist but not sure if it will work as i am using its member function inside its constructor
			//@anmol: maybe we can do this in galeshapley
			String[] choices = tempChoices.split("_");
			for(int i=0;i<choices.length;i++){
				//VirtualProgramme tempProg = new VirtualProgramme(tempChoice,pdStatus,/*@anmol: I need qouta over here. You have not read it in GaleShapley currently*/);
				tempCandidate.addPreference(programMap.get(choices[i]));
			}
			//I think we should read the programme file before student choice file then we can directly get it from programme map
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			candidateMap.put(tempId, tempCandidate);
		}
		s.close();
		} catch(FileNotFoundException e){
			System.exit(1);
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/****************************************************Start of the GaleShapley Algorithm***************************************************************/
		HashMap<String , Candidate> rejectionList = new HashMap<String , Candidate>();
		boolean completed=false;
		while(completed == false)
		{
			//boolean completedForEveryCandidate = true;
			//completed = true;
			/*for (Map.Entry<String , Candidate> entry : candidateMap.entrySet())
			{
				System.out.println(entry.getKey());
			}*/
			for (Map.Entry<String , Candidate> entry : candidateMap.entrySet())
			{
				//System.out.println(entry.getKey() + " " + entry.getValue().getAppliedUpto());
				//entry.getValue().print_preference();
				//System.out.println(entry.getKey() + " " + entry.getValue().currentVirtualProgramme().getProgramID());
				if(entry.getValue().getAppliedUpto()!=-1 && !entry.getValue().isWaitListedFor()){
					//System.out.println(entry.getKey() + " " + entry.getValue().currentVirtualProgramme().getProgramID());
					entry.getValue().currentVirtualProgramme().receiveApplication(entry.getValue() ,rejectionList);
				}
				//entry.applyForProgram(rejectionList);									/** For each candidate, Call receiveApplication on the nextVirtualProgramme which forces candidate to apply to the next preference in his list.*/
			/*	Also pass rejectionList as a Parameter so that if the candidate is rejected, he is added to the list directly. */
			}
			/** Till Now , all the candidates have applied to the programs. now the programs will either keep their applicants in the wait list or reject them (and add to the rejection list).*/
			
			for (Map.Entry<String , ArrayList<VirtualProgramme> > entry : programMap.entrySet())
			{
				for (ListIterator<VirtualProgramme> it = entry.getValue().listIterator(); it.hasNext(); )	/**listIterator with no arguements points to the beginning of the list*/
				 {
	  			 
	  			  it.next().filter(rejectionList);						/** Make sure that you add rejectionList parameter in the filter function for VirtualProgrammes */
				}
	//			entry.filter(rejectionList);		
			}
						/** @doubt Can java variables be redefined? as "entry" variable here */
			for(Map.Entry<String , Candidate> entry : rejectionList.entrySet())
			{
					entry.getValue().nextVirtualProgramme();		/** For all the candidates in the rejection list, call "nextVirtualProgramme()" which crosses of their
															 present choice and makes them apply to the next choice in their list in the next iteration*/
			}
			if(rejectionList.size()==0){completed = true;}		/** If no one is rejected in this iteration, rejectionList will be empty and the iteration can be terminated*/
																/** Iterations are terminated when , for all candidates:
																	1). When he reaches the end of his preference list and can not apply to any more programmes
																	2). When he is in Waitlist for some Program.
																When all the candidates satisfy one of the above two, the algoritihm can be safely terminated.
																Note that when a candidate reaches the end of his Preference list, he no longer applies to any Programme. 
																Thus he can not be added to rejectionList at all.(To be added in rejectionList, one needs to apply to some Programme in the first place.)
																Thus the algorithm is to be terminated iff the rejectionList is empty.*/
			rejectionList.clear();
							/**Clear the rejectionList before the next Iteration*/
		}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/******************************************************Now to output the final Seat allocation*******************************************************************/
		for (Map.Entry<String , Candidate> entry : candidateMap.entrySet())
			{
				System.out.println(entry.getKey() + " " + entry.getValue().getWaitListedFor().getProgramID()); /** @debug : Proper Syntax*/
			}
	}

	public static void main(String[] args) {
		GaleShapleyAdmission start = new GaleShapleyAdmission();
		start.startAlgorithm();
	}
}