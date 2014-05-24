package model.events;
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
    
    public EventDistance(String name, Activity activity, GregorianCalendar eventDate,
                    GregorianCalendar endDate, int preRequisite, int maxNumUsers, int distance) {
		super(name, activity, eventDate, endDate, preRequisite, maxNumUsers);
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
        stringb.append("Nome: " + this.getName() + "\n");
        stringb.append("Actividade: " + this.getActivity().getName() + "\n");
        stringb.append("Distancia(m): " + this.getActivity() + "\n");
        stringb.append("Data: " + this.getEventDate().toString() + "\n");
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
