package model.activity.distance;

import java.util.GregorianCalendar;

import model.activity.Distance;
import model.objectEnum.Weather;

public class Caiaque extends Distance{
	private static final long serialVersionUID = 1L;

	public Caiaque(Caiaque act){
        super(act);
    }
	
	public Caiaque() {
		super();
	}

	public Caiaque(long time, Weather weather, int hearthRate,
			GregorianCalendar date, int distance, int maxSpeed) {
		super(time, weather, hearthRate, date, distance, maxSpeed);
	}

	@Override
	public String getName() {
		return "Caiaque";
	}

	@Override
	public int getIntensidade() {
		return 126;
	}
	
	@Override
	public double getMET() {
		return 5.0;
	}

	@Override
	public Caiaque clone() {
		return new Caiaque(this);
	}
}
