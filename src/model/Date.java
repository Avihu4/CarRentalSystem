package model;
/**
 * This class represents a Date object.
 *
 * @author Avihu Tubi
 * @version 20/11/2022
 */
public class Date
{
    private int _day;
    private int _month;
    private int _year;
    private final int MAX_DAY = 31;
    private final int MIN_DAY = 1;
    private final int MAX_MONTH = 12;
    private final int MIN_MONTH = 1;
    private final int MAX_YEAR = 9999;
    private final int MIN_YEAR = 1000;
    private final int DEFAULT_DAY = 1;
    private final int DEFAULT_MONTH = 1;
    private final int DEFAULT_YEAR = 2000;
    private final int JANUARY_MONTH = 1;
    private final int FEBRUARY_MONTH = 2;
    private final int MARCH_MONTH = 3;
    private final int APRIL_MONTH = 4;
    private final int MAY_MONTH = 5;
    private final int JUNE_MONTH = 6;
    private final int JULY_MONTH = 7;
    private final int AUGUST_MONTH = 8;
    private final int SEPTEMBER_MONTH = 9;
    private final int OCTOBER_MONTH = 10;
    private final int NOVEMBER_MONTH = 11;
    private final int DECEMBER_MONTH = 12;
    private final int MAX_DAYS_IN_LONG_MONTH = 31;
    private final int MAX_DAYS_IN_SHORT_MONTH = 30;
    private final int DAYS_IN_LONG_FEBRUARY = 29;
    private final int DAYS_IN_SHORT_FEBRUARY = 28;
    /**
     * If the given date is valid - creates a new Date object, otherwise creates the date 1/1/2000
     * @param day - the day in the month (1-31)
     * @param month - the month in the year (1-12)
     * @param year - the year (4 digits)
     */

    public Date(int day, int month, int year)
    {
        if (isLegal(day, month, year))
        {
            _day = day;
            _month = month;
            _year = year;
        }
        else
        {
            _day = DEFAULT_DAY;
            _month = DEFAULT_MONTH;
            _year = DEFAULT_YEAR;
        }

    }

    /**
     * Copy constructor
     * @param other - the date to be copied
     */
    public Date(Date other)
    {
        _day =other._day;
        _month = other._month;
        _year = other._year;
    }

    /**
     * Gets the day
     * @return the day
     */
    public int getDay()
    {
        return _day; 
    }

    /**
     * Gets the month
     * @return the month
     */
    public int getMonth()
    {
        return _month;
    }

    /**
     * Gets the year
     * @return the year
     */
    public int getYear()
    {
        return _year;
    }

    /**
     * Set the day (only if date remains valid)
     * @param dayToSet - the day value to be set
     */
    public void setDay(int dayToSet)
    {
        if(isLegal(dayToSet, _month, _year) == true) {
            _day = dayToSet;
        }
    }

    /**
     * Set the month (only if date remains valid)
     * @param monthToSet - the month value to be set
     */
    public void setMonth(int monthToSet)
    {
        if(isLegal(_day, monthToSet, _year) == true) {
            _month = monthToSet;
        }
    }

    /**
     * Sets the year (only if date remains valid)
     * @param yearToSet - the year value to be set
     */
    public void setYear(int yearToSet)
    {
        if((isLegal(_day, _month, yearToSet) == true)) {
            _year = yearToSet;
        }
    }

    /**
     * Check if 2 dates are the same
     * @param other - the date to compare this date to
     * @return true if the dates are the same, otherwise false
     */
    public boolean equals(Date other)
    {
        return (other._day == _day && other._month == _month && other._year == _year);
    }

    /**
     * Check if this date is before other date
     * @param  other - date to compare this date to
     * @return true if this date is before other date, otherwise false
     */
    public boolean before (Date other)
    {
        return (calculateDate(_day, _month, _year) < calculateDate(other._day, other._month, other._year));
    }

    /**
     * Check if this date is after other date
     * @param  other - date to compare this date to
     * @return true if this date is  after other date, otherwise false
     */
    public boolean after (Date other)
    {
        return other.before(this);
    }

    /**
     * Calculates the difference in days between two dates
     * @param other - the date to calculate the difference between
     * @return the number of days between the dates (non-negative value)
     */
    public int difference(Date other)
    {
        if(other.before(this)) {
            return calculateDate(_day, _month, _year) - calculateDate(other._day, other._month, other._year);
        }
        return calculateDate(other._day, other._month, other._year) - calculateDate(_day, _month, _year);
    }

    /**
     * Returns a String that represents this date
     * @return String that represents this date in the following format: day (2 digits) / month(2 digits) / year (4 digits) for example: 02/03/1998
     */
    public String toString()
    {
        String st = "";
        if (_day < 10)
            st = "0" + _day;
        else
            st = st + "" +_day;
        st = st + "/";
        if (_month < 10)
            st = st + "0" + _month;
        else
            st = st + "" + _month;
        st = st + "/" + _year;
        return st;
    }

    /**
     * Calculate the date of tomorrow
     * @return the date of tomorrow
     */
    public Date tomorrow()
    {
        if(isLegal(_day + 1, _month, _year)== true){
            return new Date(_day + 1, _month, _year);
        }
        else
        if(isLegal(DEFAULT_DAY, _month + 1, _year)== true){
            return new Date(DEFAULT_DAY, _month + 1, _year);
        }
        return new Date(DEFAULT_DAY, DEFAULT_MONTH, _year + 1);
    }
    //Checking if the date is legal
    private boolean isLegal(int day, int month, int year)
    {
        if(year < MIN_YEAR || year > MAX_YEAR)
            return false;
        if(month < MIN_MONTH || month > MAX_MONTH)
            return false;
        if(day < MIN_DAY || day >MAX_DAY)
            return false;
        if (month == JANUARY_MONTH || month == MARCH_MONTH || month == MAY_MONTH || month == JULY_MONTH || month == AUGUST_MONTH || month == OCTOBER_MONTH || month == DECEMBER_MONTH)
            return day <= MAX_DAYS_IN_LONG_MONTH;
        if(month == APRIL_MONTH || month == JUNE_MONTH || month == SEPTEMBER_MONTH || month == NOVEMBER_MONTH)
            return day <= MAX_DAYS_IN_SHORT_MONTH;
        if(month == FEBRUARY_MONTH)
        {
            if(isLeap(year))
                return day <= DAYS_IN_LONG_FEBRUARY;
            else
                return day <= DAYS_IN_SHORT_FEBRUARY;
        }
        return false;
    }
    // Checking if the year is leap year
    private boolean isLeap(int year)
    {
        return (year % 400 == 0 && year % 100 == 0) || (year % 4 == 0 && year % 100 != 0);
    }
    // computes the day number since the beginning of the Christian counting of years
    private int calculateDate ( int day, int month, int year)
    {
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year/4 - year/100 + year/400 + ((month+1) * 306)/10 + (day - 62);
    } 
}
