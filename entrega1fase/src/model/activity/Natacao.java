package model.activity;



public class Natacao extends Distance {

	@Override
	public String getName() {
		return "Natação";
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
