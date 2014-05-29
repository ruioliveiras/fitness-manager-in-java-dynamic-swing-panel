package model.activity;

import java.util.GregorianCalendar;



public class SkiDownhill extends Distance {


    private static final long serialVersionUID = 1L;
    
    public SkiDownhill(SkiDownhill act){
        super(act);
    }

    public SkiDownhill() {
		super();
	}

	public SkiDownhill(long time, Weather weather, int hearthRate,
			GregorianCalendar date, int distance, int maxSpeed) {
		super(time, weather, hearthRate, date, distance, maxSpeed);
	}

	@Override
    public String getName() {
        return "Ski DownHill";
    }

    @Override
    public int getIntensidade() {
        return 119;
    }

    @Override
    public double getMET() {
        return 5.3;
    }

    @Override
    public SkiDownhill clone() {
        return new SkiDownhill(this);
    }

}
