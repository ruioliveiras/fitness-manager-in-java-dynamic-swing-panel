package core.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class Util {
	
	public static final String DATA_PATTERM = "yyyy-MM-dd HH:mm:ss.SSS";
	public static final String DATA_ONLY = "yyyy-MM-dd";
	
    public static int CalcHashCode(int a,int b,int c){
        return Long.valueOf((a * 31 + b) * 31 + c).hashCode();
    }

    public static String dateFormat_only(Calendar c){
    	DateFormat df = new SimpleDateFormat(DATA_ONLY);
    	return df.format(c.getTime());
    }
    public static long dateFormat_only(String c) throws ParseException{
    	DateFormat df = new SimpleDateFormat(DATA_ONLY);
    	return df.parse(c).getTime();
    }

    public static String dateFormat(Calendar c){
    	DateFormat df = new SimpleDateFormat(DATA_PATTERM);
    	return df.format(c.getTime());
    }
    public static long dateFormat(String c) throws ParseException{
    	DateFormat df = new SimpleDateFormat(DATA_PATTERM);
    	return df.parse(c).getTime();
    }
    
    public static String hourFormat(long miliduration){
    	Calendar c = Calendar.getInstance();
    	c.set(Calendar.HOUR_OF_DAY, 0); c.set(Calendar.MINUTE, 0); 	c.set(Calendar.SECOND, 0); 	c.set(Calendar.MILLISECOND, 0);
    	c.setTimeInMillis(c.getTimeInMillis() + miliduration);
    	DateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");
    	return df.format(c.getTime());
    }

    
    public static long hourFormat(String s) throws ParseException{
    	Calendar c = Calendar.getInstance();
    	c.set(Calendar.HOUR_OF_DAY,0); c.set(Calendar.MINUTE, 0); 	c.set(Calendar.SECOND, 0); 	c.set(Calendar.MILLISECOND, 0);

    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    	sdf.setTimeZone(TimeZone.getTimeZone("UTC"));


    	Date date = sdf.parse("1970-01-01 " + s);

    	return  date.getTime();
    }


}
