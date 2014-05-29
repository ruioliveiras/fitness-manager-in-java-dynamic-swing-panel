package controller;

public class NameDontExistException extends Exception {

	private static final long serialVersionUID = -323304088290842635L;

	public NameDontExistException() {
		super();
	}

	public NameDontExistException(String message) {
		super(message);
	}
	
}
