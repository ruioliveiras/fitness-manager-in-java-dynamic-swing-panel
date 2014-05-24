package model.activity;



public class SkiTT extends Altimetry {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SkiTT(SkiTT act){
        super(act);
    }
	
	@Override
	public String getName() {
		return "Ski de fundo";
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
		return 90;
	}
	
	@Override
	public double getMET() {
		return 7.0;
	}

	@Override
	public SkiTT clone() {
		return new SkiTT(this);
	}

}
