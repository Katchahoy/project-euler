package exercises;

import utilities.PrimeSupplier;

import static utilities.Utilities.isPrime;

public class Ex050 implements Exercise {

  private static final long MAX = 1_000_000;

  @Override
  public void solve() {

    long topCount = 0;
    long topPrime = 0;
    long i = 2;

    while (i < MAX) {

      PrimeSupplier primeSupplier = new PrimeSupplier(i);

      long sum = 0;
      long count = 0;

      while (sum < MAX) {

        sum += primeSupplier.getAsLong();
        count++;

        if (count > topCount && sum < MAX && isPrime(sum)) {
          topCount = count;
          topPrime = sum;
        }
      }

      i++;
    }

    System.out.println(topPrime + " : " + topCount);
  }
}
