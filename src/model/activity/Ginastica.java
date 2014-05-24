package model.activity;



public class Ginastica extends Skill {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Ginastica(Ginastica act){
        super(act);
    }
	
	public Ginastica() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Ginastica(Skill act) {
		super(act);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return "Ginástica Olímpica";
	}

 

 

	@Override
	public int getIntensidade() {
		return 166;
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
	public Ginastica clone() {
		return new Ginastica(this);
	}

}
