package model.activity;



public class Raguebi extends Collective {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Raguebi(Raguebi act){
        super(act);
    }
	
	@Override
	public String getName() {
		return "Râguebi";
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
		return 100;
	}

	@Override
	public String getPointName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getMET() {
		return 6.3;
	}

	@Override
	public Raguebi clone() {
		return new Raguebi(this);
	}

}
