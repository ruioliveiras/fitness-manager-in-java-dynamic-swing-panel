package model.activity;

import java.util.GregorianCalendar;



public class Golfe extends Skill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Golfe(Golfe act){
        super(act);
    }
	
	public Golfe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Golfe(long time, Weather weather, int hearthRate,
			GregorianCalendar date, int points, int maxTrick) {
		super(time, weather, hearthRate, date, points, maxTrick);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "Golfe";
	}

 

 

	@Override
	public int getIntensidade() {
		return 131;
	}

	@Override
	public String getPointName() {
		return "nº tacadas";
	}

	@Override
	public double getMET() {
		return 4.8;
	}

	@Override
	public Golfe clone() {
		return new Golfe(this);
	}

}
