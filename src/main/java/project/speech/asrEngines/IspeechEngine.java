package project.speech.asrEngines;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.io.FilenameUtils;

import project.speech.globalAccess.Globals;
import project.speech.readerAndWriter.FileDetails;
import project.speech.readerAndWriter.FileReader;
import project.speech.readerAndWriter.FileScripter;

import com.iSpeech.SpeechResult;
import com.iSpeech.iSpeechRecognizer;
import com.iSpeech.iSpeechRecognizer.SpeechRecognizerEvent;

public class IspeechEngine implements SpeechRecognizerEvent {
	private static String api;
	private static boolean production;
	private ArrayList<String> outputSentencesIspeechList = new ArrayList<String>();
	private ArrayList<String> outputFileNamesIspeechList = new ArrayList<String>();
	private double startTimeMsIspeech;
	private double stopTimeMsIspeech;
	private double timeDiffIspeech;

	public IspeechEngine() {
//		api = "developerdemokeydeveloperdemokey"; // Get your API key at http://www.ispeech.org/developers
		api = "28fdb99978fb01f47760079dab81c345";
		production = true; // Your API key server access, false is development and true is production
	}

/**
 * This function initiates speech recognition in ispeech and calls FileScripter.writer to write the output in a file
* @param currentSpeechFolder Directory containing the audio as well as reference text files
 * @param currentSpeechFiles Lower directory containing only the list of audio files
 * @param referenceFile The reference file respective to the speech files being recognized
 * @throws Exception
 */
	public void runFile(File currentSpeechFolder,File currentSpeechFiles, File referenceFile) throws Exception {
		FileReader frIspeech = new FileReader();
		FileDetails fdIspeech = frIspeech.reader(currentSpeechFiles);
		iSpeechRecognizer iSpeech = new iSpeechRecognizer(api, production);
		iSpeech.setFreeForm(3);
		
		outputSentencesIspeechList.clear();
		outputFileNamesIspeechList.clear();
		startTimeMsIspeech = System.currentTimeMillis();
		for (int idx = 0; idx < fdIspeech.getFilePath().size(); idx++) {
			String ispeechCurrentPath = fdIspeech.getFilePath().get(idx);
			String sentenceDetected;
			try{
			SpeechResult result = iSpeech.startFileRecognize(ispeechCurrentPath, new File(ispeechCurrentPath), this);
			// iSpeech.setLocale("es-ES");
			sentenceDetected = result.Text;
			}
			catch(Exception e){
				sentenceDetected = " ";
			}
			System.out.println(sentenceDetected);
			String fileName = FilenameUtils.removeExtension(fdIspeech.getFileNameExtension().get(idx));
			outputSentencesIspeechList.add(sentenceDetected);
			outputFileNamesIspeechList.add(fileName);
		}
		stopTimeMsIspeech = System.currentTimeMillis();
		timeDiffIspeech = (stopTimeMsIspeech - startTimeMsIspeech)/1000;
		FileScripter.writer(Globals.asr2SelectionNameUI, currentSpeechFolder, referenceFile, outputFileNamesIspeechList, outputSentencesIspeechList , timeDiffIspeech);	
	}

	// @Override
	public void stateChanged(int event, int freeFormValue,	Exception lastException) {
		  if(event == SpeechRecognizerEvent.RECORDING_ERROR);
		  System.out.println(RECORDING_ERROR);
	}
}
