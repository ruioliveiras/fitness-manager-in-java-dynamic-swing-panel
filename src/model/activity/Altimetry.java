package model.activity;

import java.util.GregorianCalendar;

import model.Record;
import core.util.Util;

public abstract class Altimetry extends Distance {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum Attr implements Record.enumAttr {
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
		SUBIDA_RAPIDA500("Subida 500m",Distance.Attr.TEMPO,Attr.ALTURA,500),
		SUBIDA_RAPIDA300("Subida 300m",Distance.Attr.TEMPO,Attr.ALTURA,300),
		SUBIDA_RAPIDA200("Subida 200m",Distance.Attr.TEMPO,Attr.ALTURA,200),
		SUBIDA_RAPIDA50("Subida 50m",Distance.Attr.TEMPO,Attr.ALTURA,50);

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
		public int getrecordType() {return ordinal() + Distance.MyRecords.values().length;}
		@Override
		public String getName() {return eName;}
    }
    
    
    public int getStat(int recordType) {
    	if (recordType < super.getRecordSize()){
    		return super.getStat(recordType);
    	}else{
    		Record a = MyRecords.values()[recordType - super.getRecordSize()];
    		return getStat(a);
    	}
	}
    

    /*activity*/
	@Override 
	public int compareRecord(Activity otherActivity,int recordType) {
		int sThis,sOther = 0;
		sThis  = this.getStat(recordType);
		
		if (sThis < 0){
			return -1;
		}
		if(otherActivity != null){
			sOther = ((Distance) otherActivity).getStat(recordType); //Check this cast?
		}
		return sThis - sOther;
	}
	@Override
	public int getRecordSize() {
		return super.getRecordSize() + MyRecords.values().length;
	}
	
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
