package exercises;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Set;

public class Ex059 implements Exercise {

  private final Set<String> words = Sets.newHashSet(
          "the",
          "be",
          "to",
          "of",
          "and",
          "in",
          "that",
          "have",
          "it",
          "for",
          "not",
          "on",
          "with",
          "he",
          "as",
          "you",
          "do",
          "at",
          "this",
          "but",
          "his",
          "by",
          "from",
          "they",
          "we",
          "say",
          "her",
          "she",
          "or",
          "an",
          "will",
          "my",
          "one",
          "all",
          "would",
          "there",
          "their",
          "what",
          "so",
          "up",
          "out",
          "if",
          "about",
          "who",
          "get",
          "which",
          "go",
          "me",
          "when",
          "make",
          "can",
          "like",
          "time",
          "no",
          "just",
          "him",
          "know",
          "take",
          "people",
          "into",
          "year",
          "your",
          "good",
          "some",
          "could",
          "them",
          "see",
          "other",
          "than",
          "then",
          "now",
          "look",
          "only",
          "come",
          "its",
          "over",
          "think",
          "also",
          "back",
          "after",
          "use",
          "two",
          "how",
          "our",
          "work",
          "first",
          "well",
          "way",
          "even",
          "new",
          "because",
          "any",
          "these",
          "give",
          "day",
          "most",
          "us"
  );

  @Override
  public void solve() {

    Path path = Paths.get("p059_cipher.txt");
    try (BufferedReader reader = Files.newBufferedReader(path)) {

      String line = reader.readLine();
      String[] asciis = StringUtils.split(line, ',');
      int length = asciis.length;
      byte[] bytes = new byte[length];
      for (int i = 0; i < length; i++) {
        bytes[i] = Byte.parseByte(asciis[i]);
      }

      while (true) {

        byte[] key = new byte[3];
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
          key[i] = (byte) ('a' + random.nextInt(26));
        }

        byte[] xor = new byte[length];
        for (int i = 0; i < length; i++) {
          xor[i] = (byte) (bytes[i] ^ key[i % 3]);
        }

        String message = new String(xor);

        String toTest = message.toLowerCase();

        int count = 0;
        for (String word : words) {
          count += StringUtils.countMatches(toTest, word);
        }
        if (count > 150) {
          System.err.println(new String(key));
          System.err.println(message);
          long sum = message.chars().sum();
          System.out.println(sum);
          break;
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
