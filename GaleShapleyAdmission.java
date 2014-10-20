import java.util.*
import java.io.*

public class GaleShapleyAdmission
{
	private Hashset<String,Candidate> candidateList = new Hashset<String,Candidate>;
	private Hashmap<String , ArrayList<VirtualProgramme> > programMap = new Hashmap<String , ArrayList<VirtualProgramme> >();						//the program map contains the program code as the key and the arrayList of virtual program as its key value
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
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/** To Read in the candidate list and their choices */
	fstream inChoices("choices.csv" , ios::in); /** create proper file stream object */
	inChoices>>garbage>>garbage>>garbage>>garbage;	/** reading in the first line which contains the field names */
	while()				/** Write the correct syntax for reading in from the files */
	{
		inChoices>>tempId>>tempCategory>>tempPDStatus>>tempChoices;
		if(tempPDStatus == "Y"){booleanTempPDStatus = true;}
		else {booleanTempPDStatus = false;}
		candidateList.put(tempId , new Candidate(tempId,tempCategory,booleanTempPDStatus,tempChoices) );
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/** To read in all available programs, create their respective virtual programmes*/
	String programCode;
	String programName;
	int ge,obc,sc,st,ge_pd,obc_pd,sc_pd,st_pd;
	//ArrayList<VirtualProgramme> tempVirtualProgrammeList;
	fstream inProgrammes("programs.csv" , ios::in);	/** create proper file stream object */
	inProgrammes>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage;
	while()
	{
		inProgrammes>>garbage>>programCode>>programName>>ge>>obc>>sc>>st>>ge_pd>>obc_pd>>sc_pd>>st_pd;
		programMap.put(programCode , new ArrayList<VirtualProgramme>;
		programMap.get(programCode).add(new VirtualProgramme("GE",false,ge));
		programMap.get(programCode).add(new VirtualProgramme("OBC",false,obc));
		programMap.get(programCode).add(new VirtualProgramme("SC",false,sc));
		programMap.get(programCode).add(new VirtualProgramme("ST",false,st));
		programMap.get(programCode).add(new VirtualProgramme("GE_PD",true,ge_pd));
		programMap.get(programCode).add(new VirtualProgramme("OBC_PD",true,obc_pd));
		programMap.get(programCode).add(new VirtualProgramme("SC_PD",true,sc_pd));
		programMap.get(programCode).add(new VirtualProgramme("ST_PD",true,st_pd));
		//programMap.put(programCode);

	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/** To read in the rank list of candidates and create the merit lists of different categories */
	
	fstream inRankList("ranklist.csv" , ios::in);
	String tempId;
	int tempGender,tempCML,tempGE,tempOBC,tempSC,tempST,tempCML_PD,tempGE_PD,tempOBC_PD,tempSC_PD,tempST_PD;
	inRankList>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage>>garbage;
	while(reading)					/** @debug Write proper syntax */
	{
		inRankList>>tempId>>tempGender>>tempCML>>tempGE>>tempOBC>>tempSC>>tempST>>tempCML_PD>>tempGE_PD>>tempOBC_PD>>tempSC_PD>>tempST_PD;
																		/** @debug Make sure that proper copy constructors are present to enable copy by value */
		/*if(candidateList.get(tempId).getCategory.equals("GE") && candidateList.get(tempId).getPDStatus.equals(false))
		{
			meritList[0].add(new Candidate(candidateList.get(tempId)) , int rank);
									/** @debug Make sure a proper constructor with these fewer arguements is present 
			meritList[1].add(new Candidate(candidateList.get(tempId)) , int rank);
			meritList[4].add(new Candidate(candidateList.get(tempId)) , int rank);
			meritList[5].add(new Candidate(candidateList.get(tempId)) , int rank);
		}
		if(candidateList.get(tempId).getCategory.equals("OBC") && candidateList.get(tempId).getPDStatus.equals(false))
		{
			meritList[1].add(new Candidate(candidateList.get(tempId)) , int rank);
		}
		if(candidateList.get(tempId).getCategory.equals("SC") && candidateList.get(tempId).getPDStatus.equals(false))
		{
			meritList[2].add(new Candidate(candidateList.get(tempId)) , int rank);
		}
		if(candidateList.get(tempId).getCategory.equals("ST") && candidateList.get(tempId).getPDStatus.equals(false))
		{
			meritList[3].add(new Candidate(candidateList.get(tempId)) , int rank);
		}
		if(candidateList.get(tempId).getCategory.equals("GE") && candidateList.get(tempId).getPDStatus.equals(true))
		{
			meritList[0].add(new Candidate(candidateList.get(tempId)) , int rank);
									/** @debug Make sure a proper constructor with these fewer arguements is present 
			meritList[1].add(new Candidate(candidateList.get(tempId)) , int rank);
			meritList[4].add(new Candidate(candidateList.get(tempId)) , int rank);
			meritList[5].add(new Candidate(candidateList.get(tempId)) , int rank);
		}																
		*/
		if(tempGE != 0)
		{
			meritList[0].add(new Candidate(candidateList.get(tempId)) , tempGE);
			meritList[1].add(new Candidate(candidateList.get(tempId)) , tempGE);
			meritList[4].add(new Candidate(candidateList.get(tempId)) , tempGE);
			meritList[5].add(new Candidate(candidateList.get(tempId)) , tempGE);
		}
		
		if(tempOBC != 0)
		{
			meritList[1].add(new Candidate(candidateList.get(tempId)) , tempOBC);
		}

		if(tempSC != 0)
		{
			meritList[2].add(new Candidate(candidateList.get(tempId)) , tempSC);
			meritList[6].add(new Candidate(candidateList.get(tempId)) , tempSC);
		}							

		if(tempST != 0)
		{
			meritList[3].add(new Candidate(candidateList.get(tempId)) , tempST);
			meritList[7].add(new Candidate(candidateList.get(tempId)) , tempST);
		}				

		if(tempGE_PD != 0)
		{
			meritList[4].add(new Candidate(candidateList.get(tempId)) , tempGE_PD);
		}

		if(tempOBC_PD != 0)
		{
			meritList[5].add(new Candidate(candidateList.get(tempId)) , tempOBC_PD);
		}

		if(tempSC_PD != 0)
		{
			meritList[6].add(new Candidate(candidateList.get(tempId)) , tempSC_PD);
		}
		if(tempST_PD != 0)
		{
			meritList[7].add(new Candidate(candidateList.get(tempId)) , tempST_PD);
		}
	}

	for(int i=0;i<8;i++)
	{
		meritList[i].sort(i);				/** @debug create function inside the MeritList class to sort it on the basis of the given code */
	}
		// Till this step we have created all 8 merit lists and sorted tehm in proper order.
	
}