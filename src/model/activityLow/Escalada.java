package model.activityLow;

import model.activityHigh.Activity;
import model.activityHigh.Ludic;

public class Escalada extends Ludic {

	@Override
	public String getName() {
		return "Escalada";
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
		return 8.0;
	}
}
