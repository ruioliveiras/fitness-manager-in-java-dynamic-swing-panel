package model.activityHigh;

import java.util.GregorianCalendar;

public abstract class Contest extends Activity {
	static public enum Result {WIN,LOSE,DRAW};
	
	/*Resultado do contest? */
	private int mPointRival;
	private int mPointTeam;
	private Contest.Result mResult;
	
	
	public Contest() {
		super();
		mPointRival = 0;
		mPointTeam = 0;
	}

	public Contest(long time,Weather weather,int hRate,GregorianCalendar date,int pointRival,int pointTeam,Contest.Result result) {
		super(time, weather, hRate,date);
		mPointRival = pointRival;
		mPointTeam = pointTeam;
		mResult = result;
	}   

	public Contest(Contest act) {
		this(act.getTime(),act.getWeather(),act.getHeartRate(),act.getDate(),act.getPointRival(),act.getPointTeam(),act.getResult());
	}
	
	public void setPointRival(int pointRival){mPointRival = pointRival;}
	public int getPointRival(){return mPointRival;}
	public void setPointTeam(int pointTeam){mPointTeam = pointTeam;}
	public int getPointTeam(){return mPointTeam;}
	public void setResult(Result result){mResult = result;}
	public Result getResult(){return mResult;}
	
	
	
	public abstract String getPointName(); /*Return "Goal" "points" etc..*/ 
	
	
	@Override
	public boolean equals (Object obj){
		if(this == obj) return true; 
		if((obj == null) || (this.getClass() != obj.getClass())) return false;
		Contest e = (Contest) obj;
		return super.equals(obj) && mPointRival == e.getPointRival() && mPointTeam == e.getPointRival() && mResult == e.getResult();
	}
	
	/*TODO: IMPLEMENTAR toString?? */
	
	@Override
	public int hashCode(){
		return super.hashCode() + Long.valueOf((mPointRival * 31 + mPointTeam) * 31 + mResult.hashCode()).hashCode();
	}

}
