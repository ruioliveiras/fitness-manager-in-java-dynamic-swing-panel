package model.events;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import model.activity.Activity;
import core.util.Util;


/**
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EventDistance extends Event {
    private int mEventDistance;/*An event is about a Activity with his record type, like: Run 10000m*/
    
    
    
    public EventDistance() {
        super();
        this.mEventDistance = -1;
    }
    
    public EventDistance(Activity activity){
    	super();
    	setActivity(activity);
    }
    
    public EventDistance(String name, Activity activity, GregorianCalendar eventDate,
                    GregorianCalendar endDate, long preRequisite, int maxNumUsers, int recordType, int distance) {
        super(name, activity, eventDate, endDate,new ArrayList<String>(), preRequisite, maxNumUsers, recordType);
        this.mEventDistance = distance;
    }
    
    public EventDistance(EventDistance e) {
        super(e);
        this.mEventDistance = e.getEventDistance();
    }
    
    public int getEventDistance() {
        return this.mEventDistance;
    }
    
    public void setEventDistance(int distance) {
        this.mEventDistance = distance;
    }
    
    @Override 
    public boolean equals(Object obj){
       if (this == obj)
         return true;
       if ((obj == null) || (this.getClass() != obj.getClass()))
         return false;
       else  
         return this.mEventDistance == ((EventDistance)obj).getEventDistance() && super.equals(obj);
    }
    
    @Override
    public String toString(){
        StringBuilder stringb = new StringBuilder();
        stringb.append("Nome: " + this.getName());
        stringb.append(", Actividade: " + this.getActivity().getName());
        stringb.append(", Distancia(m): " + this.getActivity());
        stringb.append(", Data: " + this.getEventDate().toString());
        return stringb.toString();
    }
    
    @Override
    public int hashCode(){
        return Util.CalcHashCode(this.getName().hashCode(), this.mEventDistance, this.getEventDate().hashCode());
    }
    
    @Override
    public Object clone(){
        return new EventDistance(this);
    }
}
