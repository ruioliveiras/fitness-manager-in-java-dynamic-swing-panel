package model.activity;

import java.util.GregorianCalendar;



public class Pilates extends Ludic {
	private static final long serialVersionUID = 1L;

	public Pilates(Pilates act){
        super(act);
    }
    
	public Pilates() {
		super();
	}

	public Pilates(long time, Weather weather, int hRate, GregorianCalendar date) {
		super(time, weather, hRate, date);
	}

	@Override
	public String getName() {
		return "Pilates";
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
	public Pilates clone(){
		return new Pilates(this);
	}

	
}
