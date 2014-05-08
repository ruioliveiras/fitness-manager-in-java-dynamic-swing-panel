package model.activity;


public class TenisMesa extends Collective {

	@Override
	public String getName() {
		return "Tenis Mesa";
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
	public String getPointName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getMET() {
		// TODO Auto-generated method stub
		return 0;
	}

}
