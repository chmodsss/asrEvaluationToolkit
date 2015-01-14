package project.speech.userInterface;

import project.speech.globalAccess.Globals;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

public class UiMethod1Frame {

	// Buttons on the UI
	static JFrame frame1;

	private static JCheckBox chkPercentHits;
	private static JCheckBox chkPercentSubs;
	private static JCheckBox chkPercentDel;
	private static JCheckBox chkPercentIns;
	private static JCheckBox chkMER;
	private static JCheckBox chkWIL;
	private static JCheckBox chkWER;
	private static JCheckBox chkALL;
	
	private static JButton btnDictionaryModel;
	private static JButton btnLanguageModel;
	private static JButton btnAcousticModel;

	private static JButton btnCheck;
	private static JButton btnInstructions;
	private static JButton btnEvaluate;
	private static JLabel lblSpeechCorpusPath;

	final static UiAsrProperties cmuProperties = new UiAsrProperties();
	final static UiAsrProperties iSpeechProperties = new UiAsrProperties();

	private static JFileChooser speechCorpusChooser;
	private static JFileChooser dictionaryChooser;
	private static JFileChooser acousticChooser;
	private static JFileChooser languageChooser;

	private static String speechCorpusChoosertitle = "Select the path of Speech corpus...";
	private static String dictionaryChoosertitle = "Select the dictionary file...";
	private static String acousticChoosertitle = "Select the acoustic file...";
	private static String languageChoosertitle = "Select the language file...";

	private static String currentAsrSelected = null;
	private static File speechCorpusPathResult = null;
	private static boolean speechCorpusLoaded = false;
	private static boolean modelsNeeded = false;
	private static boolean dictLoadedCmu = false;
	private static boolean acousLoadedCmu = false;
	private static boolean langLoadedCmu = false;
	private static boolean dictLoadedIspeech = false;
	private static boolean acousLoadedIspeech = false;
	private static boolean langLoadedIspeech = false;
	private static boolean checkBool = false;
	private static boolean checkCmu = false;
	private static boolean checkIspeech = false;

	private static Object asrSelectedObj ;

	private static ArrayList<UiAsrProperties> speechPropertiesList = new ArrayList<UiAsrProperties>();
	private static ArrayList<JCheckBox> performanceListChecked = new ArrayList<JCheckBox>();
	private static ArrayList<String> performanceListSelected = new ArrayList<String>();
	private static ArrayList<String> asrSystemsSelected = new ArrayList<String>();
	private static ArrayList<Integer> penaltyListSelected = new ArrayList<Integer>(); 
	private static String algorithmSelected = null;

	private static JLabel lblModel1;

	@SuppressWarnings("rawtypes")
	private static JComboBox comboAsrSelect;
	@SuppressWarnings("rawtypes")
	private static JComboBox comboAsrResult;
	@SuppressWarnings("rawtypes")
	private static JComboBox comboAlgorithm;
	private static JLabel lblDictionaryModel;
	private static JLabel lblLanguageModel;
	private static JLabel lblAcousticModel;
	private static JLabel lblAsrChoice;
	private static JLabel lblAlgorithmChoice;
	
	private static String lastDirectoryVisited= ".";
	private static JTextField txtSubs;
	private static JTextField txtDel;
	private static JTextField txtIns;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static void initialize() {

		frame1 = new JFrame();
		frame1.setIconImage(Toolkit.getDefaultToolkit().getImage(UiMethod1Frame.class.getResource("/project/speech/userInterface/logo.png")));
		frame1.setBounds(100, 100, 966, 492);
		frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame1.getContentPane().setLayout(null);
		frame1.setTitle("Recognise & Evaluate");
		frame1.setResizable(false);
		
		final UiInstructionFrame1 frameInstructions1 = new UiInstructionFrame1();
		frame1.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				UiMainFrame framePrimary1 = new UiMainFrame();
				framePrimary1.setVisible(true);
				if (frameInstructions1 != null) {
					frameInstructions1.dispose();
				}
			}
		});

		// Set UI to look more cool
		try {
			UIManager.setLookAndFeel(Globals.theme1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//=========== Objects to store properties ===========//
		speechPropertiesList.add(cmuProperties);
		speechPropertiesList.add(iSpeechProperties);
		

		//=================== Panels ===================//
		JPanel panelProperties = new JPanel();
		panelProperties.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Properties", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelProperties.setBounds(640, 80, 300, 362);
		frame1.getContentPane().add(panelProperties);
		panelProperties.setLayout(null);
		
		JPanel panelEvaluation = new JPanel();
		panelEvaluation.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Evaluation", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelEvaluation.setBounds(300, 312, 320, 130);
		frame1.getContentPane().add(panelEvaluation);
		panelEvaluation.setLayout(null);
		
		JPanel panelCriteria = new JPanel();
		panelCriteria.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Output Choices", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelCriteria.setBounds(20, 80, 260, 362);
		frame1.getContentPane().add(panelCriteria);
		panelCriteria.setLayout(null);
		
		JPanel panelPerformance = new JPanel();
		panelPerformance.setBounds(20, 181, 220, 161);
		panelCriteria.add(panelPerformance);
		panelPerformance.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Performance measures", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelPerformance.setLayout(null);
		
		//=================== Labels ===================//
		lblModel1 = new JLabel("Recognise & Evaluate");
		lblModel1.setHorizontalAlignment(SwingConstants.CENTER);
		lblModel1.setFont(new Font("Century Gothic", Font.PLAIN, 30));
		lblModel1.setBounds(275, 20, 390, 39);
		frame1.getContentPane().add(lblModel1);
		
		
		// Instructions
		btnInstructions = new JButton("Instructions");
		btnInstructions.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnInstructions.setBounds(390, 95, 135, 28);
		frame1.getContentPane().add(btnInstructions);
				
		//***************** Buttons under Properties panel *****************//
		
		// Speech corpus path button
		final JButton btnSpeechCorpus = new JButton("Speech corpus");
		btnSpeechCorpus.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnSpeechCorpus.setBounds(30, 40, 135, 28);
		panelProperties.add(btnSpeechCorpus);
			
		// Combo box to select asr systems
		comboAsrSelect = new JComboBox();
		comboAsrSelect.setFont(new Font("SansSerif", Font.PLAIN, 14));
		comboAsrSelect.setBounds(139, 95, 135, 28);
		panelProperties.add(comboAsrSelect);
		comboAsrSelect.setModel(new DefaultComboBoxModel(new String[] { Globals.select, Globals.asr1SelectionNameUI, Globals.asr2SelectionNameUI }));
		
		JLabel lblNewLabel = new JLabel("ASR engine :");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel.setBounds(35, 100, 94, 19);
		panelProperties.add(lblNewLabel);
		
		lblSpeechCorpusPath = new JLabel();
		lblSpeechCorpusPath.setHorizontalAlignment(SwingConstants.CENTER);
		lblSpeechCorpusPath.setFont(new Font("SansSerif", Font.PLAIN, 10));
		lblSpeechCorpusPath.setBounds(194, 61, 80, 20);
		panelProperties.add(lblSpeechCorpusPath);
		
		JPanel panel = new JPanel();
		panel.setBounds(20, 142, 260, 200);
		panelProperties.add(panel);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Model properties", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setLayout(null);
		
		// Language model path button
		btnLanguageModel = new JButton("Language model");
		btnLanguageModel.setBounds(20, 90, 138, 27);
		panel.add(btnLanguageModel);
		btnLanguageModel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnLanguageModel.setEnabled(false);
		
		// Acoustic model path button
		btnAcousticModel = new JButton("Acoustic model");
		btnAcousticModel.setBounds(20, 140, 138, 27);
		panel.add(btnAcousticModel);
		btnAcousticModel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnAcousticModel.setEnabled(false);
		
		lblDictionaryModel = new JLabel();
		lblDictionaryModel.setHorizontalAlignment(SwingConstants.CENTER);
		lblDictionaryModel.setBounds(170, 45, 80, 20);
		panel.add(lblDictionaryModel);
		lblDictionaryModel.setFont(new Font("SansSerif", Font.PLAIN, 10));
		
		// Dictionary model path button
		btnDictionaryModel = new JButton("Dictionary model");
		btnDictionaryModel.setBounds(20, 40, 138, 27);
		panel.add(btnDictionaryModel);
		btnDictionaryModel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnDictionaryModel.setEnabled(false);
		
		// Dictionary path selection
		btnDictionaryModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File dictionaryPathResult = null;
				if (currentAsrSelected != null && modelsNeeded) {
					dictionaryChooser = new JFileChooser();
					dictionaryChooser.setCurrentDirectory(new java.io.File(lastDirectoryVisited));
					dictionaryChooser.setDialogTitle(dictionaryChoosertitle);
					dictionaryChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					dictionaryChooser.setAcceptAllFileFilterUsed(false);
		
					if (dictionaryChooser.showOpenDialog(frame1) == JFileChooser.APPROVE_OPTION) {
						btnEvaluate.setEnabled(false);
						dictionaryPathResult = getRelativePath(dictionaryChooser.getSelectedFile());
						btnDictionaryModel.setToolTipText(dictionaryPathResult.getAbsolutePath());
						lblDictionaryModel.setText(dictionaryPathResult.getName());
						System.out.println("dict path" + getRelativePath(dictionaryPathResult));
						btnDictionaryModel.setBackground(Globals.turquoise);
						dictLoadedCmu = true;
					}
					if (currentAsrSelected == Globals.asr1Name) {
						cmuProperties.setUiDictionary(dictionaryPathResult);
					}
					/*
					 * if(currentAsrSelected == Globals.asr2Name ){
					 * iSpeechProperties.setUiDictionary(dictionaryPathResult);
					 * }
					 */
					}
				}
		});
		
		lblLanguageModel = new JLabel();
		lblLanguageModel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLanguageModel.setBounds(170, 95, 80, 20);
		panel.add(lblLanguageModel);
		lblLanguageModel.setFont(new Font("SansSerif", Font.PLAIN, 10));
		
		lblAcousticModel = new JLabel();
		lblAcousticModel.setHorizontalAlignment(SwingConstants.CENTER);
		lblAcousticModel.setBounds(170, 145, 80, 20);
		panel.add(lblAcousticModel);
		lblAcousticModel.setFont(new Font("SansSerif", Font.PLAIN, 10));
		
		// Acoustic path selection
		btnAcousticModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File acousticPathResult = null;
				if (currentAsrSelected != null && modelsNeeded) {
					acousticChooser = new JFileChooser();
					acousticChooser.setCurrentDirectory(new java.io.File(lastDirectoryVisited));
					acousticChooser.setDialogTitle(acousticChoosertitle);
					acousticChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					acousticChooser.setAcceptAllFileFilterUsed(false);
					
					if (acousticChooser.showOpenDialog(frame1) == JFileChooser.APPROVE_OPTION) {
						btnEvaluate.setEnabled(false);
						acousticPathResult = getRelativePath(acousticChooser.getSelectedFile());
						btnAcousticModel.setToolTipText(acousticPathResult.getAbsolutePath());
						lblAcousticModel.setText(acousticPathResult.getName());
						btnAcousticModel.setBackground(Globals.turquoise);
						acousLoadedCmu = true;
					}
					if (currentAsrSelected == Globals.asr1Name) {
						cmuProperties.setUiAcoustic(acousticPathResult);
					}
					/*
					 * if(currentAsrSelected == Globals.asr2Name){
					 * iSpeechProperties.setUiAcoustic(acousticPathResult); }
					 */
					}
				}
		});
		
		// Language path selection
		btnLanguageModel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File languagePathResult = null;
				if (currentAsrSelected != null && modelsNeeded) {
					languageChooser = new JFileChooser();
					languageChooser.setCurrentDirectory(new java.io.File(lastDirectoryVisited));
					languageChooser.setDialogTitle(languageChoosertitle);
					languageChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
					languageChooser.setAcceptAllFileFilterUsed(false);
		
					if (languageChooser.showOpenDialog(frame1) == JFileChooser.APPROVE_OPTION) {
						btnEvaluate.setEnabled(false);
						languagePathResult = getRelativePath(languageChooser.getSelectedFile());
						btnLanguageModel.setToolTipText(languagePathResult.getAbsolutePath());
						lblLanguageModel.setText(languagePathResult.getName());
						langLoadedCmu = true;
						btnLanguageModel.setBackground(Globals.turquoise);
					}
					if (currentAsrSelected == Globals.asr1Name) {
						cmuProperties.setUiLanguage(languagePathResult);
					}
					/*
					 * if(currentAsrSelected == Globals.asr2Name){
					 * iSpeechProperties.setUiLanguage(languagePathResult); }
					 */
					}
				}
		});
		
		//***************** Buttons under Evaluation panel *****************//		
				
		// Evaluation button
		btnEvaluate = new JButton("Evaluate");
		btnEvaluate.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnEvaluate.setBounds(93, 80, 135, 28);
		panelEvaluation.add(btnEvaluate);
		btnEvaluate.setEnabled(false);
		
		// Check button
		btnCheck = new JButton("Check");
		btnCheck.setFont(new Font("SansSerif", Font.PLAIN, 14));
		btnCheck.setBounds(93, 30, 135, 28);
		panelEvaluation.add(btnCheck);
		
		//***************** Buttons under Criteria panel *****************//
		
		// Select output speech engines
		comboAsrResult = new JComboBox();
		comboAsrResult.setFont(new Font("SansSerif", Font.PLAIN, 14));
		comboAsrResult.setBounds(103, 66, 135, 28);
		panelCriteria.add(comboAsrResult);
		comboAsrResult.setModel(new DefaultComboBoxModel(new String[] { Globals.select, Globals.asr1SelectionNameUI, Globals.asr2SelectionNameUI, Globals.allselectionUI }));
		
		// Combo box algorithm selection
		comboAlgorithm = new JComboBox();
		comboAlgorithm.setFont(new Font("SansSerif", Font.PLAIN, 14));
		comboAlgorithm.setBounds(103, 124, 135, 28);
		panelCriteria.add(comboAlgorithm);
		comboAlgorithm.setModel(new DefaultComboBoxModel(new String[] { Globals.select, Globals.hsdiWeightsAlgorithm}));
				
		//***************** Buttons under Criteria panel *****************//		
		
		chkPercentHits = new JCheckBox(Globals.hitsPercentUI);
		chkPercentHits.setFont(new Font("SansSerif", Font.PLAIN, 14));
		chkPercentHits.setBounds(33, 23, 75, 28);
		panelPerformance.add(chkPercentHits);
		
		chkPercentSubs = new JCheckBox(Globals.subsPercentUI);
		chkPercentSubs.setFont(new Font("SansSerif", Font.PLAIN, 14));
		chkPercentSubs.setBounds(33, 53, 75, 28);
		panelPerformance.add(chkPercentSubs);
		
		chkPercentDel = new JCheckBox(Globals.delPercentUI);
		chkPercentDel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		chkPercentDel.setBounds(33, 83, 75, 28);
		panelPerformance.add(chkPercentDel);
		
		chkPercentIns = new JCheckBox(Globals.insPercentUI);
		chkPercentIns.setFont(new Font("SansSerif", Font.PLAIN, 14));
		chkPercentIns.setBounds(33, 113, 75, 28);
		panelPerformance.add(chkPercentIns);
		
		chkWER = new JCheckBox(Globals.werUI);
		chkWER.setFont(new Font("SansSerif", Font.PLAIN, 14));
		chkWER.setBounds(128, 23, 75, 28);
		panelPerformance.add(chkWER);
		
		chkMER = new JCheckBox(Globals.merUI);
		chkMER.setFont(new Font("SansSerif", Font.PLAIN, 14));
		chkMER.setBounds(128, 53, 75, 28);
		panelPerformance.add(chkMER);
		
		chkWIL = new JCheckBox(Globals.wilUI);
		chkWIL.setFont(new Font("SansSerif", Font.PLAIN, 14));
		chkWIL.setBounds(128, 83, 75, 28);
		panelPerformance.add(chkWIL);
		
		//All the parameters
		chkALL = new JCheckBox(Globals.allUI);
		chkALL.setFont(new Font("SansSerif", Font.PLAIN, 14));
		chkALL.setBounds(128, 113, 75, 28);
		panelPerformance.add(chkALL);
		
		lblAsrChoice = new JLabel("ASR engine :");
		lblAsrChoice.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblAsrChoice.setBounds(10, 71, 94, 19);
		panelCriteria.add(lblAsrChoice);
		
		lblAlgorithmChoice = new JLabel("Algorithm :");
		lblAlgorithmChoice.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblAlgorithmChoice.setBounds(10, 129, 94, 19);
		panelCriteria.add(lblAlgorithmChoice);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Alignment penalties", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setBounds(300, 146, 320, 150);
		frame1.getContentPane().add(panel_1);
		
		JLabel label = new JLabel("Substitution penalty (0-100)");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("SansSerif", Font.PLAIN, 14));
		label.setBounds(10, 25, 180, 28);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("Deletion penalty (0-100)");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		label_1.setBounds(10, 65, 180, 28);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("Insertion penalty (0-100)");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		label_2.setBounds(10, 105, 180, 28);
		panel_1.add(label_2);
		
		txtSubs = new JTextField();
		txtSubs.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) { 
				try{
				int i = Integer.parseInt( txtSubs.getText());
				if (!(i >=0 && i<=100)){
					txtSubs.setText(Integer.toString(Globals.defaultSubstitutionPenalty));
				}
				}
				catch (Exception ee){
					txtSubs.setText(Integer.toString(Globals.defaultSubstitutionPenalty));
				}
			}
		});
		txtSubs.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ( !(Character.isDigit(c)) || (c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE)){
					e.consume();
				}
			}
		});
		txtSubs.setText(Integer.toString(Globals.defaultSubstitutionPenalty));
		txtSubs.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtSubs.setBounds(200, 30, 86, 20);
		panel_1.add(txtSubs);
		txtSubs.setColumns(10);
		
		txtDel = new JTextField();
		txtDel.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try{
				int i = Integer.parseInt( txtDel.getText());
				if (!(i >=0 && i<=100)){
					txtDel.setText(Integer.toString(Globals.defaultDeletionPenalty));
				}
				}
				catch (Exception ee){
					txtDel.setText(Integer.toString(Globals.defaultDeletionPenalty));
				}
			}
		});
		txtDel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ( !(Character.isDigit(c)) || (c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE)){
					e.consume();
				}
			}
		});
		txtDel.setText(Integer.toString(Globals.defaultDeletionPenalty));
		txtDel.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtDel.setBounds(200, 70, 86, 20);
		panel_1.add(txtDel);
		txtDel.setColumns(10);
		
		txtIns = new JTextField();
		txtIns.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				try{
				int i = Integer.parseInt( txtIns.getText());
				if (!(i >=0 && i<=100)){
					txtIns.setText(Integer.toString(Globals.defaultInsertionPenalty));
				}
				}
				catch (Exception ee){
					txtIns.setText(Integer.toString(Globals.defaultInsertionPenalty));
				}
			}
		});
		txtIns.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ( !(Character.isDigit(c)) || (c==KeyEvent.VK_BACK_SPACE) || (c==KeyEvent.VK_DELETE)){
					e.consume();
				}
			}
		});
		txtIns.setText(Integer.toString(Globals.defaultInsertionPenalty));
		txtIns.setFont(new Font("SansSerif", Font.PLAIN, 14));
		txtIns.setBounds(200, 110, 86, 20);
		panel_1.add(txtIns);
		txtIns.setColumns(10);
		
		//=================== Action listener ===================//		
		
		// Instruction - to open instruction frame
		btnInstructions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frameInstructions1.setVisible(true);
				frameInstructions1.setTitle("Instructions...");
				frameInstructions1.setResizable(false);
			}
		});

		//***************** Actions under Properties panel *****************//		
		
		// Speech corpus path selection
		btnSpeechCorpus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				speechCorpusChooser = new JFileChooser();
				speechCorpusChooser.setCurrentDirectory(new java.io.File(lastDirectoryVisited));
				speechCorpusChooser.setDialogTitle(speechCorpusChoosertitle);
				speechCorpusChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				speechCorpusChooser.setAcceptAllFileFilterUsed(false);
				if (speechCorpusChooser.showOpenDialog(frame1) == JFileChooser.APPROVE_OPTION) {
					btnEvaluate.setEnabled(false);
					speechCorpusPathResult = speechCorpusChooser.getSelectedFile();
					btnSpeechCorpus.setToolTipText(speechCorpusPathResult.getAbsolutePath());
					lblSpeechCorpusPath.setText(speechCorpusPathResult.getName());
					speechCorpusLoaded = true;
					btnSpeechCorpus.setBackground(Globals.turquoise);
				}
			}
		});
		
		// Selecting asr systems
		comboAsrSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionAsrSelected) {
				btnEvaluate.setEnabled(false);
				JComboBox comboAsrSelected = (JComboBox) actionAsrSelected.getSource();
				asrSelectedObj = comboAsrSelected.getSelectedItem();
				
				if (Globals.asr1SelectionNameUI.equals(asrSelectedObj)) {
					modelsNeeded = true;
					setSelectedAsr(Globals.asr1Name);
					btnDictionaryModel.setEnabled(true);
					btnAcousticModel.setEnabled(true);
					btnLanguageModel.setEnabled(true);
					
					lblDictionaryModel.setVisible(true);
					lblLanguageModel.setVisible(true);
					lblAcousticModel.setVisible(true);
	
					setDefaultColor();
					if (dictLoadedCmu)
						btnDictionaryModel.setBackground(Globals.turquoise);
					if (acousLoadedCmu)
						btnAcousticModel.setBackground(Globals.turquoise);
					if (langLoadedCmu)
						btnLanguageModel.setBackground(Globals.turquoise);
				}
	
				if (Globals.asr2SelectionNameUI.equals(asrSelectedObj)) {
					setSelectedAsr(Globals.asr2Name);
					modelsNeeded = false;
					
					lblDictionaryModel.setVisible(false);
					lblLanguageModel.setVisible(false);
					lblAcousticModel.setVisible(false);
	
					setDefaultColor();
					if (dictLoadedIspeech)
						btnDictionaryModel.setBackground(Globals.turquoise);
					if (acousLoadedIspeech)
						btnAcousticModel.setBackground(Globals.turquoise);
					if (langLoadedIspeech)
						btnLanguageModel.setBackground(Globals.turquoise);
	
					btnDictionaryModel.setEnabled(false);
					btnAcousticModel.setEnabled(false);
					btnLanguageModel.setEnabled(false);
				}
	
				if (Globals.select.equals(asrSelectedObj)) {
					setSelectedAsr(null);
	
					setDefaultColor();
					btnDictionaryModel.setEnabled(false);
					btnAcousticModel.setEnabled(false);
					btnLanguageModel.setEnabled(false);
				}
			}
		});
		
		//***************** Actions under Evaluation panel *****************//		

		// check button - to check whether all conditions are met
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				penaltyListSelected.add(Integer.parseInt(txtSubs.getText()));
				penaltyListSelected.add(Integer.parseInt(txtDel.getText()));
				penaltyListSelected.add(Integer.parseInt(txtIns.getText()));
				
				btnEvaluate.setEnabled(false);
				if (!asrSystemsSelected.isEmpty())
					asrSystemsSelected.clear();
				if (!performanceListSelected.isEmpty())
					performanceListSelected.clear();

				//--- Computing the selected asr systems ---
				Object resultAsrSelectedObj = comboAsrResult.getSelectedItem();
				if ("CmuSphinx".equals(resultAsrSelectedObj)) {
					asrSystemsSelected.add(Globals.asr1Name);
				}
				if ("iSpeech".equals(resultAsrSelectedObj)) {
					asrSystemsSelected.add(Globals.asr2Name);
				}
				if ("All".equals(resultAsrSelectedObj)) {
					asrSystemsSelected.add(Globals.asr1Name);
					asrSystemsSelected.add(Globals.asr2Name);
				}
				if (Globals.select.equals(resultAsrSelectedObj)) {
					asrSystemsSelected.clear();
				}

				//--- Strings for storing the performance measures ---
				performanceListChecked.clear();
				performanceListChecked.add(chkPercentHits);
				performanceListChecked.add(chkPercentSubs);
				performanceListChecked.add(chkPercentDel);
				performanceListChecked.add(chkPercentIns);
				performanceListChecked.add(chkWER);
				performanceListChecked.add(chkMER);
				performanceListChecked.add(chkWIL);

				//--- Computing the performance list ---
				if (chkALL.isSelected()) {
					for (int i = 0; i < performanceListChecked.size(); i++) {
						performanceListSelected.add(performanceListChecked.get(i).getText());
					}
				} else {
					for (int j = 0; j < performanceListChecked.size(); j++) {
						JCheckBox each = performanceListChecked.get(j);
						if (each != null) {
							if (each.isSelected()) {
								performanceListSelected.add(each.getText());
							}
						}
					}
				}
				//--- Store method of algorithm in String ---
				algorithmSelected = (String) comboAlgorithm.getSelectedItem();
				if (Globals.select.equals(algorithmSelected)) {
					algorithmSelected = null;
				}

				checkBool = speechCorpusLoaded /* && (asrSelectedObj != null) */ && (algorithmSelected != null) && (!performanceListSelected.isEmpty()) && (!asrSystemsSelected.isEmpty()) ;
				if (checkBool){
					if (asrSystemsSelected.contains(Globals.asr1Name)){
						if (Globals.asr1Name.equals(currentAsrSelected)){
								checkCmu = dictLoadedCmu && acousLoadedCmu && langLoadedCmu;
							}
						}

				checkIspeech = true;

				 if (asrSystemsSelected.contains(Globals.asr1Name) && asrSystemsSelected.contains(Globals.asr2Name)){
					 if (checkCmu && checkIspeech){
						 btnEvaluate.setEnabled(true);
					 }
				 }
				 else if (asrSystemsSelected.contains(Globals.asr1Name)){
					 if (checkCmu){
						 btnEvaluate.setEnabled(true);
					 }
				 }
				 else if (asrSystemsSelected.contains(Globals.asr2Name)){
					 if (checkIspeech){
						 btnEvaluate.setEnabled(true);
					 }
				 }
			}
				if (btnEvaluate.isEnabled()){
					JOptionPane.showMessageDialog(frame1, "Successful ! \n Click Evaluate and please wait till the evaluation is complete...", "Data completed", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				 {
					 JOptionPane.showMessageDialog(frame1, "One or more selections are missing !", "Incomplete data", JOptionPane.INFORMATION_MESSAGE);
				 }
			}
		});
		
		// Evaluate button - to send values to evaluator
		btnEvaluate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	        		try {
						new UiSplashScreenEvaluationFrame(speechCorpusPathResult, speechPropertiesList, performanceListSelected, asrSystemsSelected, algorithmSelected, penaltyListSelected);
					} catch (InvocationTargetException e1) {
						e1.printStackTrace();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
			}
		});
		
		
		//***************** Actions under Criteria panel *****************//
		
		// enable false to evaluate
		comboAsrResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEvaluate.setEnabled(false);
			}
		});
		
		// enable false to evaluate
		comboAlgorithm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnEvaluate.setEnabled(false);
			}
		});
		
		// Check all - to switch off all other check boxes
		chkALL.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				btnEvaluate.setEnabled(false);
				if (e.ACTION_PERFORMED != 0 && (!chkALL.isSelected())) {
					System.out.println("action" + e.ACTION_PERFORMED);
					chkPercentHits.setSelected(false);
					chkPercentSubs.setSelected(false);
					chkPercentDel.setSelected(false);
					chkPercentIns.setSelected(false);
					chkMER.setSelected(false);
					chkWIL.setSelected(false);
					chkWER.setSelected(false);
				}
			}
		});
					
		//=================== Item listener ===================//
		
		chkALL.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				btnEvaluate.setEnabled(false);
				if (e.getStateChange() == 1) {
					chkPercentHits.setSelected(true);
					chkPercentSubs.setSelected(true);
					chkPercentDel.setSelected(true);
					chkPercentIns.setSelected(true);
					chkMER.setSelected(true);
					chkWIL.setSelected(true);
					chkWER.setSelected(true);				}
			}
		});
		
		chkPercentHits.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
				setChkAllFalse(e);
			}
		});
		
		chkPercentDel.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setChkAllFalse(e);
			}
		});
		
		chkPercentSubs.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
				setChkAllFalse(e);
			}
		});
		
		chkPercentIns.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				setChkAllFalse(e);
			}
		});

		chkWER.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
				setChkAllFalse(e);
			}
		});
		
		chkMER.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
				setChkAllFalse(e);
			}
		});	
		
		chkWIL.addItemListener(new ItemListener() {
		public void itemStateChanged(ItemEvent e) {
				setChkAllFalse(e);
			}
		});			
	}
	
/**
 * Resets check button and evaluate button
 * @param e when the event retuns 2, the item is selected
 */
	public static void setChkAllFalse(ItemEvent e){
		btnEvaluate.setEnabled(false);
		if (e.getStateChange() == 2) {
			chkALL.setSelected(false);
		}
	}

/**
 * Function returns a relative path of the file with respect to the current directory
 * @param file File for which the path is needed
 * @return Returns a relative path
 */
	public static File getRelativePath(File file) {
		String filePath = file.getAbsolutePath();
		File currentFolder = new java.io.File("");
		String folderPath = currentFolder.getAbsolutePath();
		if (filePath.startsWith(folderPath)) {
			File returnFile = new java.io.File(filePath.substring(folderPath.length() + 1));
			return returnFile;
		} else
			return null;
	}

/**
 * Set colour to buttons
 */
	public static void setDefaultColor() {
		btnDictionaryModel.setBackground(null);
		btnAcousticModel.setBackground(null);
		btnLanguageModel.setBackground(null);
	}

/**
 * Returns selected asr system
 * @param received Sets the string to the selected asr system
 */
	public static void setSelectedAsr(String received) {
		currentAsrSelected = received;
	}
}