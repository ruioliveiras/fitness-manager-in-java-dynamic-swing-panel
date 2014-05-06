package model.activityLow;

import model.activityHigh.Activity;
import model.activityHigh.Collective;

public class Hoquei extends Collective {

	@Override
	public String getName() {
		return "Hoquei";
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

}
