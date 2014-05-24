package model.activity;



public class Boxe extends Individual {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Boxe(Boxe act){
        super(act);
    }
    
    @Override
    public String getName() {
        return "Boxe";
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
        return 49;
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
    public Boxe clone() {
        return new Boxe(this);
    }

}
