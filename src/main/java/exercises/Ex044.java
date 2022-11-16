package exercises;

public class Ex044 implements Exercise {

  private static final long MAX = 100_000_000L;

  @Override
  public void solve() {

    long min = 10_000_000;

    long i = 1;
    while (i < MAX) {

      long pi = getPentagonal(i);

      long j = i + 1;

      long pj;
      long pDif;
      do {
        pj = getPentagonal(j);

        if (isPentagonal(pDif = pj - pi) && isPentagonal(pi + pj)) {
          if (pDif < min) {
            min = pDif;
          }
        }

        j++;

      } while (pDif < min);

      i++;
    }

    System.out.println(min);
  }

  private long getPentagonal(long n) {
    return n * (3 * n - 1) / 2;
  }

  private boolean isPentagonal(long n) {
    long pn = 1 + 24 * n;
    long sqrt = (long) Math.sqrt(pn);
    return (sqrt * sqrt == pn && (sqrt + 1) % 6 == 0);
  }
}