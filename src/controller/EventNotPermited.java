package controller;

public class EventNotPermited extends Exception {
	private static final long serialVersionUID = -323304088290842635L;

	public EventNotPermited() {
		super();
	}

	public EventNotPermited(String message) {
		super(message);
	}
	
}
