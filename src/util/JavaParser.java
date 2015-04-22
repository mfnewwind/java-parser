package util;

import java.util.ArrayList;
import java.util.List;

import model.SentenceData;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JavaParser {
	
	public JavaParser() {
	}
	
	public String parseToJsonString(String filePath) throws Exception {
		List<SentenceData> list   = new ArrayList<SentenceData>();
		ObjectMapper 	   mapper = new ObjectMapper();
		
		// 本来ならここでfilePathを渡す
		SourceFile sourceFile = new SourceFile(filePath);
		ASTParser  parser     = createParser(sourceFile);
		
		// CompilationUnitの作成
		CompilationUnit unit   = (CompilationUnit) parser.createAST(new NullProgressMonitor());;
		unit.recordModifications();// ASTへの操作履歴のようなものを有効に	
		
		// Visitorの作成
		Visitor visitor = new Visitor(unit, list);;
		
        // 解析実行
        unit.accept(visitor);
//        Print.printMessage("END", "parse end, result size = " + list.size());
        
//        Print.printMessage("sentence", mapper.writeValueAsString(list));
        return mapper.writeValueAsString(list);
	}
	
	private static ASTParser createParser(SourceFile sourceFile){
		ASTParser parser = ASTParser.newParser(AST.JLS8);
        
        // 以下の setBindingsRecovery setStatementsRecovery はおまじない．
        // 完成しているソースコードを解析する時は呼ぶ必要ない．
        // 詳しく知りたいならば，IMBのASTParser関連のドキュメントとかを参照すべき．
        parser.setBindingsRecovery(true);
        parser.setStatementsRecovery(true);
        
        // 次の setResolveBindings と setEnvironment が重要！！
        // setResolveBindings(true) をしておかないとまともに解析はできない．
        // setResolveBindings をまともに機能させるために setEnvironment が必要．
        parser.setResolveBindings(true);
        
        // setEnvironment の第一引数にはクラスパスの配列．第二引数にはソースコードを検索するパスの配列
        // 第三第四については何も考えず null, true ．納得いかない時はIBMのASTPa...
        parser.setEnvironment(Envs.getClassPath(), Envs.getSourcePath(),
                null, true);
        
        // 解析対象のソースコードの入力とか
        parser.setUnitName(sourceFile.getFilePath());// なんでもいいから名前を設定しておく
        parser.setSource(sourceFile.getSourceCode().toCharArray());// 解析対象コードを設定する
		
		return parser;
	}
}
