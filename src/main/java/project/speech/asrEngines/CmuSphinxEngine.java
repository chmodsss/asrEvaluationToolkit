package project.speech.asrEngines;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.*;

import org.apache.commons.io.FilenameUtils;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import project.speech.globalAccess.Globals;
import project.speech.readerAndWriter.*;

public class CmuSphinxEngine {

	private String languageModel;
	private String dictionaryModel;
	private String acousticModel;
	private ArrayList<String> outputSentencesCmuList = new ArrayList<String>();
	private ArrayList<String> outputFileNamesCmuList = new ArrayList<String>();
	private double startTimeMsCmu;
	private double stopTimeMsCmu;
	private double timeDifferenceCmu;

/**
 * This constructor assigns the model files for configuration
 * @param dictionary Dictionary file
 * @param acoustic Acoustic file
 * @param language Language file
 */
	public CmuSphinxEngine(File dictionary, String acoustic, File language) {
		System.out.println("cmu object instantiated...");
		dictionaryModel = dictionary.getPath();
		acousticModel = acoustic;
		languageModel = language.getPath();
	}

/**
 * @return object of Configuration containing the dictionary, language and acoustic models
 */
	public Configuration configure() {
		System.out.println("Configuring cmusphinx models...");
		Configuration configuration = new Configuration();
		configuration.setAcousticModelPath(acousticModel);
		configuration.setDictionaryPath(dictionaryModel);
		configuration.setLanguageModelPath(languageModel);
		return configuration;
	}
	
/**
 * This function initiates speech recognition in cmusphinx and calls FileScripter.writer to write the generated output in a file
 * @param config configuration object containing all the model files
 * @param currentSpeechFolder Directory containing the audio as well as reference text files
 * @param currentSpeechFiles Lower directory containing only the list of audio files
 * @param referenceFile The reference file respective to the speech files being recognized
 * @throws IOException
 */
	public void recognizeSpeech(Configuration config, File currentSpeechFolder, File currentSpeechFiles, File referenceFile) throws IOException {
		System.out.println("Entered cmu");
		try{
		System.out.println("Entered try");
		StreamSpeechRecognizer recognizer = new StreamSpeechRecognizer(config);
		System.out.println("config done");
		FileReader frCmu = new FileReader();
		FileDetails fdCmu = frCmu.reader(currentSpeechFiles);
		System.out.println("reader writer done");
		startTimeMsCmu = System.currentTimeMillis();
		outputSentencesCmuList.clear();
		outputFileNamesCmuList.clear();
		for (int idx = 0; idx < fdCmu.getFilePath().size(); idx++) {
			String cmuCurrentPath = fdCmu.getFilePath().get(idx);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(fdCmu.getFilePath().get(idx)));
			AudioFormat format = audioInputStream.getFormat();
			long frames = audioInputStream.getFrameLength();
			double durationInSeconds = (frames+0.0) / format.getFrameRate();
			System.out.println("Duration..."+durationInSeconds);
			System.out.println("string name  :" + cmuCurrentPath);
			String sentenceDetected;
				try{
					recognizer.startRecognition(new FileInputStream(cmuCurrentPath));
					SpeechResult result = recognizer.getResult();
					recognizer.stopRecognition();
					sentenceDetected = result.getHypothesis();
					String fileName = FilenameUtils.removeExtension(fdCmu.getFileNameExtension().get(idx));
					outputSentencesCmuList.add(sentenceDetected);
					outputFileNamesCmuList.add(fileName);
				}
				catch (Exception e){
					System.out.println("Entered catch...");
					sentenceDetected = "  ";
					String fileName = FilenameUtils.removeExtension(fdCmu.getFileNameExtension().get(idx));
					outputSentencesCmuList.add(sentenceDetected);
					outputFileNamesCmuList.add(fileName);	
				}
				System.out.println(sentenceDetected);
		}
		}
		catch(Exception e)
		{e.printStackTrace();}
		stopTimeMsCmu = System.currentTimeMillis();
		timeDifferenceCmu = (stopTimeMsCmu - startTimeMsCmu)/1000;
		FileScripter.writer(Globals.asr1SelectionNameUI, currentSpeechFolder, referenceFile, outputFileNamesCmuList, outputSentencesCmuList , timeDifferenceCmu);
		}
}