package model.activity;



public class Ciclismo extends Altimetry {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ciclismo(Ciclismo act){
        super(act);
    }
	
	@Override
	public String getName() {
		return "Ciclismo";
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
		return 84;
	}

	@Override
	public double getMET() {
		return 7.5;
	}

	@Override
	public Ciclismo clone() {
		return new Ciclismo(this);
	}

}
