package model.events;

import java.util.GregorianCalendar;

import model.activity.Activity;


//-Classe Evento: {Conjuto Users, Actividade, Categoria, Data evento, Data inscricao, Nome, pre-requisito inscricao (long tempo/distancia), numero maximo de participantes}

public class Event  {

	private String mName;
	private Activity mActivity;
	private int mRecordType; /*An event is about a Activity with his record type, like: Run 100m*/
	private GregorianCalendar mEventDate;
	private GregorianCalendar mEndDate;
	private int mPreRequisite;
	private int mMaxNumUsers;

	public Event() {
		mName = "";
		mActivity = null;
		mRecordType = -1;
		mEventDate = new GregorianCalendar();
		mEndDate = new GregorianCalendar();
		mPreRequisite = -1;
		mMaxNumUsers = -1;
	}
	public Event(String name, Activity activity, int recordType,
			GregorianCalendar eventDate, GregorianCalendar endDate,
			int preRequisite, int maxNumUsers) {
		mName = name;
		mActivity = activity;
		mRecordType = recordType;
		mEventDate = eventDate;
		mEndDate = endDate;
		mPreRequisite = preRequisite;
		mMaxNumUsers = maxNumUsers;
	}
	public Event(Event e){
		this(e.getName(),e.getActivity(),e.getRecord(),e.getEventDate(),e.getEndDate(),e.getPreRequisite(),e.getMaxNumUsers());
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
	public int getRecord() {
		return mRecordType;
	}
	public void setRecord(int mRecord) {
		this.mRecordType = mRecord;
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

	
}
