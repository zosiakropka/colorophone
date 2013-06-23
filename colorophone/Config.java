package colorophone;


/**
 *
 * @author Zofia Sobocinska
 */
public class Config {

  private final int BYTE_SIZE = 8;
  private int signalRate = 1000;
  private int sampleSizeInBytes = 1;
  private int sample2signal = 16;

  public Config() {
  }

  public Config(int sampleSizeInBytes, int signalRate,
    int sample2signal) {
    this.signalRate = signalRate;
    this.sampleSizeInBytes = sampleSizeInBytes;
    this.sample2signal = sample2signal;
  }

  public int signalRate() {
    return signalRate;
  }

  public int sampleRate() {
    return sample2signal * signalRate;
  }

  public int signalStepMs() {
    return 1000 / signalRate();
  }

  public int sampleStepMs() {
    return 1000 / sampleRate();
  }

  public double signalStep() {
    return (double) signalStepMs() / 1000.0;
  }

  public double sampleStep() {
    return (double) sampleStepMs() / 1000.0;
  }


  public int sampleSizeInBytes() {
    return sampleSizeInBytes;
  }

  public int sampleSizeInBits() {
    return sampleSizeInBytes * BYTE_SIZE;
  }
  
  public int signalSizeInBytes() {
    return sampleSizeInBytes() * sample2signal;
  }

  public int sample2signal() {
    return sample2signal;
  }
}
