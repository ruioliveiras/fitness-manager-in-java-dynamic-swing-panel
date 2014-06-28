package model.activity;




public class BTT extends Altimetry{
    @Override
	public String getName() {
		return "BTT";
	}

 

 

	@Override
	public int getIntensidade() {
		return 100;
	}
	
	@Override
	public double getMET() {
		return 8.5;
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}
}
