package model.activity;

import java.util.GregorianCalendar;

public abstract class Skill extends Activity {
	/*Resultado do contest? */
	private int mPoints;
	private int mMaxTrick;
	
	
	public Skill() {
		super();
		mPoints = 0;
		mMaxTrick = 0;
	}

	public Skill(long time,Weather weather,int hearthRate,GregorianCalendar date,int points,int maxTrick) {
		super(time,weather,hearthRate,date);
		mPoints = points;
		mMaxTrick = maxTrick;
	}   

	public Skill(Skill act) {
		this(act.getDuration(),act.getWeather(),act.getHeartRate(),act.getDate(),act.getPoints(),act.getMaxTrick());
	}
	
	public void setPoints(int points){mPoints = points;}
	public int getPoints(){return mPoints;}
	public void setMaxTrick(int maxTrick){mMaxTrick = maxTrick;}
	public int getMaxTrick(){return mMaxTrick;}
	
	
	/* Faz sentido???*/
	public abstract String getPointName(); /*Return "Goal" "points" etc..*/ 
	
	
	@Override
	public boolean equals (Object obj){
		if(this == obj) return true; 
		if((obj == null) || (this.getClass() != obj.getClass())) return false;
		Skill e = (Skill) obj;
		return super.equals(obj) && mPoints == e.getPoints() && mMaxTrick == e.getMaxTrick();
	}
	
	/*TODO: IMPLEMENTAR toString?? */
	
	@Override
	public int hashCode(){
		return Long.valueOf((mPoints * 31 + mMaxTrick) * 31 + super.hashCode() ).hashCode();
	}

	
}
