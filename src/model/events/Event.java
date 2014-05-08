package model.events;

import java.util.GregorianCalendar;

import model.activity.Activity;


//-Classe Evento: {Conjuto Users, Actividade, Categoria, Data evento, Data inscricao, Nome, pre-requisito inscricao (long tempo/distancia), numero maximo de participantes}

public class Event  {
	private String mName;
	private Activity mActivity;
	private int mRecord;
	private GregorianCalendar mEventDate;
	private GregorianCalendar mEndDate;
	private int mPreRequisite;
	private int mMaxNumUsers;
	
	
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public Activity getmActivity() {
		return mActivity;
	}
	public void setmActivity(Activity mActivity) {
		this.mActivity = mActivity;
	}
	public int getmRecord() {
		return mRecord;
	}
	public void setmRecord(int mRecord) {
		this.mRecord = mRecord;
	}
	public GregorianCalendar getmEventDate() {
		return mEventDate;
	}
	public void setmEventDate(GregorianCalendar mEventDate) {
		this.mEventDate = mEventDate;
	}
	public GregorianCalendar getmEndDate() {
		return mEndDate;
	}
	public void setmEndDate(GregorianCalendar mEndDate) {
		this.mEndDate = mEndDate;
	}
	public int getmPreRequisite() {
		return mPreRequisite;
	}
	public void setmPreRequisite(int mPreRequisite) {
		this.mPreRequisite = mPreRequisite;
	}
	public int getmMaxNumUsers() {
		return mMaxNumUsers;
	}
	public void setmMaxNumUsers(int mMaxNumUsers) {
		this.mMaxNumUsers = mMaxNumUsers;
	}

	
}
