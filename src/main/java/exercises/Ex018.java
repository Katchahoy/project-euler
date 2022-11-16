package exercises;

import com.google.common.base.Preconditions;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Ex018 implements Exercise {

  @Override
  public void solve() {

    Path path = Paths.get("p018_pyramid.txt");

    try (Scanner scanner = new Scanner(path)) {

      int line = 1;
      int count = 0;

      int[] current = new int[count + 1];
      int[] previous = new int[count];

      while (scanner.hasNextInt()) {
        current[count] = scanner.nextInt();

        if (++count % line == 0) {
          sumLines(previous, current);

          previous = current;
          current = new int[count + 1];
          count = 0;
          line++;
        }
      }

      Arrays.stream(previous)
              .max()
              .ifPresent(System.out::println);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private static void sumLines(int[] previous, int[] current) {
    Preconditions.checkState(current.length == 1 + previous.length);

    if (previous.length == 0) {
      return;
    }

    for (int i = 0; i < current.length; i++) {
      int left = i > 0 ? previous[i - 1] : 0;
      int right = i < current.length - 1 ? previous[i] : 0;
      current[i] += Math.max(left, right);
    }
  }
}
