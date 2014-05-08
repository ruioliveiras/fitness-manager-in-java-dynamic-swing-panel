package model.activity;

import java.util.GregorianCalendar;

public abstract class Distance extends Activity {
	private int mDistance;
	private int mMaxSpeed;
	
	public Distance() {
		super();
		mDistance = 0;
		mMaxSpeed = 0;
	}

	public Distance(long time,Weather weather,int hearthRate,GregorianCalendar date,int distance,int maxSpeed) {
		super(time,weather,hearthRate,date);
		mMaxSpeed = maxSpeed;
		mDistance = distance;
	}   
	
	public Distance(Distance act) {
		this(act.getTime(),act.getWeather(),act.getHeartRate(), act.getDate() ,act.getDistance(),act.getMaxSpeed());
	}
	
	public void setDistance(int distance){mDistance = distance;}
	public int getDistance(){return mDistance;}
	public void setMaxSpeed(int maxSpeed){mMaxSpeed = maxSpeed;}
	public int getMaxSpeed(){return mMaxSpeed;}
	
	
	public float getVelocity(){
		return (float)mDistance/ (float)super.getTime();
	}
	
	
	@Override
	public boolean equals (Object obj){
		if(this == obj) return true; 
		if((obj == null) || (this.getClass() != obj.getClass())) return false;
		Distance e = (Distance) obj;
		return super.equals(obj) && mDistance == e.getDistance();
	}
	
	/*TODO: IMPLEMENTAR toString?? */
	
	@Override
	public int hashCode(){
		return CalcHashCode(mDistance,mMaxSpeed,super.hashCode());
	}


}
