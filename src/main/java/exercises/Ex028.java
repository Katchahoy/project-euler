package exercises;

public class Ex028 implements Exercise {

  @Override
  public void solve() {

    int N = 1001;

    long sum = 1;
    long i = 1;
    long k = 1;

    while (i <= N / 2) {

      long j = 0;
      while (j < 4) {
        k += (2 * i);
        sum += k;
        j++;
      }

      i++;
    }

    System.out.println(sum);
  }
}
