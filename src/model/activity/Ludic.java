package model.activity;

import java.util.GregorianCalendar;

import model.Record;


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

	public enum Attr implements Record.enumAttr {
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
   
}
