package model.activity;

import java.util.GregorianCalendar;



public class Raguebi extends Collective {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Raguebi(Raguebi act){
        super(act);
    }
	
	public Raguebi() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Raguebi(long time, Weather weather, int hRate,
			GregorianCalendar date, int pointRival, int pointTeam, Result result) {
		super(time, weather, hRate, date, pointRival, pointTeam, result);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "Râguebi";
	}

 

 

	@Override
	public int getIntensidade() {
		return 100;
	}

	@Override
	public String getPointName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getMET() {
		return 6.3;
	}

	@Override
	public Raguebi clone() {
		return new Raguebi(this);
	}

}
