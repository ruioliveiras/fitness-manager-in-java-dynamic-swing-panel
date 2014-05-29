package model.activity;

import java.util.GregorianCalendar;




public class BTT extends Altimetry{
	private static final long serialVersionUID = 1L;

	public BTT(BTT act){
        super(act);
    }
	
	public BTT() {
		super();
	}

	public BTT(long time, Weather weather, int hearthRate,
			GregorianCalendar date, int distance, int maxSpeed, int ascendent,
			int descendent, int minAltitude, int maxAltitude) {
		super(time, weather, hearthRate, date, distance, maxSpeed, ascendent,
				descendent, minAltitude, maxAltitude);
	}

	@Override
	public String getName() {
		return "BTT";
	}

	@Override
	public int getIntensidade() {
		return 74;
	}
	
	@Override
	public double getMET() {
		return 8.5;
	}

	@Override
	public BTT clone() {
		return new BTT(this);
	}
}
