import java.awt.Dimension;
import java.awt.Image;
import javax.swing.*;
public class Main {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		Game gamepanel = new Game();
		frame.add(gamepanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Snake");
		frame.setLocationRelativeTo(null);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		
	}

}
