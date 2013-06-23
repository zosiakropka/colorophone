package colorophone;


import javax.sound.sampled.*;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;

public class CaptureSound
  implements Runnable {
  // record duration, in milliseconds

  // path of the wav file
  File wavFile = new File("/home/politechnika/Desktop/test.wav");
  // format of audio file
  private final AudioFileFormat.Type fileType =
    AudioFileFormat.Type.WAVE;
  // the line from which audio data is captured
  private TargetDataLine line;
  private AudioInputStream ais;
  private Config config;

  public CaptureSound(Config config) {
    this.config = config;
  }

  /**
   * Defines an audio format
   */
  AudioFormat getAudioFormat() {
    float sampleRate = config.sampleRate();
    int sampleSizeInBits = config.sampleSizeInBits();
    int channels = 1;
    boolean signed = true;
    boolean bigEndian = true;
    AudioFormat format = new AudioFormat(sampleRate, sampleSizeInBits,
      channels, signed, bigEndian);
    return format;
  }

  /**
   * Captures the sound and record into a WAV file
   */
  @Override
  public void run() {
    try {
      AudioFormat format = getAudioFormat();
      DataLine.Info info = new DataLine.Info(TargetDataLine.class,
        format);

      // checks if system supports the data line
      if (!AudioSystem.isLineSupported(info)) {
        System.err.println("Line not supported");
        System.exit(0);
      }
      line = (TargetDataLine) AudioSystem.getLine(info);
      line.open(format);
      line.start();   // start capturing

      System.out.println("Start capturing...");

      ais = new AudioInputStream(line);

      AudioSystem.write(ais, fileType, wavFile);

    } catch (LineUnavailableException | IOException ex) {
      System.err.println(ex);
    }
  }

  public byte[] getBytes()
    throws IOException {
    byte[] bytes = new byte[config.sample2signal() * config.
      sampleSizeInBytes()];
    if (ais != null) {
      ais.read(bytes);
    }
    return bytes;
  }

  public int[] getInts()
    throws IOException {
    IntBuffer intBuf =
      ByteBuffer.wrap(getBytes())
      .order(ByteOrder.BIG_ENDIAN)
      .asIntBuffer();
    int[] array = new int[intBuf.remaining()];
    intBuf.get(array);
    return array;
  }

  /**
   * Closes the target data line to finish capturing and recording
   */
  public void finish() {
    line.stop();
    line.close();
    System.out.println("Finished");
  }
}