package controller;

public class ActivityNameDontExistException extends Exception {

	private static final long serialVersionUID = -323304088290842635L;

	public ActivityNameDontExistException() {
		super();
	}

	public ActivityNameDontExistException(String message) {
		super(message);
	}
	
}
