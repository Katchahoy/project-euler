package exercises;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class Ex019 implements Exercise {

  @Override
  public void solve() {

    int count = 0;
    for (int year = 1901; year < 2001; year++) {
      for (final Month month : Month.values()) {
        LocalDate currentDay = LocalDate.of(year, month, 1);
        DayOfWeek dayOfWeek = currentDay.getDayOfWeek();
        if (DayOfWeek.SUNDAY.equals(dayOfWeek)) {
          count++;
        }
      }
    }
    System.out.println(count);
  }
}
