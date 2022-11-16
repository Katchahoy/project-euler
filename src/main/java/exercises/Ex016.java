package exercises;

import java.math.BigInteger;

import static utilities.Utilities.sumDigits;

public class Ex016 implements Exercise {

  @Override
  public void solve() {

    System.out.println(sumDigits(BigInteger.TWO.pow(1000)));
  }


}
