package model.activity.contest;

import java.util.GregorianCalendar;

import model.activity.Contest;
import model.activity.Contest.Result;
import model.objectEnum.Weather;



public class Futebol extends Collective {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Futebol(Futebol act){
        super(act);
    }
	
	public Futebol() {
		super();
	}

	public Futebol(long time, Weather weather, int hRate,
			GregorianCalendar date, int pointRival, int pointTeam, Result result) {
		super(time, weather, hRate, date, pointRival, pointTeam, result);
	}

	@Override
	public String getName() {
		return "Futebol";
	}

	@Override
	public int getIntensidade() {
		return 90;
	}

	@Override
	public String getPointName() {
		return "Goooolos";
	}
	
	@Override
	public double getMET() {
		return 7.0;
	}

	@Override
	public Futebol clone() {
		return new Futebol(this);
	}

}
