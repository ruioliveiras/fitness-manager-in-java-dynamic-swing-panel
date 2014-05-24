package model.activity;



public class Golfe extends Skill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Golfe(Golfe act){
        super(act);
    }
	
	@Override
	public String getName() {
		return "Golfe";
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
		return 131;
	}

	@Override
	public String getPointName() {
		return "nº tacadas";
	}

	@Override
	public double getMET() {
		return 4.8;
	}

	@Override
	public Golfe clone() {
		return new Golfe(this);
	}

}
