package model.activity;


public class Mergulho extends Ludic {

	@Override
	public String getName() {
		return "Mergulho";
	}

	@Override
	public int getRecordType() {
		return 0;
	}

	@Override
	public int compareRecord(Activity otherActivity) {
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
}
