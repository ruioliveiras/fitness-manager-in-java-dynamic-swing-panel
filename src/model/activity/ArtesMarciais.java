package model.activity;



public class ArtesMarciais extends Individual {

	@Override
	public String getName() {
		return "Artes Marciais";
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
		return "Pontos";
	}
	
	@Override
	public double getMET() {
		return 10.3;
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
