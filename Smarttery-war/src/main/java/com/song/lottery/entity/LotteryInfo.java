package com.song.lottery.entity;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
@Entity
@Table(name="lottery_info")
public class LotteryInfo implements Serializable {

	/**
	 * 彩票信息Id，PK
	 */
	private int lotteryInfoId;
	/**
	 * 彩票种类，体育彩票和福利彩票之分
	 */
	private String lotterySort;
	
	/**
	 * 彩票名称
	 */
	private String lotteryName;
	/**
	 * 彩票公布时间
	 */
	private String openTime;
	/**
	 * 彩票公布日期
	 */
	private String openDate;
	/**
	 * 彩票期次
	 */
	private String expect;
	/**
	 * 开奖号码，同wonNumMap数据一样，只是书写形式不同
	 */
	private String drawingNum;
	/**
	 * 中奖号码
	 */
	@Transient
	private Map<String,String> wonNumMap;
	/**
	 * 中奖详情
	 */
	private Set<LotteryBonusInfo> wonDetail;
	/**
	 * 一等奖中奖情况
	 */
	private String WonCondition;
	/**
	 * 奖池
	 * @return
	 */
	private double bonusPool;
	/**
	 * 当期销量
	 * @return
	 */
	private double lotterySales;
	@Id
	@Column(name="lottery_info_id")
	public int getLotteryInfoId() {
		return lotteryInfoId;
	}
	@Column(name="lottery_sort")
	public String getLotterySort() {
		return lotterySort;
	}
	@Column(name="lottery_name")
	public String getLotteryName() {
		return lotteryName;
	}
	@Column(name="open_time")
	public String getOpenTime() {
		return openTime;
	}
	@Column(name="open_date")
	public String getOpenDate() {
		return openDate;
	}
	@Column(name="lottery_expect")
	public String getExpect() {
		return expect;
	}
	@Column(name="drawing_num")
	public String getDrawingNum() {
		return drawingNum;
	}
	@Transient
	public Map<String, String> getWonNumMap() {
		return wonNumMap;
	}
	@OneToMany(targetEntity=LotteryBonusInfo.class,fetch=FetchType.EAGER)
	@JoinColumn(name="lottery_info_id")//两个表的链接属性，这里意思是用lottery_info_id关联两个表
	@Cascade(value={org.hibernate.annotations.CascadeType.ALL,org.hibernate.annotations.CascadeType.PERSIST})
	public Set<LotteryBonusInfo> getWonDetail() {
		return wonDetail;
	}
	@Column(name="won_condition")
	public String getWonCondition() {
		return WonCondition;
	}
	@Column(name="bonus_pool")
	public double getBonusPool() {
		return bonusPool;
	}
	@Column(name="lottery_sales")
	public double getLotterySales() {
		return lotterySales;
	}
	public void setLotteryInfoId(int lotteryInfoId) {
		this.lotteryInfoId = lotteryInfoId;
	}
	public void setLotterySort(String lotterySort) {
		this.lotterySort = lotterySort;
	}
	public void setLotteryName(String lotteryName) {
		this.lotteryName = lotteryName;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public void setExpect(String expect) {
		this.expect = expect;
	}
	public void setDrawingNum(String drawingNum) {
		this.drawingNum = drawingNum;
	}
	@Transient
	public void setWonNumMap(Map<String, String> wonNumMap) {
		this.wonNumMap = wonNumMap;
	}
	public void setWonDetail(Set<LotteryBonusInfo> wonDetail) {
		this.wonDetail = wonDetail;
	}
	public void setWonCondition(String wonCondition) {
		WonCondition = wonCondition;
	}
	public void setBonusPool(double bonusPool) {
		this.bonusPool = bonusPool;
	}
	public void setLotterySales(double lotterySales) {
		this.lotterySales = lotterySales;
	}
	
}
