package objects;

public class Timer extends GameObject {
  private boolean countsDown;
  private double initialTime;

  public Timer() {
    this.countsDown = true;
    this.initialTime = 60;
  }

  public void timerCountsDown(boolean b) {
    this.countsDown = b;
  }

  public boolean start() {
    // TODO
    return true;
  }

  public boolean reset() {
    // TODO
    return true;
  }

  public float pause() {
    // TODO
    return 1;
  }

  public float stop() {
    // TODO
    return 1;
  }

  public float getTime() {
    // TODO
    return 1;
  }

  public void setInitialTime(double initialTime) {
    this.initialTime = initialTime;
  }

  public double getInitialTime() {
    return this.initialTime;
  }
}
