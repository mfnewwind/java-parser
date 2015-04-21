package util;


import java.io.File;

import java.io.File;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;

public class JavaParser {
	public static void main(String[] args) throws Exception {
		
		SourceFile sourceFile = new SourceFile("src" + File.separator + "JavaParser" + File.separator + "sampleClass.java");
		CompilationUnit unit;
		ASTParser parser;
		
		
		System.out.println("boot JavaParser");
	}
	
	private static void parser() {
		
	}
	
	private static void testCoode() {
	}
}
