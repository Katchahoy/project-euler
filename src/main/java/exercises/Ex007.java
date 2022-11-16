package exercises;

import utilities.PrimeSupplier;

import java.util.stream.LongStream;

public class Ex007 implements Exercise {

  @Override
  public void solve() {

    LongStream.generate(new PrimeSupplier())
            .limit(10001)
            .max()
            .ifPresent(System.out::println);
  }
}
