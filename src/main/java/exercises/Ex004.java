package exercises;

import static utilities.Utilities.isPalindrome;

public class Ex004 implements Exercise {

  @Override
  public void solve() {

    int a = 100;
    int b;

    int max = 0;

    while (a <= 999) {
      b = a;
      while (b <= 999) {
        int product = a * b;
        if (product > max && isPalindrome(product)) {
          max = product;
        }
        b++;
      }
      a++;
    }

    System.out.println(max);
  }


}
