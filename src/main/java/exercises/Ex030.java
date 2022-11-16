package exercises;

public class Ex030 implements Exercise {

  @Override
  public void solve() {

    int p = 5;
    int N = 1_000_000;

    int i = 2;
    int sum = 0;
    while (i < N) {

      int sumOfPowerOfDigits = Integer.toString(i)
              .chars()
              .map(j -> j - '0')
              .map(j -> (int) Math.pow(j, p))
              .sum();

      if (sumOfPowerOfDigits == i) {
        sum += i;
      }

      i++;
    }

    System.out.println(sum);
  }

}
