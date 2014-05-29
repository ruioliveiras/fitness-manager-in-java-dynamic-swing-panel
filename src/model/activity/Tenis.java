package model.activity;

import java.util.GregorianCalendar;



public class Tenis extends Individual {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tenis(Tenis act){
        super(act);
    }
	
	public Tenis() {
		super();
	}

	public Tenis(long time, Weather weather, int hRate, GregorianCalendar date,
			int pointRival, int pointTeam, Result result) {
		super(time, weather, hRate, date, pointRival, pointTeam, result);
	}

	@Override
	public String getName() {
		return "Tenis";
	}

	@Override
	public int getIntensidade() {
		return 86;
	}

	@Override
	public String getPointName() {
		return "Pontos";
	}
	
	@Override
	public double getMET() {
		return 7.3;
	}

	@Override
	public Tenis clone() {
		return new Tenis(this);
	}

}
