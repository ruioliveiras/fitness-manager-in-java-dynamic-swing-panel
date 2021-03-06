package model.activity;
import java.util.GregorianCalendar;

import model.Clonable;
import core.util.Util;


/**
 * 
 * @author ruioliveiras
 *
 * 
 * 
 * 
 * 
 * Scheme:
 * 
 *                  Activity
 *          Contest                 Skill       Distance            Ludic
 *  Individual      Collective      (Skate)         altimetry       s(Zumba)
 * (KickBox)        (FootBall)
 */


public abstract class Activity implements Clonable {
    private GregorianCalendar mDate;
    private long mDuration; /*activity duration [ms]*/
    private Weather mWeather;
    private int mHearthRate;/*heart rate [1/min] - for calorie burn calculation*/
    
    /**TODO
     * array de strings para comentarios nas actividades?
     */
    public Activity() {
        mDuration = 0;
        mWeather = Weather.INDOOR;

        mHearthRate = 0;
        mDate = new GregorianCalendar();
    }

    public Activity(long time, Weather weather, int hRate, GregorianCalendar date) {
        mDuration = time;
        mWeather = weather;
        mHearthRate = hRate;
        mDate = (GregorianCalendar) date.clone();
    }   

    public Activity(Activity act) {
        this(act.getDuration(), act.getWeather(), act.getHeartRate(), act.getDate());
    }

    
    public void setDuration(long time)		{mDuration = time;}
    public long getDuration()				{return mDuration;}
    public double getDuration_inHours()		{return (double) getDuration_inSeconds()/3600;}
    public double getDuration_inMinutes()	{return (double) getDuration_inSeconds()/60;}
    public double getDuration_inSeconds()	{return (double) mDuration/1000;}  
    
    public void setWeather(Weather weather)	{mWeather = weather;}
    public Weather getWeather()				{return mWeather;}
    
    public void setHeartRate(int heathRate)	{mHearthRate = heathRate;}
    public int getHeartRate()				{return mHearthRate;}

    public GregorianCalendar getDate()				{return (GregorianCalendar) mDate.clone();}
    public void setDate(int ano, int mes, int dia)	{mDate = new GregorianCalendar(ano, mes, dia);}
    public void setDate(GregorianCalendar date)		{mDate = (GregorianCalendar) date.clone();}
    
    
    /*abstract things*/
    public abstract String getName();
    public abstract int getRecordSize();
    public abstract int compareRecord(Activity otherActivity,int recordType);
    public abstract int getIntensidade();
    public abstract double getMET(); /*for calories*/
    public  abstract Object clone();
    
    
    
    /*Object statements*/
    @Override
    public boolean equals (Object obj){
        if(this == obj) return true; 
        if((obj == null) || (this.getClass() != obj.getClass())) return false;
        Activity e = (Activity) obj;
        return this.mDuration == e.getDuration() && this.mWeather == e.getWeather() && this.mHearthRate == e.getHeartRate();
    }
    
    @Override
    public int hashCode(){
        return Util.CalcHashCode((int) mDuration, mWeather.hashCode(),mHearthRate);
    }

    
   
    
  

}
