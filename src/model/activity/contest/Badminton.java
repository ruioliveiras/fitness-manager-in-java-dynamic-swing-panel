package model.activity.contest;

import java.util.GregorianCalendar;

import model.activity.Contest;
import model.activity.Contest.Result;
import model.objectEnum.Weather;



public class Badminton extends Individual {
	private static final long serialVersionUID = 1L;

	public Badminton(Badminton act){
        super(act);
    }
	
	public Badminton() {
		super();
	}

	public Badminton(long time, Weather weather, int hRate,
			GregorianCalendar date, int pointRival, int pointTeam, Result result) {
		super(time, weather, hRate, date, pointRival, pointTeam, result);
	}

	@Override
	public String getName() {
		return "Badminton";
	}

 
	@Override
	public int getIntensidade() {
		return 115;
	}
	
	@Override
	public String getPointName() {
		return "Pontos";
	}
	
	@Override
	public double getMET() {
		return 5.5;
	}

	@Override
	public Badminton clone() {
		return new Badminton(this);
	}

}
