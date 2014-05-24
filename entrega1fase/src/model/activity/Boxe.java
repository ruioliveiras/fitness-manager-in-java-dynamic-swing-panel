package model.activity;



public class Boxe extends Individual {

	@Override
	public String getName() {
		return "Boxe";
	}

 

 

	@Override
	public int getIntensidade() {
		return 100;
	}

	@Override
	public String getPointName() {
		return "Pontos";
	}
	
	@Override
	public double getMET() {
		return 12.8;
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
