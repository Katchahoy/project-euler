package exercises;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Ex029 implements Exercise {

  @Override
  public void solve() {

    int a = 2;
    int b = 100;

    Stream<BigInteger> powerStream = StreamSupport.stream(
            Spliterators.spliteratorUnknownSize(new PowerIterator(a, b), 0),
            false
    );

    long count = powerStream
            .distinct()
            .count();

    System.out.println(count);

  }

  private static class PowerIterator implements Iterator<BigInteger> {

    int a;
    int b;

    int i = 2;
    int j = 2;

    PowerIterator(int a, int b) {
      this.a = a;
      this.b = b;
    }

    @Override
    public boolean hasNext() {
      return i <= b;
    }

    @Override
    public BigInteger next() {
      BigInteger val = BigInteger.valueOf(i).pow(j);
      j++;
      if (j > b) {
        j = 2;
        i++;
      }
      return val;
    }
  }

}
