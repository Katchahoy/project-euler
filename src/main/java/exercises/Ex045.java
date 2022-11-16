package exercises;

public class Ex045 implements Exercise {

  @Override
  public void solve() {

    long i = 143 + 1;

    long hi;
    do {
      hi = i * (2 * i - 1);
      i++;
    } while (!isPentagonal(hi));

    System.out.println(hi);
  }

  private boolean isPentagonal(long n) {
    long delta = 1 + 24 * n;
    long sqrt = (long) Math.sqrt(delta);
    return sqrt * sqrt == delta && (1 + sqrt) % 6 == 0;
  }
}
