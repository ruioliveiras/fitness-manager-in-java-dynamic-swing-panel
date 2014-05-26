package model.activity;

import java.util.GregorianCalendar;



public class Escalada extends Ludic {
	private static final long serialVersionUID = 1L;

	public Escalada(Escalada act){
        super(act);
    }
	
	public Escalada() {
		super();
	}

	public Escalada(long time, Weather weather, int hRate,
			GregorianCalendar date) {
		super(time, weather, hRate, date);
	}

	@Override
	public String getName() {
		return "Escalada";
	}

	@Override
	public int getIntensidade() {
		return 79;
	}

	@Override
	public double getMET() {
		return 8.0;
	}

	@Override
	public Escalada clone() {
		return new Escalada(this);
	}
}
