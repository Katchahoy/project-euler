package exercises;

public class Ex040 implements Exercise {

  private static final int N = 1_000_000;

  @Override
  public void solve() {

    int i = 1;
    StringBuilder builder = new StringBuilder();
    while (builder.length() < N) {
      builder.append(i++);
    }
    int product = 1;
    i = 1;
    while (i <= N) {
      product *= builder.charAt(i - 1) - '0';
      i *= 10;
    }
    System.out.println(product);
  }
}
