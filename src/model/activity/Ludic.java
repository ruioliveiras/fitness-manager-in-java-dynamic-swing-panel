package model.activity;

import java.util.GregorianCalendar;

import model.ObjectRecord;
import core.util.Util;


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

	public enum Attr implements ObjectRecord.EnumAttr {
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
		if (att.getName().equals(Attr.TEMPO.getName())){
			return getDuration();
		}else{
			return -1;
		}
	}

	@Override
    public boolean isRecordBiggerBetter(Record recordType){
		return true;
	}
	
    @Override
    public void correct(Record recordType) {	
    	//no need to correct
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
    	
    	if (att.getName().equals(Attr.TEMPO.getName())){
    		sb.append(Util.hourFormat(getDuration()));
    	}

    	return sb.toString();
    }

}
