package model.activity;


public class Basquetebol extends Collective {

	@Override
	public String getName() {
		return "Basquetebol";
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
		return "Pontos";
	}

	@Override
	public double getMET() {
		// TODO Auto-generated method stub
		return 0;
	}

}
