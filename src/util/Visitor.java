package util;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.IVariableBinding;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TagElement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

public class Visitor extends ASTVisitor{
	
	private CompilationUnit mUnit;
	
	public Visitor(CompilationUnit unit) {
		super();
		mUnit = unit;
	}
	
	 /**
     * クラス宣言が見つかると呼ばれるメソッド
     */
    public boolean visit(TypeDeclaration node) {
        Print.printTitle("クラス宣言");
        ITypeBinding typeBinding = node.resolveBinding();// 詳細な情報をITypeBindingインスタンスを使って取得したい
        String name = typeBinding.getBinaryName();// クラス名の取得
        Print.printMessage("name", name);
        return super.visit(node);
    }
    /**
     * フィールド宣言が見つかると呼ばれるメソッド
     */
    public boolean visit(FieldDeclaration node) {
        Print.printTitle("フィールド宣言");
        List fragments = node.fragments();
        for (Object frg : fragments) {
            if (frg instanceof VariableDeclarationFragment) {
                IVariableBinding variableBinding = ((VariableDeclarationFragment) frg)
                        .resolveBinding();
                Print.printMessage("name", variableBinding.getName());
            }
        }
        int line = mUnit.getLineNumber(node.getStartPosition());
        Print.printMessage("line", Integer.toString(line));
        
        return super.visit(node);
    }
    
    /**
     * メソッド宣言が見つかると呼ばれるメソッド
     */
    public boolean visit(MethodDeclaration node) {
        Print.printTitle("メソッド宣言");
        Print.printMessage("name", node.getName()
                .getFullyQualifiedName());
        int startLine = mUnit.getLineNumber(node.getName().getStartPosition());
        Print.printMessage("line", Integer.toString(startLine));
        
        return super.visit(node);
    }
    
    /**
     * コメントが見つかると呼ばれるメソッド
     */
    public boolean visit(Javadoc node) {
      Iterator iterator = node.tags().iterator();
      String comment = "";
      while (iterator.hasNext()) {
        TagElement element = (TagElement) iterator.next();
        comment +=  element.toString();
      }
      Print.printMessage("comment", comment);
      return super.visit(node);
    }
}
