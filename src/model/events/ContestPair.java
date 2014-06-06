package model.events;


/**
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ContestPair
{
    private String mUser1;
    private int mPtsUser1;
    private String mUser2;
    private int mPtsUser2;

    public ContestPair(){mUser1 = ""; mUser2 = ""; mPtsUser1=0; mPtsUser2=0;}
    public ContestPair(String user1, String user2){mUser1 = user1; mUser2 = user2; mPtsUser1=0; mPtsUser2=0;}
    
    public String getFstUser(){return this.mUser1;}
    public String getSndUser(){return this.mUser2;}
    public int getUser1Pts(){return this.mPtsUser1;}
    public int getUser2Pts(){return this.mPtsUser2;}
    
    public void setFstUser(String user1){this.mUser1=user1;}
    public void setSndUser(String user2){this.mUser1=user2;}
    public void setUser1Pts(int pts){this.mPtsUser1=pts;}
    public void setUser2Pts(int pts){this.mPtsUser2=pts;}
    
    public String toString(){
        String resultado;
        if(this.mPtsUser1 == this.mPtsUser2)
            resultado = "\t ------- Empate";
        else if(this.mPtsUser1 > this.mPtsUser2)
            resultado = " ------- Vencedor= " + this.mUser1;
        else
            resultado = " ------- Vencedor= " + this.mUser2;
        return (this.mUser1 + "  vs  " + this.mUser2 + resultado);
    }
}
