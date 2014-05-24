package model.activity;

import java.util.GregorianCalendar;



public class Danca extends Ludic {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Danca(Danca act){
        super(act);
    }
	
	public Danca() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Danca(long time, Weather weather, int hRate, GregorianCalendar date) {
		super(time, weather, hRate, date);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "Dança";
	}

 

 

	@Override
	public int getIntensidade() {
		return 81;
	}
	
	@Override
	public double getMET() {
		return 7.8;
	}

	@Override
	public Danca clone() {
		return new Danca(this);
	}

}
