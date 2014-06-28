package model.activity.contest;

import java.util.GregorianCalendar;

import model.activity.Contest;
import model.activity.Contest.Result;
import model.objectEnum.Weather;



public class VoleibolPraia extends Collective {
	private static final long serialVersionUID = 1L;

	public VoleibolPraia(VoleibolPraia act){
        super(act);
    }
	
	public VoleibolPraia() {
		super();
	}

	public VoleibolPraia(long time, Weather weather, int hRate,
			GregorianCalendar date, int pointRival, int pointTeam, Result result) {
		super(time, weather, hRate, date, pointRival, pointTeam, result);
	}

	@Override
	public String getName() {
		return "Voleibol de Praia";
	}

	@Override
	public int getIntensidade() {
		return 79;
	}
	
	@Override
	public String getPointName() {
		return "Pontos";
	}
	
	@Override
	public double getMET() {
		return 8.0;
	}

	@Override
	public VoleibolPraia clone() {
		return new VoleibolPraia(this);
	}

}
