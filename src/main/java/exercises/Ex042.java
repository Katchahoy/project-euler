package exercises;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Ex042 implements Exercise {

  @Override
  public void solve() {

    Path path = Paths.get("p042_words.txt");

    try (BufferedReader reader = Files.newBufferedReader(path)) {

      long time0 = System.currentTimeMillis();

      String input = reader.readLine();
      String wordStr = StringUtils.remove(input, '\"');
      String[] words = StringUtils.split(wordStr, ',');

      long count = Stream.of(words)
              .mapToInt(word -> word.chars().map(i -> i - 'A' + 1).sum())
              .filter(this::isTriangle)
              .count();

      System.out.println(count);

      long time1 = System.currentTimeMillis() - time0;
      System.out.println("Time: " + time1 + " ms");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private boolean isTriangle(int i) {
    int delta = 8 * i + 1;
    int sqrt = (int) Math.sqrt(delta);
    return sqrt * sqrt == delta;
  }
}
