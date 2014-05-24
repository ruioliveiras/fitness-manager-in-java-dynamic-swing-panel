package model.activity;



public class Danca extends Ludic {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Danca(Danca act){
        super(act);
    }
	
	@Override
	public String getName() {
		return "Dança";
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
	public double getMET() {
		return 7.8;
	}

	@Override
	public Danca clone() {
		return new Danca(this);
	}

}
