package project.speech.userInterface;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import org.apache.commons.io.FileUtils;

import project.speech.globalAccess.Globals;


public class UiMainFrame extends JFrame {

	static JFrame frameMain;
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;
	private static JButton btnModel1;
	private static JButton btnModel2;
	private static JLabel lblAsrTool;
	private static JButton btnAbout;
	
	public static void main(String[] args) {
		try{
		if (Globals.recognitionOutputDirectory.exists()){
		FileUtils.deleteDirectory(Globals.recognitionOutputDirectory);}
		if (Globals.RecogniseAndEvaluateResultDirectory.exists()){
		FileUtils.deleteDirectory(Globals.RecogniseAndEvaluateResultDirectory);}
		if (Globals.performanceCalculationResultDirectory.exists()){
		FileUtils.deleteDirectory(Globals.performanceCalculationResultDirectory);}
		}
		catch(Exception e){
		}
		new UiSplashScreenLoadingFrame();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UiMainFrame framePrimaryMain = new UiMainFrame();
					framePrimaryMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public UiMainFrame() {
	
		setIconImage(Toolkit.getDefaultToolkit().getImage(UiMainFrame.class.getResource("/project/speech/userInterface/logo.png")));
		setTitle("ASR evaluation tool");
		setResizable(false);
		frameMain = new JFrame();
		frameMain.setBounds(100, 100, 695, 450);
		frameMain.getContentPane().setLayout(null);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
		    public void windowClosing(WindowEvent we) {
		    	System.out.println("exit...");
		        if (JOptionPane.showConfirmDialog(frameMain, 
		            "Are you sure to exit the application ?", "Quit ASR evaluation toolkit ?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		    }
		});
		
		
		// Set UI to look more cool
		try {
			UIManager.setLookAndFeel(Globals.theme1);
			// UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 954, 597);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//*************************** Labels ***************************//
		
		// Asr label
		lblAsrTool = new JLabel("ASR Evaluation toolkit v1.0");
		lblAsrTool.setHorizontalAlignment(SwingConstants.CENTER);
		lblAsrTool.setFont(new Font("Century Gothic", Font.PLAIN, 30));
		lblAsrTool.setBounds(322, 50, 405, 39);
		contentPane.add(lblAsrTool);
		
		
		JLabel lblLogo = new JLabel();
		lblLogo.setIcon(new ImageIcon(UiMainFrame.class.getResource("/project/speech/userInterface/logoreduced.png")));
		lblLogo.setBounds(209, 22, 120, 111);
		contentPane.add(lblLogo);
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBounds(50, 222, 400, 233);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("<html> <b>Recognise & Evaluate :</b> <br>\r\nChoose this model, if you have to recognise speeches and evaluate them using the CmuSphinx and iSpeech<br>\r\nOther speech recognition SDK using java implementation could also be added <br>\r\n<b>Requirements</b>\r\n<ul>\r\n<li> Speech database (speech files and respective transcription) </li>\r\n<li> Speech recognition SDK in java </li>\r\n<li> Dictionary, languae and acoustic models </li>\r\n</ul>\r\n</html>");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setFont(new Font("Century Gothic", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 11, 380, 210);
		panel.add(lblNewLabel);
				
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
				panel_1.setBounds(500, 222, 400, 233);
				contentPane.add(panel_1);
				panel_1.setLayout(null);
				
				JLabel lblNewLabel_1 = new JLabel("<html>\r\n<b>Performance calculator :</b><br>\r\nChoose this model, if you have to evaluate the following two files<br>\r\n<ul>\r\n<li> Reference file (transcription) </li>\r\n<li> Hypothesis file (recognition output) </li>\r\n</ul>\r\nThe evaluation is carried out with respect to the corresponding lines in the files. ie, the first line of the reference file is compared with the first line of the hypothesis file.\r\n</html>");
				lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
				lblNewLabel_1.setFont(new Font("Century Gothic", Font.PLAIN, 14));
				lblNewLabel_1.setBounds(10, 11, 380, 211);
				panel_1.add(lblNewLabel_1);
				
						//*************************** Buttons ***************************//
						
						// Model1 button
						btnModel1 = new JButton("Recognise & Evaluate");
						btnModel1.setBounds(160, 160, 180, 35);
						contentPane.add(btnModel1);
						btnModel1.setFont(new Font("SansSerif", Font.PLAIN, 14));
						
						// Model2 button
						btnModel2 = new JButton("Performance calculator");
						btnModel2.setBounds(610, 160, 180, 35);
						contentPane.add(btnModel2);
						btnModel2.setFont(new Font("SansSerif", Font.PLAIN, 14));
						
						btnAbout = new JButton("About");
						btnAbout.setFont(new Font("SansSerif", Font.PLAIN, 14));
						btnAbout.setBounds(428, 496, 100, 30);
						contentPane.add(btnAbout);
						
						btnAbout.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								UiAboutMainFrame frameInstructionMain = new UiAboutMainFrame();
								frameInstructionMain.setTitle("About software...");
								frameInstructionMain.setVisible(true);
								frameInstructionMain.setResizable(false);
							}
						});
						
						// Model2 action
						btnModel2.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								setVisible(false);
								UiMethod2Frame.initialize();
								UiMethod2Frame.frame2.setVisible(true);
							}
						});
						
								
								//********************** Action listeners **********************//
								
								// Model1 action
								btnModel1.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										setVisible(false);
										UiMethod1Frame.initialize();
										UiMethod1Frame.frame1.setVisible(true);
									}
						
						
								});
		
	}
}