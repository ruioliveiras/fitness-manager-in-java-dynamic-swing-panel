package model.events;


/**
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ContestPair
{
    private String mUser1;
    private String mUser2;

    public ContestPair(){mUser1 = ""; mUser2 = "";}
    public ContestPair(String user1, String user2){mUser1 = user1; mUser2 = user2;}
    
    public String getFstUser(){return this.mUser1;}
    public String getSndUser(){return this.mUser2;}
    public void setFstUser(String user1){this.mUser1=user1;}
    public void setSndUser(String user2){this.mUser1=user2;}
}
