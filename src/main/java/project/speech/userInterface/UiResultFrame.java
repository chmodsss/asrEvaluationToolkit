package project.speech.userInterface;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

import java.awt.Toolkit;

import javax.swing.JButton;

import org.apache.commons.io.FileUtils;

import project.speech.globalAccess.Globals;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class UiResultFrame {

	private static JFrame frameResult;
	private static JPanel contentPane;
	private static String pathName;
	private static String pathToSave;
	private static String modelReceived;

	//private JFrame localFrame;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void initialise(String path , String model) {
		
		pathName = path;
		modelReceived = model;
		frameResult =  new JFrame();
		frameResult.setIconImage(Toolkit.getDefaultToolkit().getImage(UiResultFrame.class.getResource("/project/speech/userInterface/logo.png")));
		frameResult.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frameResult.setBounds(100, 100, 720, 502);
		frameResult.setTitle("Evaluation result...");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frameResult.setContentPane(contentPane);
		contentPane.setLayout(null);
		frameResult.setResizable(false);
		
		JTextArea txtAreaResult2 = new JTextArea();
		txtAreaResult2.setEditable(false);
		txtAreaResult2.setBounds(10, 11, 545, 293);
		//=================== Action listener ===================//
		
		try {
				FileReader reader = new FileReader(pathName);
				BufferedReader br = new BufferedReader(reader);
				txtAreaResult2.read(br, null);
				br.close();
				txtAreaResult2.requestFocus();
				JScrollPane scroll = new JScrollPane (txtAreaResult2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				scroll.setSize(697, 390);
				scroll.setLocation(5, 5);
				frameResult.getContentPane().add(scroll);
				
				JButton btnSave = new JButton("Save");
				btnSave.setFont(new Font("SansSerif", Font.PLAIN, 14));
				btnSave.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if (modelReceived == Globals.model1){
							pathToSave = Globals.model1CompleteOutputFilePath;
						}
						else if(modelReceived == Globals.model2){
							pathToSave = Globals.model2CompleteOutputFilePath;
						}
						
						JFileChooser fileChooser = new JFileChooser();
						fileChooser.setDialogTitle("Specify a file to save");   
						 
						int userSelection = fileChooser.showSaveDialog(frameResult);
						 
						if (userSelection == JFileChooser.APPROVE_OPTION) {
							File currentFolder = new java.io.File("");
							String currentPath = currentFolder.getAbsolutePath();
							String newPath;
							newPath = currentPath + pathToSave;
						    File fileToSave = fileChooser.getSelectedFile();
						    System.out.println("Save as file: " + fileToSave.getAbsolutePath());
						    File fileToCopy = new File(newPath);
						    try {
								FileUtils.copyFile(fileToCopy, fileToSave);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						
					}
				});
				btnSave.setBounds(460, 420, 95, 30);
				contentPane.add(btnSave);
				
				JButton btnCancel = new JButton("Cancel");
				btnCancel.setFont(new Font("SansSerif", Font.PLAIN, 14));
				btnCancel.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						frameResult.dispose();
					}
				});
				btnCancel.setBounds(580, 420, 95, 30);
				contentPane.add(btnCancel);
				frameResult.setVisible(true);
		}
		catch (Exception e){
				JOptionPane.showMessageDialog(null, e);
		}
	}	
}
