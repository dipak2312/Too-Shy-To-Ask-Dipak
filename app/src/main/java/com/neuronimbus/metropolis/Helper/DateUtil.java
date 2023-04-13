package com.neuronimbus.metropolis.Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class DateUtil {

    /**
     * Maximum date range count between two selected dates
     */
    private static final int MAX_DATE_DIFFERENCE_COUNT = -92;

    /**
     * This method returns the current date in string format.
     *
     * @param displayDateFormat Example : dd/mm/yyyy , dd MM yyyy etc
     * @return Returns date in given string format
     */
    public String getCurrentDateInString(String displayDateFormat) {
        Calendar mCalendar = Calendar.getInstance();
        return getSimpleDateFormat(displayDateFormat).format(mCalendar.getTime());
    }

    /**
     * This method returns the yesterday date in string format.
     *
     * @param displayDateFormat Example : dd/mm/yyyy , dd MM yyyy etc
     * @return Returns date in given string format
     */
    public String getYesterdayDateInString(String displayDateFormat) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.add(Calendar.DATE, -1);
        return getSimpleDateFormat(displayDateFormat).format(mCalendar.getTime());
    }
    /**
     * This method is used to return the SimpleDateFormat object of the specified date format string
     *
     * @param displayDateFormat Example : dd/mm/yyyy , dd MM yyyy etc
     * @return returns SimpleDateFormat object
     */
    public SimpleDateFormat getSimpleDateFormat(String displayDateFormat) {
        return new SimpleDateFormat(displayDateFormat, Locale.US);
    }


    /**
     * Return a Date object which has no time factor
     *
     * @param displayDateFormat date format
     * @return Date object
     */
    public Date getCurrentDate(String displayDateFormat) {
        return getDateFromString(getCurrentDateInString(displayDateFormat), displayDateFormat);
    }

    /**
     * This method is used to get the string date from the date object in the specified date format.
     *
     * @param date              Date to be converted to string format
     * @param displayDateFormat display date format
     * @return String date
     */
    public String getDateInDisplayFormat(Date date, String displayDateFormat) {
        return getSimpleDateFormat(displayDateFormat).format(date.getTime());
    }

    /**
     * This method is used to convert string date to Date object
     *
     * @param stringDate date in string
     * @param dateFormat date format
     * @return Date object
     */
    public Date getDateFromString(String stringDate, String dateFormat) {
        Date mDate = null;
        try {
            mDate = getSimpleDateFormat(dateFormat).parse(stringDate);
        } catch (ParseException e) {
           //Utilities.getInstance().printException(e);
        }
        return mDate;
    }

    /**
     * This method is used to get difference between two dates
     *
     * @param startDate Date 1
     * @param endDate   Date 2
     * @return Difference between two dates in number of days.
     */
    public long getDifferenceDays(String startDate, String endDate, String dateFormat) {
        long mDiff = getDateFromString(endDate, dateFormat).getTime() - getDateFromString(startDate, dateFormat).getTime();
        return TimeUnit.DAYS.convert(mDiff, TimeUnit.MILLISECONDS);
    }

    /**
     * Method to get the current time as per the time format
     *
     * @param strTimeFormat - Time format like hh:mm a
     * @return Time in string
     */
    public String getCurrentTime(String strTimeFormat) {
        Calendar mCalendar = Calendar.getInstance();
        Date mDate = mCalendar.getTime();
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(strTimeFormat);
        return mSimpleDateFormat.format(mDate);
    }

    /**
     * This method is used to get number of days between two string dates.
     *
     * @param startDate start date
     * @param endDate   end date
     * @return Calculated number of days
     */
    public int getNoOfDaysBetweenTwoDatesString(String startDate, String endDate) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        float dayCount = 0;
        Date mDate1 = null, mDate2 = null;
        try {
            mDate1 = mSimpleDateFormat.parse(startDate);
            mDate2 = mSimpleDateFormat.parse(endDate);

            Calendar mCalendar1 = Calendar.getInstance();
            mCalendar1.setTime(mDate1);

            Calendar mCalendar2 = Calendar.getInstance();
            mCalendar2.setTime(mDate2);

            long mDiffMili = mCalendar2.getTimeInMillis() - mCalendar1.getTimeInMillis();
            dayCount = (float) mDiffMili / (24 * 60 * 60 * 1000);
        } catch (ParseException e) {
           //Utilities.getInstance().printException(e);
        }

        return (int) dayCount;
    }


    /**
     * This method returns the Calendar object from date and time.
     *
     * @param strDate       - Source date
     * @param strTime       - Source time
     * @param strDateFormat - Source date format
     * @param strTimeFormat - Source time format
     * @return Calendar object with the given date and time arguments
     */
    public Calendar getCalendarFromDateTime(String strDate, String strTime, String strDateFormat, String strTimeFormat) {
        Date mDate = getDateFromString(strDate, strDateFormat);
        Date mTime = getDateFromString(strTime, strTimeFormat);

        Calendar mCalendarDateTime = Calendar.getInstance();

        mCalendarDateTime.setTime(mDate);

        Calendar mCalendarTime = Calendar.getInstance();
        mCalendarTime.setTime(mTime);

        int mHour = mCalendarTime.get(Calendar.HOUR_OF_DAY);
        int mMinute = mCalendarTime.get(Calendar.MINUTE);

        mCalendarDateTime.set(Calendar.HOUR_OF_DAY, mHour);
        mCalendarDateTime.set(Calendar.MINUTE, mMinute);
        mCalendarDateTime.set(Calendar.SECOND, 0);

        return mCalendarDateTime;
    }

    /**
     * This method calculates the difference between 2 dates in months
     *
     * @param date1 - Start date
     * @param date2 - End date
     * @return - The number of months between 2 dates
     */
    public int getNumberOfMonthsBetweenDates(Date date1, Date date2) {
        Calendar mCalendarD1, mCalendarD2;

        mCalendarD1 = Calendar.getInstance();
        mCalendarD1.setTime(date1);

        mCalendarD2 = Calendar.getInstance();
        mCalendarD2.setTime(date2);

        int mDiffYear = mCalendarD2.get(Calendar.YEAR) - mCalendarD1.get(Calendar.YEAR);

        return mDiffYear * 12 + mCalendarD2.get(Calendar.MONTH) - mCalendarD1.get(Calendar.MONTH);
    }

    /**
     * This function converts the given string date into the given display format
     *
     * @param date          - Date
     * @param sourceFormat  - Date format of the given date
     * @param displayFormat - Date format required for the display
     * @return - String date in the given display format
     */
    public String getStringDateInDisplayFormat(String date, String sourceFormat, String displayFormat) {
        return getDateInDisplayFormat(getDateFromString(date, sourceFormat), displayFormat);
    }

    /**
     * This method calculates the number of months between the two dates and returns the list of months in the required format
     *
     * @param strStartDate     Start date
     * @param strEndDate       End date
     * @param strSourceFormat  Date format of the start and end dates
     * @param strDisplayFormat Date format required for the dates in list
     * @return The list of all the dates between the two dates inclusive of start and end dates
     */
    public ArrayList<String> getMonthListBetweenDates(String strStartDate, String strEndDate, String strSourceFormat, String strDisplayFormat) {
        ArrayList<String> mMonths = new ArrayList<>();

        // Start Date
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTime(getDateFromString(strStartDate, strSourceFormat));

        // End Date
        Calendar mCalendar2 = Calendar.getInstance();
        mCalendar2.setTime(getDateFromString(strEndDate, strSourceFormat));

        // Difference in dates
        int mDiffYear = mCalendar2.get(Calendar.YEAR) - mCalendar.get(Calendar.YEAR);
        int mDiffMonth = mDiffYear * 12 + mCalendar2.get(Calendar.MONTH) - mCalendar.get(Calendar.MONTH);

        for (int i = 0; i <= mDiffMonth; i++) {
            mMonths.add(getDateInDisplayFormat(mCalendar.getTime(), strDisplayFormat));
            mCalendar.add(Calendar.MONTH, 1);
        }

        return mMonths;
    }

    /**
     * This method returns the number of weeks in a month
     *
     * @param iMonth - Month number - 0 to 11
     * @return - no of weeks
     * Note: Here calendar week starts from Sunday.
     * Hence number of weeks may very for the calendar starting with monday weeks(windows calendar)
     */
    public int getNumberOfWeeksInMonth(int iMonth, int iYear) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.YEAR, iYear);
        mCalendar.set(Calendar.DAY_OF_MONTH, 1);
        mCalendar.set(Calendar.MONTH, iMonth);// here month value starts from 0 to 11

        // Month value starts from 0 to 11 for Jan to Dec
        return mCalendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
    }

    /**
     * This method Compare two string dates
     *
     * @param strDate1
     * @param strDate2
     * @return true if strDate1 is greater than strDate2
     */
    public boolean compareDate(String strDate1, String strDate2, String strDateFormat) {
        try {
            SimpleDateFormat formatter = getSimpleDateFormat(strDateFormat);
            Date mDate1 = formatter.parse(strDate1);
            Date mDate2 = formatter.parse(strDate2);
            if (mDate1.compareTo(mDate2) > 0) {
                return true;
            }
        } catch (ParseException e) {
           //Utilities.getInstance().printException(e);
        }

        return false;
    }


    /**
     * This method returns start and end date of 92 days from current day as start date
     *
     * @param strDate       - Current date
     * @param strDateFormat - Date format of current day
     * @return - Returns array of start and end date
     */
    public String[] getThreeMonthDateRange(String strDate, String strDateFormat) {

        Calendar mCalendar = setCustomDateToCalendar(strDate, strDateFormat);
        String[] arrDate = new String[2];
        arrDate[0] = getSimpleDateFormat(strDateFormat).format(mCalendar.getTime());
        mCalendar.add(Calendar.DATE, MAX_DATE_DIFFERENCE_COUNT);                  // subtract 92 month to current month
        arrDate[1] = getSimpleDateFormat(strDateFormat).format(mCalendar.getTime());
        return arrDate;
    }

    /**
     * Set string date to calendar and return calendar object
     *
     * @param strDate       - String date to set to calendar
     * @param strDateFormat - Date format of output date
     * @return
     */
    public Calendar setCustomDateToCalendar(String strDate, String strDateFormat) {
        Calendar mCalendar = Calendar.getInstance();
        try {
            mCalendar.setTime(getSimpleDateFormat(strDateFormat).parse(strDate));
        } catch (ParseException e) {
           //Utilities.getInstance().printException(e);
        }
        return mCalendar;
    }

    /**
     * This method returns months quarter array list in an year
     *
     * @param year - year for which quarters need to be fetched
     * @return - returns array list of months quarter
     */
    public ArrayList<String> getQuarterName(int year, boolean isFirst) {
        // Timeline cycles month range 1
        String JAN_MAR = "JANUARY - MARCH";
        // Timeline cycles month range 2
        String APR_JUN = "APRIL - JUNE";
        // Timeline cycles month range 3
        String JUL_SEP = "JULY - SEPTEMBER";
        //Timeline cycles month range 4
        String OCT_DEC = "OCTOBER - DECEMBER";

        Calendar mCalendar = Calendar.getInstance();
        mCalendar.add(Calendar.YEAR, year);
        ArrayList<String> mQuarters = new ArrayList<>();
        int count;
        if (year < Calendar.getInstance().get(Calendar.YEAR)) {
            count = 12;
            mCalendar.set(Calendar.MONTH, 11);
        } else {
            mCalendar.set(Calendar.MONTH, mCalendar.get(Calendar.MONTH) + 1);
            count = mCalendar.get(Calendar.MONTH);
        }

        if (!isFirst) {
            mQuarters.add(JAN_MAR);
        }

        if (count > 0 && count <= 3) {
            return mQuarters;
        } else if (count >= 4 && count <= 6) {
//            mQuarters.add(JAN_MAR);
            mQuarters.add(APR_JUN);
            return mQuarters;
        } else if (count >= 7 && count <= 9) {
//            mQuarters.add(JAN_MAR);
            mQuarters.add(APR_JUN);
            mQuarters.add(JUL_SEP);
            return mQuarters;
        } else {
//            mQuarters.add(JAN_MAR);
            mQuarters.add(APR_JUN);
            mQuarters.add(JUL_SEP);
            mQuarters.add(OCT_DEC);
            return mQuarters;
        }
    }

    /**
     * This method returns start and end date of selected quarter
     *
     * @param startMonth    - start month of quarter
     * @param endMonth      - end month of quarter
     * @param yearSelected
     * @param strDateFormat - output date format  @return - Returns array of start and end date
     */
    public String[] getQuarterDateRange(int startMonth, int endMonth, String yearSelected, String strDateFormat) {

        Calendar mCalendar = Calendar.getInstance();
        String[] arrDate = new String[2];
        mCalendar.set(Calendar.DATE, 1);          // set DATE to 1, so first date of selected month
        mCalendar.set(Calendar.MONTH, startMonth);
        mCalendar.set(Calendar.YEAR, Integer.parseInt(yearSelected));
        arrDate[0] = getSimpleDateFormat(strDateFormat).format(mCalendar.getTime());
        mCalendar.set(Calendar.MONTH, endMonth);
        mCalendar.set(Calendar.YEAR, Integer.parseInt(yearSelected));
        Calendar mTodayCalendar = Calendar.getInstance();

        // Check if current date is greater than selected quarter maximum date. Future dates are not allowed in quarter selection.
        try {
            SimpleDateFormat mFormatter = getSimpleDateFormat(strDateFormat);
            Date mDate1 = mFormatter.parse(getSimpleDateFormat(strDateFormat).format(mTodayCalendar.getTime()));
            Date mDate2 = mFormatter.parse(getSimpleDateFormat(strDateFormat).format(mCalendar.getTime()));
            if (mDate1.compareTo(mDate2) > 0) {
                mCalendar.set(Calendar.DATE, mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)); // Max day of month
            } else {
                mCalendar.set(Calendar.DATE, mTodayCalendar.get(Calendar.DAY_OF_MONTH)); // Max day of month
                mCalendar.set(Calendar.MONTH, mTodayCalendar.get(Calendar.MONTH));
            }
        } catch (ParseException e) {
           //Utilities.getInstance().printException(e);
        }


        arrDate[1] = getSimpleDateFormat(strDateFormat).format(mCalendar.getTime());
        return arrDate;
    }

    /**
     * This method returns the tomorrow date in string format.
     *
     * @param displayDateFormat Example : dd/mm/yyyy , dd MM yyyy etc
     * @return Returns date in given string format
     */
    public String getTomorrowDateString(String displayDateFormat) {
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.add(Calendar.DAY_OF_MONTH, 1); //add a day
        return getSimpleDateFormat(displayDateFormat).format(mCalendar.getTime());
    }

    /**
     * Method to create bill history date range
     */
    public String[] getDefaultBillHistoryRange(String strDateFormat) {
        Calendar mCalendar = Calendar.getInstance();
        String[] arrDate = new String[2];
        if (mCalendar.get(Calendar.DATE) == 1) {
            mCalendar.add(Calendar.MONTH, -1);
            arrDate[0] = getSimpleDateFormat(strDateFormat).format(mCalendar.getTime());
            mCalendar.set(Calendar.DATE, mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)); // Max day of month
            arrDate[1] = getSimpleDateFormat(strDateFormat).format(mCalendar.getTime());
        } else {
            mCalendar.set(Calendar.DATE, 1);          // set DATE to 1, so first date of selected month
            arrDate[0] = getSimpleDateFormat(strDateFormat).format(mCalendar.getTime());
            Calendar mCalendarEndDate = Calendar.getInstance();
            //  mCalendarEndDate.add(Calendar.DATE, -1);
            arrDate[1] = getSimpleDateFormat(strDateFormat).format(mCalendarEndDate.getTime());
        }
        return arrDate;
    }

}
