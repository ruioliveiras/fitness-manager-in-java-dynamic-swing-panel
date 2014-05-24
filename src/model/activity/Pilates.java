package model.activity;



public class Pilates extends Ludic {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Pilates(Pilates act){
        super(act);
    }
    
	@Override
	public String getName() {
		return "Pilates";
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
		return 210;
	}

	@Override
	public double getMET() {
		return 3.0;
	}

	@Override
	public Pilates clone(){
		return new Pilates(this);
	}

	
}
