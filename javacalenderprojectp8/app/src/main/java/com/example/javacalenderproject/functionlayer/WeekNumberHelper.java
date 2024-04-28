package com.example.javacalenderproject.functionlayer;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Locale;

// Class reponsible for
// - getting the current weeknumber
// - Set the displayed week number as the current weeknumber by default
// - incremening weeknumber
// - Accessing start + end date (LocalDateTime) for a week.
public class WeekNumberHelper {
    // Instance variables
    int currentWeekNumber;
    int displayedWeekNumber;
    LocalDateTime startDate;
    LocalDateTime endDate;


    // Constructor
    public WeekNumberHelper() {
        this.currentWeekNumber = findCurrentWeekNumber();
        this.displayedWeekNumber = currentWeekNumber; // assigns current weeknumber as default
        this.startDate = calculateStartDate(displayedWeekNumber); // assigns default value (based on current week)
        this.endDate = calculateEndDate(displayedWeekNumber);
    }

    // METHODS

    // Public methods (to use in other parts of the program):
    public int getDisplayedWeekNumber() {
        return displayedWeekNumber;
        // Remember: By default, this is the current week number (unless explicitly changed)
    }

    public LocalDateTime getStartDate() {
        // Returns start date for displayedWeekNumber
        return startDate;
    }

    public LocalDateTime getEndDate() {
        // Returns end date based on displayedWeekNumber
        return endDate;
    }

    public void incrementWeekNumber() {
        displayedWeekNumber++; // Increment
        startDate = calculateStartDate(displayedWeekNumber); // Update values of start/end date
        endDate = calculateEndDate(displayedWeekNumber);
    }

    public void decrementWeekNumber() {
        displayedWeekNumber--; // Decrement
        startDate = calculateStartDate(displayedWeekNumber); // Update values of start/end date
        endDate = calculateEndDate(displayedWeekNumber);
    }


    // Private helper methods:
    private int findCurrentWeekNumber() {
        // Returns the current week-number, based on today's date
        return LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                .get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
    }

    private LocalDateTime calculateStartDate(int weekNumber) {
        // Figures out the start date of a given week.
        return LocalDate.now()
                .with(WeekFields.of(DayOfWeek.MONDAY, 1).dayOfWeek(), 1) // Set to Monday
                .with(WeekFields.of(DayOfWeek.MONDAY, 1).weekOfWeekBasedYear(), weekNumber)
                .atStartOfDay();
    }

    private LocalDateTime calculateEndDate(int weekNumber) {
        // Figures out the end date of a given week.
        return LocalDate.now()
                .with(WeekFields.of(DayOfWeek.MONDAY, 1).dayOfWeek(), 7) // Set to Sunday
                .with(WeekFields.of(DayOfWeek.MONDAY, 1).weekOfWeekBasedYear(), weekNumber)
                .atTime(23, 00, 00); // Set time to the end of the day
    }


}
