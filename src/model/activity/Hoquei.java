package model.activity;



public class Hoquei extends Collective {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		return 81;
	}

	@Override
	public String getPointName() {
		return "Pontos";
	}

	@Override
	public double getMET() {
		return 7.9;
	}

	@Override
	public Activity clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
