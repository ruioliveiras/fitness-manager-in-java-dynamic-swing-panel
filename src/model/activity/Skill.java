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
	
	public enum Attr implements ObjectRecord.EnumAttr {
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
    	
		private ObjectRecord.EnumAttr eFix;
		private ObjectRecord.EnumAttr eMov;
		private int eValue;
		private String eName;

		MyRecords(String name,ObjectRecord.EnumAttr var,ObjectRecord.EnumAttr fixo,int value){
			eName = name;eFix = fixo; eMov = var; eValue = value;
		}
		MyRecords(String name,ObjectRecord.EnumAttr var){
			eName = name;eMov = var;eFix = null;	eValue = -1;
		}
		@Override
		public EnumAttr getFixed() {return eFix;}
		@Override
		public boolean similar(long value) 
			{return (Math.abs(value - eValue) < eValue/2);}
		@Override
		public EnumAttr getMov() {return eMov;}
		@Override
		public int getrecordType() {return ordinal();}
		@Override
		public String getName() {return eName;}
		@Override
		public long getValue() {return eValue;}
    }
    

	    
	@Override
	public long get(EnumAttr att){
	
		if (att.getName().equals(Attr.MAX_TRICK.getName())){
			return getMaxTrick();
		}else if (att.getName().equals(Attr.POINTS.getName())){
			return getPoints();
		}else if (att.getName().equals(Attr.TEMPO.getName())){
			return getDuration();
		}else {
			return -1;
		}
	}
	@Override
    public boolean isRecordBiggerBetter(Record recordType){
    	return true;
    }
	
    @Override
    public void correct(Record recordType) {	
    	//DOnt need
    } 
    
    @Override
	public int getRecordSize() {
		return MyRecords.values().length;
	}
   @Override
    public Record getRecord(int index){
    	return MyRecords.values()[index];
    }
   
    @Override
    public String getRecordToString(Record recordType){
    	EnumAttr att = recordType.getMov();
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append(getName());sb.append(" |- ");sb.append(recordType.getName());
    	sb.append(" ");
    	if (att.getName().equals(Attr.MAX_TRICK.getName())){
    		sb.append(getMaxTrick());sb.append(" ");sb.append(getPointName());
    	}else if (att.getName().equals(Attr.POINTS.getName())){
    		sb.append(getPoints());sb.append(" ");sb.append(getPointName());
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
