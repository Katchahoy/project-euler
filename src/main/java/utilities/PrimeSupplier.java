package utilities;

import java.util.function.LongSupplier;

import static utilities.Utilities.isPrime;

public class PrimeSupplier implements LongSupplier {

  private long current;

  public PrimeSupplier() {
    this.current = 2;
  }

  public PrimeSupplier(long current) {
    this.current = current;
  }

  @Override
  public long getAsLong() {
    while (!isPrime(current)) {
      current++;
    }
    long val = current;
    current++;
    return val;
  }
}
