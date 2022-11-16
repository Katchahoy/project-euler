package exercises;

import com.google.common.base.Preconditions;

import java.util.stream.LongStream;

public class Ex078 implements Exercise {

  private static final long N = 100;

  private static long[] coins = LongStream.range(1, N).toArray();

  @Override
  public void solve() {

    System.out.println(countCombinations(N, 0));
  }

  private long countCombinations(long n, int i) {
    Preconditions.checkArgument(n >= 0);
    Preconditions.checkArgument(i >= 0);

    if (n < coins[i]) {
      return 0;
    }

    if (n == coins[i]) {
      return 1;
    }

    int count = 0;
    int j = i;
    while (j < coins.length && coins[j] <= n) {
      if (n == coins[j]) {
        count++;
      } else {
        count += countCombinations(n - coins[j], j);
      }
      j++;
    }

    return count;
  }
}
