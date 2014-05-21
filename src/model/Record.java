package model;

import model.Record.enumAttr;
import model.activity.Activity;

public interface Record {
	public interface enumAttr{
		public int ordinal();
		public int getAttrType();
		public String getName();
	}
	
	
	public int ordinal();
	public int getrecordType();
	public boolean similar(int value);
	public enumAttr getFixed();
	public enumAttr getMov();
	public String getName();
	
	
}
