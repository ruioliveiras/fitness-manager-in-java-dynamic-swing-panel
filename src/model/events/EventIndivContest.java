package model.events;
import java.util.GregorianCalendar;
import java.util.List;
import model.activity.Activity;
import core.util.Util;


/**
 * Write a description of class EventIndivContest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EventIndivContest extends Event{
    private ContestType mType;
    
    public EventIndivContest() {
        super();
        this.mType = ContestType.ND;
    }
    
    public EventIndivContest(String name, Activity activity, GregorianCalendar eventDate,
                    GregorianCalendar endDate, int preRequisite, int maxNumUsers, ContestType type) {
		super(name, activity, eventDate, endDate, preRequisite, maxNumUsers);
		this.mType = type;
    } 
    
    public EventIndivContest(EventIndivContest e) {
        super(e);
        this.mType = e.getContestType();
    }
    
    public ContestType getContestType() {
        return this.mType;
    }
    
    public void setContestType(ContestType type) {
        this.mType = type;
    }
    
    /*TODO: list of all games between users*/
    public List<String> getGames(){
        return null;
    }
    
    @Override 
    public boolean equals(Object obj){
       if (this == obj)
         return true;
       if ((obj == null) || (this.getClass() != obj.getClass()))
         return false;
       else  
         return this.mType == ((EventIndivContest)obj).getContestType() && super.equals(obj);
    }
    
    @Override
    public String toString(){
        StringBuilder stringb = new StringBuilder();
        stringb.append("Nome: " + this.getName() + "\n");
        stringb.append("Tipo: " + this.getContestType().toString() + "\n");
        stringb.append("Data: " + this.getEventDate().toString() + "\n");
        return stringb.toString();
    }
    
    @Override
    public int hashCode(){
        return Util.CalcHashCode(this.getName().hashCode(), this.mType.hashCode(), this.getEventDate().hashCode());
    }
    
    @Override
    public Object clone(){
        return new EventIndivContest(this);
    }
}
