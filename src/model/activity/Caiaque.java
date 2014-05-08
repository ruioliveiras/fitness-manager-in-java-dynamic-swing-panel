package model.activity;



public class Caiaque extends Distance{
    @Override
	public String getName() {
		return "Caiaque";
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
		return 5.0;
	}
}
