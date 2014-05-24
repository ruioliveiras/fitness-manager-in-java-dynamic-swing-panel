package model.activity;



public class Mergulho extends Ludic {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Mergulho(Mergulho act){
        super(act);
    }
	
	@Override
	public String getName() {
		return "Mergulho";
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
		return 90;
	}

	@Override
	public double getMET() {
		return 7.0;
	}

	@Override
	public Mergulho clone() {
		return new Mergulho(this);
	}
}
