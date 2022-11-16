package utilities;

import static utilities.Utilities.sumOfDivisors;

public enum NumberDivisorType {
  PERFECT,
  ABUNDANT,
  DEFICIENT;

  public static NumberDivisorType resolve(int n) {

    int sumOfDivisors = sumOfDivisors(n);

    if (n < sumOfDivisors) {
      return NumberDivisorType.ABUNDANT;

    } else if (n > sumOfDivisors) {
      return NumberDivisorType.DEFICIENT;

    } else {
      return NumberDivisorType.PERFECT;

    }
  }
}
