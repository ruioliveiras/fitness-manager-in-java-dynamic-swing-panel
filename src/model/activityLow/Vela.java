package model.activityLow;

import model.activityHigh.Activity;
import model.activityHigh.Distance;

public class Vela extends Distance {

	@Override
	public String getName() {
		return "Vela";
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
		return 3.0;
	}

}
