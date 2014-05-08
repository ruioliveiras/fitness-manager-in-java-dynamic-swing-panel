package model.activity;
import model.user.Genero;
import model.user.User;

import java.util.GregorianCalendar;


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


public abstract class Activity {
    private GregorianCalendar mDate;
    private long mTime; /*activity duration [ms]*/
    private Weather mWeather;
    private int mHearthRate;/*heart rate [1/min] - for calorie burn calculation*/
    
    /**TODO
     * array de strings para comentarios nas actividades?
     */
    

    public Activity() {
        mTime = 0;
        mWeather = Weather.INDOOR;

        mHearthRate = 0;
        mDate = new GregorianCalendar();
    }

    public Activity(long time, Weather weather, int hRate, GregorianCalendar date) {
        mTime = time;
        mWeather = weather;
        mHearthRate = hRate;
        mDate = (GregorianCalendar) date.clone();
    }   

    public Activity(Activity act) {
        this(act.getTime(), act.getWeather(), act.getHeartRate(), act.getDate());
    }

    
    public void setTime(long time){mTime = time;}
    public long getTime(){return mTime;}
    public void setWeather(Weather weather){mWeather = weather;}
    public Weather getWeather(){return mWeather;}
    public void setHeartRate(int heathRate){mHearthRate = heathRate;}
    public int getHeartRate(){return mHearthRate;}

    public GregorianCalendar getDate(){ return (GregorianCalendar) mDate.clone();}
    public void setDate(int ano, int mes, int dia){mDate = new GregorianCalendar(ano, mes, dia);}
    public void setDate(GregorianCalendar date){mDate = (GregorianCalendar) date.clone();}
    
    
    public abstract String getName();
    /*public abstract int getRecordSize();*/ /*need? to know how many different records exists?*/
    public abstract int getRecordSize();
    public abstract int compareRecord(Activity otherActivity,int recordType);
    public abstract int getIntensidade();
    public abstract double getMET(); /*for calories*/
    
    @Override
    public boolean equals (Object obj){
        if(this == obj) return true; 
        if((obj == null) || (this.getClass() != obj.getClass())) return false;
        Activity e = (Activity) obj;
        return this.mTime == e.getTime() && this.mWeather == e.getWeather() && this.mHearthRate == e.getHeartRate();
    }
    
    /*TODO: IMPLEMENTAR toString?? */
    
    @Override
    public int hashCode(){
        return CalcHashCode((int) mTime, mWeather.hashCode(),mHearthRate);
    }
    @Override
    public  abstract Object clone();
    
    
    /*a importar para um package util*/
    public static int CalcHashCode(int a,int b,int c){
        return Long.valueOf((a * 31 + b) * 31 + c).hashCode();
    }
    
    public double timeInHours(){
        return (double) timeInSeconds()/3600;
    }
    
    public double timeInMinutes(){
        return (double) timeInSeconds()/60;
    }
    
    public double timeInSeconds(){
        return (double) mTime/1000;
    }
    /**
     * Calories burn calculation - based on HR *******ATENTION - NEEDS TESTING*****
     * @param u user from User classe, namely {gender, weight, height, age, at rest heart rate}
     * @return net calories burn [kcal]
     */
    private int calcCaloriesHR(User u){
        double ncb, gcb, bmr, rmrcb;
        double hrm = 208.0 - 0.7*((double) u.getIdade());
        double vo2m = 15.3 * hrm / ((double) u.getFreqCardio());
        if(u.getGenero() == Genero.Masculino){
            gcb = ((-95.7735 + (0.634 * (double) mHearthRate) + (0.404 * vo2m) + (0.394 * (double) u.getPeso()) + (0.271 * (double) u.getIdade()))/4.184) * 60 * timeInHours();
            bmr = (13.75 * (double) u.getPeso()) + (5 * (double) u.getAltura()) - (6.76 * (double) u.getIdade()) + 66;
            rmrcb = bmr * 1.1 / 24 * timeInHours();
            ncb = gcb - rmrcb;
        }
        else if(u.getGenero() == Genero.Feminino) {
            gcb = ((-59.3954 + (0.45 * (double) mHearthRate) + (0.38 * (double) vo2m) + (0.103 * (double) u.getPeso()) + (0.274 * (double) u.getIdade()))/4.184) * 60 * timeInHours();
            bmr = (9.56 * (double) u.getPeso()) + (1.85 * (double) u.getAltura()) - (4.68 * (double) u.getIdade()) + 655;
            rmrcb = bmr * 1.1 / 24 * timeInHours();
            ncb = gcb - rmrcb; 
        }
        else ncb = 0;
        
        return (int) ncb;
    }
    
    /**
     * Calories burn calculation - based on MET *******ATENTION - NEEDS TESTING*****
     * @param u user from User classe, namely {gender, weight, height, age}
     * @return calories burn [kcal]
     */
    private int calcCaloriesMET(User u){
        double cb, cmet, rmr;
        if(u.getGenero() == Genero.Masculino){
            rmr = (66.473 + 5.0033 * (double) u.getAltura() + 13.7516 * (double) u.getPeso() - 6.755 * (double) u.getIdade()) / (1440 * 5 * (double) u.getPeso()) * 1000;
            cmet = getMET() * 3.5 / rmr;
            cb = cmet * (double) u.getPeso() * timeInHours();
        }
        else if(u.getGenero() == Genero.Feminino) {
            rmr = (655.0955 + 1.8496 * (double) u.getAltura() + 9.5634 * (double) u.getPeso() - 4.6756 * (double) u.getIdade()) / (1440 * 5 * (double) u.getPeso()) * 1000;
            cmet = getMET() * 3.5 / rmr;
            cb = cmet * (double) u.getPeso() * timeInHours();
        }
        else cb = 0;
        
        return (int) cb;
    }
    
    /**
     * Calories burn calculation - based on MET or HR *******ATENTION - NEEDS TESTING*****
     * @param u user from User classe, namely {gender, weight, height, age, hrr}
     * @return calories burn [kcal]
     */
    public int calcCalories(User u){
        int cb = 0;
        
        if(u.getGenero() != Genero.Desconhecido && u.getPeso() != 0 && u.getAltura() != 0 && u.getIdade() != 0) {
           if(u.getFreqCardio() != 0) cb = calcCaloriesHR(u); 
           else if(getMET() != 0) cb = calcCaloriesMET(u); 
           else cb = 0;
        }
            
        return cb;
    }
}
