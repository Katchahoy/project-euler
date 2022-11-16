package exercises;

import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static utilities.Utilities.isPalindrome;

public class Ex055 implements Exercise {

  private static final int N = 10_000;

  private final Set<String> notLychrel = new TreeSet<>();

  @Override
  public void solve() {

    int count = N;

    for (int i = 1; i < N; i++) {

      String s = Integer.toString(i);

      if (isLychrel(s)) {
        count--;
      }

      i++;
    }

    System.out.println(count);
    System.out.println(10000 - notLychrel.size());
  }

  private boolean isLychrel(String s) {

    Set<String> candidates = new HashSet<>();
    for (int j = 0; j < 50; j++) {

      String rev = StringUtils.reverse(s);
      if (!StringUtils.containsOnly(rev, "0")) {
        rev = StringUtils.stripStart(rev, "0");
      }

      candidates.add(s);
      candidates.add(rev);

      if (notLychrel.contains(s) || isPalindrome(s)) {
        notLychrel.addAll(candidates);
        return false;
      }

      s = flipAndAdd(s);

      j++;
    }
    return true;
  }

  private String flipAndAdd(String s) {

    StringBuilder builder = new StringBuilder();

    int len = s.length();
    int r = 0;

    for (int i = 0; i < len; i++) {
      int n = r + s.charAt(i) - '0' + s.charAt(len - 1 - i) - '0';
      r = n / 10;
      builder.append(n % 10);
    }

    if (r > 0) {
      builder.append(r);
    }

    return StringUtils.reverse(builder.toString());
  }
}
