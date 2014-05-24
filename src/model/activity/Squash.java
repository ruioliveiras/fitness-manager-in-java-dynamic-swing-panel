package model.activity;

import java.util.GregorianCalendar;



public class Squash extends Individual {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Squash(Squash act){
        super(act);
    }
	
	public Squash() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Squash(long time, Weather weather, int hRate,
			GregorianCalendar date, int pointRival, int pointTeam, Result result) {
		super(time, weather, hRate, date, pointRival, pointTeam, result);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "Squash";
	}

 

 

	@Override
	public int getIntensidade() {
		return 86;
	}
	
	@Override
	public String getPointName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public double getMET() {
		return 7.3;
	}

	@Override
	public Squash clone() {
		return new Squash(this);
	}

}
