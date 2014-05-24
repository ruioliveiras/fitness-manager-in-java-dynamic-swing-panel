package model.activity;

import java.util.GregorianCalendar;



public class Hoquei extends Collective {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Hoquei(Hoquei act){
        super(act);
    }
	
	public Hoquei() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Hoquei(long time, Weather weather, int hRate,
			GregorianCalendar date, int pointRival, int pointTeam, Result result) {
		super(time, weather, hRate, date, pointRival, pointTeam, result);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "Hoquei";
	}

 

 

	@Override
	public int getIntensidade() {
		return 81;
	}

	@Override
	public String getPointName() {
		return "Pontos";
	}

	@Override
	public double getMET() {
		return 7.9;
	}

	@Override
	public Hoquei clone() {
		return new Hoquei(this);
	}

}
