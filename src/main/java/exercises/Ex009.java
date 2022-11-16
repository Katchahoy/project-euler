package exercises;

public class Ex009 implements Exercise {

  @Override
  public void solve() {

    System.out.println(product());
  }

  private static int product() {

    int a = 1;
    int b;
    int c;

    while (a < 1000) {
      b = a + 1;
      while (b < 1000) {
        c = b + 1;
        while (a + b + c <= 1000) {
          if (a * a + b * b == c * c && a + b + c == 1000) {
            return a * b * c;
          }
          c++;
        }
        b++;
      }
      a++;
    }
    return 0;
  }
}
