package model.activity;



public class CorridaOrientacao extends Distance {

	@Override
	public String getName() {
		return "Corrida de Orientação";
	}

 

 

	@Override
	public int getIntensidade() {
		return 100;
	}
	
	@Override
	public double getMET() {
		return 9.0;
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
