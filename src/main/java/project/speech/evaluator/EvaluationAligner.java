package project.speech.evaluator;
import java.io.*;
import java.util.*;

import project.speech.globalAccess.Globals;


public class EvaluationAligner {

		private int numHitsTotal = 0;
		private int numSubTotal = 0;
		private int numDelTotal = 0;
		private int numInsTotal = 0;
		private int numHits = 0;
		private int numSub = 0;
		private int numDel = 0;
		private int numIns = 0;
		private int numberOfWords = 0;
		private int numberOfWordsTotal = 0;
		private int markLimit = 1000;
		private static String empty = "*****";
		private String timeSpan;
		
		private String model1= "model1";
		private String model2 = "model2";
		private String model;
		private File alignmentFile;
		
		private String asrUSed;
		private File subSpeechFolder;
		
		private int deletionPenalty;
		private int substitutionPenalty;
		private int insertionPenalty;
		
		File ref = null;
		File hyp = null;
		File time = null;
		
/**
 * This constructor assigns the following files and sets the model to be recognize and evaluate(model1)
 * @param referenceFile Reference text file for evaluation
 * @param hypothesisFile Hypothesis text file to be evaluated
 * @param timeFile Time file contains the duration in seconds needed to recognize the whole folder
 */
		public EvaluationAligner(File referenceFile, File hypothesisFile, File timeFile, ArrayList<Integer> penaltyListSelected) {
			this.ref = referenceFile;
			this.hyp = hypothesisFile;
			this.time = timeFile;
			this.substitutionPenalty = penaltyListSelected.get(0);
			this.deletionPenalty = penaltyListSelected.get(1);
			this.insertionPenalty = penaltyListSelected.get(2);
			this.model= model1;
		}
/**
 *  This constructor assigns the following files and sets the model to be Performance calculator (model2) 
 * @param referenceFile Reference text file for evaluation
 * @param hypothesisFile Hypothesis text file to be evaluated
 */
		public EvaluationAligner(File referenceFile, File hypothesisFile, ArrayList<Integer> penaltyListSelected) {
			this.ref = referenceFile;
			this.hyp = hypothesisFile;
			this.substitutionPenalty = penaltyListSelected.get(0);
			this.deletionPenalty = penaltyListSelected.get(1);
			this.insertionPenalty = penaltyListSelected.get(2);
			this.model = model2;
		}
		
/**
 * This function is used by recognise and evaluate - model1, and it calls evaluate function
 * @param subSpeechFolder Name of the directory containing the speech files and transcription file
 * @param asrUsed Name of the speech recognition engine used
 * @return Object of EvaluatorResult containing number of hits, substitutions, deletions, insertions, reference words and time consumed
 * @throws IOException
 */
		@SuppressWarnings("resource")
		public EvaluatorResult evaluateWithTime(File subSpeechFolder , String asrUsed) throws IOException {
			this.asrUSed = asrUsed;
			this.subSpeechFolder = subSpeechFolder;
			BufferedReader readTime = new BufferedReader(new FileReader(time));
			timeSpan = readTime.readLine();
			this.evaluate();
			EvaluatorResult eval = new EvaluatorResult(numHitsTotal , numSubTotal, numDelTotal, numInsTotal , numberOfWordsTotal , timeSpan);
			return eval;
		}
		
/**
 * This function is used by performance calculator - model2, and it calls evaluate function
 * @return Object of EvaluatorResult containing number of hits, substitutions, deletions, insertions and reference words
 * @throws IOException
 */
		public EvaluatorResult evaluateNoTime() throws IOException {
			this.evaluate();
			System.out.println("result total hits " + numHitsTotal);
			System.out.println("result total subs " + numSubTotal);
			System.out.println("Result total ins :" + numInsTotal);
			System.out.println("Result total del :" + numDelTotal);
			System.out.println("Result total numbr of words :" + numberOfWordsTotal);
			
			EvaluatorResult eval = new EvaluatorResult(numHitsTotal , numSubTotal, numDelTotal, numInsTotal , numberOfWordsTotal );
			return eval;
		}
		
/**
 * This function inserts empty slots equal to the number of reference words, when nothing is recognised by the speech recognizer
 * @param refWords list containing the reference words
 * @param readHyp Buffered reader to reset the pointer to the previous location
 * @param newHypList list adding the number of empty spaces as the number of reference words
 * @throws IOException
 */
		void insertEmptyLines(List<String> refWords, BufferedReader readHyp, List<List<String>> newHypList) throws IOException{
			List<String> tempRefWords = new ArrayList<String>();
			
			tempRefWords.add(refWords.get(0));
			for (int i=0; i<refWords.size()-1; i++){
				tempRefWords.add(empty);
			}
			newHypList.add(tempRefWords);
			readHyp.reset();				
		}
		
/**
 * This function aligns the reference text with the recognition output(hypothesized text) and writes the output in an alignment file
 * @throws IOException when a file could not be read
 */
		public void evaluate() throws IOException{
			
			final int OK = 0;  
			final int SUB = 1;
			final int INS = 2;
			final int DEL = 3;
			
			BufferedReader readRef = new BufferedReader(new FileReader(ref));
			BufferedReader readHyp = new BufferedReader(new FileReader(hyp));
			String refLine;
			String hypLine;
			
			List<List<String>> newHypList = new ArrayList<List<String>>();
			List<List<String>> newRefList = new ArrayList<List<String>>();
			

			while  ((refLine = readRef.readLine()) !=null ) {
				refLine = refLine.replace(".", " ");
				readHyp.mark(markLimit);
				List<String> refWords = new ArrayList<String>(Arrays.asList(refLine.split(" ")));
				
				try{
				hypLine = readHyp.readLine();

				hypLine = hypLine.replace(".", " ");
				List<String> hypWords = new ArrayList<String>(Arrays.asList(hypLine.split(" ")));
				
				if (! refWords.get(0).equals( hypWords.get(0))){
					insertEmptyLines(refWords , readHyp, newHypList);
				}
				else{
					newHypList.add(hypWords);
				}
				newRefList.add(refWords);
			}
				catch(Exception e){
						insertEmptyLines(refWords , readHyp, newHypList);
						newRefList.add(refWords);
				}
			}

			if(model == model1){
				File currentFile = new File("");
				String currentPath = currentFile.getAbsolutePath();
				String newPath = currentPath +"/"+ Globals.RecogniseAndEvaluateResultDirectory;
				if (Globals.RecogniseAndEvaluateResultDirectory.exists()){
				}
				alignmentFile = new File(newPath, Globals.recogniseAndEvaluateAlignmentFileName);
			}
			else if (model == model2){

				Globals.performanceCalculationResultDirectory.mkdirs();
				File currentFile = new File("");
				String currentPath = currentFile.getAbsolutePath();
				String newPath = currentPath + "/"+Globals.performanceCalculationResultDirectory;
				alignmentFile = new File(newPath, Globals.performanceCalculationAlignmentFileName);
			}
			
			PrintWriter alignmentPrintFile = new PrintWriter(new FileWriter((alignmentFile),true));
			if (model == model1){
				alignmentPrintFile.println(" [-----------------------------  "+ subSpeechFolder.getName() +"  -----------------------------] ");
				alignmentPrintFile.print("   (< -------------------------  "+ asrUSed +"  ------------------------- >) \n\n ");
			}
			
			for (int index=0 ; index < newRefList.size(); index++){
				List<String> hypWords = newHypList.get(index);
				List<String> refWords = newRefList.get(index);
				
				numberOfWords = refWords.size() - 1; 
				numIns = 0;
				numSub = 0;
				numDel = 0;
				numHits = 0;
				
		int [][] cost = new int[refWords.size()+1][hypWords.size() + 1];
		int [][] backtrace = new int[refWords.size() + 1][hypWords.size() + 1];
		
		cost[0][0] = 0;
		backtrace[0][0] = OK;
		
		// First column represents the case where we achieve zero hypothesis words by deleting all reference words.
		for(int i=1; i<cost.length; i++) {
			cost[i][0] = deletionPenalty * i;
			backtrace[i][0] = DEL; 
		}

		// First row represents the case where we achieve the hypothesis by inserting all hypothesis words into a zero-length reference.
		for(int j=1; j<cost[0].length; j++) {
			cost[0][j] = insertionPenalty * j;
			backtrace[0][j] = INS; 
		}
		
		// For each next column, go down the rows, recording the min cost edit operation (and the cumulative cost). 
		for(int i=1; i<cost.length; i++) {
			for(int j=1; j<cost[0].length; j++) {
				int subOp, cs;  // it is a substitution if the words aren't equal, but if they are, no penalty is assigned.
				if(refWords.get(i-1).toLowerCase().equals(hypWords.get(j-1).toLowerCase())) {
					subOp = OK;
					cs = cost[i-1][j-1];
				} else {
					subOp = SUB;
					cs = cost[i-1][j-1] + substitutionPenalty;
				}
				int ci = cost[i][j-1] + insertionPenalty;
				int cd = cost[i-1][j] + deletionPenalty;
				
				int mincost = Math.min(cs, Math.min(ci, cd));
				if(cs == mincost) {
					cost[i][j] = cs;
					backtrace[i][j] = subOp;
				} else if(ci == mincost) {
					cost[i][j] = ci;
					backtrace[i][j] = INS;					
				} else {
					cost[i][j] = cd;
					backtrace[i][j] = DEL;					
				}
			}
		}
		
		// Now that we have the minimal costs, find the lowest cost edit to create the hypothesis sequence
		LinkedList<String> alignedReference = new LinkedList<String>();
		LinkedList<String> alignedHypothesis = new LinkedList<String>();

		int i = cost.length - 1;
		int j = cost[0].length - 1;
		while(i > 0 || j > 0) {
			switch(backtrace[i][j]) {
			case OK: alignedReference.add(0, refWords.get(i-1).toLowerCase()); alignedHypothesis.add(0,hypWords.get(j-1).toLowerCase()); i--; j--; break;
			case SUB: alignedReference.add(0, refWords.get(i-1).toUpperCase()); alignedHypothesis.add(0,hypWords.get(j-1).toUpperCase()); i--; j--; numSub++; break;
			case INS: alignedReference.add(0, empty); alignedHypothesis.add(0,hypWords.get(j-1).toUpperCase()); j--; numIns++; break;
			case DEL: alignedReference.add(0, refWords.get(i-1).toUpperCase()); alignedHypothesis.add(0, empty); i--; numDel++; break;
			}
		}
		
		numHits = numberOfWords - (numSub + numDel);
		
		numSubTotal = numSubTotal + numSub;
		numDelTotal = numDelTotal + numDel;
		numInsTotal = numInsTotal + numIns;
		numHitsTotal = numHitsTotal + numHits;
		numberOfWordsTotal = numberOfWordsTotal + numberOfWords;
		
		System.out.println("Line numbr : "+index);
		System.out.println("numsub : "+numSub);
		System.out.println("numdel : "+numDel);
		System.out.println("numins : "+numIns);
		System.out.println("numHits : "+numHits);
		System.out.println("total num sub : "+numSubTotal);
		System.out.println("total num del : "+numDelTotal);
		System.out.println("total num ins : "+numInsTotal);
		System.out.println("hits : "+numHitsTotal);
		System.out.println("total numbr of words : "+ numberOfWordsTotal );
		System.out.println(" \n------------------------------------------------- \n");
		
		String resultRef = alignedReference.toString();
		String resultHyp = alignedHypothesis.toString();
//		hits = alignedHypothesis.size() - (numSub + numIns);
		
//		System.out.println(resultRef);
//		System.out.println(resultHyp);
		
		alignmentPrintFile.println("REF: " + resultRef);
		alignmentPrintFile.println("HYP: " +resultHyp);
		alignmentPrintFile.println();
		}
			readRef.close();
			alignmentPrintFile.println("===============================================================================\n");
			alignmentPrintFile.close();
	}
}