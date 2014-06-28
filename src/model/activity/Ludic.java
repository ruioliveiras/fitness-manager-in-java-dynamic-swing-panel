package model.activity;

import java.util.GregorianCalendar;

import model.Activity;
import model.objectEnum.Weather;
import model.objectInterface.ObjectRecordClass;
import model.objectInterface.ObjectRecordClass.EnumAttr;
import model.objectInterface.ObjectRecordClass.Record;
import core.util.Util;


public abstract class Ludic extends Activity {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;

	
    /**
     * Construtor vazio
     */
	public Ludic() {
		super();
	}
	
    /**
     * Construtor de Copia
     * @param act actividade a copiar
     */
	public Ludic(Activity act) {
		super(act);
	}
	

    /**
     * Contrutor parametrizado
     * @param time duração da actividade em milisegundos
     * @param weather Weather tempo meteorologico da actividade
     * @param hRate pulsasão da actividade
     * @param date data da actividade
     */
	public Ludic(long time, Weather weather, int hRate, GregorianCalendar date) {
		super(time, weather, hRate, date);
	}

	
	/**
	 * Enumeração que representa os Atributos das actividades
	 */
	public enum Attr implements ObjectRecordClass.EnumAttr {
		TEMPO("Tempo");

		private String eName;
		Attr(String name){eName = name;}
		
		@Override
		public String getName() {return eName;}
		@Override
		public int getAttrType() {return ordinal();}
	}

	/**
	 * Enumeração para os tipos de Recordes
	 */
	public enum MyRecords implements Record{
		TIME ("Tempo de duracao",Attr.TEMPO);
    	
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
