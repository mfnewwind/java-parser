package factory;

import model.SentenceData;
import model.SentenceDataManager;

public class SentenceDataFactory {
	private static SentenceDataFactory mInstance;
	
	private SentenceDataManager mSentenceDataManager =SentenceDataManager.getInstance();
	
	private String mHoldClassName;
	private String mHoldComment;
	private int    mHoldCommentEndLine;
	
	
	
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
		mHoldCommentEndLine = 0;
	}
	
	
	// ----------------------------------------------------
	// carry methods
	// ----------------------------------------------------
	
	public void carrySentenceDataParts(String type, String name, int line) {
		SentenceData sentence = createSentenceData(type, name, line);
		mSentenceDataManager.registerSentenceData(sentence);
	}
	
	public void carrySentenceDataParts(String className) {
		this.mHoldClassName = className;
	}
	
	public void carrySentenceDataParts(String comment, int endLine) {
		
		int sentenceStartLine = ++endLine;
		int index = 0;
		for (SentenceData sentence : mSentenceDataManager.getSentenceDataList()) {
			if (sentence.getLine() == sentenceStartLine) {
				sentence.setComment(comment);
				mSentenceDataManager.getSentenceDataList().set(index, sentence);
				return;
			}
			index++;
		}
		
		this.mHoldComment = comment;
		this.mHoldCommentEndLine = endLine;
	}
	
	
	// ----------------------------------------------------
	// refresh methods
	// ----------------------------------------------------
	
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
		mHoldCommentEndLine = 0;
	}
	
	
	// ----------------------------------------------------
	// create methods
	// ----------------------------------------------------
	
	/**
	 * SentenceDataの作成
	 * @param type
	 * @param name
	 * @param line
	 * @return
	 */
	private SentenceData createSentenceData(String type, String name, int line) {
		SentenceData sentence = new SentenceData();
		sentence.setType(type);
		sentence.setName(name);
		sentence.setClass_name(mHoldClassName);
		sentence.setComment(fetchComment(line));
		sentence.setLine(line);
		
		refreshHoldComment();

		return sentence;
	}
	
	
	// ----------------------------------------------------
	// fetch methods
	// ----------------------------------------------------
	
	/**
	 * コメントを取得する
	 * @param nameLine
	 * @return
	 */
	private String fetchComment(int nameLine) {
		String comment = "";
		if (mHoldComment.equals("")) return comment;
		
		int commentLine = nameLine++;
		if (commentLine == mHoldCommentEndLine) {
			comment = mHoldComment;
			refreshHoldComment();
		}
		return "";
	}
}
