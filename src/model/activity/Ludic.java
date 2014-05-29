package model.activity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import model.ObjectRecord;


public abstract class Ludic extends Activity {

	
	
	
	public Ludic() {
		super();
	}
	public Ludic(Activity act) {
		super(act);
	}
	public Ludic(long time, Weather weather, int hRate, GregorianCalendar date) {
		super(time, weather, hRate, date);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum Attr implements ObjectRecord.enumAttr {
		TEMPO("Tempo");

		private String eName;
		Attr(String name){eName = name;}
		
		@Override
		public String getName() {return eName;}
		@Override
		public int getAttrType() {return ordinal();}
	}


	public enum MyRecords implements Record{
       	TIME ("Tempo de duracao",Attr.TEMPO);
    	
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
    	case TEMPO:     return (int) (getDuration() / (1000) ); //* seconds
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
    	
    	Calendar c = Calendar.getInstance();
    	c.set(Calendar.HOUR_OF_DAY, 0);
    	c.set(Calendar.MINUTE, 0);
    	c.set(Calendar.SECOND, 0);
    	c.set(Calendar.MILLISECOND, 0);
    	c.setTimeInMillis(c.getTimeInMillis() + getDuration());
    	DateFormat df = new SimpleDateFormat("HH:mm:ss.SSS");
    	
    	switch (a) {
		case TEMPO:   sb.append(df.format(c.getTime()));break;
		default:
			break;
    	}
    	
    	return sb.toString();
    }
   
}
