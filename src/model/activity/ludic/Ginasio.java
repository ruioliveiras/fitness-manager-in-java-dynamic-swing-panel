package model.activity.ludic;

import java.util.GregorianCalendar;

import model.activity.Ludic;
import model.objectEnum.Weather;



public class Ginasio extends Ludic {
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;
    
    /**
     * Construtor de Copia
     * @param act actividade a copiar
     */
    public Ginasio(Ginasio act){
        super(act);
    }
    /**
     * Construtor vazio
     */
    public Ginasio() {
		super();
	}

    /**
     * Contrutor parametrizado
     * @param time duração da actividade em milisegundos
     * @param weather Weather tempo meteorologico da actividade
     * @param hRate pulsasão da actividade
     * @param date data da actividade
     */
	public Ginasio(long time, Weather weather, int hRate, GregorianCalendar date) {
		super(time, weather, hRate, date);
	}

	@Override
    public String getName() {
        return "Ginasio";
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
    public Ginasio clone() {
        return new Ginasio(this);
    }
 
}
