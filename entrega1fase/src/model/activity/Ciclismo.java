package model.activity;



public class Ciclismo extends Altimetry {

	@Override
	public String getName() {
		return "Ciclismo";
	}

 

 

	@Override
	public int getIntensidade() {
		return 100;
	}

	@Override
	public double getMET() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
