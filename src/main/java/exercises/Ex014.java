package exercises;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.LongStream;

public class Ex014 implements Exercise {

  private static Map<Long, Long> cache = new ConcurrentHashMap<>();

    @Override
    public void solve() {

    int N = 1_000_000;

    LongStream.range(1, N)
            .parallel()
            .mapToObj(j -> new Long[] { j, collatzLength(j) })
            .reduce((a, b) -> a[1] > b[1] ? a : b)
            .ifPresent(longs -> System.out.println(longs[0]));
  }

  private static long collatzLength(long seed) {
    if (seed < 1) {
      throw new IllegalArgumentException();
    }

    if (cache.containsKey(seed)) {
      return cache.get(seed);
    }

    if (seed == 1) {
      cache.putIfAbsent(1L, 0L);
      return 0;
    }

    long len = 1 + collatzLength(seed % 2 == 0 ? seed / 2 : 3 * seed + 1);
    cache.putIfAbsent(seed, len);
    return len;
  }
}
