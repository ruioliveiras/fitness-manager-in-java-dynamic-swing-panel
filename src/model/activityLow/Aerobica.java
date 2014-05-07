package model.activityLow;

import model.activityHigh.Activity;
import model.activityHigh.Ludic;

public class Aerobica extends Ludic {

	@Override
	public String getName() {
		return "Aeróbica";
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
	public double getMET() {
		return 7.3;
	}
}

