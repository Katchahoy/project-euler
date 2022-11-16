package exercises;

import org.apache.commons.lang3.mutable.MutableInt;

import java.util.*;
import java.util.stream.IntStream;

public class ExFBK implements Exercise {

  private final List<Person> people;

  public ExFBK() {
    people = new ArrayList<>();
    Random random = new Random();
    IntStream.range(1, 10000)
            .forEach(i -> {
              int birthYear = random.nextInt(2000);
              int deathYear = birthYear + random.nextInt(100);
              Person person = new Person(birthYear, deathYear);
              people.add(person);
            });
  }

  @Override
  public void solve() {

    Map<Integer, MutableInt> nbPeopleVariationByYearMap = new TreeMap<>();

    people.forEach(person -> {
      nbPeopleVariationByYearMap.computeIfAbsent(person.birthYear, k -> new MutableInt()).increment();
      nbPeopleVariationByYearMap.computeIfAbsent(person.deathYear + 1, k -> new MutableInt()).decrement();
    });

    int topYear = 0;
    int topNbPeopleAlive = 0;
    int nbPeopleAlive = 0;

//    nbPeopleVariationByYearMap.forEach((year, nbPeopleVariation) -> System.err.println(year + ": " + nbPeopleVariation));

    for (var entry : nbPeopleVariationByYearMap.entrySet()) {
      int year = entry.getKey();
      nbPeopleAlive += entry.getValue().getValue();
      if (nbPeopleAlive > topNbPeopleAlive) {
        topNbPeopleAlive = nbPeopleAlive;
        topYear = year;
      }
    }

    System.out.println(topYear);
  }

  private static class Person {

    private final int birthYear;
    private final int deathYear;

    public Person(int birthYear, int deathYear) {
      this.birthYear = birthYear;
      this.deathYear = deathYear;
    }

    @Override
    public String toString() {
      return birthYear + "-" + deathYear;
    }
  }
}
