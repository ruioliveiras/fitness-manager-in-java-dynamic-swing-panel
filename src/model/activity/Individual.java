package model.activity;

import java.util.GregorianCalendar;

public abstract class Individual extends Contest {
	private static final long serialVersionUID = 1L;

	public Individual() {
		super();
	}

	public Individual(Contest act) {
		super(act);
	}

	public Individual(long time, Weather weather, int hRate,
			GregorianCalendar date, int pointRival, int pointTeam, Result result) {
		super(time, weather, hRate, date, pointRival, pointTeam, result);
	}

}
