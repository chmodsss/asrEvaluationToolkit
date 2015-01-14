package project.speech.userInterface;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.awt.Toolkit;


@SuppressWarnings("serial")
public class UiInstructionFrame1 extends JFrame {

	private JPanel contentPane;
	private JPanel panelTree;
	private JPanel panelSteps;
	private JLabel lblSteps;
	private JPanel panelNote;
	private JLabel lblNote;
	private JPanel panelTranscript;
	private JLabel lblTranscript;
	@SuppressWarnings("unused")
	private BufferedImage myPicture = null;
	private JLabel lblPerformance2;

	public UiInstructionFrame1() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UiInstructionFrame1.class.getResource("/project/speech/userInterface/logo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 200, 1024, 708);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//=================== Panels ===================//
		
		panelTree = new JPanel();
		panelTree.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Speech database structure", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTree.setBounds(10, 371, 271, 174);
		contentPane.add(panelTree);
		panelTree.setLayout(null);
		JLabel picTree = new JLabel(new ImageIcon(UiInstructionFrame1.class.getResource("/project/speech/userInterface/tree.png")));
		picTree.setBounds(10, 22, 224, 147);
		panelTree.add(picTree);
		picTree.setText("");
		
		panelSteps = new JPanel();
		panelSteps.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Instruction steps to use the tool", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSteps.setBounds(10, 30, 515, 330);
		contentPane.add(panelSteps);
		panelSteps.setLayout(null);
		
		panelTranscript = new JPanel();
		panelTranscript.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Transcript file example", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTranscript.setBounds(291, 371, 235, 174);
		contentPane.add(panelTranscript);
		panelTranscript.setLayout(null);

		//=================== Labels ===================//
		
		lblSteps = new JLabel("<html> <b>Properties pane </b> <br>\r\nStep 1: Browse the path containing the speech database using Speech Corpus button (see NOTE)<br>\r\nStep 2: Choose one of the speech recognition systems from the dropdown list. <br>\r\nStep 3: Select the respective model files (dictionary, language and acoustic). - hover over the buttons for the file paths<br>\r\n<b> Result choice pane </b><br>\r\nStep 4: Select the speech engines to be evaluated for output.<br>\r\nStep 5: Choose the performance measures to be indicated in output.<br>\r\n<b>Evaluation pane</b><br>\r\nStep 6: Click the check button to check the requirements for evaluation, if not satisfied check the missing requirements<br>\r\nStep 7: Click Evaluate button to start the evaluation (it will take some time) and the result is displayed in a window <br>\r\nStep 8 : Click Save result button to save the result in a file (no extension is needed) - it will be saved as text file\r\n</html>");
		lblSteps.setBounds(10, 11, 494, 308);
		panelSteps.add(lblSteps);
		lblSteps.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblSteps.setVerticalAlignment(SwingConstants.TOP);
		
		lblTranscript = new JLabel("<html>\r\nspeech1 wd1 wd2 wd3. <br>\r\nspeech2 wd1 wd2. <br>\r\nspeech3 wd1 wd2 wd3 wd4. <br>\r\nspeech4 wd1. <br>\r\nspeech5 wd1 wd2. <br>\r\nspeech6 wd1 wd2. <br>\r\nspeech7 wd1 wd2 wd3 wd4 <br>\r\n</html>");
		lblTranscript.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblTranscript.setVerticalAlignment(SwingConstants.TOP);
		lblTranscript.setBounds(15, 20, 209, 169);
		panelTranscript.add(lblTranscript);
		
		JPanel panelPerformance = new JPanel();
		panelPerformance.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Performance metrics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelPerformance.setBounds(535, 30, 463, 512);
		contentPane.add(panelPerformance);
		panelPerformance.setLayout(null);
		
		JLabel lblPerformance1 = new JLabel("<html>\r\n<h> Notations: </h> <br>\r\nH - # Correct words <br>\r\nS - # Substituted words <br>\r\nD - # Deleted words <br>\r\nI - # Inserted words <br>\r\nN - # Reference words <br>\r\n<TR> <TD> <b>%Hits</b> - Percent of correct words <TD> = \r\n<TD> <U> H </U> <br> N  <TD> * 100\r\n<br>\r\n<TR> <TD> <b>%Subs</b> - Percent of substituted words <TD> = \r\n<TD> <U> S </U> <br> N <TD> * 100\r\n<br>\r\n<TR> <TD> <b>%Del</b> - Percent of deleted words <TD> = \r\n<TD> <U> D </U> <br> N  <TD> * 100\r\n<br>\r\n<TR> <TD> <b>%Ins</b> - Percent of inserted words <TD> = \r\n<TD> <U> I </U> <br> N <TD> * 100\r\n<br>\r\n</html>");
		lblPerformance1.setVerticalAlignment(SwingConstants.TOP);
		lblPerformance1.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblPerformance1.setBounds(10, 21, 432, 276);
		panelPerformance.add(lblPerformance1);
		
		lblPerformance2 = new JLabel("<html>\r\n<TR> <TD> <b>WER</b> - Word error rate <TD> = \r\n<TD> <U> S + D + I </U> <br> S + D + H  <TD> \r\n<br>\r\n<TR> <TD> <b>MER</b> - Match error rate <TD> = \r\n<TD> <U> S + D + I </U> <br> H + S + D + I  <TD> \r\n<br>\r\n<TR> <TD> <b>WIL</b> - Word Information lost  &emsp &ensp &ensp  &emsp<TD> = 1 - (\r\n<TD> <U> &ensp &ensp &ensp   H * H &ensp &ensp &ensp &ensp</U> <br> (H+S+D) * (H+S+I)  <TD> )\r\n<br>\r\n</html>");
		lblPerformance2.setVerticalAlignment(SwingConstants.TOP);
		lblPerformance2.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblPerformance2.setBounds(10, 308, 443, 144);
		panelPerformance.add(lblPerformance2);
		
		panelNote = new JPanel();
		panelNote.setBounds(7, 556, 991, 112);
		contentPane.add(panelNote);
		panelNote.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "NOTE", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelNote.setLayout(null);
		
		lblNote = new JLabel("<html>\r\n1. Speech corpus can have multiple folders, where each folder should contain two subfolders <b>wav</b> and <b>etc</b> (Refer the tree).<br>\r\n2. Transcription of all the files are stored in a single file \"prompts-original\" with each line consisting of audio file name followed by  the spoken text (eg: wd1 wd2 wd3 ,...) <br>\r\n3. cmu-sphinx should be configured with dictionary model, language model and acoustic model <br>\r\n4. Ispeech do not need any models for recognition\r\n</html>");
		lblNote.setVerticalAlignment(SwingConstants.TOP);
		lblNote.setBounds(10, 11, 960, 90);
		panelNote.add(lblNote);
		lblNote.setFont(new Font("Rockwell", Font.PLAIN, 14));
	}
}
