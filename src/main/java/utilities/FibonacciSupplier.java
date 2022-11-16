package utilities;

import java.util.function.LongSupplier;

public class FibonacciSupplier implements LongSupplier {

  private int previous = 0;
  private int current = 1;

  @Override
  public long getAsLong() {
    int val = current;
    current += previous;
    previous = val;
    return val;
  }
}
