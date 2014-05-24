package model.activity;

import java.util.GregorianCalendar;



public class Badminton extends Individual {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Badminton(Badminton act){
        super(act);
    }
	
	public Badminton() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Badminton(long time, Weather weather, int hRate,
			GregorianCalendar date, int pointRival, int pointTeam, Result result) {
		super(time, weather, hRate, date, pointRival, pointTeam, result);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "Badminton";
	}

 

 

	@Override
	public int getIntensidade() {
		return 115;
	}
	
	@Override
	public String getPointName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public double getMET() {
		return 5.5;
	}

	@Override
	public Badminton clone() {
		return new Badminton(this);
	}

}
