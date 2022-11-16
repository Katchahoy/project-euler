package exercises;

import org.apache.commons.lang3.mutable.MutableInt;

import java.util.HashMap;
import java.util.Map;

public class Ex039bis implements Exercise {

  @Override
  public void solve() {

    Map<Integer, MutableInt> countByPerimeter = new HashMap<>();

    int topCount = 0;
    int topPerimeter = 0;

    for (int p = 4; p < 1000; p += 2) {
      for (int a = 1; a < p / 3; a++) {
        int i1 = p * (p - 2 * a);
        int i2 = 2 * (p - a);
        if (i1 % i2 == 0) {
          int count = countByPerimeter.computeIfAbsent(p, i -> new MutableInt()).incrementAndGet();
          if (count > topCount) {
            topCount = count;
            topPerimeter = p;
          }
        }
      }
    }
    System.out.println(topPerimeter);
  }
}
