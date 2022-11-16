package utilities;

import java.math.BigInteger;
import java.util.function.Supplier;

public class FibonacciSupplier2 implements Supplier<BigInteger> {

  private BigInteger previous = BigInteger.ZERO;
  private BigInteger current = BigInteger.ONE;

  @Override
  public BigInteger get() {
    BigInteger val = current;
    current = current.add(previous);
    previous = val;
    return val;
  }
}
