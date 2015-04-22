package model;

public class SentenceData {
	private String type;
	private String name;
	private String class_name;
	private String comment;
	private int    line;
	
	
	// ---------------------------------------------
	// constructor
	// ---------------------------------------------
	
	public SentenceData() {
		// init
		this.type 		= "";
		this.name 		= "";
		this.class_name = "";
		this.comment 	= "";
		this.line 		= -1;
	}
	
	
	// ---------------------------------------------
	// getter
	// ---------------------------------------------
	
	public String getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	// TODO:キャメルとスネークが混ざっててキモい。
	public String getClass_name() {
		return class_name;
	}
	
	public String getComment() {
		return comment;
	}
	
	public int getLine() {
		return line;
	}
	
	
	// ---------------------------------------------
	// setter
	// ---------------------------------------------
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setClass_name(String className) {
		this.class_name = className;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public void setLine(int line) {
		this.line = line;
	}
}
