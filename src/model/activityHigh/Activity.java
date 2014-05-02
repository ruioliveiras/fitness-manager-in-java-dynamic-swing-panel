package model.activityHigh;


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
    private long mTime; /*activity duration [s]*/
    private Weather mWeather;
    private int mHR;/*heart rate [1/min] - for calorie burn calculation*/
    
    /**TODO
     * array de strings para comentarios nas actividades?
     */
    
    public enum Genero{MASCULINO, FEMININO, NA}; /*necessario? - importar do USER?*/


    public Activity() {
        mTime = 0;
        mWeather = Weather.INDOOR;
        mHR = 0;
    }

    public Activity(long time, Weather weather, int hRate) {
        mTime = time;
        mWeather = weather;
        mHR = hRate;
    }   

    public Activity(Activity act) {
        this(act.getTime(), act.getWeather(), act.getHeartRate());
    }

    
    public void setTime(long time){mTime = time;}
    public long getTime(){return mTime;}
    public void setWeather(Weather weather){mWeather = weather;}
    public Weather getWeather(){return mWeather;}
    public void setHeartRate(int hRate){mHR = hRate;}
    public int getHeartRate(){return mHR;}

    public abstract String getName();
    public abstract int getRecordType();
    public abstract int compareRecord(Activity otherActivity);

    
    @Override
    public boolean equals (Object obj){
        if(this == obj) return true; 
        if((obj == null) || (this.getClass() != obj.getClass())) return false;
        Activity e = (Activity) obj;
        return this.mTime == e.getTime() && this.mWeather == e.getWeather() && this.mHR == e.getHeartRate();
    }
    
    /*TODO: IMPLEMENTAR toString?? */
    
    @Override
    public int hashCode(){
        return (int)mTime + mWeather.hashCode() + mHR;
    }
    
    
    
    public static int CalcHashCode(int a,int b,int c){
        return Long.valueOf((a * 31 + b) * 31 + c).hashCode();
    }
    
    public double timeInHours(){
        return (double) mTime/3600;
    }
    
    public double timeInMinutes(){
        return (double) mTime/60;
    }
    /**
     * Calories burn calculation - based on HR *******ATENTION - NEEDS TESTING*****
     * @param g gender
     * @param w weight [kg]
     * @param h height [cm]
     * @param a age [years]
     * @param hrr heart rate at rest [1/min]
     * @return net calories burn [kcal]
     */
    private int calcCaloriesHR(Genero g, int w, int h, int a, int hrr){
        double ncb, gcb, bmr, rmrcb;
        double hrm = 208.0 - 0.7*((double) a);
        double vo2m = 15.3 * hrm / ((double) hrr);
        if(g == Genero.MASCULINO){
            gcb = ((-95.7735 + (0.634 * (double) mHR) + (0.404 * vo2m) + (0.394 * (double) w) + (0.271 * (double) a))/4.184) * 60 * timeInHours();
            bmr = (13.75 * (double) w) + (5 * (double) h) - (6.76 * (double) a) + 66;
            rmrcb = bmr * 1.1 / 24 * timeInHours();
            ncb = gcb - rmrcb;
        }
        else if(g == Genero.FEMININO) {
            gcb = ((-59.3954 + (0.45 * (double) mHR) + (0.38 * (double) vo2m) + (0.103 * (double) w) + (0.274 * (double) a))/4.184) * 60 * timeInHours();
            bmr = (9.56 * (double) w) + (1.85 * (double) h) - (4.68 * (double) a) + 655;
            rmrcb = bmr * 1.1 / 24 * timeInHours();
            ncb = gcb - rmrcb; 
        }
        else ncb = 0;
        
        return (int) ncb;
    }
    
    /**
     * Calories burn calculation - based on MET *******ATENTION - NEEDS TESTING*****
     * @param g gender
     * @param w weight [kg]
     * @param h height [cm]
     * @param a age [years]
     * @param met Metabolic Equivalent of Task - to be assign by each particular activity
     * @return calories burn [kcal]
     */
    private int calcCaloriesMET(Genero g, int w, int h, int a, double met){
        double cb, cmet, rmr;
        if(g == Genero.MASCULINO){
            rmr = (66.473 + 5.0033 * (double) h + 13.7516 * (double) w - 6.755 * (double) a) / (1440 * 5 * (double) w) * 1000;
            cmet = met * 3.5 / rmr;
            cb = cmet * (double) w * timeInHours();
        }
        else if(g == Genero.FEMININO) {
            rmr = (655.0955 + 1.8496 * (double) h + 9.5634 * (double) w - 4.6756 * (double) a) / (1440 * 5 * (double) w) * 1000;
            cmet = met * 3.5 / rmr;
            cb = cmet * (double) w * timeInHours();
        }
        else cb = 0;
        
        return (int) cb;
    }
    
    public int calcCalories(Genero g, int w, int h, int a, int hrr, double met){
        int cb = 0;
        
        if(g != Genero.NA && w != 0 && h != 0 && a != 0) {
           if(hrr != 0) cb = calcCaloriesHR(g, w, h, a, hrr); 
           else if(met != 0) cb = calcCaloriesMET(g, w, h, a, met); 
           else cb = 0;
        }
            
        return cb;
    }
}
