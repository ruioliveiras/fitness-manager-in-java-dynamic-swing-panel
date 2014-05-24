package model.activity;

import java.util.GregorianCalendar;





public class Caminhada extends Distance{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public Caminhada(Caminhada act){
        super(act);
    }

    public Caminhada() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Caminhada(long time, Weather weather, int hearthRate,
			GregorianCalendar date, int distance, int maxSpeed) {
		super(time, weather, hearthRate, date, distance, maxSpeed);
		// TODO Auto-generated constructor stub
	}

	@Override
    public String getName() {
        return "Caminhada";
    }

    @Override
    public int getIntensidade() {
        return 180;
    }
    
    @Override
    public double getMET() {
        return 3.5;
    }

    @Override
    public Caminhada clone() {
        return new Caminhada(this);
    }
}
