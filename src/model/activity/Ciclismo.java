package model.activity;

import java.util.GregorianCalendar;



public class Ciclismo extends Altimetry {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ciclismo(Ciclismo act){
        super(act);
    }
	
	public Ciclismo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ciclismo(long time, Weather weather, int hearthRate,
			GregorianCalendar date, int distance, int maxSpeed, int ascendent,
			int descendent, int minAltitude, int maxAltitude) {
		super(time, weather, hearthRate, date, distance, maxSpeed, ascendent,
				descendent, minAltitude, maxAltitude);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "Ciclismo";
	}

 

 

	@Override
	public int getIntensidade() {
		return 84;
	}

	@Override
	public double getMET() {
		return 7.5;
	}

	@Override
	public Ciclismo clone() {
		return new Ciclismo(this);
	}

}
