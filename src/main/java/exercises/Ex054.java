package exercises;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.MutablePair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ex054 implements Exercise {

  @Override
  public void solve() {

    Path path = Paths.get("p054_poker.txt");
    try (Stream<String> lines = Files.lines(path)) {
      long count = lines
  //            .peek(System.err::println)
              .map(line -> StringUtils.split(line, ' '))
              .map(Duel::parse)
  //            .peek(duel -> System.err.println(duel.getLeft().bestFigure() + " " + duel.getRight().bestFigure()))
  //            .peek(duel -> System.err.println(duel.getLeft().bestFigure().compareTo(duel.getRight().bestFigure())))
              .filter(duel -> duel.getLeft().bestFigure().compareTo(duel.getRight().bestFigure()) > 0)
              .count();

      System.out.println(count);

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static class Duel extends MutablePair<Hand, Hand> {

    public Duel(Hand left, Hand right) {
      super(left, right);
    }

    public static Duel parse(String[] cards) {
      Preconditions.checkArgument(cards.length == 10);
      int i = 0;
      Hand hand1 = new Hand();
      Hand hand2 = new Hand();
      for (String str : cards) {
        Preconditions.checkArgument(str.length() == 2);
        Card card = new Card(str.charAt(0), str.charAt(1));
        if (i < 5) {
          hand1.add(card);
        } else {
          hand2.add(card);
        }
        i++;
      }
      return new Duel(hand1, hand2);
    }

    @Override
    public String toString() {
      return left.toString() + " vs " + right.toString();
    }
  }

  public static class Hand extends TreeSet<Card> {

    public Hand() {
      super(Comparator
              .comparing(Card::getValue)
              .reversed()
              .thenComparing(Card::getColor));
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder();
      forEach(card -> builder.append(card).append(' '));
      builder.deleteCharAt(builder.length() - 1);
      return builder.toString();
    }

    public Figure2 bestFigure() {
      boolean isFlush = isFlush();
      boolean isStraight = isStraight();
      if (isFlush && isStraight) {
        return new StraightFlush(first().getValue());
      }
      Map<Integer, Long> valuesCountMap = stream().collect(Collectors.groupingBy(
              Card::getValue,
              LinkedHashMap::new,
              Collectors.counting())
      );
      List<Integer> distinctValuesList = valuesCountMap.entrySet().stream()
              .sorted(Entry.<Integer, Long>comparingByValue().reversed())
              .map(Entry::getKey)
              .collect(Collectors.toList());

      if (valuesCountMap.size() == 2) {
        return getMaxCount(valuesCountMap) == 4
                ? new FourOfAKind(first().getValue(), last().getValue())
                : new FullHouse(first().getValue(), last().getValue());
      }
      if (isFlush) {
        List<Integer> valueList = stream().map(Card::getValue).collect(Collectors.toList());
        return new Flush(valueList);
      }
      if (isStraight) {
        return new Straight(first().getValue());
      }
      long maxCount = getMaxCount(valuesCountMap);
      if (maxCount == 3) {
        return new ThreeOfAKind(
                distinctValuesList.get(0),
                distinctValuesList.get(1),
                distinctValuesList.get(2)
        );
      }
      if (valuesCountMap.size() == 3) {
        return new TwoPairs(
                distinctValuesList.get(0),
                distinctValuesList.get(1),
                distinctValuesList.get(2)
        );
      }
      if (maxCount == 2) {
        return new OnePair(
                distinctValuesList.get(0),
                distinctValuesList.stream().skip(1).collect(Collectors.toSet())
        );
      }
      return new HighCard(stream().map(Card::getValue).collect(Collectors.toSet()));
    }

    private long getMaxCount(Map<Integer, Long> valuesCountMap) {
      var bestEntry = valuesCountMap.entrySet().stream()
              .max(Entry.comparingByValue());
      assert bestEntry.isPresent();
      return bestEntry.get().getValue();
    }

    public boolean isFlush() {
      return this.stream().map(Card::getColor).distinct().count() == 1;
    }

    public boolean isStraight() {
      Iterator<Card> iterator = this.iterator();
      Card previous = iterator.next();
      Card current;
      while (iterator.hasNext()) {
        current = iterator.next();
        if (previous.getValue() - current.getValue() != 1) {
          return false;
        }
        previous = current;
      }
      return true;
    }
  }

  public static class Card {

    private final char value;
    private final char color;

    private Card(char value, char color) {
      this.value = value;
      this.color = color;
    }

    public int getValue() {
      switch (value) {
        case 'A':
          return 14;
        case 'K':
          return 13;
        case 'Q':
          return 12;
        case 'J':
          return 11;
        case 'T':
          return 10;
        case '9':
        case '8':
        case '7':
        case '6':
        case '5':
        case '4':
        case '3':
        case '2':
          return value - '0';
        default:
          throw new IllegalArgumentException();
      }
    }

    public char getColor() {
      return color;
    }

    @Override
    public int hashCode() {
      int hash = 1;
      hash = 31 * hash + value;
      hash = 31 * hash + color;
      return hash;
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (!(obj instanceof Card)) {
        return false;
      }
      Card that = (Card) obj;
      return Objects.equals(value, that.value)
              && Objects.equals(color, that.color);
    }

    @Override
    public String toString() {
      return value + "" + color;
    }
  }

  public static abstract class Figure2 implements Comparable<Figure2> {

    private final Figure figure;

    public Figure2(Figure figure) {
      this.figure = figure;
    }

    @Override
    public int compareTo(Figure2 that) {
      return figure.ordinal() - that.figure.ordinal();
    }

    @Override
    public String toString() {
      return figure.toString();
    }
  }

  public static final class StraightFlush extends Figure2 {

    private final int high;

    public StraightFlush(int high) {
      super(Figure.StraightFlush);
      this.high = high;
    }

    @Override
    public int compareTo(Figure2 that) {
      if (!(that instanceof StraightFlush)) {
        return super.compareTo(that);
      }
      return high - ((StraightFlush) that).high;
    }

    @Override
    public String toString() {
      return super.toString() + ' ' + high;
    }
  }

  public static final class FourOfAKind extends Figure2 {

    private final int quad;
    private final int high;

    public FourOfAKind(int quad, int high) {
      super(Figure.FourOfAKind);
      this.quad = quad;
      this.high = high;
    }

    @Override
    public int compareTo(Figure2 that) {
      if (!(that instanceof FourOfAKind)) {
        return super.compareTo(that);
      }
      int compare;
      if ((compare = quad - ((FourOfAKind) that).quad) != 0) {
        return compare;
      }
      return high - ((FourOfAKind) that).high;
    }

    @Override
    public String toString() {
      return super.toString() + ' ' + quad + ' ' + high;
    }
  }

  public static final class FullHouse extends Figure2 {

    private final int triple;
    private final int pair;

    public FullHouse(int triple, int pair) {
      super(Figure.FullHouse);
      this.triple = triple;
      this.pair = pair;
    }

    @Override
    public int compareTo(Figure2 that) {
      if (!(that instanceof FullHouse)) {
        return super.compareTo(that);
      }
      int compare;
      if ((compare = triple - ((FullHouse) that).triple) != 0) {
        return compare;
      }
      return pair - ((FullHouse) that).pair;
    }

    @Override
    public String toString() {
      return super.toString() + ' ' + triple + ' ' + pair;
    }
  }

  public static final class Flush extends Figure2 {

    private final List<Integer> values = new ArrayList<>();

    public Flush(List<Integer> values) {
      super(Figure.Flush);
      this.values.addAll(values);
      this.values.sort(Comparator.reverseOrder());
    }

    @Override
    public int compareTo(Figure2 that) {
      if (!(that instanceof Flush)) {
        return super.compareTo(that);
      }
      Iterator<Integer> thisIterator = values.iterator();
      Iterator<Integer> thatIterator = ((Flush) that).values.iterator();
      while (thisIterator.hasNext() && thatIterator.hasNext()) {
        int thisValue = thisIterator.next();
        int thatValue = thatIterator.next();
        int compare;
        if ((compare = thisValue - thatValue) != 0) {
          return compare;
        }
      }
      return 0;
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder(super.toString()).append(' ');
      values.forEach(value -> builder.append(value).append(' '));
      builder.deleteCharAt(builder.length() - 1);
      return builder.toString();
    }
  }

  public static final class Straight extends Figure2 {

    private final int high;

    public Straight(int high) {
      super(Figure.Straight);
      this.high = high;
    }

    @Override
    public int compareTo(Figure2 that) {
      if (!(that instanceof Straight)) {
        return super.compareTo(that);
      }
      return high - ((Straight) that).high;
    }

    @Override
    public String toString() {
      return super.toString() + ' ' + high;
    }
  }

  public static final class ThreeOfAKind extends Figure2 {

    private final int triple;
    private final int high;
    private final int low;

    public ThreeOfAKind(int triple, int high, int low) {
      super(Figure.ThreeOfAKind);
      this.triple = triple;
      this.high = high;
      this.low = low;
    }

    @Override
    public int compareTo(Figure2 that) {
      if (!(that instanceof ThreeOfAKind)) {
        return super.compareTo(that);
      }
      int compare;
      if ((compare = triple - ((ThreeOfAKind) that).triple) != 0) {
        return compare;
      } else if ((compare = high - ((ThreeOfAKind) that).triple) != 0) {
        return compare;
      } else {
        return low - ((ThreeOfAKind) that).low;
      }
    }

    @Override
    public String toString() {
      return super.toString() + ' ' + triple + ' ' + high + ' ' + low;
    }
  }

  public static final class TwoPairs extends Figure2 {

    private final int high;
    private final int low;
    private final int extra;

    public TwoPairs(int high, int low, int extra) {
      super(Figure.TwoPairs);
      this.high = high;
      this.low = low;
      this.extra = extra;
    }

    @Override
    public int compareTo(Figure2 that) {
      if (!(that instanceof TwoPairs)) {
        return super.compareTo(that);
      }
      int compare;
      if ((compare = high - ((TwoPairs) that).high) != 0) {
        return compare;
      } else if ((compare = low - ((TwoPairs) that).low) != 0) {
        return compare;
      } else {
        return extra - ((TwoPairs) that).extra;
      }
    }

    @Override
    public String toString() {
      return super.toString() + ' ' + high + ' ' + low + ' ' + extra;
    }
  }

  public static final class OnePair extends Figure2 {

    private final int pair;
    private final SortedSet<Integer> others = new TreeSet<>(Comparator.reverseOrder());

    public OnePair(int pair, Set<Integer> others) {
      super(Figure.OnePair);
      Preconditions.checkArgument(others.size() == 3);
      this.pair = pair;
      this.others.addAll(others);
    }

    @Override
    public int compareTo(Figure2 that) {
      if (!(that instanceof OnePair)) {
        return super.compareTo(that);
      }
      int compare;
      if ((compare = pair - ((OnePair) that).pair) != 0) {
        return compare;
      }
      Iterator<Integer> thisIterator = others.iterator();
      Iterator<Integer> thatIterator = ((OnePair) that).others.iterator();
      while (thisIterator.hasNext() && thatIterator.hasNext()) {
        int thisValue = thisIterator.next();
        int thatValue = thatIterator.next();
        if ((compare = thisValue - thatValue) != 0) {
          return compare;
        }
      }
      return 0;
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder(super.toString()).append(' ').append(pair).append(' ');
      others.forEach(value -> builder.append(value).append(' '));
      builder.deleteCharAt(builder.length() - 1);
      return builder.toString();
    }
  }

  public static final class HighCard extends Figure2 {

    private final SortedSet<Integer> values = new TreeSet<>(Comparator.reverseOrder());

    public HighCard(Set<Integer> values) {
      super(Figure.HighCard);
      Preconditions.checkArgument(values.size() == 5);
      this.values.addAll(values);
    }

    @Override
    public int compareTo(Figure2 that) {
      if (!(that instanceof HighCard)) {
        return super.compareTo(that);
      }
      int compare;
      Iterator<Integer> thisIterator = values.iterator();
      Iterator<Integer> thatIterator = ((HighCard) that).values.iterator();
      while (thisIterator.hasNext() && thatIterator.hasNext()) {
        int thisValue = thisIterator.next();
        int thatValue = thatIterator.next();
        if ((compare = thisValue - thatValue) != 0) {
          return compare;
        }
      }
      return 0;
    }

    @Override
    public String toString() {
      StringBuilder builder = new StringBuilder(super.toString()).append(' ');
      values.forEach(value -> builder.append(value).append(' '));
      builder.deleteCharAt(builder.length() - 1);
      return builder.toString();
    }
  }

  public enum Figure {

    HighCard,
    OnePair,
    TwoPairs,
    ThreeOfAKind,
    Straight,
    Flush,
    FullHouse,
    FourOfAKind,
    StraightFlush
  }
}
