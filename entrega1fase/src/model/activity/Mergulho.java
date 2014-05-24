package model.activity;



public class Mergulho extends Ludic {

	@Override
	public String getName() {
		return "Mergulho";
	}

 

 

	@Override
	public int getIntensidade() {
		return 100;
	}

	@Override
	public double getMET() {
		return 7.0;
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}
}
