package exercises;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;

import java.time.*;
import java.util.*;

public class ExAAA implements Exercise {

  private final Map<Plane, FlightSchedule> planeFlightScheduleMap = ImmutableMap.<Plane, FlightSchedule>builder()
          .put(
                  new Plane("F-LKJE"),
                  new FlightSchedule(
                          LocalDate.of(2020, 1, 5),
                          LocalDate.of(2020, 11, 25),
                          LocalTime.of(15,14),
                          EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.FRIDAY)
                  )
          ).put(
                  new Plane("F-UKVD"),
                  new FlightSchedule(
                          LocalDate.of(2019, 3, 2),
                          LocalDate.of(2020, 6, 5),
                          LocalTime.of(6,12),
                          EnumSet.of(DayOfWeek.SUNDAY)
                  )
          ).put(
                  new Plane("F-PLKR"),
                  new FlightSchedule(
                          LocalDate.of(2019, 2, 8),
                          LocalDate.of(2020, 8, 13),
                          LocalTime.of(17,20),
                          EnumSet.of(DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.FRIDAY)
                  )
          ).put(
                  new Plane("F-MLEJ"),
                  new FlightSchedule(
                          LocalDate.of(2019, 2, 7),
                          LocalDate.of(2021, 1, 2),
                          LocalTime.of(3,2),
                          EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.SATURDAY)
                  )
          ).put(
                  new Plane("F-AHIF"),
                  new FlightSchedule(
                          LocalDate.of(2019, 9, 30),
                          LocalDate.of(2020, 7, 17),
                          LocalTime.of(18,45),
                          EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.THURSDAY, DayOfWeek.SUNDAY)
                  )
          )
          .build();

  @Override
  public void solve() {
    Duration windowHours = Duration.ofHours(23);
    getActivePlanes(windowHours).forEach(System.out::println);
  }

  public Set<Plane> getActivePlanes(Duration windowHours) {
    Preconditions.checkArgument(windowHours.compareTo(Duration.ofHours(24)) < 0);

    LocalDateTime now = LocalDateTime.of(2020, 1, 6, 0, 12);
    LocalDateTime end = now.plus(windowHours);
    LocalDate today = now.toLocalDate();
    LocalDate endDay = end.toLocalDate();
    DayOfWeek dayOfWeek = now.getDayOfWeek();
    DayOfWeek endDayOfWeek = end.getDayOfWeek();
    LocalTime timeStart = now.toLocalTime();
    LocalTime timeEnd = timeStart.plus(windowHours); // what if it spreads over several days

    Set<Plane> activePlanes = new HashSet<>();
    planeFlightScheduleMap.forEach((plane, flightSchedule) -> {
      if (flightSchedule.getStartDate().isAfter(endDay) || flightSchedule.getEndDate().isBefore(today)) {
        return;
      }
//      if (flightSchedule.getDaysOfWeek().contains(dayOfWeek)
//              && flightSchedule.getTimeOfDay().isAfter(timeStart))


      if (!flightSchedule.getDaysOfWeek().contains(dayOfWeek) && !flightSchedule.getDaysOfWeek().contains(endDayOfWeek)) {
        return;
      }
      if (flightSchedule.getTimeOfDay().isAfter(timeStart) && flightSchedule.getTimeOfDay().isBefore(timeEnd)) {
        activePlanes.add(plane);
      }
    });
    return activePlanes;
  }

  private static class Plane {

    private final String identifier;

    public Plane(String identifier) {
      this.identifier = Objects.requireNonNull(identifier);
    }

    public String getIdentifier() {
      return identifier;
    }

    @Override
    public int hashCode() {
      return identifier.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (!(obj instanceof Plane)) {
        return false;
      }
      Plane that = (Plane) obj;
      return Objects.equals(this.identifier, that.identifier);
    }

    @Override
    public String toString() {
      return identifier;
    }
  }

  private static class FlightSchedule {

    private final LocalDate startDate;
    private final LocalDate endDate;
    private final LocalTime timeOfDay;
    private final Set<DayOfWeek> daysOfWeek;

    public FlightSchedule(LocalDate startDate, LocalDate endDate, LocalTime timeOfDay, Set<DayOfWeek> daysOfWeek) {
      this.startDate = Objects.requireNonNull(startDate);
      this.endDate = Objects.requireNonNull(endDate);
      this.timeOfDay = Objects.requireNonNull(timeOfDay);
      this.daysOfWeek = Objects.requireNonNull(daysOfWeek);
    }

    public LocalDate getStartDate() {
      return startDate;
    }

    public LocalDate getEndDate() {
      return endDate;
    }

    public LocalTime getTimeOfDay() {
      return timeOfDay;
    }

    public Set<DayOfWeek> getDaysOfWeek() {
      return daysOfWeek;
    }
  }
}
