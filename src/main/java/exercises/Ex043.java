package exercises;

import utilities.PrimeSupplier;

public class Ex043 implements Exercise {

  @Override
  public void solve() {

    long n = 1460357289L
            + 4160357289L
            + 1406357289L
            + 4106357289L
            + 1430952867L
            + 4130952867L;
    System.out.println(n);/*
    List<Integer> digits = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);

    PermutationIterator<Integer> permutationIterator = new PermutationIterator<>(digits);

    long sum = 0;

    while (permutationIterator.hasNext()) {

      CharSequence current = getAsString(permutationIterator.next());
      if (testProperty(current)) {
        sum += Integer.parseInt(current, 0, 9, 10);
      }
    }
    System.out.println(sum);
  }

  private static CharSequence getAsString(List<Integer> digits) {

    return digits.stream()
            .map(String::valueOf)
            .collect(Collectors.joining());*/
  }

  private boolean testProperty(CharSequence pandigital) {

    PrimeSupplier primeSupplier = new PrimeSupplier();

    int i = 1;

    while (i < 9) {
      int sub = Integer.parseInt(pandigital, i, i + 3, 10);
      int prime = (int) primeSupplier.getAsLong();
      if (sub % prime != 0) {
        return false;
      }
      i++;
    }

    return true;
  }
}
