package model.activity;



public class TenisMesa extends Individual {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TenisMesa(TenisMesa act){
        super(act);
    }
	
	@Override
	public String getName() {
		return "Tenis Mesa";
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
	public String getPointName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getMET() {
		return 4.8;
	}

	@Override
	public TenisMesa clone() {
		return new TenisMesa(this);
	}

}
