package model.activity;

import java.util.GregorianCalendar;

import model.user.User;


public class WindSurf extends Distance {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public WindSurf(WindSurf act){
        super(act);
    }

    public WindSurf() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WindSurf(long time, Weather weather, int hearthRate,
			GregorianCalendar date, int distance, int maxSpeed) {
		super(time, weather, hearthRate, date, distance, maxSpeed);
		// TODO Auto-generated constructor stub
	}

	@Override
    public String getName() {
        return "WindSurf";
    }

 

    @Override
    public int getIntensidade() {
        return 210;
    }

    @Override
    public double getMET() {
        // TODO Auto-generated method stub
        return 3.0;
    }

    @Override
    public WindSurf clone() {
        return new WindSurf(this);
    }

}
