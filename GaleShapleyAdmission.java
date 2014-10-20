import java.util.*
import java.io.*

public class GaleShapleyAdmission
{
	private ArrayList<Candidate> candidateList;
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
		candidateList.add(new Candidate(tempId,tempCategory,booleanTempPDStatus,tempChoices));
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
	
	fstream 


}