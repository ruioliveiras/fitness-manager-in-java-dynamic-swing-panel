package model.user;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class Estatisticas
{
    int getDistanceByMonth(User usr, int month);
    int getDistanceByMonth(User usr, GregorianCalendar data);
    int getDistanceByYear(User usr, int year);
    int getDistanceByYear(User usr, GregorianCalendar data);
    
    int getCaloriesByMonth(User usr, int month);
    int getCaloriesByMonth(User usr, GregorianCalendar data); 
    int getCaloriesByYear(User usr, int year);
    int getCaloriesByYear(User usr, GregorianCalendar data);
    
    /**
     * @return time in hours
     */
    double getWorkoutTimeByMonth(User usr, int month);
    double getWorkoutTimeByMonth(User usr, GregorianCalendar data); 
    double getWorkoutTimeByYear(User usr, int year);
    double getWorkoutTimeByYear(User usr, GregorianCalendar data);
}
