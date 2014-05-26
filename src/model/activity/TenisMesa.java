package model.activity;

import java.util.GregorianCalendar;



public class TenisMesa extends Individual {
	private static final long serialVersionUID = 1L;

	public TenisMesa(TenisMesa act){
        super(act);
    }
	
	public TenisMesa() {
		super();
	}

	public TenisMesa(long time, Weather weather, int hRate,
			GregorianCalendar date, int pointRival, int pointTeam, Result result) {
		super(time, weather, hRate, date, pointRival, pointTeam, result);
	}

	@Override
	public String getName() {
		return "Tenis Mesa";
	}

	@Override
	public int getIntensidade() {
		return 158;
	}

	@Override
	public String getPointName() {
		return "Pontos";
	}

	@Override
	public double getMET() {
		return 4.8;
	}

	@Override
	public TenisMesa clone() {
		return new TenisMesa(this);
	}

}
