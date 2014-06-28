package model.activity;



public class Ginastica extends Skill {

	@Override
	public String getName() {
		return "Ginástica Olímpica";
	}

 

 

	@Override
	public int getIntensidade() {
		return 100;
	}
	
	@Override
	public String getPointName() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public double getMET() {
		return 3.8;
	}

	@Override
	public Object clone() {
		// TODO Auto-generated method stub
		return null;
	}

}
