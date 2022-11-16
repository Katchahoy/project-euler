package exercises;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

public class Ex022 implements Exercise {

  @Override
  public void solve() {

    Path path = Paths.get("p022_names.txt");

    String[] names;

    try (BufferedReader reader = Files.newBufferedReader(path)) {

      String input = reader.readLine();
      String nameStr = StringUtils.remove(input, '\"');
      names = StringUtils.split(nameStr, ',');
      Arrays.sort(names);

      long totalScore = 0;
      int i = 1;

      for (String name : names) {
        long score = nameScore(name);
        totalScore += i * score;
        i++;
      }

      System.out.println(totalScore);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static long nameScore(String name) {
    Objects.requireNonNull(name);

    return name.chars()
            .filter(i -> i >= 'A' && i <= 'Z')
            .map(i -> i - 'A' + 1)
            .sum();
  }
}
