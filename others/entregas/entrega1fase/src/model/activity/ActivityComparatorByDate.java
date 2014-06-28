package model.activity;

import java.io.Serializable;
import java.util.Comparator;
import java.util.GregorianCalendar;

/**
 * Classe de criacao de comparator de actividades por data de realizacao.
 * 
 * @author andrerfcsantos 
 */
public class ActivityComparatorByDate implements Comparator<Activity>,Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int compare(Activity a1, Activity a2){
        GregorianCalendar d1 = a1.getDate();
        GregorianCalendar d2 = a2.getDate();
        if(d1.after(d2)) return 1;
        else if(d1.before(d2)) return -1;
        else return 0;
    }
}
