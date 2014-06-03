package model.activity;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import model.ObjectClonable;
import model.ObjectRecord;
import model.user.User;
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


public abstract class Activity implements ObjectClonable,ObjectRecord ,Serializable {



	private static final long serialVersionUID = -4202759185512132598L;
	private GregorianCalendar mDate;
    private long mDuration; /*activity duration [ms]*/
    private Weather mWeather;
    private int mHearthRate;/*heart rate [1/min] - for calorie burn calculation*/
    
    /**TODO
     * array de strings para comentarios nas actividades?
     */
    public Activity() {
        mDuration = 0;
        mWeather = new Weather();

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

    public abstract int getIntensidade();
    public abstract double getMET(); /*for calories*/
    public  abstract Activity clone();
    
    /*Record*/
	public abstract String getRecordToString(Record recordType);
	public abstract int getRecordSize();
	public abstract Record getRecord(int index);
	public abstract long get(EnumAttr iAttr);
    public abstract boolean isRecordBiggerBetter(Record recordType);
    public abstract void correct(Record record);
    
    public long getValue(Record recordType){
    	return get(recordType.getMov());
    }
    
    public final long compareRecord(Activity otherActivity,Record recordType) {
    	long sThis,sOther;
    	boolean isSimilar = true;
    	
    	//1º tem fixo? -> é similar?
    	if (recordType.getFixed() != null){
    		sThis  = this.get(recordType.getFixed());
    		isSimilar = recordType.similar(sThis); 
    	}
    	sThis  = this.get(recordType.getMov());
    	sOther = 0;
    	if (isSimilar){
    		
    		if(otherActivity != null){
    			sOther = otherActivity.get(recordType.getMov());
//    			if (!recordType.similar(sOther)){
//    				return 1; 
//    			}	
    		}
    		if (sOther == 0){
    			return 1;
    		}
    		
    		if (isRecordBiggerBetter(recordType)){
    			return sThis - sOther;
    		}else{
    			return (Long.MAX_VALUE - sThis) - (Long.MAX_VALUE - sOther);
    		}
    	
    	}
    	return -1;

		
	}
    
    
    /*
    public abstract String getRecordToString(int recordType);
    public abstract int getRecordSize();
    public abstract long get(int iAttr);
    public abstract void correct(int iAttr);
    public abstract long getStat(int recordType); 
    public long getStat(Record a){
    	long value = Integer.MAX_VALUE;
    	if (a.getFixed() != null){
    		//Means that a has fixed element, is similar?
    		value = this.get(a.getFixed().getAttrType());//get real ordinal
    	}
    	if ((value == Integer.MAX_VALUE) || (a.similar(value))){
    		return get(a.getMov().getAttrType());
    	}else{
    		return -1;
    	}
    }

    public long getRecordTypeValue(int recordType){
    	return getStat(recordType);
    }
  
	/**
	 * O this será o novo, o otherActivity será o actual. Assim quando não existir nenhum faz-se compareRercord(null,ac); 
	 * 
	 * return será: -1 (<0) se o this for menor, ou seja ignorar
	 * 				0 (==0) se o this for igual alterar
	 *        será +1 (>0) se o this for "maior"/"melhor", alterar 
	 */
   /* public abstract boolean isRecordBigger(int recordType);
	public final long compareRecord(Activity otherActivity,int recordType) {
		long sThis,sOther = 0;
		if (this instanceof Ciclismo && recordType == 3){
			recordType++;
			recordType--;
		}
		sThis  = this.getStat(recordType);
		
		
		
		if (sThis  == Integer.MAX_VALUE || sThis == -1){
			return -1;
		}
		if(otherActivity != null){
			sOther = otherActivity.getStat(recordType); 
		}
		if (isRecordBigger(recordType)){
			return sThis - sOther;
		}else{
			return (Long.MAX_VALUE - sThis) - sOther;
		}
		
		
	}
    */
    
    public int calculateCalories(User u) {
	    return core.CaloriesCalculation.calcCalories(this, u);
	}
    
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

    public String toString(){
        String data = new SimpleDateFormat("dd/MM/yyyy").format(this.getDate().getTime());
        StringBuilder stringb = new StringBuilder();
        stringb.append(data);
        stringb.append(", " + this.getName());
        stringb.append(", " + Util.hourFormat(this.getDuration()));
        stringb.append(", " + this.getWeather().toString());
        return stringb.toString();
    }

}
