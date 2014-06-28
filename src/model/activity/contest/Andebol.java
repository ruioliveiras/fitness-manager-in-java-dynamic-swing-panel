package model.activity.contest;

import java.util.GregorianCalendar;

import model.activity.Contest;
import model.activity.Contest.Result;
import model.objectEnum.Weather;



public class Andebol extends Collective {
	private static final long serialVersionUID = 1L;

	public Andebol(Andebol act){
        super(act);
    }
	
	
	
	public Andebol() {
		super();
	}



	public Andebol(long time, Weather weather, int hRate,
			GregorianCalendar date, int pointRival, int pointTeam, Result result) {
		super(time, weather, hRate, date, pointRival, pointTeam, result);
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
		return "Golos";
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
