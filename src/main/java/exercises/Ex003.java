package exercises;

public class Ex003 implements Exercise {

  @Override
  public void solve() {

    long n = 600_851_475_143L;
    long i = 1;
    long max = 1;

    while (i <= n) {
      if (n % i == 0) {
        max = i;
        n /= i;
      }
      i++;
    }

    System.out.println(max);
  }
}
