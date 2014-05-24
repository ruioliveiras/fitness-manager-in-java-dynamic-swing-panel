package model.activity;

import java.util.GregorianCalendar;



public class Vela extends Distance {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Vela(Vela act){
        super(act);
    }
	
	public Vela() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Vela(long time, Weather weather, int hearthRate,
			GregorianCalendar date, int distance, int maxSpeed) {
		super(time, weather, hearthRate, date, distance, maxSpeed);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "Vela";
	}

 

 

	@Override
	public int getIntensidade() {
		return 210;
	}
	
	@Override
	public double getMET() {
		return 3.0;
	}

	@Override
	public Vela clone() {
		return new Vela(this);
	}

}
