package model.activity;



public class CorridaOrientacao extends Distance {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CorridOrientacao(CorridaOrientacao act){
        super(act);
    }
	
	@Override
	public String getName() {
		return "Corrida de Orientação";
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
		return 70;
	}
	
	@Override
	public double getMET() {
		return 9.0;
	}

	@Override
	public CorridaOrientacao clone() {
		return new CorridaOrientacao(this);
	}

}
