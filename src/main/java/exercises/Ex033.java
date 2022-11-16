package exercises;

import com.google.common.math.IntMath;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.Fraction;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Ex033 implements Exercise {

  private static final int scale = 20;

  private static final int range = 100;

  @Override
  public void solve() {

    IntStream.range(10, range)
            .boxed()
            .flatMap(this::fractionStream)
            .filter(this::testFractions)
            .limit(4)
            .reduce(Fraction::multiplyBy)
            .map(Fraction::getDenominator)
            .ifPresent(System.out::println);
  }

  private int findLowestDenominator(Fraction fraction) {
    int numerator = fraction.getNumerator();
    int denominator = fraction.getDenominator();
    int gcd = IntMath.gcd(numerator, denominator);
    return denominator / gcd;
  }

  private boolean testFractions(Fraction fraction) {

    String left = Integer.toString(fraction.getNumerator());
    String right = Integer.toString(fraction.getDenominator());

    if (left.endsWith("0") && right.endsWith("0")) {
      return false;
    }

    String oneDigitLeft = StringUtils.replaceChars(left, right, "");
    String oneDigitRight = StringUtils.replaceChars(right, left, "");

    if (oneDigitLeft.length() != 1
            || oneDigitRight.length() != 1
            || "0".equals(oneDigitRight)) {
      return false;
    }

    BigDecimal leftInt = BigDecimal.valueOf(Integer.parseInt(left));
    BigDecimal rightInt = BigDecimal.valueOf(Integer.parseInt(right));
    BigDecimal oneDigitLeftInt = BigDecimal.valueOf(Integer.parseInt(oneDigitLeft));
    BigDecimal oneDigitRightInt = BigDecimal.valueOf(Integer.parseInt(oneDigitRight));

    return leftInt.divide(rightInt, scale, RoundingMode.FLOOR)
            .equals(oneDigitLeftInt.divide(oneDigitRightInt, scale, RoundingMode.FLOOR));
  }

  private Stream<Fraction> fractionStream(int i) {

    return StreamSupport.stream(
            Spliterators.spliteratorUnknownSize(
                    new FractionIterator(i), Spliterator.SORTED | Spliterator.ORDERED),
            false
    );
  }

  private static class FractionIterator implements Iterator<Fraction> {

    private int numerator;
    private int denominator;

    FractionIterator(int numerator) {
      this.numerator = numerator;
      this.denominator = numerator + 1;
    }

    @Override
    public boolean hasNext() {
      return denominator < range;
    }

    @Override
    public Fraction next() {
      return Fraction.getFraction(numerator, denominator++);
    }
  }
}
