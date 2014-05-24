package model.activity;

import java.util.GregorianCalendar;



public class Hipismo extends Skill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Hipismo(Hipismo act){
        super(act);
    }
	
	public Hipismo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hipismo(long time, Weather weather, int hearthRate,
			GregorianCalendar date, int points, int maxTrick) {
		super(time, weather, hearthRate, date, points, maxTrick);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "Hipismo";
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
	public Hipismo clone() {
		return new Hipismo(this);
	}

}
