package model.activity;


public class Golfe extends Skill {

	@Override
	public String getName() {
		return "Golfe";
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
	public String getPointName() {
		return "nº tacadas";
	}

	@Override
	public double getMET() {
		// TODO Auto-generated method stub
		return 0;
	}

}
