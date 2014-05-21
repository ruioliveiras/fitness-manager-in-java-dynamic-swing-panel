package model.activity;

import java.util.GregorianCalendar;



public class Natacao extends Distance {

	public Natacao() {
		super();
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
		return 100;
	}

	@Override
	public double getMET() {
		return 0;
	}

	@Override
	public Object clone() {
		return new Natacao(this);
	}

}
