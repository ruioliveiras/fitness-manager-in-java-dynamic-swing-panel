package model.activity;

import java.util.GregorianCalendar;



public class Snowboard extends Skill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Snowboard(Snowboard act){
        super(act);
    }
	
	public Snowboard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Snowboard(long time, Weather weather, int hearthRate,
			GregorianCalendar date, int points, int maxTrick) {
		super(time, weather, hearthRate, date, points, maxTrick);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "Snowboard";
	}

 

 

	@Override
	public int getIntensidade() {
		return 119;
	}
	
	@Override
	public String getPointName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public double getMET() {
		return 5.3;
	}

	@Override
	public Snowboard clone() {
		return new Snowboard(this);
	}

}
