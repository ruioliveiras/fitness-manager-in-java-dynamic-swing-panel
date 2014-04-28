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
	public enum Local{INDOOR,OUTDOOR}
	private long mTime;
	
	
	public abstract String getName();
	
	public void setTime(long time){mTime = time;}
	public long getTime(){return mTime;}
	
	
	@Override
	public boolean equals (Object obj){
		return false;
	}
	
	@Override
	public Object clone(){
		return null;
	}
	
}
