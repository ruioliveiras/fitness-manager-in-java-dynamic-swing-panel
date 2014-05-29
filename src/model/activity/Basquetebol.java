package model.activity;

import java.util.GregorianCalendar;



public class Basquetebol extends Collective {
	private static final long serialVersionUID = 1L;

	public Basquetebol(Basquetebol act){
        super(act);
    }
	
	public Basquetebol() {
		super();
	}

	public Basquetebol(long time, Weather weather, int hRate,
			GregorianCalendar date, int pointRival, int pointTeam, Result result) {
		super(time, weather, hRate, date, pointRival, pointTeam, result);
	}

	@Override
	public String getName() {
		return "Basquetebol";
	}

	@Override
	public int getIntensidade() {
		return 97;
	}

	@Override
	public String getPointName() {
		return "Cestos";
	}

	@Override
	public double getMET() {
		return 6.5;
	}

	@Override
	public Basquetebol clone() {
		return new Basquetebol(this);
	}

}
