package exercises;

public class Ex066 implements Exercise {

  private static final long N = 1_000;

  @Override
  public void solve() {

    long max = 0;
    for (long i = 2; i <= N; i++) {
      if (isSquare(i)) {
        continue;
      }
      long j = 1;
      while (true) {
        long value = 1 + i * j * j;
        if (isSquare(value)) {
          long sqrt = (int) Math.sqrt(value);
          if (sqrt > max) {
            max = sqrt;
          }
          System.out.println(sqrt + "²-" + i + "x" + j + "²=1");
          break;
        }
        j++;
      }
    }
    System.out.println(max);
  }

  public boolean isSquare(long n) {
    long sqrt = (long) Math.sqrt(n);
    return sqrt * sqrt == n;
  }
}
