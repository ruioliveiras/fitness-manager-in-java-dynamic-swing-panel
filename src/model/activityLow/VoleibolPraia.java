package model.activityLow;

import model.activityHigh.Activity;
import model.activityHigh.Collective;

public class VoleibolPraia extends Collective {

	@Override
	public String getName() {
		return "Voleibol de Praia";
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
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public double getMET() {
		return 8.0;
	}

}
