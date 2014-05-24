package model.activity;



public class Vela extends Distance {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Vela(Vela act){
        super(act);
    }
	
	@Override
	public String getName() {
		return "Vela";
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
	public Vela clone() {
		return new Vela(this);
	}

}
