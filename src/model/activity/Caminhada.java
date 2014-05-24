package model.activity;





public class Caminhada extends Distance{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String getName() {
		return "Caminhada";
	}

	@Override
	public int getIntensidade() {
		return 180;
	}
	
	@Override
	public double getMET() {
		return 3.5;
	}

	@Override
	public Activity clone() {
		// TODO Auto-generated method stub
		return null;
	}
}
