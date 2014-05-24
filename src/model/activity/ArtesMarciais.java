package model.activity;

import java.util.GregorianCalendar;



public class ArtesMarciais extends Individual {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ArtesMarciais(ArtesMarciais act){
        super(act);
    }
	
	public ArtesMarciais() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArtesMarciais(long time, Weather weather, int hRate,
			GregorianCalendar date, int pointRival, int pointTeam, Result result) {
		super(time, weather, hRate, date, pointRival, pointTeam, result);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "Artes Marciais";
	}

 

 

	@Override
	public int getIntensidade() {
		return 61;
	}

	@Override
	public String getPointName() {
		return "Pontos";
	}
	
	@Override
	public double getMET() {
		return 10.3;
	}

	@Override
	public ArtesMarciais clone() {
		return new ArtesMarciais(this);
	}

}
