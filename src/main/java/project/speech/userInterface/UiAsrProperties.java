package project.speech.userInterface;

import java.io.File;

public class UiAsrProperties {
	private File uiSpeechCorpus;
	private File uiDictionary;
	private File uiAcoustic;
	private File uiLanguage;
	
/**
 * Returns speech database path
 * @return Folder path of the speech database
 */
	public File getUiSpeechCorpus() {
		return uiSpeechCorpus;
	}

 /**
 *  Sets speech database path
 * @param uiSpeechCorpus Folder path of the speech database
 */
	public void setUiSpeechCorpus(File uiSpeechCorpus) {
		this.uiSpeechCorpus = uiSpeechCorpus;
	}
	
/**
 * Returns dictionary model path
 * @return File path of dictionary file
 */
	public File getUiDictionary() {
		return uiDictionary;
	}
	
/**
 * Sets dictionary model path
 * @param uiDictionary File path of dictionary file
 */
	public void setUiDictionary(File uiDictionary) {
		this.uiDictionary = uiDictionary;
	}
	
/**
 * Returns acoustic model path
 * @return File path of acoustic file
 */
	public File getUiAcoustic() {
		return uiAcoustic;
	}
	
/**
 * Sets acoustic file path
 * @param uiAcoustic  File path of acoustic file
 */
	public void setUiAcoustic(File uiAcoustic) {
		this.uiAcoustic = uiAcoustic;
	}
	
/**
 * Returns Language model path
 * @return File path of language file
 */
	public File getUiLanguage() {
		return uiLanguage;
	}
	
/**
 * Sets language file path
 * @param uiLanguage File path of language file
 */
	public void setUiLanguage(File uiLanguage) {
		this.uiLanguage = uiLanguage;
	}
}
