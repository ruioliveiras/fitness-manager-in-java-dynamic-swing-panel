package model.activity;



public class CaminhadaMontanha extends Altimetry {

	@Override
	public String getName() {
		return "Caminhada Montanha";
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
