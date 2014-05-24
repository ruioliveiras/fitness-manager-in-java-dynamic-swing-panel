package model.activity;



public class Ioga extends Ludic {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ioga(Ioga act){
        super(act);
    }
	
	@Override
	public String getName() {
		return "Ioga";
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
		return 158;
	}
	
	@Override
	public double getMET() {
		return 4.0;
	}

	@Override
	public Ioga clone() {
		return new Ioga(this);
	}

}
