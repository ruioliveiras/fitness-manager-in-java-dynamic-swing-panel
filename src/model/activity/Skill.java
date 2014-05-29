package model.activity;

import java.util.GregorianCalendar;

import model.ObjectRecord;

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
	
	public enum Attr implements ObjectRecord.enumAttr {
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
    	
		private ObjectRecord.enumAttr eFix;
		private ObjectRecord.enumAttr eMov;
		private int eValue;
		private String eName;

		MyRecords(String name,ObjectRecord.enumAttr var,ObjectRecord.enumAttr fixo,int value){
			eName = name;eFix = fixo; eMov = var; eValue = value;
		}
		MyRecords(String name,ObjectRecord.enumAttr var){
			eName = name;eMov = var;eFix = null;	eValue = -1;
		}
		@Override
		public enumAttr getFixed() {return eFix;}
		@Override
		public boolean similar(long value) 
			{return (Math.abs(value - eValue) < eValue/2);}
		@Override
		public enumAttr getMov() {return eMov;}
		@Override
		public int getrecordType() {return ordinal();}
		@Override
		public String getName() {return eName;}
		@Override
		public long getValue() {return eValue;}
    }
    
    public long getStat(int recordType) {
    	MyRecords a = MyRecords.values()[recordType];
    	return super.getStat(a);
	}
    public long get(int iAttr) {
    	Attr a = Attr.values()[iAttr];
    	switch (a) {
		case MAX_TRICK: return getMaxTrick();
		case POINTS: 	return getPoints();
    	case TEMPO:     return (int) (-1) * (getDuration() / (1000) ); //* seconds
    	default:		return -1;
    	}
	} 
    @Override
    public void correct(int recordType) {
    	//Don't need to correct nothing
	} 
    @Override
    public int getRecordSize() {
    	return MyRecords.values().length;
    }
    public String getRecordToString(int recordType){
    	Record r = MyRecords.values()[recordType];
    	Attr a = Attr.values()[r.getMov().getAttrType()];
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append(getName());sb.append(" |- ");sb.append(a.getName());
    	sb.append(" ");
    	
    	switch (a) {
		case MAX_TRICK: sb.append(getMaxTrick());sb.append(" ");sb.append(getPointName()); break;
		case POINTS: 	sb.append(getPoints());sb.append(" ");sb.append(getPointName()); break;
		default:
			break;
    	}
    	
    	return sb.toString();
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
