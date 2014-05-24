package model.activity;



public class VoleibolPraia extends Collective {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VoleibolPraia(VoleibolPraia act){
        super(act);
    }
	
	@Override
	public String getName() {
		return "Voleibol de Praia";
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
	public VoleibolPraia clone() {
		return new VoleibolPraia(this);
	}

}
