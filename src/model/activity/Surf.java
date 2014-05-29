package model.activity;

import java.util.GregorianCalendar;



public class Surf extends Skill {
	private static final long serialVersionUID = 1L;

	public Surf(Surf act){
        super(act);
    }
	
	public Surf() {
		super();
	}

	public Surf(long time, Weather weather, int hearthRate,
			GregorianCalendar date, int points, int maxTrick) {
		super(time, weather, hearthRate, date, points, maxTrick);
	}

	@Override
	public String getName() {
		return "Surf";
	}

	@Override
	public int getIntensidade() {
		return 210;
	}

	@Override
	public String getPointName() {
		return "Pontos";
	}

	@Override
	public double getMET() {
		return 3.0;
	}

	@Override
	public Surf clone() {
		return new Surf(this);
	}

}
