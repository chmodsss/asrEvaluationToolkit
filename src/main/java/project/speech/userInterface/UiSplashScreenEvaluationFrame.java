package project.speech.userInterface;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.*;

import org.apache.commons.io.FileUtils;

import project.speech.evaluationSystem.EvaluationSystem;
import project.speech.globalAccess.Globals;

/**
 * This class contains the frame, which displays an ongoing process, while the evaluation process is executing
 *
 */
@SuppressWarnings("serial")
public class UiSplashScreenEvaluationFrame extends JWindow {
	
/**
 * 
 * @param speechDatabaseDirectory
 * @param asrPropertiesObj
 * @param selectedPerformanceList
 * @param selectedAsrList
 * @param algorithmSelected
 * @throws InvocationTargetException
 * @throws InterruptedException
 * @wbp.parser.constructor
 */
	    public UiSplashScreenEvaluationFrame
	    ( final File speechDatabaseDirectory, final ArrayList<UiAsrProperties> asrPropertiesObj, final ArrayList<String> selectedPerformanceList , final ArrayList<String> selectedAsrList, final String algorithmSelected, final ArrayList<Integer> penaltyListSelected) throws InvocationTargetException, InterruptedException  {
	    	EventQueue.invokeLater(new Runnable(){
	    	      public void run() {
				    	try{
							UIManager.setLookAndFeel(Globals.theme2);
						} catch (Exception e) {
							e.printStackTrace();
						}
				    	JWindow guiWindow = createGUI();
				        guiWindow.setVisible( true );
					    MySwingWorker worker = new MySwingWorker(speechDatabaseDirectory,  asrPropertiesObj,  selectedPerformanceList ,  selectedAsrList, algorithmSelected, penaltyListSelected, guiWindow , Globals.model1);
					    worker.execute();
			           }
	    });
	    }
	    
/**
 * Creates a thread to run the evaluation, in parallel to showing the loading splash screen
 * @param referenceFilePath Path of the reference file
 * @param hypothesisFilePath Path of the hypothesis file
 * @param performanceListSelected List of performance metrics selected
 * @param algorithmSelected Alignment algorithm selected for evaluation
 * @throws InvocationTargetException
 * @throws InterruptedException
 */
	    public UiSplashScreenEvaluationFrame  
	    ( final File referenceFilePath, final File hypothesisFilePath, final ArrayList<String> performanceListSelected , final String algorithmSelected, final ArrayList<Integer> penaltyListSelected) throws InvocationTargetException, InterruptedException  {
	    	EventQueue.invokeLater(new Runnable(){
	    	      public void run() {
				    	try{
							UIManager.setLookAndFeel(Globals.theme2);
						} catch (Exception e) {
							e.printStackTrace();
						}
				    	JWindow guiWindow = createGUI();
				        guiWindow.setVisible( true );
					    MySwingWorker worker = new MySwingWorker(referenceFilePath, hypothesisFilePath, performanceListSelected, algorithmSelected , penaltyListSelected, guiWindow, Globals.model2);
					    worker.execute();
			           }
	    });
	    }
	    
/**   
 * @return Object of JWindow having all the properties
 */
	    private JWindow createGUI(){
	    	JPanel content = (JPanel) getContentPane();
	        content.setBackground(Color.white);

	        // Set the window's bounds, centering the window
	        int width = 680;
	        int height = 440;
	        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	        int x = (screen.width - width) / 2;
	        int y = (screen.height - height) / 2;
	        setBounds(x, y, width, height);
	        getContentPane().setLayout(null);

	        // Build the splash screen
	        JLabel label = new JLabel(new ImageIcon(getClass().getResource("/project/speech/userInterface/splashscreenimg.png")));
	        label.setBounds(15, 15, 650, 415);

	        content.add(label);
	       	
	       	JLabel lblgif = new JLabel("");
	       	lblgif.setIcon(new ImageIcon(UiSplashScreenEvaluationFrame.class.getResource("/project/speech/userInterface/evaluategif.GIF")));
	       	lblgif.setBounds(150, 360, 190, 50);
	       	getContentPane().add(lblgif);
	       	
	       	JLabel label_1 = new JLabel("");
	       	label_1.setIcon(new ImageIcon(UiSplashScreenEvaluationFrame.class.getResource("/project/speech/userInterface/evaluategif.GIF")));
	       	label_1.setBounds(335, 360, 200, 50);
	       	getContentPane().add(label_1);
	       	
	       	JLabel lblLoading = new JLabel("");
	       	getContentPane().add(lblLoading);
	       	lblLoading.setIcon(new ImageIcon(UiSplashScreenEvaluationFrame.class.getResource("/project/speech/userInterface/load.GIF")));
	       	lblLoading.setFont(new Font("SansSerif", Font.PLAIN, 16));
	       	lblLoading.setBounds(270, 315, 200, 30);

	        
	        content.setBorder(BorderFactory.createLineBorder(Globals.turquoise, 5));

	        // Display it
	        setVisible(true);
	        toFront();
	        //setAlwaysOnTop(true);
	        
	        return this;
	    }
	  }
	  class MySwingWorker extends SwingWorker<String, Double>{

		  private File speechDatabaseDirectory1;
		  private ArrayList<UiAsrProperties> asrPropertiesObj1;
		  private ArrayList<String> selectedPerformanceList1;
		  private ArrayList<String> selectedAsrList1;
		  private String algorithmSelected1;
		  private ArrayList<Integer> penaltySelected1;
		  private JWindow guiWindow1;
		  
		  private File referenceFilePath2;
		  private File hypothesisFilePath2;
		  private ArrayList<String> performanceListSelected2;
		  private String algorithmSelected2;
		  private ArrayList<Integer> penaltySelected2;
		  private JWindow guiWindow2;
		  
		  private String model;
  /**
   * Constructor of swing worker thread class setting up parameters for model1
   * @param speechDatabaseDirectory Path of the speech database to be used
   * @param asrPropertiesObj Speech recognition properties containing dictionary, languge and acoustic models
   * @param selectedPerformanceList List of performance metrics selected
   * @param selectedAsrList List of Speech recognition engines selected
   * @param algorithmSelected Alignment algorithm selected for evaluation
   * @param guiWindow guiWindow consisting of all the properties
   * @param model1model is set as Recognise and evaluate
   */
	    public MySwingWorker(File speechDatabaseDirectory,
				ArrayList<UiAsrProperties> asrPropertiesObj,
				ArrayList<String> selectedPerformanceList,
				ArrayList<String> selectedAsrList, String algorithmSelected, ArrayList<Integer> penaltyListSelected, JWindow guiWindow , String model1) {
	    	speechDatabaseDirectory1 = speechDatabaseDirectory;
	    	asrPropertiesObj1 = asrPropertiesObj;
			selectedPerformanceList1 = selectedPerformanceList;
			selectedAsrList1 = selectedAsrList;
			algorithmSelected1 = algorithmSelected;
			penaltySelected1 = penaltyListSelected;
			guiWindow1 = guiWindow;
			model = model1;
		}
/**
 * Constructor of swing worker thread class setting up parameters for model2
* @param referenceFilePath Path of the reference file
 * @param hypothesisFilePath Path of the hypothesis file
 * @param performanceListSelected List of performance metrics selected
 * @param algorithmSelected Alignment algorithm selected for evaluation
 * @param guiWindow guiWindow consisting of all the properties
 * @param model2 model is set as Performance calculator
 */
	    public MySwingWorker(File referenceFilePath, File hypothesisFilePath, ArrayList<String> performanceListSelected , String algorithmSelected, ArrayList<Integer> penaltyListSelected, JWindow guiWindow, String model2) {
	    	  referenceFilePath2 = referenceFilePath;
			  hypothesisFilePath2 = hypothesisFilePath;
			  performanceListSelected2 = performanceListSelected;
			  algorithmSelected2 = algorithmSelected;
			  penaltySelected2 = penaltyListSelected;
			  guiWindow2 = guiWindow;
			  model = model2;
			  }	    

		@Override
	    protected String doInBackground() throws Exception {
			if (model == Globals.model1){
	    	EvaluationSystem.recogniseAndEvaluate(speechDatabaseDirectory1,  asrPropertiesObj1,  selectedPerformanceList1 ,  selectedAsrList1, algorithmSelected1, penaltySelected1);
			}
			else if (model == Globals.model2){
				EvaluationSystem.performanceCalculation(referenceFilePath2, hypothesisFilePath2, performanceListSelected2, algorithmSelected2, penaltySelected2);
			}
	    	return "Completed";
	    }

	    @Override
	    protected void done()  {
	    	if (model == Globals.model1){
	    		guiWindow1.setVisible(false);
	    		try {
					openUpResult(Globals.model1ResultFilePath, Globals.model1AlignmentFilePath,  Globals.model1CompleteOutputFilePath , model);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	}
	    	else if (model == Globals.model2){
	    	guiWindow2.setVisible(false);
	    	try {
				openUpResult(Globals.model2ResultFilePath, Globals.model2AlignmentFilePath, Globals.model2CompleteOutputFilePath , model);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	} 
	    }

 /**
 * This function writes the whole result file, appending the alignment result with the performance metric result 	    
 * @param modelOutputFilePath Path of file containing performance metrics result
 * @param modelAlignmentFilePath Path of file containing alignment output
 * @param completeOutputFilePath Path of file containing whole result alignment and performance metrics
 * @throws IOException
 */
		public void openUpResult(String modelOutputFilePath, String modelAlignmentFilePath, String completeOutputFilePath, String model) throws IOException {

			File currentFile = new File("");
			String currentPath = currentFile.getAbsolutePath();

			File file1 = new File(currentPath+"/"+modelAlignmentFilePath);
			File file2 = new File(currentPath+"/"+modelOutputFilePath);
			File file3 = new File(currentPath+"/"+completeOutputFilePath);
			
			String file1Str = FileUtils.readFileToString(file1);
			String file2Str = FileUtils.readFileToString(file2);

			// Write the file
			FileUtils.write(file3, file1Str);
			FileUtils.write(file3, file2Str, true); // true for append
			
			String newnewPath = currentPath+"/"+completeOutputFilePath;
			UiResultFrame.initialise(newnewPath , model);
		}
}
