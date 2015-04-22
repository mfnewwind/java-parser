package model;

public class Response {
	private String status;
	private String result;

	
	// ---------------------------------------------
	// constructor
	// ---------------------------------------------
	
	public Response(String status, String result) {
		this.status = status;
		this.result = result;
	}
	
	
	// ---------------------------------------------
	// getter
	// ---------------------------------------------
	
	public String getStatus() {
		return status;
	}
	
	public String getResult() {
		return result;
	}
}
