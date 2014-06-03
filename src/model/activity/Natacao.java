package model.activity;

import java.util.GregorianCalendar;



public class Natacao extends Distance {
	private static final long serialVersionUID = 1L;

	public Natacao() {
	}
	
	public Natacao(Natacao act) {
		super(act);
	}


	public Natacao(long time, Weather weather, int hearthRate,
			GregorianCalendar date, int distance, int maxSpeed) {
		super(time, weather, hearthRate, date, distance, maxSpeed);
	}

	@Override
	public String getName() {
		return "Natação";
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
	public Natacao clone() {
		return new Natacao(this);
	}




}
