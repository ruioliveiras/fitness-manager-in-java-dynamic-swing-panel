package core;
import model.user.User;
import java.util.Calendar;
import java.util.GregorianCalendar;
import model.activity.Activity;

/**TODO++++++++++++++++++*/
public class UserStats
{
    static public int getDistanceStats(User usr, GregorianCalendar startDate, GregorianCalendar endDate){
        return 0;
    }
    
    static public int getCaloriesStats(User usr, GregorianCalendar startDate, GregorianCalendar endDate){
        return 0;
    }
    
    /**
     * @return time in hours
     */
    static public double getWorkoutStats(User usr, GregorianCalendar startDate, GregorianCalendar endDate){
        return 0.0;
    }
    
    /**
     * points only for simulation purpose
     */
    static public int getPtsFromLastYear(User usr, Class<? extends Activity> category){
        /*data agora*/
        GregorianCalendar endDate = new GregorianCalendar();
        /*data um ano antes*/
        GregorianCalendar startDate = new GregorianCalendar(endDate.YEAR - 1, endDate.MONTH, endDate.DAY_OF_MONTH);

        return 0;
    }
}
