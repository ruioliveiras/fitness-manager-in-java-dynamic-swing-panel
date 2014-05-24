package model.activity;



public class Andebol extends Collective {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Andebol(Andebol act){
        super(act);
    }
	
	@Override
	public String getName() {
		return "Andebol";
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
	public String getPointName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public double getMET() {
		return 8.0;
	}

	@Override
	public Andebol clone() {
		return new Andebol(this);
	}

}
