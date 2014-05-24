package model.activity;



public class Danca extends Ludic {

	@Override
	public String getName() {
		return "Dança";
	}

 

 

	@Override
	public int getIntensidade() {
		return 100;
	}
	
	@Override
	public double getMET() {
		return 7.8;
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
