package model.activity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import model.ObjectRecord;
import core.util.Util;

public abstract class Distance extends Activity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int mDistance;
	private int mMaxSpeed;
   
    /*map*/
	
	
	public Distance() {
		super();
		mDistance = 0;
		mMaxSpeed = 0;
	}

	public Distance(long time,Weather weather,int hearthRate,GregorianCalendar date,int distance,int maxSpeed) {
		super(time,weather,hearthRate,date);
		mMaxSpeed = maxSpeed;
		mDistance = distance;
	}   
	
	public Distance(Distance act) {
		this(act.getDuration(),act.getWeather(),act.getHeartRate(), act.getDate() ,act.getDistance(),act.getMaxSpeed());
	}
	
	public void setDistance(int distance){mDistance = distance;}
	public int getDistance(){return mDistance;}
	public void setMaxSpeed(int maxSpeed){mMaxSpeed = maxSpeed;}
	public int getMaxSpeed(){return mMaxSpeed;}
	
	/*atencao as unidades: m/s!*/
	public float getVelocity(){
		return (float)mDistance/ (float)getDuration_inSeconds();
	}



	public enum Attr implements ObjectRecord.enumAttr {
		TIME("Tempo"),DISTANCE("Distancia"),SPEED("Velocidade");

		private String eName;
		Attr(String name){eName = name;}
		
		@Override
		public String getName() {return eName;}
		@Override
		public int getAttrType() {return ordinal();}
	}
	

	public enum MyRecords implements Record{
    	MENOR_TEMPO40000("Menor tempo 40km",Attr.TIME,Attr.DISTANCE,40000),
    	MENOR_TEMPO20000("Menor tempo 20km",Attr.TIME,Attr.DISTANCE,20000),
    	MENOR_TEMPO10000("Menor tempo 10km",Attr.TIME,Attr.DISTANCE,10000),
    	MENOR_TEMPO5000 ("Menor tempo 5km",Attr.TIME,Attr.DISTANCE,5000),
    	MENOR_TEMPO3000 ("Menor tempo 3km",Attr.TIME,Attr.DISTANCE,3000),
    	MENOR_TEMPO2000 ("Menor tempo 2km",Attr.TIME,Attr.DISTANCE,2000),
    	MENOR_TEMPO1000 ("Menor tempo 1km",Attr.TIME,Attr.DISTANCE,1000),
    	MENOR_TEMPO500  ("Menor tempo 500m",Attr.TIME,Attr.DISTANCE,500),
    	MENOR_TEMPO200  ("Menor tempo 200m",Attr.TIME,Attr.DISTANCE,200),
    	MENOR_TEMPO100  ("Menor tempo 100m",Attr.TIME,Attr.DISTANCE,100),
    	MAXSPEED  		("Maior Velocidade",Attr.SPEED);
    	
		private ObjectRecord.enumAttr eFix;
		private ObjectRecord.enumAttr eMov;
		private long eValue;
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
		public long getValue() {
			return eValue;
		}
    }
	
	@Override
    public long getStat(int recordType) {
    	MyRecords a = MyRecords.values()[recordType];
    	return super.getStat(a);
	}
    @Override
    public long get(int iAttr) {
    	Attr a = Attr.values()[iAttr];

    	switch (a) {
    	case DISTANCE: return getDistance();
    	case SPEED:  	return getMaxSpeed();
    	case TIME:     return (int) (-1) * (getDuration() ); //* seconds
    	default:		return -1;
    	}
	}
    
    @Override
    public void correct(int recordType) {
    	MyRecords a = MyRecords.values()[recordType];
    	if (a == MyRecords.MAXSPEED)
    		return;
    	if (a.getMov() == Attr.DISTANCE){
    		setDuration((getDuration() * a.getValue() )/mDistance);
    		setDistance((int) a.getValue());
    	}
	} 
    
    @Override
	public int getRecordSize() {
		return MyRecords.values().length;
	}
   
    public String getRecordToString(int recordType){
    	MyRecords a = MyRecords.values()[recordType];
    	Attr att = Attr.values()[a.getMov().getAttrType()];
    	StringBuilder sb = new StringBuilder();
    	sb.append(getName());sb.append(" |- ");sb.append(a.getName());
    	sb.append(" ");
    	switch (att) {
    	case SPEED:  	sb.append(getMaxSpeed());sb.append("m/s");break;
    	case TIME:     	sb.append(Util.hourFormat(getDuration()));break;
    	default:		break;
    	}
    	return sb.toString();
    }
    
//    public String getRecordName(int recordType){
//    	return ; 
//    }

	
	
	@Override
	public boolean equals (Object obj){
		if(this == obj) return true; 
		if((obj == null) || (this.getClass() != obj.getClass())) return false;
		Distance e = (Distance) obj;
		return super.equals(obj) && mDistance == e.getDistance();
	}
	
	/*TODO: IMPLEMENTAR toString?? */
	
	@Override
	public int hashCode(){
		return Util.CalcHashCode(mDistance,mMaxSpeed,super.hashCode());
	}


}
