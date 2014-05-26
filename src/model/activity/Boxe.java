package model.activity;

import java.util.GregorianCalendar;



public class Boxe extends Individual {
    private static final long serialVersionUID = 1L;

    public Boxe(Boxe act){
        super(act);
    }
    
    public Boxe() {
		super();
	}

	public Boxe(long time, Weather weather, int hRate, GregorianCalendar date,
			int pointRival, int pointTeam, Result result) {
		super(time, weather, hRate, date, pointRival, pointTeam, result);
	}

	@Override
    public String getName() {
        return "Boxe";
    }

    @Override
    public int getIntensidade() {
        return 49;
    }

    @Override
    public String getPointName() {
        return "Pontos";
    }
    
    @Override
    public double getMET() {
        return 12.8;
    }

    @Override
    public Boxe clone() {
        return new Boxe(this);
    }

}
