package project.speech.userInterface;

import javax.swing.*;
import java.awt.Font;
import javax.swing.border.*;
import java.awt.Toolkit;


@SuppressWarnings("serial")
public class UiInstructionFrame2 extends JFrame {

	private JPanel contentPane;
	private JPanel panelInstructions;
	private JLabel lblSteps;
	private JPanel panelTranscript;
	private JLabel lblTranscript;
	private JPanel panel;
	private JLabel label;
	private JLabel lblwerWord;

	public UiInstructionFrame2() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(UiInstructionFrame2.class.getResource("/project/speech/userInterface/logo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 802, 653);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//=================== Panels ===================//
		
		panelInstructions = new JPanel();
		panelInstructions.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Instruction steps to use the tool", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelInstructions.setBounds(5, 30, 563, 255);
		contentPane.add(panelInstructions);
		panelInstructions.setLayout(null);
		
		panelTranscript = new JPanel();
		panelTranscript.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Transcript file example", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTranscript.setBounds(573, 30, 203, 255);
		contentPane.add(panelTranscript);
		panelTranscript.setLayout(null);
		
		//=================== Labels ===================//
		
		lblSteps = new JLabel("<html> <b>Properties pane </b> <br>\r\nStep 1: Browse the path containing the reference text file. <br>\r\nStep 2: Browse the path containing the hypothesis (transcript) text file.<br>\r\n<b> Criteria pane </b><br>\r\nStep 4: Select the alignment algorithm to be used for evaluation.<br>\r\nStep 5: Choose the performance measures to be indicated in output.<br>\r\n<b>Evaluation pane</b><br>\r\nStep 6: Click the check button to check the requirements for evaluation, if not satisfied check the missing requirements<br>\r\nStep 7: Click Evaluate button to start the evaluation (it will take some time) and the result will be displayed in a window<br>\r\nStep 8 : Click Save result button to save the result in a file (no extension is needed) - it will be saved as text file\r\n</html>");
		lblSteps.setBounds(10, 11, 543, 233);
		panelInstructions.add(lblSteps);
		lblSteps.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblSteps.setVerticalAlignment(SwingConstants.TOP);
		
		lblTranscript = new JLabel("<html>\r\nwd1 wd2 wd3. <br>\r\nwd1 wd2. <br>\r\nwd1 wd2 wd3 wd4. <br>\r\nwd1. <br>\r\nwd1 wd2. <br>\r\nwd1 wd2. <br>\r\nwd1 wd2 wd3 wd4 <br>\r\n</html>");
		lblTranscript.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblTranscript.setVerticalAlignment(SwingConstants.TOP);
		lblTranscript.setBounds(15, 20, 163, 169);
		panelTranscript.add(lblTranscript);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Performance metrics", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 296, 771, 308);
		contentPane.add(panel);
		panel.setLayout(null);
		
		label = new JLabel("<html>\r\n<h> Notations: </h> <br>\r\nH - # Correct words <br>\r\nS - # Substituted words <br>\r\nD - # Deleted words <br>\r\nI - # Inserted words <br>\r\nN - # Reference words <br>\r\n<TR> <TD> <b>%Hits</b> - Percent of correct words <TD> = \r\n<TD> <U> H </U> <br> N  <TD> * 100\r\n<br>\r\n<TR> <TD> <b>%Subs</b> - Percent of substituted words <TD> = \r\n<TD> <U> S </U> <br> N <TD> * 100\r\n<br>\r\n<TR> <TD> <b>%Del</b> - Percent of deleted words <TD> = \r\n<TD> <U> D </U> <br> N  <TD> * 100\r\n<br>\r\n<TR> <TD> <b>%Ins</b> - Percent of inserted words <TD> = \r\n<TD> <U> I </U> <br> N <TD> * 100\r\n<br>\r\n</html>");
		label.setBounds(10, 25, 350, 276);
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setFont(new Font("Rockwell", Font.PLAIN, 14));
		panel.add(label);
		
		lblwerWord = new JLabel("<html>\r\n<TR> <TD> <b>WER</b> - Word error rate <TD> = \r\n<TD> <U> S + D + I </U> <br> S + D + H  <TD> \r\n<br>\r\n<TR> <TD> <b>MER</b> - Match error rate <TD> = \r\n<TD> <U> S + D + I </U> <br> H + S + D + I  <TD> \r\n<br>\r\n<TR> <TD> <b>WIL</b> - Word Information lost  <TD> = 1 - (\r\n<TD> <U> &ensp &ensp &ensp   H * H &ensp &ensp &ensp &ensp</U> <br> (H+S+D) * (H+S+I)  <TD> )\r\n<br>\r\n</html>");
		lblwerWord.setVerticalAlignment(SwingConstants.TOP);
		lblwerWord.setFont(new Font("Rockwell", Font.PLAIN, 14));
		lblwerWord.setBounds(368, 133, 393, 144);
		panel.add(lblwerWord);
		
	}
}
