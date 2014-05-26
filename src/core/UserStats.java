package core;
import model.user.User;
import java.util.Calendar;
import java.util.GregorianCalendar;
import model.activity.Activity;

/**TODO++++++++++++++++++*/
public class UserStats
{
    static public int getDistanceByMonth(User usr, int month){
        return 0;
    }
    static public int getDistanceByMonth(User usr, GregorianCalendar data){
        return 0;
    }
    static public int getDistanceByYear(User usr, int year){
        return 0;
    }
    static public int getDistanceByYear(User usr, GregorianCalendar data){
        return 0;
    }
    
    static public int getCaloriesByMonth(User usr, int month){
        return 0;
    }
    static public int getCaloriesByMonth(User usr, GregorianCalendar data){
        return 0;
    } 
    static public int getCaloriesByYear(User usr, int year){
        return 0;
    }
    static public int getCaloriesByYear(User usr, GregorianCalendar data){
        return 0;
    }
    
    static public int getPtsByMonth(User usr, int month, Class<? extends Activity> category){
        return 0;
    }
    static public int getPtsByMonth(User usr, GregorianCalendar data, Class<? extends Activity> category){
        return 0;
    } 
    static public int getPtsByYear(User usr, int year, Class<? extends Activity> category){
        return 0;
    }
    static public int getPtsByYear(User usr, GregorianCalendar data, Class<? extends Activity> category){
        return 0;
    }
    
    /**
     * @return time in hours
     */
    static public double getWorkoutTimeByMonth(User usr, int month){
        return 0.0;
    }
    static public double getWorkoutTimeByMonth(User usr, GregorianCalendar data){
        return 0.0;
    } 
    static public double getWorkoutTimeByYear(User usr, int year){
        return 0.0;
    }
    static public double getWorkoutTimeByYear(User usr, GregorianCalendar data){
        return 0.0;
    }
}
