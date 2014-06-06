package model.activity;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import model.ObjectClonable;
import model.ObjectRecordClass;
import model.ObjectRecordClass.EnumAttr;
import model.ObjectRecordClass.ObjectRecord;
import model.ObjectRecordClass.Record;
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


public abstract class Activity implements ObjectClonable,ObjectRecordClass.ObjectRecord ,Serializable {


	/**
	 * Serial id, para a Serialização
	 */
	private static final long serialVersionUID = -4202759185512132598L;
	/**
	 * Data
	 */
	private GregorianCalendar mDate;
    /**
     * Duração, em milisegundos
     */
	private long mDuration; /*activity duration [ms]*/
    /**
     * Tempo meteorologico
     */
	private Weather mWeather;
	/**
	 * Pulsações, [1/min] - for calorie burn calculation 
	 */
    private int mHearthRate;
    
   /**
    * Construtor vazio, incializa:
    *  -duração a 0,
    *  -data actual.
    */
    public Activity() {
        mDuration = 0;
        mWeather = new Weather();

        mHearthRate = 0;
        mDate = new GregorianCalendar();
    }

    /**
     * Contrutor parametrizado
     * @param time duração da actividade em milisegundos
     * @param weather Weather tempo meteorologico da actividade
     * @param hRate pulsasão da actividade
     * @param date data da actividade
     */
    public Activity(long time, Weather weather, int hRate, GregorianCalendar date) {
        mDuration = time;
        mWeather = weather;
        mHearthRate = hRate;
        mDate = (GregorianCalendar) date.clone();
    }   

    /**
     * Construtor de Copia
     * @param act actividade a copiar
     */
    public Activity(Activity act) {
        this(act.getDuration(), act.getWeather(), act.getHeartRate(), act.getDate());
    }

    /**
     *  Setter duração da actividade
     * @param time tempo em milisegundos
     */
    public void setDuration(long time)		{mDuration = time;}
    /**
     * Getter da duração da actividade 
     * 
     * @return tempo em milisegundos
     */
    public long getDuration()				{return mDuration;}
    /**
     * Getter da duração da actividade em Horas
     * @return duração da actividade em Horas
     */
    public double getDuration_inHours()		{return (double) getDuration_inSeconds()/3600;}
    /**
     * Getter da duração da actividade em minutos
     * @return duração da actividade em minutos
     */
    public double getDuration_inMinutes()	{return (double) getDuration_inSeconds()/60;}
    /**
     * Getter da duração da actividade em segundos
     * @return duração da actividade em segundos
     */
    public double getDuration_inSeconds()	{return (double) mDuration/1000;}  
    
    
    
    /**
     * Setter Tempo meteorologico,
     * @param weather Tempo meteorologico,
     */
    public void setWeather(Weather weather)	{mWeather = weather;}
    /**
     * Getter Tempo meteorologico,
     * @return weather Tempo meteorologico,
     */
    public Weather getWeather()				{return mWeather;}
    
    
    /**
     * Setter da pulsação
     * @param heathRate pulsação
     */
    public void setHeartRate(int heathRate)	{mHearthRate = heathRate;}
    /**
     * Getter da pulsação
     * @return heathRate pulsação
     */
    public int getHeartRate()				{return mHearthRate;}

    /**
     * Getter Data da actividade
     * @return GregorianCalendar com a data da actividade
     */
    public GregorianCalendar getDate()				{return (GregorianCalendar) mDate.clone();}
    /**
     * Setter data da actividade por ano mes dia
     * @param ano ano da actividade
     * @param mes mes da actividade
     * @param dia dia da actividade
     */
    public void setDate(int ano, int mes, int dia)	{mDate = new GregorianCalendar(ano, mes, dia);}
    
    /**
     * Setter da data da actividade,  
     * @param date Gregorian calendar.
     */
    public void setDate(GregorianCalendar date)		{mDate = (GregorianCalendar) date.clone();}
    
    /*                       ABSTRART                                                      */
    
    
    /**
     * Getter do nome da actividade
     * @return
     */
    public abstract String getName();

    /**
     * TODO: O que é isto?
     * @return
     */
    public abstract int getIntensidade();
    
    /**
     * Getter do MET da Actividade
     * @return
     */
    public abstract double getMET();
    
  
    
    /*Record*/
    @Override
	public abstract String getRecordToString(Record recordType);
    @Override
    public abstract int getRecordSize();
    @Override
    public abstract Record getRecord(int index);
    @Override
    public abstract long get(EnumAttr iAttr);
    @Override
    public abstract boolean isRecordBiggerBetter(Record recordType);
    @Override
    public abstract void correct(Record record);
    
    @Override
    public long getValue(Record recordType){
    	return get(recordType.getMov());
    }
    
 
    @Override
    public final long compareRecord(ObjectRecord otherActivity,Record recordType) {
    	long sThis,sOther;
    	boolean isSimilar = true;
    	
    	//1º tem fixo? -> é similar?
    	if (recordType.getFixed() != null){
    		sThis  = this.get(recordType.getFixed());
    		isSimilar = recordType.similar(sThis); 
    	}
    	
    	sOther = 0;
    	if (isSimilar){
    		Activity thisCorrected = this.clone();
        	thisCorrected.correct(recordType);
        	sThis  = thisCorrected.get(recordType.getMov());
        	
    		if(otherActivity != null){
    			sOther = otherActivity.get(recordType.getMov());
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
    
    

    /**
     * Calcula Calorias desta actividade para um User
     * @param u User que realizou a actividade
     * @return o número de calorias
     */
    public int calculateCalories(User u) {
	    return core.CaloriesCalculation.calcCalories(this, u);
	}
    
    /*                              Object statements                     */
    @Override
    public  abstract Activity clone();
    
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
