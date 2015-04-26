package model;

import java.util.ArrayList;
import java.util.List;

public class SentenceDataManager {
	private static SentenceDataManager mInstance;
	
	private List<SentenceData> mSentenceDataList;
	
	public static SentenceDataManager getInstance() {
		if (mInstance == null) {
			mInstance = new SentenceDataManager();
			
			return mInstance;
		}
		return mInstance;
	}
	
	
	public SentenceDataManager() {
		// init
		this.mSentenceDataList = new ArrayList<SentenceData>();
	}
	
	/**
	 * SentenceDataを登録
	 * @param sentence
	 */
	public void registerSentenceData(SentenceData sentence) {
		mSentenceDataList.add(sentence);
	}
	
	/**
	 * SentenceDataListを取得
	 * @return
	 */
	public List<SentenceData> getSentenceDataList() {
		return mSentenceDataList;
	}
	
	/** 
	 * 指定したインデックスのSentenceDataを更新
	 * @param index
	 * @param sentence
	 */
	public void updateSentence(int index, SentenceData sentence) {
		mSentenceDataList.set(index, sentence);
	}
}
