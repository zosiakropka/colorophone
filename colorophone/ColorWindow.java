package colorophone;


import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author Zofia Sobocinska
 */
public class ColorWindow {

	private JFrame frame;

	public ColorWindow(CaptureSound recorder) {
    createFrame();
	}
  
  private void createFrame() {
		frame = new JFrame("Colorofon");
		frame.setSize(400, 400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
  
  public void setBackground(Color color) {
    frame.getContentPane().setBackground(color);
  }
}
