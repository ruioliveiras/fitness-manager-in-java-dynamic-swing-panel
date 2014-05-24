package model.activity;



public class Polo extends Collective {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Polo(Polo act){
        super(act);
    }
	
	@Override
	public String getName() {
		return "Polo";
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
		return "Pontos";
	}
	
	@Override
	public double getMET() {
		return 8.0;
	}

	@Override
	public Polo clone() {
		return new Polo(this);
	}

}
