package model.activity;



public class Corrida extends Altimetry {

	@Override
	public String getName() {
		return "Corrida";
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
		return 7.0;
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
