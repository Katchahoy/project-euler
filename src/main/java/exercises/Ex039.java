package exercises;

import org.apache.commons.lang3.mutable.MutableInt;

import java.util.HashMap;
import java.util.Map;

public class Ex039 implements Exercise {

  @Override
  public void solve() {

    Map<Integer, MutableInt> countByPerimeter = new HashMap<>();

    int topCount = 0;
    int topPerimeter = 0;

    for (int a = 1; a < 1000 / 3; a++) {
      for (int b = a; b < (1000 - a) / 2 - 1; b++) {
        int sumOfSquares = a * a + b * b;
        int c = (int) Math.sqrt(sumOfSquares);
        if (sumOfSquares != c * c) {
          continue;
        }
        int p = a + b + c;
        if (p > 1000) {
          break;
        }
        MutableInt count = countByPerimeter.computeIfAbsent(p, i -> new MutableInt());
        count.increment();
        Integer value = count.getValue();
        if (value > topCount) {
          topCount = value;
          topPerimeter = p;
        }
      }
    }
    System.out.println(topPerimeter);
  }
}
