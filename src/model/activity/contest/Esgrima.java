package model.activity.contest;

import java.util.GregorianCalendar;

import model.activity.Contest;
import model.activity.Contest.Result;
import model.objectEnum.Weather;



public class Esgrima extends Individual {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Esgrima(Esgrima act){
        super(act);
    }
	
	public Esgrima() {
		super();
	}

	public Esgrima(long time, Weather weather, int hRate,
			GregorianCalendar date, int pointRival, int pointTeam, Result result) {
		super(time, weather, hRate, date, pointRival, pointTeam, result);
	}

	@Override
	public String getName() {
		return "Esgrima";
	}

	@Override
	public int getIntensidade() {
		return 105;
	}
	
	@Override
	public String getPointName() {
		return "Pontos";
	}
	
	@Override
	public double getMET() {
		return 6.0;
	}

	@Override
	public Esgrima clone() {
		return new Esgrima(this);
	}

}
