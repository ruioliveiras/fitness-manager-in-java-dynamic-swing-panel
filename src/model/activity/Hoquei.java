package model.activity;


public class Hoquei extends Collective {

	@Override
	public String getName() {
		return "Hoquei";
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
		return "Pontos";
	}

	@Override
	public double getMET() {
		// TODO Auto-generated method stub
		return 0;
	}

}
