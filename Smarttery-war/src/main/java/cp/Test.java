package cp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Test {

	public static void test(){}
	public static void test2(){}
	public static void main(String[] args) {
		URL url;
		try {
			url = new URL("http://localhost:8080/cp/test");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("jsessionid", "E8EF842D23170A5EA59159FDA571BD52");
			InputStream in = conn.getInputStream();
//			byte[] b= new byte[1024];
//			int index = 0 ; 
//			while((index = in.read(b)) > 0){
//				System.out.println(new String(b,0,index));
//			}
//			
//			HttpURLConnection conn2 = (HttpURLConnection)new URL("http://localhost:8080/cp/test2").openConnection();
//			conn2.getInputStream();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
