package core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Util {

	
    public static int CalcHashCode(int a,int b,int c){
        return Long.valueOf((a * 31 + b) * 31 + c).hashCode();
    }
    
    
    public static String hourFormat(long miliduration){
    	Calendar c = Calendar.getInstance();
    	c.set(Calendar.HOUR_OF_DAY, 0); c.set(Calendar.MINUTE, 0); 	c.set(Calendar.SECOND, 0); 	c.set(Calendar.MILLISECOND, 0);
    	c.setTimeInMillis(c.getTimeInMillis() + miliduration);
    	DateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");
    	return df.format(c.getTime());
    }


}
