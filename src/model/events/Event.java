package model.events;

import java.util.GregorianCalendar;

import model.ObjectClonable;
import model.activity.Activity;


//-Classe Evento: {Conjuto Users, Actividade, Categoria, Data evento, Data inscricao, Nome, pre-requisito inscricao (long tempo/distancia), numero maximo de participantes}

public abstract class Event implements ObjectClonable  {
    /*Set de strings (email)*/
    private String mName;
    private Activity mActivity;/*usar String com nome do tipo de Actividade?*/
    private GregorianCalendar mEventDate;
    private GregorianCalendar mEndDate;
    private int mPreRequisite;
    private int mMaxNumUsers;

    public Event() {
        mName = "";
        mActivity = null;
        mEventDate = new GregorianCalendar();
        mEndDate = new GregorianCalendar();
        mPreRequisite = -1;
        mMaxNumUsers = -1;
    }
    public Event(String name, Activity activity, GregorianCalendar eventDate,
                    GregorianCalendar endDate, int preRequisite, int maxNumUsers) {
        mName = name;
        mActivity = activity;
        mEventDate = eventDate;
        mEndDate = endDate;
        mPreRequisite = preRequisite;
        mMaxNumUsers = maxNumUsers;
    }

    public Event(Event e){
        this(e.getName(),e.getActivity(),e.getEventDate(),e.getEndDate(),e.getPreRequisite(),e.getMaxNumUsers());
    }
    

    public String getName() {
        return mName;
    }
    public void setName(String mName) {
        this.mName = mName;
    }
    public Activity getActivity() {
        return mActivity;
    }
    public void setActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }
    
    public GregorianCalendar getEventDate() {
        return mEventDate;
    }
    public void setEventDate(GregorianCalendar mEventDate) {
        this.mEventDate = mEventDate;
    }
    public GregorianCalendar getEndDate() {
        return mEndDate;
    }
    public void setEndDate(GregorianCalendar mEndDate) {
        this.mEndDate = mEndDate;
    }
    public int getPreRequisite() {
        return mPreRequisite;
    }
    public void setPreRequisite(int mPreRequisite) {
        this.mPreRequisite = mPreRequisite;
    }
    public int getMaxNumUsers() {
        return mMaxNumUsers;
    }
    public void setMaxNumUsers(int mMaxNumUsers) {
        this.mMaxNumUsers = mMaxNumUsers;
    }
    
    public boolean equals(Object obj){
      if(this == obj) return true; 
      if((obj == null) || (this.getClass() != obj.getClass())) return false;
      Event e = (Event) obj;
      return this.mName.equals(e.getName()) && this.mEventDate.equals(e.getEventDate());
    }
    
    public abstract int hashCode();
    
    public abstract Object clone();
    
    public abstract String toString();
}
