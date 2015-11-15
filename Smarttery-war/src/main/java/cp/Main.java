package cp;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.song.commontools.util.LotteryUtil;

public class Main {
//http://www.cwl.gov.cn/kjxx/ssq/kjgg/395058.shtml
	public static void main(String[] args) {
//		test2();
//		EntityManagerFactory mmf = Persistence.createEntityManagerFactory("persistence_1");
//		
//		System.out.println(LotteryUtil.generateLotteryInfoId(mmf.createEntityManager()));
		test3();
	}
	public static void test2(){
		String str = LotteryUtil.getWebPageStr("http://www.cwl.gov.cn/kjxx/ssq/kjgg/395651.shtml");
		System.out.println(str);
//		String regExpStr = "<li[\\s]+class=[\",a-z,A-Z,0-9,_,>,<,\\s,\\u4E00-\\u9FFF,/,:,：,-]+</li>";
//		Pattern ptn = Pattern.compile(regExpStr);
//		Matcher m = ptn.matcher(str);
//		while(m.find()){
//			String tmp = m.group();
//			System.out.println(tmp);
//		}
	}
	public static void test3(){
		//http://www.cwl.gov.cn/kjxx/ssq/kjgg/397799.shtml
		//http://www.cwl.gov.cn/kjxx/ssq/kjgg/395058.shtml
		String str = LotteryUtil.getWebPageStr("http://www.cwl.gov.cn/kjxx/ssq/kjgg/397799.shtml");
		String regExpStr = "<tbody[\\s]*>[\\w<>/\"（）\\u4E00-\\u9FFF\\uff1a\\s,=]+</tbody>";
		Pattern ptn = Pattern.compile(regExpStr);
		Matcher m = ptn.matcher(str);
		System.out.println("-------------------------------------------------------");
		StringBuffer sb = new StringBuffer();
		while(m.find()){
			String tmp = m.group();
			sb.append(tmp);
//			System.out.println(tmp);
		}
		System.out.println("result = "+sb.toString().replaceAll("[\\n,\\r,\\t]", ""));
	}
	public static void test1(){
		HttpClient client = HttpClients.createDefault();
		URI uri;
		try {
			System.out.println(Thread.currentThread().getContextClassLoader());
			System.out.println(Thread.currentThread().getClass().getClassLoader());
			uri = new URI(LotteryUtil.getUrl("lottery_fl_url"));
			HttpGet get = new HttpGet(uri);
			HttpResponse response = client.execute(get);
			HttpEntity entity =  response.getEntity();
			InputStream inputStream1 = entity.getContent();
			byte[] b = new byte[1024];
			int position = 0 ;
			StringBuffer sb = new StringBuffer();
			while((position = inputStream1.read(b)) > 0){
				sb.append(new String(b, 0, position));
			}
			String result = sb.toString();
//			System.out.println(result);
			String regExp = "<li><span class=\"fr\">[0-9]{4}-[0-9]{2}-[0-9]{2}[\\s]*[0-9]{2}:[0-9]{2}</span>" ;
			String regExp2 = "<a[\\s]*title=\"[\\u4E00-\\u9FFF,\u201c,\u201d,0-9,\"]+[\\s]*href=[\",\\.,0-9,a-z,/]+>[\\u4E00-\\u9FFF,\u201c,\u201d,0-9,\",\\s]+[\\s]*</a>[\\s]*</li>" ;
			Pattern ptn = Pattern.compile(regExp+regExp2);
			Matcher mcher = ptn.matcher(result);
			Pattern ptn2 = Pattern.compile(regExp2);
			Matcher mcher2 = ptn2.matcher(result);
			while(mcher.find()){
				String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
				String tmp = mcher.group();
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				try {
					DocumentBuilder builder = dbf.newDocumentBuilder();
					Document document = builder.parse(new InputSource(new ByteArrayInputStream(tmp.getBytes())));
					XPathFactory xpf = XPathFactory.newInstance();
					XPath xpath = xpf.newXPath();
					String expression = "/li/span";
					String expression2 = "/li/a";
					String expression3 ="/li/a/@href";
					XPathExpression xpathexp = xpath.compile(expression);
					XPathExpression xpathexp2 = xpath.compile(expression2);
					XPathExpression xpathexp3 = xpath.compile(expression3);
					
					Node node = (Node) xpathexp.evaluate(document, XPathConstants.NODE);
					Node node2 = (Node) xpathexp2.evaluate(document, XPathConstants.NODE);
					String str = (String) xpathexp3.evaluate(document, XPathConstants.STRING);
					System.out.print(node.getTextContent()+"\t");
					System.out.print(node2.getTextContent() + "\t");
					System.out.println(str);
				} catch (ParserConfigurationException | SAXException | XPathExpressionException e) {
					e.printStackTrace();
				}
			}
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
	
	}
	public static void test4(){
		String str = LotteryUtil.getWebPageStr("http://www.cwl.gov.cn/kjxx/ssq/kjgg/395058.shtml");
//		System.out.println(str);
		String regExpStr = "<li[\\s]*class=\"caizhong\"[\"\\w_><\\s\\u4E00-\\u9FFF/\\uff1a:\\-=,]+</li>";
		String regExpStr2 = "[^\\w^\\u4E00-\\u9FFF^/^\\uff1a^:^\\^=^,^<^>^;^&^\\.^!^\\(^\\)^\"^\\{^\\}^\\$^#^\\s^“^”^-^,^\\?^\\[^\\]^\\-^_^+^'^，^。^%^、^；,^（^）^\\u2014]";
		String regExpStr3 = "<div[\\s]*class[\\s]*=[\\s]*\"[\\s]*mt10[\\s]*\">[\\u4E00-\\u9FFF0-9\\uff1a:,\uff0ca-zA-Z=<>\u3002\\s]+</div>";
		Pattern ptn = Pattern.compile(regExpStr3);
		Matcher m = ptn.matcher(str);
		System.out.println("-------------------------------------------------------");
		StringBuffer sb = new StringBuffer();
		while(m.find()){
			String tmp = m.group();
			sb.append(tmp);
			System.out.print(tmp);
		}}
	public static void test5(){
//		Lottery_FL_Crawler lfc = new Lottery_FL_Crawler();
//		LotteryInfo li = lfc.getLotteryInfo();
//		
//		System.out.println(li.getWonDetail().get(0).getBonusAmount());
	}
}
