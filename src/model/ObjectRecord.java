package model;

import model.activity.Activity;

public interface ObjectRecord {
	public String getRecordToString(Record recordType);
	public  int getRecordSize();
	public Record getRecord(int index);
	public long get(EnumAttr iAttr);
	public long getValue(Record recordType);
	public long compareRecord(Activity otherActivity,Record recordType);
	public void correct(Record record);
	public boolean isRecordBiggerBetter(Record recordType);
   
	
	
	/*public String getRecordToString(int recordType);
	public String getRecordName(int recordType);
	public int getRecordSize();
	public long get(int iAttr);
	public void correct(int iAttr);
	abstract long getStat(int recordType); 
	abstract long getStat(Record a);
	public long getRecordTypeValue(int recordType);
	public long compareRecord(Activity otherActivity,int recordType);
    public boolean isRecordBigger(int recordType);
	*/
	
	public interface Record {
	    public int ordinal();
		public int getrecordType();
		public long getValue();
		public boolean similar(long value);
		public EnumAttr getFixed();
		public EnumAttr getMov();
		public String getName();	
	}
	
	public interface EnumAttr{
		public int ordinal();
		public int getAttrType();
		public String getName();
	}
	
}
