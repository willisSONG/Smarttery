package com.song.lottery.crawler;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.song.builder.impl.LotteryInfoBuilder;
import com.song.commontools.util.LotteryUtil;
import com.song.lottery.constant.LotteryConstant;
import com.song.lottery.entity.LotteryBonusInfo;
import com.song.lottery.entity.LotteryInfo;
import com.song.lottery.factory.LotteryFactory;

/**
 * 福利彩票数据爬虫
 * @author willis
 * 2015 / 08 / 01
 */
public class Lottery_FL_Crawler {

	//开奖日期,本期销量,奖池<li[\\s]*class=\"caizhong\"[\"\\w_><\\s\\u4E00-\\u9FFF/\\uff1a:\\-=,]+</li>
	private static	String regExpStr_1 = "<li[\\s]+class=[\"0-9,a-zA-Z_><\\s\\u4E00-\\u9FFF/\\uff1a:\\-=,]+</li>";
	//中奖情况
	private static	String regExpStr_2 = "<tbody[\\s]*>[\\w<>（）/\"\\u4E00-\\u9FFF\\uff1a\\s,=]+</tbody>";
	//一等奖中奖情况
	private static	String regExpStr_3 = "<div[\\s]*class[\\s]*=[\\s]*\"[\\s]*mt10[\\s]*\">[\\u4E00-\\u9FFF0-9\\uff1a:,\uff0ca-zA-Z=<>\u3002\\s]+</div>";
			
	private static class InnerClass{
		private static final Lottery_FL_Crawler crawlerInstance = new Lottery_FL_Crawler();
	}
	public static Lottery_FL_Crawler getInstance(){
		return InnerClass.crawlerInstance;
	}
	private Lottery_FL_Crawler() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 以双色球为例,加一个参数吧，叫做lotteryInfoId，生成一个出来
	 * 获取开奖信息
	 * @return
	 */
	public synchronized LotteryInfo getLotteryInfo(EntityManager entityManager){
		int lotteryInfoId = LotteryUtil.generateLotteryInfoId(entityManager);
		LotteryFactory lf = LotteryFactory.newInstance();
		LotteryInfoBuilder libuilder = lf.createLotteryBuilder();
		String result = LotteryUtil.getWebPageStr(LotteryUtil.getUrl("lottery_fl_url"))
						.replaceAll(LotteryConstant.FILTER_GARBLED, "");//去掉由于网络传输丢包而带来的乱码
		String regExp = "<li><span class=\"fr\">[0-9]{4}-[0-9]{2}-[0-9]{2}[\\s]*[0-9]{2}:[0-9]{2}</span>" ;
		String regExp_ = "<a[\\s]*title=\"[\\u4E00-\\u9FFF,\u201c,\u201d,0-9,\"]+[\\s]*href=[\",\\.,0-9,a-z,/]+>[\\u4E00-\\u9FFF,\u201c,\u201d,0-9,\",\\s]+[\\s]*</a>[\\s]*</li>" ;
		Pattern ptn = Pattern.compile(regExp+regExp_);
		Matcher mcher = ptn.matcher(result);
		StringBuffer sbf = new StringBuffer();
		while(mcher.find()){
			sbf.append(mcher.group());
		}
//		System.out.println(sbf.toString());
		Document document = LotteryUtil.getDocumentObj("<lottery>"+sbf.toString()+"</lottery>");
		String expression_opentime = "/lottery/li/span[position()=1]";
		String expression_expect = "/lottery/li/a[position()=1]";
		String expression_latest_kjgg_url = "/lottery/li/a[position()=1]/@href";
		//开奖时间
		Node node_opentime = (Node)LotteryUtil.getValueViaXpath(expression_opentime, document, XPathConstants.NODE);
		//开奖期次
		Node node_expect = (Node)LotteryUtil.getValueViaXpath(expression_expect,document, XPathConstants.NODE);
		//详情的链接地址
		String latest_kjgg_url = (String) LotteryUtil.getValueViaXpath(expression_latest_kjgg_url,document,XPathConstants.STRING);
		String expect = node_expect.getTextContent().replaceAll("[\\u4E00-\\u9FFF,\u201c,\u201d]", "");
		if(node_expect.getTextContent().indexOf("\u53cc\u8272\u7403") >= 0){
			//双色球
			libuilder.setLotteryName("ssq");
		}
		libuilder.setLotteryInfoId(lotteryInfoId);
		libuilder.setOpenTime(node_opentime.getTextContent());
		libuilder.setExpect(expect.trim());
		String detailUrl = LotteryUtil.getUrl("lottery_fl_url") + LotteryUtil.getValueViaReg("[0-9]+.shtml", latest_kjgg_url);
		libuilder = this.getDetailInfo(libuilder, detailUrl);
		LotteryInfo lotteryInfo =libuilder.build();
		return lotteryInfo;
	}
	/**
	 * 以双色球为例
	 * @param lotteryInfoBuilder
	 * @param url
	 * @return
	 * Description双色球刚开奖那段时间（1个小时左右）除了头奖号码公布，其他信息都没更新
	 */
	private LotteryInfoBuilder getDetailInfo(LotteryInfoBuilder lotteryInfoBuilder,String url){
		System.out.println("url = "+url);
		String webPageStr = LotteryUtil.getWebPageStr(url);
//		System.out.println(webPageStr);
		//去掉乱码字符
		webPageStr = webPageStr.replaceAll(LotteryConstant.FILTER_GARBLED, "");
		
		
		String str_1 = LotteryUtil.getValueViaReg(Lottery_FL_Crawler.regExpStr_1, webPageStr);
		String str_2 = LotteryUtil.getValueViaReg(Lottery_FL_Crawler.regExpStr_2, webPageStr).replaceAll("[\\t,\\n]", "");
		String str_3 = LotteryUtil.getValueViaReg(Lottery_FL_Crawler.regExpStr_3, webPageStr).replaceAll("<br>|<br />|<br/>", "");
		str_1 = LotteryUtil.wrapperXmlStr(str_1);
		str_2 = LotteryUtil.wrapperXmlStr(str_2);
		str_3 = LotteryUtil.wrapperXmlStr(str_3);
//		System.out.println("str_1 = " + str_1);
//		System.out.println("str_2 = " + str_2);
//		System.out.println("str_3 = " + str_3);
		Document document = LotteryUtil.getDocumentObj(str_1);
		Document document_ = LotteryUtil.getDocumentObj(str_2);
		Document document__ = LotteryUtil.getDocumentObj(str_3);
		//开奖日期											  lottery
		Node node_date = (Node)LotteryUtil.getValueViaXpath("/lottery/li[position()=1]/span[position()=2]", document, XPathConstants.NODE);
		
//		System.out.println("node_date = " + node_date.getTextContent());
		String openDate = LotteryUtil.getValueViaReg("[\\d-]+", node_date.getTextContent().trim()) ;
		//销量
		Node node_sale_count = (Node)LotteryUtil.getValueViaXpath("/lottery/li[position()=1]/span[position()=3]", document, XPathConstants.NODE);
		String saleCount = node_sale_count.getTextContent().replaceAll("[^\\d^\\.]", "");
//		System.out.println("salecount=" + saleCount);
		//奖池
		Node node_bonuspool = (Node)LotteryUtil.getValueViaXpath("/lottery/li[position()=1]/span[position()=4]", document, XPathConstants.NODE);
		String bonusPoolStr = node_bonuspool.getTextContent().replaceAll("[^\\d^\\.]", "").trim();
		double bonusPool = ("".equals(bonusPoolStr) || null == bonusPoolStr) ? null : Double.parseDouble( bonusPoolStr );
		//开奖号码
		Node node_winnum = (Node)LotteryUtil.getValueViaXpath("/lottery/li[position()=2]", document, XPathConstants.NODE);
		NodeList winnum_list = node_winnum.getChildNodes();
		int winnum_list_length = winnum_list.getLength();
		Map<String,String> winnum_map = new HashMap<String, String>();
		StringBuffer winnum_str = new StringBuffer();
		int redcounter = 0 ;
		int bluecounter = 0;
		for(int i = 0 ; i < winnum_list_length ; i ++ ){
			Node node_tmp = winnum_list.item(i);
			if(node_tmp.getNodeName().equals("span")){
				Element element_tmp = (Element)node_tmp;
				String attr_class = element_tmp.getAttribute("class");
				if(attr_class == null || attr_class.trim().equals("")){
					winnum_map.put("red" + (++redcounter), element_tmp.getTextContent());
					winnum_str.append(element_tmp.getTextContent()+ ",");
//					System.out.println("text = " + element_tmp.getTextContent());
				}else{
					if(attr_class.toLowerCase().indexOf("blue") >=0){
						winnum_map.put("blue" + (++bluecounter), element_tmp.getTextContent());
						winnum_str.append("+" + element_tmp.getTextContent()+ ",");
					}
				}
			}
		}
//		winnum_str = winnum_str.substring(0, winnum_str.length()-1);
		//开奖情况，一等奖，二等奖。。。。。。
		Set<LotteryBonusInfo> bonusInfoSet = new HashSet<LotteryBonusInfo>();
		String tbodyXpath = "/lottery/tbody";
		Node node_tbody = (Node)LotteryUtil.getValueViaXpath(tbodyXpath, document_, XPathConstants.NODE);
		int bonus_level_count = node_tbody.getChildNodes().getLength();
		for(int i = 0 ; i < bonus_level_count ; i ++){
//			System.out.println("---------------------------------");
			LotteryBonusInfo bonusInfo = new LotteryBonusInfo();
			bonusInfo.setLotteryInfoId(lotteryInfoBuilder.getLotteryInfoId());
			bonusInfo.setBonusLevel((i+1) + "");
			//中奖注数
			String xpath_win_count_tmp = "/lottery/tbody/tr[position()="+(i+1) + "]/td[position()=2]";
			Node node_win_conut_tmp = (Node)LotteryUtil.getValueViaXpath(xpath_win_count_tmp, document_, XPathConstants.NODE);
			String wonCountStr = node_win_conut_tmp.getTextContent().replaceAll("[^\\d]", "").trim();
			int wonCount = ( "".equals(wonCountStr) || wonCountStr == null ) ? 0 : Integer.parseInt(wonCountStr);
			//System.out.println("node_win_conut_tmp = " + Integer.parseInt(wonCountStr));
			bonusInfo.setWonCount(wonCount);
			//中奖金额
			String xpath_win_amount_tmp = "/lottery/tbody/tr[position()="+(i+1) + "]/td[position()=3]";
			Node node_win_amount_tmp = (Node)LotteryUtil.getValueViaXpath(xpath_win_amount_tmp, document_, XPathConstants.NODE);
			String bonusAmountStr = node_win_amount_tmp.getTextContent().replaceAll("[^\\d^\\.]", "").trim();
			double  bonusAmout = ( "".equals(bonusAmountStr) || bonusAmountStr == null ) ? 0 : Double.parseDouble(bonusAmountStr);
			bonusInfo.setBonusAmount(bonusAmout);
			bonusInfoSet.add(bonusInfo);
		}
		//一等奖中奖情况
		Node node_win_1_condition = (Node)LotteryUtil.getValueViaXpath("/lottery/div[@class='mt10']", document__, XPathConstants.NODE);
//		System.out.println("一等奖中奖情况=="+node_win_1_condition.getTextContent());
		String wonCondition = node_win_1_condition.getTextContent().trim();
		lotteryInfoBuilder.setOpenDate(openDate)
		.setLotteryName("ssq")
		.setLotterySort("fl")
		.setBonusPool(bonusPool)
		.setWonCondition(wonCondition)
		.setWonNumMap(winnum_map)
		.setDrawingNum(winnum_str.substring(0, winnum_str.length()-1))
		.setWonDetail(bonusInfoSet)
		.setLotterySales(Double.parseDouble(saleCount));
		return  lotteryInfoBuilder;
	}
}
