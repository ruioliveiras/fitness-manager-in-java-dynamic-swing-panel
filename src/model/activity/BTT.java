package model.activity;




public class BTT extends Altimetry{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BTT(BTT act){
        super(act);
    }
	
	@Override
	public String getName() {
		return "BTT";
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
		return 74;
	}
	
	@Override
	public double getMET() {
		return 8.5;
	}

	@Override
	public BTT clone() {
		return new BTT(this);
	}
}
