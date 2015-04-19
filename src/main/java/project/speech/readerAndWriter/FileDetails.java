/**
 * This package contain classes for writing and reading data into and from a file
 */
package project.speech.readerAndWriter;

import java.util.ArrayList;

/**
 * This class is used to store the details of a file, such as the path of the file and 
 * the file name
 */
public class FileDetails {
private ArrayList<String> filePath;
private ArrayList<String> fileNameExtension;


	public FileDetails(ArrayList<String> FilePath, ArrayList<String> FileNameExtension ){
		this.filePath = FilePath;
		this.fileNameExtension = FileNameExtension;
	}

 /**
 * @return path of the file
 */
	public ArrayList<String> getFilePath() {
		return filePath;
	}

 /**
 * @return Name of the file
 */
	public ArrayList<String> getFileNameExtension() {
		return fileNameExtension;
	}
}