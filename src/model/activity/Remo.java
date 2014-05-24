package model.activity;




public class Remo extends Distance{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Remo(Remo act){
        super(act);
    }
	
	@Override
	public String getName() {
		return "Remo";
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
		return 109;
	}
	
	@Override
	public double getMET() {
		return 5.8;
	}

	@Override
	public Remo clone() {
		return new Remo(this);
	}
}
