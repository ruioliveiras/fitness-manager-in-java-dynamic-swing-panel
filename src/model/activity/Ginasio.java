package model.activity;

import java.util.GregorianCalendar;



public class Ginasio extends Ludic {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public Ginasio(Ginasio act){
        super(act);
    }
    
    public Ginasio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ginasio(long time, Weather weather, int hRate, GregorianCalendar date) {
		super(time, weather, hRate, date);
		// TODO Auto-generated constructor stub
	}

	@Override
    public String getName() {
        return "Ginasio";
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
    public Ginasio clone() {
        return new Ginasio(this);
    }

    
}
