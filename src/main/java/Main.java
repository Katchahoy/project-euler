import exercises.*;

public class Main {

  public static void main(String[] args) {

    Exercise exercise = new Ex070();

    long time0 = System.currentTimeMillis();

    exercise.solve();

    long time1 = System.currentTimeMillis() - time0;
    System.err.println("Time: " + time1 + " ms");
  }
}
