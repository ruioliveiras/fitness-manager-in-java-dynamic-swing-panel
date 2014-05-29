package model.activity;

import java.util.GregorianCalendar;



public class CaminhadaMontanha extends Altimetry {
	private static final long serialVersionUID = 1L;

	public CaminhadaMontanha(CaminhadaMontanha act){
        super(act);
    }
	
	public CaminhadaMontanha() {
		super();
	}

	public CaminhadaMontanha(long time, Weather weather, int hearthRate,
			GregorianCalendar date, int distance, int maxSpeed, int ascendent,
			int descendent, int minAltitude, int maxAltitude) {
		super(time, weather, hearthRate, date, distance, maxSpeed, ascendent,
				descendent, minAltitude, maxAltitude);
	}

	@Override
	public String getName() {
		return "Caminhada Montanha";
	}

	@Override
	public int getIntensidade() {
		return 105;
	}

	@Override
	public double getMET() {
		return 6.0;
	}

	@Override
	public CaminhadaMontanha clone() {
		return new CaminhadaMontanha(this);
	}

}
