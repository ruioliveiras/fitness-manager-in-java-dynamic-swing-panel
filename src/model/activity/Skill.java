package model.activity;

import java.util.GregorianCalendar;

import model.Record;

public abstract class Skill extends Activity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	
	public abstract String getPointName(); /*Return "Goal" "points" etc..*/ 
	
	public enum Attr implements Record.enumAttr {
		TEMPO("Tempo"),POINTS("Pontos"),MAX_TRICK("Melhor truque");

		private String eName;
		Attr(String name){eName = name;}
		
		@Override
		public String getName() {return eName;}
		@Override
		public int getAttrType() {return ordinal();}
	}


	public enum MyRecords implements Record{
    	MAX_TRICK("Melhor Trick",Attr.MAX_TRICK),
    	MAX_PONTOS("Maior Pontos",Attr.POINTS);
    	
		private Record.enumAttr eFix;
		private Record.enumAttr eMov;
		private int eValue;
		private String eName;

		MyRecords(String name,Record.enumAttr var,Record.enumAttr fixo,int value){
			eName = name;eFix = fixo; eMov = var; eValue = value;
		}
		MyRecords(String name,Record.enumAttr var){
			eName = name;eMov = var;eFix = null;	eValue = -1;
		}
		@Override
		public enumAttr getFixed() {return eFix;}
		@Override
		public boolean similar(int value) 
			{return (Math.abs(value - eValue) < eValue/2);}
		@Override
		public enumAttr getMov() {return eMov;}
		@Override
		public int getrecordType() {return ordinal();}
		@Override
		public String getName() {return eName;}
    }
    
    public int getStat(int recordType) {
    	MyRecords a = MyRecords.values()[recordType];
    	return super.getStat(a);
	}
    public int get(int iAttr) {
    	Attr a = Attr.values()[iAttr];
    	switch (a) {
		case MAX_TRICK: return getMaxTrick();
		case POINTS: 	return getPoints();
    	case TEMPO:     return (int) (getDuration() / (1000) ); //* seconds
    	default:		return -1;
    	}
	} 
    
    @Override
	public int getRecordSize() {
		return MyRecords.values().length;
	}

	
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
