package model.activity;

import java.util.GregorianCalendar;



public class Andebol extends Collective {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Andebol(Andebol act){
        super(act);
    }
	
	
	
	public Andebol() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Andebol(long time, Weather weather, int hRate,
			GregorianCalendar date, int pointRival, int pointTeam, Result result) {
		super(time, weather, hRate, date, pointRival, pointTeam, result);
		// TODO Auto-generated constructor stub
	}



	@Override
	public String getName() {
		return "Andebol";
	}

 

 

	@Override
	public int getIntensidade() {
		return 79;
	}
	
	@Override
	public String getPointName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public double getMET() {
		return 8.0;
	}

	@Override
	public Andebol clone() {
		return new Andebol(this);
	}

}
