package model.activity;


public class Escalada extends Ludic {

	@Override
	public String getName() {
		return "Escalada";
	}

	@Override
	public int getRecordSize() {
		return 0;
	}

	@Override
	public int compareRecord(Activity otherActivity,int recordType) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIntensidade() {
		return 100;
	}

	@Override
	public double getMET() {
		return 8.0;
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}
}
