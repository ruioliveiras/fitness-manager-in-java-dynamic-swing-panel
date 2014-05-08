package model.user;

import model.activityHigh.Activity;
import model.activityHigh.ActivityComparatorByDate;
import java.util.Set;
import java.util.TreeSet;
import java.util.GregorianCalendar;

/**
 * Classe com as actividades um utilizador.
 */
public class ActividadesUser{
    private TreeSet<Activity> actividadesUser;
    
    public ActividadesUser(){
        actividadesUser = new TreeSet<Activity>(new ActivityComparatorByDate());
    }
    
    public void addActivity(Activity a){
        actividades.add(a.clone());
    }
    
    public Set<Activity> actividadesEntre(GregorianCalendar dataInferior, GregorianCalendar dataSuperior){
        Set<Activity> res = new TreeSet<Activity>(new ActivityComparatorByDate());
        /*Actividades artificiais para efeitos de comparacao*/
        Activity a1 = new Activity(); a1.setDate(dataInferior);
        Activity a2 = new Activity(); a2.setDate(dataSuperior);
        
        for(Activity ac : this.actividadesUser.subSet(a1, true, a2, true)){
            res.add(ac.clone());
        }
        
        return res;
    }
    
    
    
}