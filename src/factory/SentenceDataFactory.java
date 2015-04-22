package factory;

import model.SentenceData;

public class SentenceDataFactory {
	private static SentenceDataFactory mInstance;
	
	private String mHoldClassName;
	private String mHoldComment;
	
	
	public static SentenceDataFactory getInstance() {
		if (mInstance == null) {
			mInstance = new SentenceDataFactory();
			
			return mInstance;
		}
		return mInstance;
	}
	
	
	public SentenceDataFactory() {
		// init
		mHoldClassName = "";
		mHoldComment   = "";
	}
	
	
	public SentenceData createSentenceData(String type, String name, int line) {
		SentenceData sentence = new SentenceData();
		sentence.setType(type);
		sentence.setName(name);
		sentence.setClassName(mHoldClassName);
		sentence.setComment(mHoldComment);
		sentence.setLine(line);
		
		refreshHoldComment();

		return sentence;
	}
	
	
	public void registerHoldClassName(String className) {
		this.mHoldClassName = className;
	}
	
	public void registerHoldComment(String comment) {
		this.mHoldComment = comment;
	}
	
	public void refreshHoldClassName() {
		mHoldClassName = "";
	}
	
	public void refreshHoldComment() {
		mHoldComment = "";
	}
}
