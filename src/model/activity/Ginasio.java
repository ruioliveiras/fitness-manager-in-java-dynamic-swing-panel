package model.activity;



public class Ginasio extends Ludic {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Override
    public String getName() {
        return "Ginasio";
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
        return 126;
    }

    @Override
    public double getMET() {
        return 5.0;
    }

    @Override
    public Activity clone() {
        // TODO Auto-generated method stub
        return null;
    }

    
}
