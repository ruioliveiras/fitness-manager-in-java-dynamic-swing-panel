package model.activity;

import java.util.GregorianCalendar;



public class Surf extends Skill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Surf(Surf act){
        super(act);
    }
	
	public Surf() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Surf(long time, Weather weather, int hearthRate,
			GregorianCalendar date, int points, int maxTrick) {
		super(time, weather, hearthRate, date, points, maxTrick);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "Surf";
	}

 

 

	@Override
	public int getIntensidade() {
		return 210;
	}

	@Override
	public String getPointName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getMET() {
		return 3.0;
	}

	@Override
	public Surf clone() {
		return new Surf(this);
	}

}
