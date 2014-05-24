package model.activity;





public class Caminhada extends Distance{
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public Caminhada(Caminhada act){
        super(act);
    }

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
    public Caminhada clone() {
        return new Caminhada(this);
    }
}
