package model.activity;

import java.util.GregorianCalendar;

import core.Util;

public abstract class Altimetry extends Distance {
	
	private int mAscendent;
	private int mDescendent;
	private int mMinAltitude;
	private int mMaxAltitude;
	
	
	public Altimetry() {
		super();
		mAscendent = 0;
		mDescendent = 0;
		mMinAltitude = 0;
		mMaxAltitude = 0;
	}

	public Altimetry(long time,Weather weather,int hearthRate,GregorianCalendar date,int distance,int maxSpeed,int ascendent,int descendent,int minAltitude, int maxAltitude) {
		super(time,weather,hearthRate,date,distance,maxSpeed);
		mAscendent = ascendent;
		mDescendent = descendent;
		mMinAltitude = minAltitude;
		mMaxAltitude = maxAltitude;
	}   
	
	public Altimetry(Altimetry act) {
		this(act.getDuration(),act.getWeather(),act.getHeartRate(),act.getDate(),act.getDistance(),act.getMaxSpeed(),act.getAscendent(),act.getDescendent(),act.getMinAltitude(),act.getMaxAltitude());
	}
	
	public void setAscendent(int ascendent){mAscendent = ascendent;}
	public int getAscendent(){return mAscendent;}
	public void setDescendent(int descendent){mDescendent = descendent;}
	public int getDescendent(){return mDescendent;}
	public void setMinAltitude(int minAltitude){mMinAltitude = minAltitude;}
	public int getMinAltitude(){return mMinAltitude;}
	public void setMaxAltitude(int maxAltitude){mMaxAltitude = maxAltitude;}
	public int getMaxAltitude(){return mMaxAltitude;}
	

	
	@Override
	public boolean equals (Object obj){
		if(this == obj) return true; 
		if((obj == null) || (this.getClass() != obj.getClass())) return false;
		Altimetry e = (Altimetry) obj;
		return super.equals(obj) && mAscendent == e.getAscendent() && mDescendent == e.getDescendent() 
				&& mMaxAltitude == e.getMaxAltitude() && mMinAltitude == e.getMinAltitude();
	}
	
	/*TODO: IMPLEMENTAR toString?? */
	
	@Override
	public int hashCode(){
		return Util.CalcHashCode(mAscendent,mDescendent,mMinAltitude) + Util.CalcHashCode(mMaxAltitude, super.hashCode(), 0);
	}

	
	
	
}
