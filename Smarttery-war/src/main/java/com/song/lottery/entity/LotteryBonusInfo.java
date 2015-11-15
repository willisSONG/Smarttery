package com.song.lottery.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 奖金信息实体类
 * @author willis
 * 2015/08/11
 */
@Entity
@Table(name="lottery_bonus_info")
public class LotteryBonusInfo implements Serializable {

	/**
	 * 奖金等级（一等奖，2等奖...）
	 */
	private String bonusLevel;
	/**
	 * 中奖数量
	 */
	private int wonCount;
	
	/**
	 * 中奖金额
	 */
	private double bonusAmount;
	/**
	 * 奖金信息编号，本字段是该实体的主键PK
	 */
	private int lotteryBonusInfoId;
	/**
	 * 彩票编号,FK
	 */
	private int lotteryInfoId;
	
	@Column(name="bonus_level")
	public String getBonusLevel() {
		return bonusLevel;
	}
	@Column(name="won_count")
	public int getWonCount() {
		return wonCount;
	}
	@Column(name="bonus_amount")
	public double getBonusAmount() {
		return bonusAmount;
	}
	@Id
	@Column(name="lottery_bonus_info_id")
	@GeneratedValue
	public int getLotteryBonusInfoId() {
		return lotteryBonusInfoId;
	}
	@Column(name="lottery_info_id")
	public int getLotteryInfoId() {
		return lotteryInfoId;
	}
	
	public void setBonusLevel(String bonusLevel) {
		this.bonusLevel = bonusLevel;
	}
	public void setLotteryBonusInfoId(int lotteryBonusInfoId) {
		this.lotteryBonusInfoId = lotteryBonusInfoId;
	}
	public void setWonCount(int wonCount) {
		this.wonCount = wonCount;
	}
	public void setBonusAmount(double bonusAmount) {
		this.bonusAmount = bonusAmount;
	}
	public void setLotteryInfoId(int lotteryInfoId) {
		this.lotteryInfoId = lotteryInfoId;
	}
	
	
}
