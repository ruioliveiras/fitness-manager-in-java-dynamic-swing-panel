package model.activity;



public class Voleibol extends Collective {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Voleibol(Voleibol act){
        super(act);
    }
	
	@Override
	public String getName() {
		return "Voleibol";
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
		return 158;
	}

	@Override
	public String getPointName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getMET() {
		return 4.0;
	}

	@Override
	public Voleibol clone() {
		return new Voleibol(this);
	}

}
