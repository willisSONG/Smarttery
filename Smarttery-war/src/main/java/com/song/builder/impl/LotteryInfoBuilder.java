package com.song.builder.impl;

import java.util.Map;
import java.util.Set;

import com.song.builder.inter.Builder;
import com.song.lottery.entity.LotteryBonusInfo;
import com.song.lottery.entity.LotteryInfo;

public class LotteryInfoBuilder implements Builder {

	private LotteryInfo lotteryInfo;
	@Override
	public LotteryInfo build() {
		// TODO Auto-generated method stub
		return this.lotteryInfo;
	}
	public LotteryInfoBuilder(LotteryInfo lotteryInfo) {
		this.lotteryInfo = lotteryInfo;
	}
	public LotteryInfoBuilder setLotteryInfoId(int lotteryInfoId) {
		this.lotteryInfo.setLotteryInfoId(lotteryInfoId);
		return this;
	}
	public LotteryInfoBuilder setLotteryName(String lotteryName) {
		this.lotteryInfo.setLotteryName(lotteryName);
		return this;
	}
	public LotteryInfoBuilder setOpenTime(String openTime) {
		this.lotteryInfo.setOpenTime(openTime);
		return this;
	}
	public LotteryInfoBuilder setOpenDate(String openDate) {
		this.lotteryInfo.setOpenDate(openDate);
		return this;
	}
	public LotteryInfoBuilder setExpect(String expect) {
		this.lotteryInfo.setExpect(expect);
		return this;
	}
	public LotteryInfoBuilder setWonNumMap(Map<String, String> wonNumMap) {
		this.lotteryInfo.setWonNumMap(wonNumMap);
		return this;
	}
	public LotteryInfoBuilder setWonCondition(String wonCondition) {
		this.lotteryInfo.setWonCondition(wonCondition);
		return this;
	}
	public LotteryInfoBuilder setWonDetail(Set<LotteryBonusInfo> wonDetail) {
		this.lotteryInfo.setWonDetail(wonDetail);
		return this;
	}
	public LotteryInfoBuilder setBonusPool(double bonusPool) {
		this.lotteryInfo.setBonusPool(bonusPool);
		return this;
	}
	public LotteryInfoBuilder setLotterySales(double lotterySales) {
		this.lotteryInfo.setLotterySales(lotterySales);
		return this;
	}
	public LotteryInfoBuilder setDrawingNum(String drawingNum) {
		this.lotteryInfo.setDrawingNum(drawingNum);
		return this;
	}
	public LotteryInfoBuilder setLotterySort(String lotterySort) {
		this.lotteryInfo.setLotterySort(lotterySort);
		return this;
	}
	public int getLotteryInfoId(){
		return this.lotteryInfo.getLotteryInfoId();
	}
}
