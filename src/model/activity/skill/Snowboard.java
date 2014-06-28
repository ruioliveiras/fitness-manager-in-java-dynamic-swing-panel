package model.activity.skill;

import java.util.GregorianCalendar;

import model.activity.Skill;
import model.objectEnum.Weather;



public class Snowboard extends Skill {
	private static final long serialVersionUID = 1L;

	public Snowboard(Snowboard act){
        super(act);
    }
	
	public Snowboard() {
		super();
	}

	public Snowboard(long time, Weather weather, int hearthRate,
			GregorianCalendar date, int points, int maxTrick) {
		super(time, weather, hearthRate, date, points, maxTrick);
	}

	@Override
	public String getName() {
		return "Snowboard";
	}

	@Override
	public int getIntensidade() {
		return 119;
	}
	
	@Override
	public String getPointName() {
		return "Pontos";
	}
	
	@Override
	public double getMET() {
		return 5.3;
	}

	@Override
	public Snowboard clone() {
		return new Snowboard(this);
	}

}
