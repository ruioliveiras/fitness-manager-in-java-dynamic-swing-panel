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
public class EventContest extends Event{
    private int mNum;/*GamesBetweenUse*/
    private int mPointsVic;
    private int mPointsDraw;
    private int mPointsLoss;
    
    public EventContest() {
        super();
        this.mNum = 2;
        this.mPointsVic = 3;
        this.mPointsDraw = 1;
        this.mPointsDraw = 0;
    }
    
    public EventContest(String name, Activity activity, GregorianCalendar eventDate,
                    GregorianCalendar endDate, int preRequisite, int maxNumUsers,
                    int games, int victoryPts, int drawPts, int lossPts) {
        super(name, activity, eventDate, endDate, preRequisite, maxNumUsers);
        this.mNum = games;
        this.mPointsVic = victoryPts;
        this.mPointsDraw = drawPts;
        this.mPointsLoss = lossPts;
    } 
    
    public EventContest(EventContest e) {
        super(e);
        this.mNum = e.getNumGames();
        this.mPointsVic = e.getVicPts();
        this.mPointsDraw = e.getDrawPts();
        this.mPointsLoss = e.getLossPts();
    }
    
    public int getNumGames() {
        return this.mNum;
    }
    
    public void setNumGames(int games) {
        this.mNum = games;
    }
    
    public int getVicPts() {
        return this.mPointsVic;
    }
    
    public void setVicPts(int victoryPts) {
        this.mPointsVic = victoryPts;
    }
    
    public int getDrawPts() {
        return this.mPointsDraw;
    }
    
    public void setDrawPts(int drawPts) {
        this.mPointsDraw = drawPts;
    }
    
    public int getLossPts() {
        return this.mPointsLoss;
    }
    
    public void setLossPts(int lossPts) {
        this.mPointsLoss = lossPts;
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
         return ((this.mNum == ((EventContest)obj).getNumGames()) &&
                 (this.mPointsVic == ((EventContest)obj).getVicPts()) &&
                 (this.mPointsDraw == ((EventContest)obj).getDrawPts()) &&
                 (this.mPointsLoss == ((EventContest)obj).getLossPts()) &&
                 (super.equals(obj)));
    }
    
    @Override
    public String toString(){
        StringBuilder stringb = new StringBuilder();
        stringb.append("Nome: " + this.getName() + "\n");
        stringb.append("Actividade: " + this.getActivity().getName() + "\n");
        stringb.append("Data: " + this.getEventDate().toString() + "\n");
        return stringb.toString();
    }
    
    @Override
    public int hashCode(){
        return Util.CalcHashCode(this.getName().hashCode(), this.getVicPts(), this.getEventDate().hashCode());
    }
    
    @Override
    public Object clone(){
        return new EventContest(this);
    }
}
