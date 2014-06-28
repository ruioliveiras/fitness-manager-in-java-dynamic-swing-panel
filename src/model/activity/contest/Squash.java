package model.activity.contest;

import java.util.GregorianCalendar;

import model.activity.Contest;
import model.activity.Contest.Result;
import model.objectEnum.Weather;



public class Squash extends Individual {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Squash(Squash act){
        super(act);
    }
	
	public Squash() {
		super();
	}

	public Squash(long time, Weather weather, int hRate,
			GregorianCalendar date, int pointRival, int pointTeam, Result result) {
		super(time, weather, hRate, date, pointRival, pointTeam, result);
	}

	@Override
	public String getName() {
		return "Squash";
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
	public Squash clone() {
		return new Squash(this);
	}

}
