package model.activityHigh;

public enum Weather {
	INDOOR,
	OUTDOOR;
	
	private WeatherSun mSun;
	private WeatherWind mWind;
	
	public void setWeather(WeatherSun sun,WeatherWind wind ){
		if (this == OUTDOOR){
			mSun = sun;
			mWind = wind;
		}
	}
	public WeatherSun getSun(){return mSun;}
	public WeatherWind getWind(){return mWind;}
	
	public enum WeatherSun{
		SUN(10),
		SUNNY(7),
		CLOUY(3),
		RAIN(0);
	
		private int mLvl;
		private WeatherSun(int sunlvl) {
			mLvl = sunlvl;
		}
		public int getLvl (){return mLvl;}
	}
	
	public enum WeatherWind{
		WINDGREAT(10),
		WIND(5),
		WINDLESS(0);
	
		private int mLvl;
		private WeatherWind(int sunlvl) {
			mLvl = sunlvl;
		}
		public int getLvl (){return mLvl;}
	}
	

}
