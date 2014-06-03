package model.activity;

import java.io.Serializable;

public class Weather implements Serializable{
	private static final long serialVersionUID = -251648773716612805L;
	
	private Local mLocal;
    private WeatherSun mSun;
    private WeatherWind mWind;
    
    public enum Local {
        INDOOR("Indoor"),
        OUTDOOR("Outdoor");
        private String mText;
        private Local(String text) {
            mText = text;
        }
        public String getName(){return mText;} 
    }
    
    public enum WeatherSun{
        SUN(10,"Sunny"),
        CLOUD(5,"Cloudy"),
        RAIN(0,"Rainy");
    
        private int mLvl;
        private String mText;
        private WeatherSun(int sunlvl, String text) {
            mLvl = sunlvl;
            mText = text;
        }
        public int getLvl (){return mLvl;}
        public String getName(){return mText;} 
    }
    
    public enum WeatherWind{
        WINDSTRONG(0, "Strong wind"),
        WIND(5, "Moderate wind"),
        WINDLESS(10, "Windless");
    
        private int mLvl;
        private String mText;
        private WeatherWind(int sunlvl, String text) {
            mLvl = sunlvl;
            mText = text;
        }
        public int getLvl (){return mLvl;}
        public String getName(){return mText;} 
    }
    
    public Weather(){
        this.mLocal = Local.INDOOR;
        this.mSun = null;
        this.mWind = null;
    }
    
    public Weather(Local l){
        this.mLocal = l;
        this.mSun = null;
        this.mWind = null;
    }
    
    public Weather(WeatherSun s, WeatherWind w){
        this.mLocal = Local.OUTDOOR;
        this.mSun = s;
        this.mWind = w;
    }
    
    public Weather(Weather w){
        this.mLocal = w.getLocal();
        this.mSun = w.getSun();
        this.mWind = w.getWind();
    }
    
    public Local getLocal(){return this.mLocal;}
    public WeatherSun getSun(){return this.mSun;}
    public WeatherWind getWind(){return this.mWind;}
    
    public void setLocal(Local l) {this.mLocal = l;}
    public void setWeather(WeatherSun sun,WeatherWind wind ){
            if (mLocal == Local.OUTDOOR){
                mSun = sun;
                mWind = wind;
            }
        }
    
    public int getLvl(){
            if(mLocal == Local.INDOOR)
                return 20;
            return mSun.getLvl() + mWind.getLvl();
    }

    public boolean equals(Object obj){
      if(this == obj) return true; 
      if((obj == null) || (this.getClass() != obj.getClass())) return false;
      Weather e = (Weather) obj;
      return this.mLocal == e.getLocal() || (this.mSun == e.getSun() && this.mWind == e.getWind());
    }
   
    public Object clone(){return new Weather(this);}
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        if (mLocal.equals(Local.INDOOR)){
            sb.append(mLocal.getName());
        }else{
        	if (mSun!=null && mWind != null){
	            sb.append(mSun.getName());
	            sb.append(", ");
	            sb.append(mWind.getName());
        	}
        }
        
        return sb.toString();
    }
    
    
    private static Weather[] sWeather={
    	new Weather(Local.INDOOR),
    	new Weather(WeatherSun.SUN, WeatherWind.WINDLESS),
    	new Weather(WeatherSun.SUN, WeatherWind.WIND),
    	new Weather(WeatherSun.SUN, WeatherWind.WINDSTRONG),
      	new Weather(WeatherSun.CLOUD, WeatherWind.WINDLESS),
    	new Weather(WeatherSun.CLOUD, WeatherWind.WIND),
    	new Weather(WeatherSun.CLOUD, WeatherWind.WINDSTRONG),
      	new Weather(WeatherSun.RAIN, WeatherWind.WINDLESS),
    	new Weather(WeatherSun.RAIN, WeatherWind.WIND),
    	new Weather(WeatherSun.RAIN, WeatherWind.WINDSTRONG)
    };
    public static Weather[] getWeatherArray(){
    	return sWeather;
    }
    public static Weather getWeatherIndex(int index){
    	return sWeather[index];
    }
    public static int getIndexWeather(Weather weather){
    	for (int i = 0; i < sWeather.length; i++) {
			if (weather.equals(sWeather[i])){
				return i;
			}
		}
    	return -1;
    }
    
}
