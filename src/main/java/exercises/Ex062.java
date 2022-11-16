package exercises;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Ex062 implements Exercise {

  @Override
  public void solve() {

    long i = 345;


    while (true) {

      long cube = i * i * i;
      String s = Long.toString(cube);
      int len = s.length();
      long count = LongStream.iterate(i, j -> j + 1)
              .mapToObj(j -> Long.toString(j * j * j))
              .takeWhile(str -> str.length() == len)
              .filter(str -> isAnagram(str, s))
              .count();
      if (count == 5) {
        System.out.println(cube);
        break;
      }
      i++;
    }
  }

  private boolean isAnagram(String s1, String s2) {
    if (s1.length() != s2.length()) {
      return false;
    }
    char[] a1 = s1.toCharArray();
    char[] a2 = s2.toCharArray();
    Arrays.sort(a1);
    Arrays.sort(a2);
    return IntStream.range(0, s1.length())
            .allMatch(i -> a1[i] == a2[i]);
  }
}
