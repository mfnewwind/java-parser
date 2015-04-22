package main;

import util.JavaParser;

public class Main {
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args){
		String       response = "";
//		System.out.println("boot JavaParser ");
		
		// ファイルパス取得
		String filePath = "src/main/Main.java"; // TODO:Eclipse build用　必要なくなれば消す
		try {
			filePath = args[0];
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("error: ArrayIndexOutOfBoundsException, ファイルを指定してくれ");
		}
//		System.out.println("file path = " + filePath);
		
		// パース処理
		JavaParser parser = new JavaParser();
		try {
			String parseResult = parser.parseToJsonString(filePath);
			response = createResponse("succes", parseResult);
		} catch (Exception e) {
			response = createResponse("fail", "parse failed"); 
		}
		
		// レスポンス吐き出し
		System.out.println(response);
	}
	
	
	// --------------------------------------------
	// create Response methods
	// --------------------------------------------
	
	private static String createResponse(String status, String result) {
		String response = "{\"status\":\"" + status + "\""
						+ ",\"result\":" + result + "}";
		return response;
	}
}
