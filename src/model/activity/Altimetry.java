package model.activity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import model.Record;
import model.Record.enumAttr;
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
		SUBIDA_RAPIDA500("Subida 500m",Distance.Attr.TIME,Attr.ALTURA,500),
		SUBIDA_RAPIDA300("Subida 300m",Distance.Attr.TIME,Attr.ALTURA,300),
		SUBIDA_RAPIDA200("Subida 200m",Distance.Attr.TIME,Attr.ALTURA,200),
		SUBIDA_RAPIDA50("Subida 50m",Distance.Attr.TIME,Attr.ALTURA,50);

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
		public boolean similar(long value) 
			{return (Math.abs(value - eValue) < eValue/2);}
		@Override
		public enumAttr getMov() {return eMov;}
		@Override
		public int getrecordType() {return ordinal() + Distance.MyRecords.values().length;}
		@Override
		public String getName() {return eName;}
		@Override
		public long getValue() {
			return eValue;
		}
    }
    public String getRecordToString(int recordType){
    	if (recordType < super.getRecordSize()){
    		return super.getRecordToString(recordType);
    	}else{
    		Record a = MyRecords.values()[recordType - super.getRecordSize()];
    		
    		StringBuilder sb = new StringBuilder();
    		sb.append(getName());sb.append(" |- ");sb.append(a.getName());
    		sb.append(" ");
    		Calendar c = Calendar.getInstance();
    		c.set(Calendar.HOUR_OF_DAY, 0);
    		c.set(Calendar.MINUTE, 0);
    		c.set(Calendar.SECOND, 0);
    		c.set(Calendar.MILLISECOND, 0);
    		c.setTimeInMillis(c.getTimeInMillis() + getDuration());
    		DateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");
    		enumAttr att = a.getMov();


    		if (att.getName().equals(Attr.ALTURA.getName())){
    			sb.append(getMaxAltitude());sb.append("m");
    		}else if (att.getName().equals(Attr.ALTURA.getName())){
    			sb.append(getMaxSpeed());sb.append(df.format(c));
    		}
    		return sb.toString();
    	}
    }
    
    public long getStat(int recordType) {
    	if (recordType < super.getRecordSize()){
    		return super.getStat(recordType);
    	}else{
    		Record a = MyRecords.values()[recordType - super.getRecordSize()];
    		return getStat(a);
    	}
	}
    @Override
    public long get(int iAttr) {
    	if (iAttr < Distance.Attr.values().length)
    		return super.get(iAttr);

    	Attr a = Attr.values()[iAttr - Distance.Attr.values().length];

    	switch (a) {
    	case ALTURA: return getAscendent()-getDescendent();
    	default:		return -1;
    	}

	}
    @Override
    public void correct(int recordType) {
    	if (recordType < super.getRecordSize()){
    		super.correct(recordType);
    		return;
    	}
    	
    	MyRecords a = MyRecords.values()[recordType];
    	if (a == MyRecords.MAIOR_ALTURA)
    		return;
    	if (a.getMov() == Attr.ALTURA){
    		setDuration((getDuration() * a.getValue() )/(getAscendent()-getDescendent()));		
    		//a.getValue == getAcendt - getDescendent;
    		setAscendent((int) (a.getValue() + getDescendent()));
    	}
	} 

    /*activity*/
	@Override 
	public long compareRecord(Activity otherActivity,int recordType) {
		long sThis,sOther = 0;
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
