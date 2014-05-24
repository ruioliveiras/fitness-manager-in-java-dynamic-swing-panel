package model.activity;

import java.util.GregorianCalendar;




public class Remo extends Distance{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Remo(Remo act){
        super(act);
    }
	
	public Remo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Remo(long time, Weather weather, int hearthRate,
			GregorianCalendar date, int distance, int maxSpeed) {
		super(time, weather, hearthRate, date, distance, maxSpeed);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "Remo";
	}

 

 

	@Override
	public int getIntensidade() {
		return 109;
	}
	
	@Override
	public double getMET() {
		return 5.8;
	}

	@Override
	public Remo clone() {
		return new Remo(this);
	}
}
