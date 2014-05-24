package model.activity;

import java.util.GregorianCalendar;



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
		// TODO Auto-generated constructor stub
	}

	public Futebol(long time, Weather weather, int hRate,
			GregorianCalendar date, int pointRival, int pointTeam, Result result) {
		super(time, weather, hRate, date, pointRival, pointTeam, result);
		// TODO Auto-generated constructor stub
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
		return "Pontos";
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
