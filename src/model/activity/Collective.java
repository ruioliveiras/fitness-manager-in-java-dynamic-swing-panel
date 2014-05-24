package model.activity;

import java.util.GregorianCalendar;

public abstract class Collective extends Contest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Collective() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Collective(Contest act) {
		super(act);
		// TODO Auto-generated constructor stub
	}

	public Collective(long time, Weather weather, int hRate,
			GregorianCalendar date, int pointRival, int pointTeam, Result result) {
		super(time, weather, hRate, date, pointRival, pointTeam, result);
		// TODO Auto-generated constructor stub
	}

}
