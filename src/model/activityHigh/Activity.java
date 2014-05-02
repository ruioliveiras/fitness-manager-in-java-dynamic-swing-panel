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
 * 					Activity
 * 			Contest					Skill		Distance			Ludic
 * 	Individual  	Collective		(Skate)			altimetry 		s(Zumba)
 * (KickBox)		(FootBall)
 */


public abstract class Activity {
	private long mTime;
	private Weather mWeather;


	public Activity() {
		mTime = 0;
		mWeather = Weather.INDOOR;
	}

	public Activity(long time,Weather weather) {
		mTime = time;
		mWeather = weather;
	}   

	public Activity(Activity act) {
		this(act.getTime(),act.getWeather());
	}

	
	public void setTime(long time){mTime = time;}
	public long getTime(){return mTime;}
	public void setWeather(Weather weather){mWeather = weather;}
	public Weather getWeather(){return mWeather;}

	public abstract String getName();
	public abstract int calcCalories();
	public abstract int getRecordType();
	public abstract int compareRecord(Activity otherActivity);

	
	@Override
	public boolean equals (Object obj){
		if(this == obj) return true; 
		if((obj == null) || (this.getClass() != obj.getClass())) return false;
		Activity e = (Activity) obj;
		return this.mTime == e.getTime() && this.mWeather == e.getWeather();
	}
	
	/*TODO: IMPLEMENTAR toString?? */
	
	@Override
	public int hashCode(){
		return (int)mTime + mWeather.hashCode();
	}
	
	
	
	public static int CalcHashCode(int a,int b,int c){
		return Long.valueOf((a * 31 + b) * 31 + c).hashCode();
	}
}
