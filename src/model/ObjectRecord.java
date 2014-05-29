package model;

import model.activity.Activity;

public interface ObjectRecord {
	public String getRecordToString(int recordType);
	public int getRecordSize();
	public long get(int iAttr);
	public void correct(int iAttr);
	public long getStat(int recordType); 
	public long getStat(Record a);
	public long getRecordTypeValue(int recordType);
	public long compareRecord(Activity otherActivity,int recordType);
	
	
	
	public interface Record {
	
		public int ordinal();
		public int getrecordType();
		public long getValue();
		public boolean similar(long value);
		public enumAttr getFixed();
		public enumAttr getMov();
		public String getName();
		
		
	}
	
	public interface enumAttr{
		public int ordinal();
		public int getAttrType();
		public String getName();
	}
	
}
