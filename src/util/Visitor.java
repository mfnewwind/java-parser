package util;

import java.util.Iterator;
import java.util.List;

import model.SentenceData;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TagElement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import factory.SentenceDataFactory;

public class Visitor extends ASTVisitor{
	private CompilationUnit    mUnit;
	private List<SentenceData> mSentenceDataList;
	
	private SentenceDataFactory mSentenceDataFactory = SentenceDataFactory.getInstance();
	
	
	public Visitor(CompilationUnit unit, List<SentenceData> list) {
		super();
		
		this.mUnit = unit;
		this.mSentenceDataList = list;
	}
	
	
	// ---------------------------------------------------
	// visit methods
	// ---------------------------------------------------
	
	 /**
     * クラス宣言が見つかると呼ばれる
     */
    public boolean visit(TypeDeclaration node) {
//        Print.printTitle("クラス宣言");
       
        String type = "class"; // TODO: typeはFactory内にenumもたせといて選択させたい感
        String name = node.getName().getFullyQualifiedName();// クラス名の取得
        int    line = mUnit.getLineNumber(node.getName().getStartPosition());
        
//        Print.printMessage("name", name);
//        Print.printMessage("line", ""+line);
        
        // Sentence生成&リスト登録
        SentenceData sentence = mSentenceDataFactory.createSentenceData(type, name, line);
        mSentenceDataList.add(sentence);
        
        // クラス名の登録
        mSentenceDataFactory.registerHoldClassName(name);
        
        return super.visit(node);
    }
    
    /**
     * フィールド宣言が見つかると呼ばれる
     */
    public boolean visit(FieldDeclaration node) {
//        Print.printTitle("フィールド宣言");
        
        String type	= "variable";
        String name = "";
        int    line = this.mUnit.getLineNumber(node.getStartPosition());
        
        List fragments = node.fragments();
        for (Object frg : fragments) {
            if (frg instanceof VariableDeclarationFragment) {
                IVariableBinding variableBinding = ((VariableDeclarationFragment) frg)
                        .resolveBinding();
                name = variableBinding.getName();
            }
        }
        
//        Print.printMessage("type", type);
//        Print.printMessage("name", name);
//        Print.printMessage("line", ""+line);
        
        // Sentence生成&リスト登録
        SentenceData sentence = mSentenceDataFactory.createSentenceData(type, name, line);
        mSentenceDataList.add(sentence);
        
        return super.visit(node);
    }
    
    /**
     * ローカル変数が見つかると呼ばれる
     */
    public boolean visit(VariableDeclarationFragment node) {
    	String type = "variable";
    	String name = node.getName().getFullyQualifiedName();
    	int    line = this.mUnit.getLineNumber(node.getStartPosition());
    	
    	// Sentence生成&リスト登録
        SentenceData sentence = mSentenceDataFactory.createSentenceData(type, name, line);
        mSentenceDataList.add(sentence);
    	
    	return super.visit(node);
    }
    
    /**
     * メソッド宣言が見つかると呼ばれる
     */
    public boolean visit(MethodDeclaration node) {
//        Print.printTitle("メソッド宣言");
        
        String type = "function";
        String name = node.getName().getFullyQualifiedName();
        int    line = mUnit.getLineNumber(node.getStartPosition());
        
//        Print.printMessage("type", type);
//        Print.printMessage("name", name);
//        Print.printMessage("line", ""+line);
        
     // Sentence生成&リスト登録
        SentenceData sentence = mSentenceDataFactory.createSentenceData(type, name, line);
        mSentenceDataList.add(sentence);
        
        return super.visit(node);
    }
    
    /**
     * コメントが見つかると呼ばれる
     */
    public boolean visit(Javadoc node) {
      Iterator iterator = node.tags().iterator();
      String comment = "";
      while (iterator.hasNext()) {
        TagElement element = (TagElement) iterator.next();
//        Print.printMessage("内容", element.toString());
        
    	comment +=  element.toString();
      }
//      Print.printMessage("comment", comment);
      
      // 先頭の改行削除
      comment = deleteFistLineBreak(comment);
      
      // コメントの登録
      mSentenceDataFactory.registerHoldComment(comment);
      
      return super.visit(node);
    }
    
    /**
     * 先頭に改行がある場合消す
     * @param string
     * @return
     */
    private String deleteFistLineBreak(String string) {
    	if (string.length() < 3) return string;
    	
    	String firstString = string.substring(0,1);
    	if (firstString.equals(System.getProperty("line.separator"))) {
    		StringBuilder sb = new StringBuilder(string);    		
    		sb.delete(0,2);
    		
    		return sb.toString();
    	}
    	
    	return string;
    }
}
