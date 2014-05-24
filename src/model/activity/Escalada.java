package model.activity;



public class Escalada extends Ludic {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Escalada(Escalada act){
        super(act);
    }
	
	@Override
	public String getName() {
		return "Escalada";
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
		return 79;
	}

	@Override
	public double getMET() {
		return 8.0;
	}

	@Override
	public Escalada clone() {
		return new Escalada(this);
	}
}
