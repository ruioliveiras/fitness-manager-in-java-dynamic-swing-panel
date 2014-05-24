package model.activity;

public enum Weather {
    INDOOR("Indoor"),
    OUTDOOR("Outdoor");
    
    private String mText;
    private WeatherSun mSun;
    private WeatherWind mWind;
    
    private Weather(String text) {
        mText = text;
    }
    
    public void setWeather(WeatherSun sun,WeatherWind wind ){
        if (this == OUTDOOR){
            mSun = sun;
            mWind = wind;
        }
    }
    public WeatherSun getSun(){return mSun;}
    public WeatherWind getWind(){return mWind;}
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        if (this.equals(INDOOR)){
            sb.append(mText);
        }else{
            sb.append("Weather: ");sb.append("Weather: ");
        }
        
        return null;
    }
    
    public enum WeatherSun{
        SUN(10),
        CLOUD(5),
        RAIN(0);
    
        private int mLvl;
        private WeatherSun(int sunlvl) {
            mLvl = sunlvl;
        }
        public int getLvl (){return mLvl;}
    }
    
    public enum WeatherWind{
        WINDSTRONG(0),
        WIND(5),
        WINDLESS(10);
    
        private int mLvl;
        private WeatherWind(int sunlvl) {
            mLvl = sunlvl;
        }
        public int getLvl (){return mLvl;}
    }
    
    public int getLvl(){
        if(this == Weather.INDOOR)
            return 20;
        return this.mSun.getLvl() + this.mWind.getLvl();
    }
}
