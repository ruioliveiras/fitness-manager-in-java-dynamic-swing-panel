package model;


public interface Record {
	public interface enumAttr{
		public int ordinal();
		public int getAttrType();
		public String getName();
	}
	
	
	public int ordinal();
	public int getrecordType();
	public long getValue();
	public boolean similar(long value);
	public enumAttr getFixed();
	public enumAttr getMov();
	public String getName();
	
	
}
