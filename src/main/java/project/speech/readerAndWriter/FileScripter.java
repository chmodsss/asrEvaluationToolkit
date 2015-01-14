package project.speech.readerAndWriter;

import project.speech.globalAccess.Globals;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class FileScripter {
/**
 * This function writes the output obtained by the speech recognizer in a file
 * @param asrName Name of the speech recognition engine used 
 * @param databaseName Name of the speech folder used for recognition
 * @param referenceFile Reference file available in the speech database for evaluation
 * @param fileNameList List consisting of the names of all speech files present in the folder,
 * inserted as the first word in every sentence
 * @param sentenceDetectedList List containing the sentences recognized by the speech recognition engine 
 * @param timeDifference time consumed by the speech engine for recognition
 * @throws IOException
 */
	public static void writer(String asrName, File databaseName, File referenceFile, ArrayList<String> fileNameList ,ArrayList<String> sentenceDetectedList, double timeDifference) throws IOException {
		File createFolder = new File(Globals.recognitionOutputDirectory,databaseName.getName());
		createFolder.mkdirs();
		
		File promptOriginal = new File(createFolder, Globals.referenceFileName);
		FileUtils.copyFile(referenceFile, promptOriginal);
		File createAsrFile = new File(createFolder,  asrName+"-output");

		PrintWriter asrOutFile = new PrintWriter(new FileWriter((createAsrFile),true));
		for (int i=0 ; i<fileNameList.size() ; i++){
		asrOutFile.print(fileNameList.get(i) + " ");
		asrOutFile.println(sentenceDetectedList.get(i));
		}
		asrOutFile.close();
		
		File createTimeFile = new File(createFolder, asrName+"-time");
		PrintWriter timeOutFile = new PrintWriter(createTimeFile);
		timeOutFile.print(timeDifference);
		timeOutFile.close();
	}
}