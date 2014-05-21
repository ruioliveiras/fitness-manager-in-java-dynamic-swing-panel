package model.activity;

import java.util.GregorianCalendar;

import model.Record;
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
	
	
	public float getVelocity(){
		return (float)mDistance/ (float)super.getDuration();
	}



	public enum Attr implements Record.enumAttr {
		TEMPO("Tempo"),DISTANCIA("Distancia"),SPEED("Velocidade");

		private String eName;
		Attr(String name){eName = name;}
		
		@Override
		public String getName() {return eName;}
		@Override
		public int getAttrType() {return ordinal();}
	}


	public enum MyRecords implements Record{
    	MENOR_TEMPO40000("Menor tempo 40km",Attr.TEMPO,Attr.DISTANCIA,40000),
    	MENOR_TEMPO20000("Menor tempo 20km",Attr.TEMPO,Attr.DISTANCIA,20000),
    	MENOR_TEMPO10000("Menor tempo 10km",Attr.TEMPO,Attr.DISTANCIA,10000),
    	MENOR_TEMPO5000 ("Menor tempo 5km",Attr.TEMPO,Attr.DISTANCIA,5000),
    	MENOR_TEMPO3000 ("Menor tempo 3km",Attr.TEMPO,Attr.DISTANCIA,3000),
    	MENOR_TEMPO2000 ("Menor tempo 2km",Attr.TEMPO,Attr.DISTANCIA,2000),
    	MENOR_TEMPO1000 ("Menor tempo 1km",Attr.TEMPO,Attr.DISTANCIA,1000),
    	MENOR_TEMPO500  ("Menor tempo 500m",Attr.TEMPO,Attr.DISTANCIA,500),
    	MENOR_TEMPO200  ("Menor tempo 200m",Attr.TEMPO,Attr.DISTANCIA,200),
    	MENOR_TEMPO100  ("Menor tempo 100m",Attr.TEMPO,Attr.DISTANCIA,100),
    	MAXSPEED  		("Maior Velocidade",Attr.SPEED);
    	
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
	@Override
    public int getStat(int recordType) {
    	MyRecords a = MyRecords.values()[recordType];
    	return super.getStat(a);
	}
    @Override
    public int get(int iAttr) {
    	Attr a = Attr.values()[iAttr];

    	switch (a) {
    	case DISTANCIA: return getDistance();
    	case SPEED:  	return getMaxSpeed();
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
		Distance e = (Distance) obj;
		return super.equals(obj) && mDistance == e.getDistance();
	}
	
	/*TODO: IMPLEMENTAR toString?? */
	
	@Override
	public int hashCode(){
		return Util.CalcHashCode(mDistance,mMaxSpeed,super.hashCode());
	}


}
