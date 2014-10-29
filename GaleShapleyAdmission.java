import java.util.*;
import java.io.*;

public class GaleShapleyAdmission
{
	private Map<String,Candidate> candidateMap = new HashMap<String,Candidate>();
	private Map<String,Candidate> dsCandidateMap = new HashMap<String,Candidate>();
	private Map<String,Candidate> fCandidateMap = new HashMap<String,Candidate>();
	private ArrayList<String> orderedCandidate = new ArrayList<String>();
	private Map<String , ArrayList<VirtualProgramme> > programMap = new HashMap<String , ArrayList<VirtualProgramme> >();						//the program map contains the program code as the key and the arrayList of virtual program as its key value
	private Map<String , ArrayList<Candidate> > instiAppliedMap = new HashMap<String , ArrayList<Candidate> >();
	//private ArrayList<VirtualProgramme> virtualProgrammeList;
	private MeritList[] meritList = new MeritList[8];
	private MeritList[] categoryList = new MeritList[4];

	//Candidate tempCandidate;
	String tempId;
	String tempCategory;
	String tempPDStatus;
	String tempChoices;
	String garbage;
	boolean booleanTempPDStatus;
	boolean booleanTempDSStatus;
	boolean booleanTempNationality;

	public GaleShapleyAdmission(){
		for(int i=0;i<8;i++){
			meritList[i] = new MeritList();
		}
		for(int i=0;i<4;i++){
			categoryList[i] = new MeritList();
		}
	}
	
	public Candidate getCandidate(Candidate candidate){
		return candidateMap.get(candidate.getUniqueID());
	}

	public ArrayList<VirtualProgramme> getProgram(VirtualProgramme program){
		return programMap.get(program.getProgramID());
	}

	public void sortList(ArrayList<Candidate> rankList){
		Collections.sort(rankList, new Comparator<Candidate>() {
	        @Override
	        public int compare(Candidate candidate1, Candidate candidate2)
	        {
	        	if(meritList[0].getRank(candidate1.getUniqueID()) < meritList[0].getRank(candidate2.getUniqueID()))
	        		return -1;
	        	else
	        		return 1;
	        }
    	});
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
			if(tempGE != 0)
			{
				meritList[0].addCandidate(tempId , tempGE);
				meritList[1].addCandidate(tempId , tempGE);
				meritList[4].addCandidate(tempId , tempGE);
				meritList[5].addCandidate(tempId , tempGE);
				categoryList[3].addCandidate(tempId , tempGE);
			}
			
			if(tempOBC != 0)
			{
				meritList[1].addCandidate(tempId , tempOBC);
				categoryList[0].addCandidate(tempId , tempOBC);
			}

			if(tempSC != 0)
			{
				meritList[2].addCandidate(tempId , tempSC);
				meritList[6].addCandidate(tempId , tempSC);
				categoryList[1].addCandidate(tempId , tempSC);
			}							

			if(tempST != 0)
			{
				meritList[3].addCandidate(tempId , tempST);
				meritList[7].addCandidate(tempId , tempST);
				categoryList[2].addCandidate(tempId , tempST);
			}				

			if(tempGE_PD != 0)
			{
				meritList[4].addCandidate(tempId , tempGE_PD);
			}

			if(tempOBC_PD != 0)
			{
				meritList[5].addCandidate(tempId , tempOBC_PD);
				categoryList[0].addCandidate(tempId , tempOBC_PD);
			}

			if(tempSC_PD != 0)
			{
				meritList[6].addCandidate(tempId , tempSC_PD);
				categoryList[1].addCandidate(tempId , tempSC_PD);
			}
			if(tempST_PD != 0)
			{
				meritList[7].addCandidate(tempId , tempST_PD);
				categoryList[2].addCandidate(tempId , tempST_PD);
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
		//All merit lists checked
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		
		/** To read in all available programs, create their respective virtual programmes*/
		String programCode;
		String programName;
		String instiCode;
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
	  		instiCode = programCode.substring(0,1);
	  		programName = sb.next();
	  		ge = sb.nextInt();
	  		obc = sb.nextInt();
	  		sc = sb.nextInt();
	  		st = sb.nextInt();
	  		ge_pd = sb.nextInt();
	  		obc_pd = sb.nextInt();
	  		sc_pd = sb.nextInt();
	  		st_pd = sb.nextInt();
	  		instiAppliedMap.put(instiCode , new ArrayList<Candidate>());
			programMap.put(programCode , new ArrayList<VirtualProgramme>());
			programMap.get(programCode).add(new VirtualProgramme("GE",false,ge,meritList,programCode,instiCode));
			programMap.get(programCode).add(new VirtualProgramme("OBC",false,obc,meritList,programCode,instiCode));
			programMap.get(programCode).add(new VirtualProgramme("SC",false,sc,meritList,programCode,instiCode));
			programMap.get(programCode).add(new VirtualProgramme("ST",false,st,meritList,programCode,instiCode));
			programMap.get(programCode).add(new VirtualProgramme("GE_PD",true,ge_pd,meritList,programCode,instiCode));
			programMap.get(programCode).add(new VirtualProgramme("OBC_PD",true,obc_pd,meritList,programCode,instiCode));
			programMap.get(programCode).add(new VirtualProgramme("SC_PD",true,sc_pd,meritList,programCode,instiCode));
			programMap.get(programCode).add(new VirtualProgramme("ST_PD",true,st_pd,meritList,programCode,instiCode));
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
			if(tempPDStatus.equals("Y"))
				booleanTempPDStatus = true;
			else 
				booleanTempPDStatus = false;
			
			if(tempCategory.equals("DS")){
				booleanTempDSStatus = true;
				if(categoryList[3].getRank(tempId)==-1)
					booleanTempDSStatus = false;
				if(categoryList[0].getRank(tempId)!=-1)
					tempCategory="OBC";
				else if(categoryList[1].getRank(tempId)!=-1)
					tempCategory="SC";
				else if(categoryList[2].getRank(tempId)!=-1)
					tempCategory="ST";
				else
					tempCategory="GE";
			}
			else 
				booleanTempDSStatus = false;

			if(tempCategory.equals("F"))
				booleanTempNationality = false;
			else 
				booleanTempNationality = true;

			Candidate tempCandidate = new Candidate(tempId,tempCategory,booleanTempPDStatus,booleanTempDSStatus,booleanTempNationality);
			orderedCandidate.add(tempId);
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
			if(booleanTempDSStatus)
				tempCandidate.setWaitListedFor(tempCandidate.getDSChoice(0));
			else if(booleanTempNationality)
				tempCandidate.setWaitListedFor(tempCandidate.getChoice(0));
			else
				tempCandidate.setWaitListedFor(tempCandidate.getFChoice(0));
			candidateMap.put(tempId, tempCandidate);
			if(booleanTempDSStatus){
				dsCandidateMap.put(tempId, tempCandidate);
			}
			if(!booleanTempNationality)
				fCandidateMap.put(tempId, tempCandidate);
		}
		s.close();
		} catch(FileNotFoundException e){
			System.exit(1);
		}
		//Checked, everything OK till here
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/****************************************************Start of the GaleShapley Algorithm***************************************************************/
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		HashMap<String , Candidate> rejectionList = new HashMap<String , Candidate>();

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/****************************************************Start of the DS Allocation***************************************************************/
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		boolean dsCompleted=false;
		while(dsCompleted == false){
			for (Map.Entry<String , Candidate> entry : dsCandidateMap.entrySet())
			{
				if(candidateMap.get(entry.getKey()).getAppliedUpto()!=-1){
					//programMap.get(candidateMap.get(entry.getKey()).currentDSVirtualProgramme().getProgramID()).get(entry.getValue().currentDSVirtualProgramme().getMeritListIndex()).receiveApplication(entry.getValue() ,rejectionList);
					instiAppliedMap.get(candidateMap.get(entry.getKey()).currentDSVirtualProgramme().getInstiID()).add(entry.getValue());
				}
			}

			for (Map.Entry<String , ArrayList<Candidate> > entry : instiAppliedMap.entrySet())
			{
				sortList(entry.getValue());
				int seatsGiven=2;
				for(int i=2;i<entry.getValue().size();i++){
					if(meritList[0].getRank(entry.getValue().get(i).getUniqueID())==meritList[0].getRank(entry.getValue().get(i-1).getUniqueID()))
						seatsGiven++;
					else
						break;
				}
				for(int i=seatsGiven; i<entry.getValue().size(); i++){
					rejectionList.put(entry.getValue().get(i).getUniqueID(), entry.getValue().get(i));
				}
				entry.getValue().clear();
			}

			for(Map.Entry<String , Candidate> entry : rejectionList.entrySet())
			{
				//System.out.println(entry.getKey() + " " + entry.getValue().getAppliedUpto());
					candidateMap.get(entry.getKey()).nextDSVirtualProgramme();		/** For all the candidates in the rejection list, call "nextVirtualProgramme()" which crosses of their
															 present choice and makes them apply to the next choice in their list in the next iteration*/
					//System.out.println(entry.getKey() + " " + entry.getValue().getAppliedUpto());
			}
			if(rejectionList.size()==0){dsCompleted = true;}
			rejectionList.clear();
		}
		for(Map.Entry<String , Candidate> entry : dsCandidateMap.entrySet()){
			if(candidateMap.get(entry.getKey()).getAppliedUpto()==-1){
				candidateMap.get(entry.getKey()).setDSStatus(false);
				candidateMap.get(entry.getKey()).setAppliedUpto(0);
			}
		}
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/****************************************************Start of the General Allocation***************************************************************/
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
				if(entry.getValue().getAppliedUpto()!=-1 && entry.getValue().getNationality() && !(entry.getValue().getDSStatus())){
					//System.out.println(entry.getKey() + " " + entry.getValue().currentVirtualProgramme().getProgramID() + " " + entry.getValue().getAppliedUpto());
					programMap.get(entry.getValue().currentVirtualProgramme().getProgramID()).get(entry.getValue().currentVirtualProgramme().getMeritListIndex()).receiveApplication(entry.getValue() ,rejectionList);
				}
					//System.out.println(entry.getKey() + " " + entry.getValue().currentVirtualProgramme().getProgramID() + " " + entry.getValue().getAppliedUpto());
				//entry.applyForProgram(rejectionList);									/** For each candidate, Call receiveApplication on the nextVirtualProgramme which forces candidate to apply to the next preference in his list.*/
			/*	Also pass rejectionList as a Parameter so that if the candidate is rejected, he is added to the list directly. */
			}
			//return;
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
				//System.out.println(entry.getKey() + " " + entry.getValue().getAppliedUpto());
					candidateMap.get(entry.getKey()).nextVirtualProgramme();		/** For all the candidates in the rejection list, call "nextVirtualProgramme()" which crosses of their
															 present choice and makes them apply to the next choice in their list in the next iteration*/
					//System.out.println(entry.getKey() + " " + entry.getValue().getAppliedUpto());
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

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/****************************************************Start of the Foreign Allocation***************************************************************/
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		boolean fCompleted=false;
		while(fCompleted == false){
			for (Map.Entry<String , Candidate> entry : fCandidateMap.entrySet())
			{
				if(candidateMap.get(entry.getKey()).getAppliedUpto()!=-1){
					programMap.get(candidateMap.get(entry.getKey()).currentFVirtualProgramme().getProgramID()).get(entry.getValue().currentFVirtualProgramme().getMeritListIndex()).receiveApplication(entry.getValue() ,rejectionList);
				}
			}
			for (Map.Entry<String , ArrayList<VirtualProgramme> > entry : programMap.entrySet())
			{
				entry.getValue().get(0).fFilter(rejectionList);
				entry.getValue().get(1).fFilter(rejectionList);
				entry.getValue().get(4).fFilter(rejectionList);
				entry.getValue().get(5).fFilter(rejectionList);
			}
			for(Map.Entry<String , Candidate> entry : rejectionList.entrySet())
			{
				//System.out.println(entry.getKey() + " " + entry.getValue().getAppliedUpto());
					candidateMap.get(entry.getKey()).nextFVirtualProgramme();		/** For all the candidates in the rejection list, call "nextVirtualProgramme()" which crosses of their
															 present choice and makes them apply to the next choice in their list in the next iteration*/
					//System.out.println(entry.getKey() + " " + entry.getValue().getAppliedUpto());
			}
			if(rejectionList.size()==0){fCompleted = true;}
			rejectionList.clear();
		}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/******************************************************Now to output the final Seat allocation*******************************************************************/
		for (int i=0;i<orderedCandidate.size();i++){
			if(candidateMap.get(orderedCandidate.get(i)).getAppliedUpto()!=-1){
				System.out.println(orderedCandidate.get(i) + "," + candidateMap.get(orderedCandidate.get(i)).getWaitListedFor().getProgramID() + "," + candidateMap.get(orderedCandidate.get(i)).getWaitListedFor().getCategory() ); /** @debug : Proper Syntax*/
			}
			else{
				System.out.println(orderedCandidate.get(i) + ",-1");
			}
		}
	}
}