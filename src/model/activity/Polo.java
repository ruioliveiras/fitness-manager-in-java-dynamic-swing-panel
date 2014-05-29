package model.activity;

import java.util.GregorianCalendar;



public class Polo extends Collective {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Polo(Polo act){
        super(act);
    }
	
	public Polo() {
		super();
	}

	public Polo(long time, Weather weather, int hRate, GregorianCalendar date,
			int pointRival, int pointTeam, Result result) {
		super(time, weather, hRate, date, pointRival, pointTeam, result);
	}

	@Override
	public String getName() {
		return "Polo";
	}

 
	@Override
	public int getIntensidade() {
		return 79;
	}

	@Override
	public String getPointName() {
		return "Pontos";
	}
	
	@Override
	public double getMET() {
		return 8.0;
	}

	@Override
	public Polo clone() {
		return new Polo(this);
	}

}
