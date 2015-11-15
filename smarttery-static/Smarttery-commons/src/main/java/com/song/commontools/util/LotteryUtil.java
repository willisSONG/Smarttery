package com.song.commontools.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * 公用方法
 * @author willis
 *
 */
public class LotteryUtil {

	/**
	 * get url
	 * @param key
	 * @return
	 */
	private static Logger logger = LoggerFactory.getLogger(LotteryUtil.class);
	public static String getUrl(String key){
		InputStream inputStream = LotteryUtil.class.getResourceAsStream("/com/song/lottery/site/config/lottery_url_mapper.properties");
//		System.out.println("class = "+Thread.currentThread().getContextClassLoader().getClass());
//		System.out.println("class2 = "+Thread.currentThread().getContextClassLoader());
		Properties p = new Properties();
		try {
			p.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p.getProperty(key);
	}
	/**
	 * get webpage string
	 */
	public static String getWebPageStr(String url){
		HttpClient client = HttpClients.createDefault();
//		client.getParams().setParameter(HTTP.CONTENT_ENCODING, HTTP.UTF_8);
		URI uri;
		try {
			uri = new URI(url);
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
			return result;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 通过正则表达式返回匹配串
	 * @param patternStr
	 * @param goalStr
	 * @return
	 */
	public static String getValueViaReg(String patternStr,String goalStr){
		Pattern ptn = Pattern.compile(patternStr);
		Matcher matcher = ptn.matcher(goalStr);
		StringBuffer sb = new StringBuffer();
		while(matcher.find()){
			sb.append(matcher.group());
		}
		return sb.toString();
	}
	/**
	 * 通过正则表达式返回匹配实例
	 * @param xpathStr
	 * @param obj
	 * @return
	 */
	public static Object getValueViaXpath(String xpathStr,Object obj,QName qname){
		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();
		Object object = null ;
		try {
//			System.out.println("xpathStr"+xpathStr);
			XPathExpression xpathExpression = xpath.compile(xpathStr);
			object = xpathExpression.evaluate(obj, qname);
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return object;
	}
	/**
	 * 根据传入的xml字符串，转换成document对象并返回
	 * @param str
	 * @return
	 */
	public static Document getDocumentObj(String str){
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;
		try {
			builder = dbf.newDocumentBuilder();
			document = builder.parse(new ByteArrayInputStream(str.getBytes("UTF-8")));
		} catch (ParserConfigurationException e) {
			document = null ;
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			document = null ;
			e.printStackTrace();
		} catch (SAXException e) {
			document = null ;
			e.printStackTrace();
		} catch (IOException e) {
			document = null ;
			e.printStackTrace();
		}
		return document;
	}
	/**
	 * 包装xml字符串，为最外层加一级<lottery>节点，以防报not well-formed
	 * 的异常
	 */
	public static String wrapperXmlStr(String str){
		return "<lottery>" + str + "</lottery>";
	}
	
	/**
	 * 生成lottery info的主键
	 */
	public static int generateLotteryInfoId(EntityManager entityManager){
		Query query =entityManager.createQuery("select max(lotteryInfoId) as maxNum from LotteryInfo");
		Object object = query.getSingleResult();
		return (object == null || "".equals(object.toString()) ? 100000000 : Integer.parseInt(object.toString()) + 1 );
	}
	
	/**
	 * 判断是否已经导入
	 */
	public static Object chargeExist(EntityManager entityManager,String tableName,String filedToFetch, Map<String,Object> fieldMap){
		
//		Query query =entityManager.createQuery("select lotteryInfoId from :tableName T where 1==1");
//		Query query =entityManager.createQuery("select lotteryInfoId from :tableName T where T.expect=:expect");
		StringBuffer sqlSb = new StringBuffer("select " + filedToFetch + " from " + tableName + " T where 1=1");
		Set<Map.Entry<String , Object>> entrySet = fieldMap.entrySet();
		Iterator<Map.Entry<String, Object>> iterator = entrySet.iterator();
		Iterator<Map.Entry<String, Object>> iterator_ = entrySet.iterator() ;
		
		while(iterator.hasNext()){
			Map.Entry<String, Object> entry = iterator.next();
			sqlSb.append(" and ").append(entry.getKey()).append("=:").append(entry.getKey());
		}
		Query query =entityManager.createNativeQuery(sqlSb.toString());
		logger.debug(sqlSb.toString());
//		query.setParameter("tableName", tableName);
		while(iterator_.hasNext()){
			Map.Entry<String, Object> entry = iterator_.next();
			query.setParameter(entry.getKey(), entry.getValue());
		}
		Object object = null;
		try{
			object = query.getSingleResult();
		}catch(NoResultException e){
			e.printStackTrace();
		}
		if(object == null )
			return object;
		logger.debug("result exists" + object);
//		logger.debug("object->" + ((Object[])object)[0]);
		return object;
	}
	/**
	 * 更新彩票数据
	 */
//	public static int
}

