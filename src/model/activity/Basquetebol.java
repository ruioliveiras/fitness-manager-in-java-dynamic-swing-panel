package model.activity;



public class Basquetebol extends Collective {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Basquetebol(Basquetebol act){
        super(act);
    }
	
	@Override
	public String getName() {
		return "Basquetebol";
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
		return 97;
	}

	@Override
	public String getPointName() {
		return "Pontos";
	}

	@Override
	public double getMET() {
		return 6.5;
	}

	@Override
	public Basquetebol clone() {
		return new Basquetebol(this);
	}

}
