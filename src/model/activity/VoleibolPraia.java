package model.activity;

import java.util.GregorianCalendar;



public class VoleibolPraia extends Collective {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VoleibolPraia(VoleibolPraia act){
        super(act);
    }
	
	public VoleibolPraia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VoleibolPraia(long time, Weather weather, int hRate,
			GregorianCalendar date, int pointRival, int pointTeam, Result result) {
		super(time, weather, hRate, date, pointRival, pointTeam, result);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "Voleibol de Praia";
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
	public VoleibolPraia clone() {
		return new VoleibolPraia(this);
	}

}
