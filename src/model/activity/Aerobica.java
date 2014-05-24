package model.activity;



public class Aerobica extends Ludic {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "Aeróbica";
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
		return 86;
	}

	@Override
	public double getMET() {
		return 7.3;
	}

	@Override
	public Activity clone() {
		// TODO Auto-generated method stub
		return null;
	}
}

