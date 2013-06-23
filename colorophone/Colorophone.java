package colorophone;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Colorophone {

  private static Config config = new Config();
  final long RECORD_TIME = 5000;  // 1 minute

  /**
   * Entry to run the program
   */
  public static void main(String[] args) {

    final CaptureSound recorder = new CaptureSound(config);
    ColorWindow colorWindow = new ColorWindow(recorder);

    /////////////////////////////////////////////////////////////

    // start Capturing
    (new Thread(recorder)).start();
    TimerTask s2cTask = new Sound2Color(recorder, colorWindow);

    Timer s2cTimer = new Timer();
    s2cTimer.schedule(s2cTask, new Date(), config.signalStepMs());
  }
}