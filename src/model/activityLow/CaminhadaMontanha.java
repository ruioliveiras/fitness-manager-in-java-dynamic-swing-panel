package model.activityLow;

import model.activityHigh.Activity;
import model.activityHigh.Altimetry;

public class CaminhadaMontanha extends Altimetry {

	@Override
	public String getName() {
		return "Caminhada Montanha";
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
