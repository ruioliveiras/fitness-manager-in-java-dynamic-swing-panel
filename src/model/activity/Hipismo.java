package model.activity;



public class Hipismo extends Skill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "Hipismo";
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
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public double getMET() {
		return 5.5;
	}

	@Override
	public Activity clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
