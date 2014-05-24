package model.activity;

import model.user.User;


public class WindSurf extends Distance {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public WindSurf(WindSurf act){
        super(act);
    }

    @Override
    public String getName() {
        return "WindSurf";
    }

    @Override
    public int getRecordSize() {
        return 0;
    }

    @Override
    public int compareRecord(Activity otherActivity,int recordType) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getIntensidade() {
        return 210;
    }

    @Override
    public double getMET() {
        // TODO Auto-generated method stub
        return 3.0;
    }

    @Override
    public WindSurf clone() {
        new WindSurf(this);
    }

}
