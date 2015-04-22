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
	
	/**
	 * SentenceDataの作成
	 * @param type
	 * @param name
	 * @param line
	 * @return
	 */
	public SentenceData createSentenceData(String type, String name, int line) {
		SentenceData sentence = new SentenceData();
		sentence.setType(type);
		sentence.setName(name);
		sentence.setClass_name(mHoldClassName);
		sentence.setComment(mHoldComment);
		sentence.setLine(line);
		
		refreshHoldComment();

		return sentence;
	}
	
	
	/**
	 * 保持しておくクラス名を登録
	 * @param className
	 */
	public void registerHoldClassName(String className) {
		this.mHoldClassName = className;
	}
	
	/**
	 * 保持しておくコメントを登録
	 * @param comment
	 */
	public void registerHoldComment(String comment) {
		this.mHoldComment = comment;
	}
	
	/**
	 * 保持していたクラス名を消す
	 */
	public void refreshHoldClassName() {
		mHoldClassName = "";
	}
	
	/**
	 * 保持していコメントを消す
	 */
	public void refreshHoldComment() {
		mHoldComment = "";
	}
}
