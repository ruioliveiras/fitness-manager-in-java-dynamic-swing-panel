package model.activity;

import java.util.GregorianCalendar;



public class Ioga extends Ludic {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ioga(Ioga act){
        super(act);
    }
	
	public Ioga() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ioga(long time, Weather weather, int hRate, GregorianCalendar date) {
		super(time, weather, hRate, date);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "Ioga";
	}

 

 

	@Override
	public int getIntensidade() {
		return 158;
	}
	
	@Override
	public double getMET() {
		return 4.0;
	}

	@Override
	public Ioga clone() {
		return new Ioga(this);
	}

}
