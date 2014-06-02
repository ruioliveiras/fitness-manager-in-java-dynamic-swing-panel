package core;
import model.user.User;
import java.util.Calendar;
import java.util.GregorianCalendar;
import model.activity.Activity;
import model.activity.Distance;
import model.activity.Skill;
import model.activity.Contest;

/**TODO++++++++++++++++++*/
public class UserStats
{
    static public int getDistanceStats(User usr, GregorianCalendar startDate, GregorianCalendar endDate){
        int totalDist = 0;
        for(Activity act : usr.actividadesEntre(startDate, endDate))
            if(act instanceof Distance) totalDist += ((Distance) act).getDistance();
        return totalDist;
    }
    
    static public int getCaloriesStats(User usr, GregorianCalendar startDate, GregorianCalendar endDate){
        int totalCal = 0;
        for(Activity act : usr.actividadesEntre(startDate, endDate))
            totalCal += act.calculateCalories(usr);
        return totalCal;
    }
    
    /**
     * @return time in hours
     */
    static public double getWorkoutStats(User usr, GregorianCalendar startDate, GregorianCalendar endDate){
        int totalTime = 0;
        for(Activity act : usr.actividadesEntre(startDate, endDate))
            totalTime += act.getDuration_inHours();
        return totalTime;
    }
    
    /**
     * points only for simulation purpose
     */
    static public int getPtsFromLastYear(User usr, Class<? extends Activity> category){
        int totalPontos = 0;
        /*data agora*/
        GregorianCalendar endDate = new GregorianCalendar();
        /*data um ano antes*/
        GregorianCalendar startDate = new GregorianCalendar(endDate.YEAR - 1, endDate.MONTH, endDate.DAY_OF_MONTH);
        
        for(Activity act: usr.actividadesEntre(startDate, endDate)){
            if(act.getClass().equals(category.getClass()) && act instanceof Skill) totalPontos += ((Skill) act).getPoints();
            else
            if(act.getClass().equals(category.getClass()) && act instanceof Contest)
                totalPontos += ((Contest) act).getPointTeam() - ((Contest) act).getPointRival();
        }
        
        
        return totalPontos;
    }
}
