package model.activityLow;

import model.activityHigh.Activity;
import model.activityHigh.Distance;

public class WindSurf extends Distance {

	@Override
	public String getName() {
		return "WindSurf";
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

}
