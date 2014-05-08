package model.activity;


public class CorridaOrientacao extends Distance {

	@Override
	public String getName() {
		return "Corrida de Orientação";
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
		return 9.0;
	}

}
