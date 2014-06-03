package model.activity;

import java.util.GregorianCalendar;

import model.ObjectRecordClass;
import model.ObjectRecordClass.EnumAttr;
import model.ObjectRecordClass.Record;
import core.util.Util;

public abstract class Altimetry extends Distance {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	
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
	

	
	
	
	public enum Attr implements ObjectRecordClass.EnumAttr {
		ALTURA("Altura");

		private String eName;
		Attr(String name){eName = name;}
		
		@Override
		public String getName() {return eName;}
		@Override
		public int getAttrType() {return ordinal() + Distance.Attr.values().length;}
	}


	/*Como esta herda tenho que "adicionar estes no outro" ou seja ter total"*/
	public enum MyRecords implements Record{
		MAIOR_ALTURA("Maior Altura",Attr.ALTURA),
		SUBIDA_RAPIDA500("Subida 500m",Distance.Attr.TIME,Attr.ALTURA,500),
		SUBIDA_RAPIDA300("Subida 300m",Distance.Attr.TIME,Attr.ALTURA,300),
		SUBIDA_RAPIDA200("Subida 200m",Distance.Attr.TIME,Attr.ALTURA,200),
		SUBIDA_RAPIDA50("Subida 50m",Distance.Attr.TIME,Attr.ALTURA,50);

		private ObjectRecordClass.EnumAttr eFix;
		private ObjectRecordClass.EnumAttr eMov;
		private int eValue;
		private String eName;

		MyRecords(String name,ObjectRecordClass.EnumAttr var,ObjectRecordClass.EnumAttr fixo,int value){
			eName = name;eFix = fixo; eMov = var; eValue = value;
		}
		MyRecords(String name,ObjectRecordClass.EnumAttr var){
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
		public int getrecordType() {return ordinal() + Distance.MyRecords.values().length;}
		@Override
		public String getName() {return eName;}
		@Override
		public long getValue() {
			return eValue;
		}
    }

	
	@Override
	public long get(EnumAttr att){
		
		if (att.getName().equals(Attr.ALTURA.getName())){
			return getAscendent()-getDescendent();
		}
		return super.get(att);
	}

	@Override
    public boolean isRecordBiggerBetter(Record recordType){
		if (recordType.getrecordType() < super.getRecordSize()){
    		return super.isRecordBiggerBetter(recordType);
    	}
		//all are biggerbetter
    	return true;
    }
		
    @Override
    public void correct(Record recordType) {	
    	if (recordType.getName().equals(MyRecords.MAIOR_ALTURA.getName()))
    		return;
    	if (recordType.getMov().getName().equals(Attr.ALTURA.getName())){
    		setDuration((getDuration() * recordType.getValue() )/(getAscendent()-getDescendent()));		
    		//a.getValue == getAcendt - getDescendent;
    		setAscendent((int) (recordType.getValue() + getDescendent()));
    		return;
    	}
    	super.correct(recordType);
	} 
    
    @Override
	public int getRecordSize() {
		return super.getRecordSize() + MyRecords.values().length;
	}
   @Override
    public Record getRecord(int index){
	   	if (index < super.getRecordSize()){
	   		return super.getRecord(index);
	   	}
    	return MyRecords.values()[index - super.getRecordSize()];
    }
    @Override
    public String getRecordToString(Record recordType){
    	if (recordType.getrecordType() < super.getRecordSize()){
    		return super.getRecordToString(recordType);
    	}
    	
    	EnumAttr att = recordType.getMov();
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append(getName());sb.append(" |- ");sb.append(recordType.getName());
    	sb.append(" ");
    	
		if (att.getName().equals(Attr.ALTURA.getName())){
			sb.append(getMaxAltitude());sb.append("m");
		}else if (att.getName().equals(Attr.ALTURA.getName())){
			sb.append(getMaxSpeed());sb.append(Util.hourFormat(getDuration()));
		}
		return sb.toString();
    }

 
 

	
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
