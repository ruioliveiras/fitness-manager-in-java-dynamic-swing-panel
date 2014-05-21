package model.activity;

import model.Record;


public abstract class Ludic extends Activity {

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
		public boolean similar(int value) 
			{return (Math.abs(value - eValue) < eValue/2);}
		@Override
		public enumAttr getMov() {return eMov;}
		@Override
		public int getrecordType() {return ordinal();}
		@Override
		public String getName() {return eName;}
    }
    
    public int getStat(int recordType) {
    	MyRecords a = MyRecords.values()[recordType];
    	return super.getStat(a);
	}
    public int get(int iAttr) {
    	Attr a = Attr.values()[iAttr];

    	switch (a) {
    	case TEMPO:     return (int) (getDuration() / (1000) ); //* seconds
    	default:		return -1;
    	}
	} 
    
    @Override
	public int getRecordSize() {
		return MyRecords.values().length;
	}
   
}
