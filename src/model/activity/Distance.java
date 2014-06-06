package model.activity;

import java.util.GregorianCalendar;

import model.ObjectRecordClass;
import model.ObjectRecordClass.EnumAttr;
import model.ObjectRecordClass.Record;
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



	public enum Attr implements ObjectRecordClass.EnumAttr {
		TIME("Tempo"),DISTANCE("Distancia"),SPEED("Velocidade");

		private String eName;
		Attr(String name){eName = name;}
		
		@Override
		public String getName() {return eName;}
		@Override
		public int getAttrType() {
			int aux = ordinal(); 
			return aux;}
	}
	

	public enum MyRecords implements Record{
    	MENOR_TEMPO40000("Menor tempo 40km",Attr.TIME,Attr.DISTANCE,40000),
    	MENOR_TEMPO20000("Menor tempo 20km",Attr.TIME,Attr.DISTANCE,20000),
    	MENOR_TEMPO10000("Menor tempo 10km",Attr.TIME,Attr.DISTANCE,10000),
    	MENOR_TEMPO5000 ("Menor tempo 5km",Attr.TIME,Attr.DISTANCE,5000),
    	MENOR_TEMPO3000 ("Menor tempo 3km",Attr.TIME,Attr.DISTANCE,3000),
    	MENOR_TEMPO1500 ("Menor tempo 1.5km",Attr.TIME,Attr.DISTANCE,1500),
    	MENOR_TEMPO800 ("Menor tempo 800m",Attr.TIME,Attr.DISTANCE,800),
    	MENOR_TEMPO400  ("Menor tempo 400m",Attr.TIME,Attr.DISTANCE,400),
    	MENOR_TEMPO200  ("Menor tempo 200m",Attr.TIME,Attr.DISTANCE,200),
    	MENOR_TEMPO100  ("Menor tempo 100m",Attr.TIME,Attr.DISTANCE,100),
    	MAXSPEED  		("Maior Velocidade",Attr.SPEED);
    	
		private ObjectRecordClass.EnumAttr eFix;
		private ObjectRecordClass.EnumAttr eMov;
		private long eValue;
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
		public boolean similar(long value) {
			if (value <  eValue){
				return false;
			}else return (Math.abs(value) < eValue*2);
		}
		@Override
		public EnumAttr getMov() {return eMov;}
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
	public long get(EnumAttr att){
	
		if (att.getName().equals(Attr.DISTANCE.getName())){
			return getDistance();
		}else if (att.getName().equals(Attr.SPEED.getName())){
			return (long) getVelocity();
		}else if (att.getName().equals(Attr.TIME.getName())){
			return getDuration();
		}else {
			return -1;
		}
	}
	@Override
    public boolean isRecordBiggerBetter(Record recordType){
		EnumAttr att = recordType.getMov();
		
		if (att.getName().equals(Attr.TIME.getName())){
			return false;
		}
    	return true;
    }
	
    @Override
    public void correct(Record recordType) {	
    	if (recordType.getName().equals(MyRecords.MAXSPEED.getName()))
    		return;
    	if (recordType.getMov().getName().equals(Attr.TIME.getName())){
    		setDuration((getDuration() * recordType.getValue() )/mDistance);
    		setDistance((int) recordType.getValue());
    	}
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
    	if (att.getName().equals(Attr.SPEED.getName())){
    		sb.append(getVelocity());sb.append("m/s");
    	}else if (att.getName().equals(Attr.TIME.getName())){
    		sb.append(Util.hourFormat(getDuration()));
    	}

    	return sb.toString();
    }
/*	
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
    	case TIME:     return (getDuration() ); //* seconds
    	default:		return -1;
    	}
	}
    
    //em activity> modificaf
    public boolean isRecordBigger(int recordType){
    	MyRecords a = MyRecords.values()[recordType];
    	Attr attr = Attr.values()[a.getMov().getAttrType()];
    	
    	switch (attr) {
    	case DISTANCE: return true;
    	case SPEED:  	return true;
    	case TIME:     return false; //* seconds
    	default:		return false;
    	}
    	
    }*/
    

    

	
	
	@Override
	public boolean equals (Object obj){
		if(this == obj) return true; 
		if((obj == null) || (this.getClass() != obj.getClass())) return false;
		Distance e = (Distance) obj;
		return super.equals(obj) && mDistance == e.getDistance();
	}
	
	public String getRecordName(int recordType){
		Record a = MyRecords.values()[recordType ];
		return a.getName();
    }
	
	/*TODO: IMPLEMENTAR toString?? */
	
	@Override
	public int hashCode(){
		return Util.CalcHashCode(mDistance,mMaxSpeed,super.hashCode());
	}


}
