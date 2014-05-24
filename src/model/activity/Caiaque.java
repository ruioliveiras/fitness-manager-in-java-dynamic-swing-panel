package model.activity;




public class Caiaque extends Distance{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Caiaque(Caiaque act){
        super(act);
    }
	
	@Override
	public String getName() {
		return "Caiaque";
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
		return 126;
	}
	
	@Override
	public double getMET() {
		return 5.0;
	}

	@Override
	public Caiaque clone() {
		return new Caiaque(this);
	}
}
