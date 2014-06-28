package model.activity;



public class Aerobica extends Ludic {

	@Override
	public String getName() {
		return "Aeróbica";
	}

 

 

	@Override
	public int getIntensidade() {
		return 100;
	}

	@Override
	public double getMET() {
		return 7.3;
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}
}

