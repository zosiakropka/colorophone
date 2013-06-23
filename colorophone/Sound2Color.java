package colorophone;


import java.awt.Color;
import java.io.IOException;
import java.util.Arrays;
import java.util.TimerTask;

/**
 *
 * @author Zofia Sobocinska
 */
public class Sound2Color
  extends TimerTask {

  CaptureSound recorder;
  ColorWindow colorWindow;

  private static final double expRange = Math.exp(1.0) - 1.0;
  
  public Sound2Color(CaptureSound recorder, ColorWindow colorWindow) {
    this.recorder = recorder;
    this.colorWindow = colorWindow;
  }

  @Override
  public void run() {

    try {
      byte[] values = recorder.getBytes();
      colorWindow.setBackground(toColor(maximum(values)));
    } catch (IOException ex) {
      System.err.println(ex);
    }
  }

  private static byte maximum(byte[] array) {
    Arrays.sort(array);
    byte max = (byte) Math.abs(array[array.length - 1]);
    byte min = (byte) Math.abs(array[0]);
    return (max > min ? max : min);
  }

  private static Color toColor(byte val) {
//    double norm =  (val>64)?0.0:((double) (64 - val) / (double) 64);
    double norm = ((double) (128 - val) / (double) 128);
    float hue;
//    hue = (float) norm / 2;
    hue = (float) (norm * 0.75);
//    hue = (float) ((Math.exp(norm) - 1.0)/expRange) / 2;
    
    return Color.getHSBColor(hue, 1, (float) 0.9);
  }
}
