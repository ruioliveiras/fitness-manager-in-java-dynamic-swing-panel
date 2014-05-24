package model.activity;



public class CaminhadaMontanha extends Altimetry {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CaminhadaMontanha(CaminhadaMontanha act){
        super(act);
    }
	
	@Override
	public String getName() {
		return "Caminhada Montanha";
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
		return 105;
	}

	@Override
	public double getMET() {
		return 6.0;
	}

	@Override
	public CaminhadaMontanha clone() {
		return new CaminhadaMontanha(this);
	}

}
