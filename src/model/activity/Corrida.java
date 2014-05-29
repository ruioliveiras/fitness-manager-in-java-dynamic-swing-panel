package model.activity;

import java.util.GregorianCalendar;



public class Corrida extends Altimetry {
	private static final long serialVersionUID = 1L;

	public Corrida(Corrida act){
        super(act);
    }
	
	public Corrida() {
		super();
	}

	public Corrida(long time, Weather weather, int hearthRate,
			GregorianCalendar date, int distance, int maxSpeed, int ascendent,
			int descendent, int minAltitude, int maxAltitude) {
		super(time, weather, hearthRate, date, distance, maxSpeed, ascendent,
				descendent, minAltitude, maxAltitude);
	}

	@Override
	public String getName() {
		return "Corrida";
	}

	@Override
	public int getIntensidade() {
		return 90;
	}
	
	@Override
	public double getMET() {
		return 7.0;
	}

	@Override
	public Corrida clone() {
		return new Corrida(this);
	}

}
