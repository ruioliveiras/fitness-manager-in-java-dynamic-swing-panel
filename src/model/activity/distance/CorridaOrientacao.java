package model.activity.distance;

import java.util.GregorianCalendar;

import model.activity.Distance;
import model.objectEnum.Weather;



public class CorridaOrientacao extends Distance {
	private static final long serialVersionUID = 1L;

	public CorridaOrientacao(CorridaOrientacao act){
        super(act);
    }
	
	public CorridaOrientacao() {
		super();
	}

	public CorridaOrientacao(long time, Weather weather, int hearthRate,
			GregorianCalendar date, int distance, int maxSpeed) {
		super(time, weather, hearthRate, date, distance, maxSpeed);
	}

	@Override
	public String getName() {
		return "Corrida de Orientação";
	}

	@Override
	public int getIntensidade() {
		return 70;
	}
	
	@Override
	public double getMET() {
		return 9.0;
	}

	@Override
	public CorridaOrientacao clone() {
		return new CorridaOrientacao(this);
	}

}
