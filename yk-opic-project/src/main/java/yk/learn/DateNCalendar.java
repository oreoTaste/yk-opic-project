package yk.learn;
import java.sql.Date;
import java.util.Calendar;

public class DateNCalendar {
  public static void main(StringClass[] args) {

    System.out.println("Regarding \"Date\"");
    Date date;
    date = new Date(System.currentTimeMillis());
    System.out.print("* (getting Y/M/D) : ");
    System.out.print(date.getYear() + 1900 + "-");
    System.out.print(date.getMonth() + 1 + "-");
    System.out.print(date.getDate());
    System.out.println();
    
    System.out.printf("* (using printf) : %1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS", date);
    System.out.println();
    System.out.println(String.format("* (using string.format) : %tF %tH:%tM:%tS", date, date, date, date));
    
    date = Date.valueOf("2019-12-18");
    System.out.printf("* (when key in date) : %tF",date);
    
    System.out.println();
    System.out.println("-----------------------------------");
    
    System.out.println("Regarding \"Calendar\"");
    Calendar cal = Calendar.getInstance();
    System.out.print("* (getting Y/M/D) : ");
    System.out.print(cal.get(Calendar.YEAR) + "-");
    System.out.print(cal.get(Calendar.MONTH) + "-");
    System.out.print(cal.get(Calendar.DATE) + " ");
    System.out.print(cal.get(Calendar.HOUR_OF_DAY) + ":");
    System.out.print(cal.get(Calendar.MINUTE) + ":");
    System.out.print(cal.get(Calendar.SECOND));
  }
}
