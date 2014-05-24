package model.activity;

import java.util.GregorianCalendar;



public class Aerobica extends Ludic {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Aerobica() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Aerobica(long time, Weather weather, int hRate,
			GregorianCalendar date) {
		super(time, weather, hRate, date);
		// TODO Auto-generated constructor stub
	}

	public Aerobica(Aerobica act){
        super(act);
    }
    
    @Override
    public String getName() {
        return "Aeróbica";
    }

 

    @Override
    public int getIntensidade() {
        return 86;
    }

    @Override
    public double getMET() {
        return 7.3;
    }

    @Override
    public Aerobica clone() {
        return new Aerobica(this);
    }
}

