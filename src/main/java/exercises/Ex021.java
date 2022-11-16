package exercises;

import static utilities.Utilities.sumOfDivisors;

public class Ex021 implements Exercise {

  @Override
  public void solve() {

    int N = 10_000;
    int sum = 0;

    for (int i = 1; i <= N - 1; i++) {
      int j = sumOfDivisors(i);
      if (i < j && i == sumOfDivisors(j)) {
        sum += (i + j);
      }
    }

    System.out.println(sum);
  }
}
