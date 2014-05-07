package model.activityLow;

import model.activityHigh.Activity;
import model.activityHigh.Collective;

public class Boxe extends Collective {

	@Override
	public String getName() {
		return "Boxe";
	}

	@Override
	public int getRecordType() {
		return 0;
	}

	@Override
	public int compareRecord(Activity otherActivity) {
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
		return 12.8;
	}

}
