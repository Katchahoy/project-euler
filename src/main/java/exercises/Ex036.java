package exercises;

import java.util.stream.IntStream;

import static utilities.Utilities.isPalindrome;

public class Ex036 implements Exercise {

  private static final int N = 1_000_000;

  @Override
  public void solve() {

    long sum = IntStream.range(1, N)
            .filter(this::isDoubleBasePalindrome)
            .sum();

    System.out.println(sum);
  }

  private boolean isDoubleBasePalindrome(int n) {
    return isBase10Palindrome(n) && isBase2Palindrome(n);
  }

  private boolean isBase10Palindrome(int n) {
    return isPalindrome(Integer.toString(n));
  }

  private boolean isBase2Palindrome(int n) {
    return isPalindrome(Integer.toBinaryString(n));
  }


}
