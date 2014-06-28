package model.activity.ludic;

import java.util.GregorianCalendar;

import model.activity.Ludic;
import model.objectEnum.Weather;


/**
 * Actividade Aerobica, é um deposto Ludico
 * 
 */
public class Aerobica extends Ludic {
    private static final long serialVersionUID = 1L;

    /**
     * Construtor vazio
     */
    public Aerobica() {
		super();
	}
    
    /**
     * Contrutor parametrizado
     * @param time duração da actividade em milisegundos
     * @param weather Weather tempo meteorologico da actividade
     * @param hRate pulsasão da actividade
     * @param date data da actividade
     */
	public Aerobica(long time, Weather weather, int hRate,
			GregorianCalendar date) {
		super(time, weather, hRate, date);
	}
	
    /**
     * Construtor de Copia
     * @param act actividade a copiar
     */
	public Aerobica(Aerobica act){
        super(act);
    }
    
    @Override
    public String getName() {
        return "Aeróbica";
    }

    @Override
    public int getIntensidade() {
        return 86;
    }

    @Override
    public double getMET() {
        return 7.3;
    }

    @Override
    public Aerobica clone() {
        return new Aerobica(this);
    }
}

