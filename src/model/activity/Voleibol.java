package model.activity;

import java.util.GregorianCalendar;



public class Voleibol extends Collective {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Voleibol(Voleibol act){
        super(act);
    }
	
	public Voleibol() {
		super();
	}

	public Voleibol(long time, Weather weather, int hRate,
			GregorianCalendar date, int pointRival, int pointTeam, Result result) {
		super(time, weather, hRate, date, pointRival, pointTeam, result);
	}

	@Override
	public String getName() {
		return "Voleibol";
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
		return 4.0;
	}

	@Override
	public Voleibol clone() {
		return new Voleibol(this);
	}

}
