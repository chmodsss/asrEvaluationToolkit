package project.speech.userInterface;

import java.awt.*;

import javax.swing.*;

import project.speech.globalAccess.Globals;

@SuppressWarnings("serial")
public class UiSplashScreenLoadingFrame extends JWindow {
	
	private int threadCount = 3000;

	    public UiSplashScreenLoadingFrame() {
			try {
				UIManager.setLookAndFeel(Globals.theme1);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	showSplash();
	    	}

	    public void showSplash()  {

	    	JPanel content = (JPanel) getContentPane();
	        content.setBackground(Color.white);

	        // Set the window's bounds, centering the window
	        int width = 510;
	        int height = 340;
	        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	        int x = (screen.width - width) / 2;
	        int y = (screen.height - height) / 2;
	        setBounds(x, y, width, height);
	        getContentPane().setLayout(null);

	        // Build the splash screen
	        JLabel label = new JLabel(new ImageIcon(UiSplashScreenLoadingFrame.class.getResource("/project/speech/userInterface/splashscreenimgstart.png")));
	        label.setBounds(20, 20, 470, 300);

	        content.add(label);

	        
	        content.setBorder(BorderFactory.createLineBorder(Globals.turquoise, 5));

	        // Display it
	        setVisible(true);
	        toFront();
	        setAlwaysOnTop(true);
	        
	        try {
				Thread.sleep(threadCount);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        setVisible(false);
	        }
}
