package model.activity;

import java.util.GregorianCalendar;



public class Skate extends Skill {
	private static final long serialVersionUID = 1L;

	public Skate(Skate act){
        super(act);
    }
	
	public Skate() {
		super();
	}

	public Skate(long time, Weather weather, int hearthRate,
			GregorianCalendar date, int points, int maxTrick) {
		super(time, weather, hearthRate, date, points, maxTrick);
	}

	@Override
	public String getName() {
		return "Skate";
	}

	@Override
	public int getIntensidade() {
		return 90;
	}
	
	@Override
	public String getPointName() {
		return "Pontos";
	}
	
	@Override
	public double getMET() {
		return 7.0;
	}

	@Override
	public Skate clone() {
		return new Skate(this);
	}

}
