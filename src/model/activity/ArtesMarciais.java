package model.activity;



public class ArtesMarciais extends Individual {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArtesMarciais(ArtesMarciais act){
        super(act);
    }
	
	@Override
	public String getName() {
		return "Artes Marciais";
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
		return 61;
	}

	@Override
	public String getPointName() {
		return "Pontos";
	}
	
	@Override
	public double getMET() {
		return 10.3;
	}

	@Override
	public ArtesMarciais clone() {
		return new ArtesMarciais(this);
	}

}
