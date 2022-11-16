package exercises;

import org.apache.commons.collections4.map.MultiKeyMap;

public class Ex015 implements Exercise {

  private static final int N = 20;

  private static MultiKeyMap<Integer, Long> cache = new MultiKeyMap<>();

  @Override
  public void solve() {

    long count = countRoutesToExit(0, 0);
    System.out.println(count);
  }

  private static long countRoutesToExit(int i, int j) {

    if (i < 0 || j < 0 || i > N || j > N) {
      throw new IllegalArgumentException();
    }

    if (cache.containsKey(i, j)) {
      return cache.get(i, j);
    }

    if (i == N) {
      cache.put(i, j, 1L);
      return 1;
    }

    if (j == N) {
      cache.put(i, j, 1L);
      return 1;
    }

    long count = countRoutesToExit(i, j + 1) + countRoutesToExit(i + 1, j);
    cache.put(i, j, count);
    return count;
  }
}
