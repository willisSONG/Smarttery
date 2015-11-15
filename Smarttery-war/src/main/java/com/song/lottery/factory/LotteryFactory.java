/**
 * 
 */
package com.song.lottery.factory;

import com.song.builder.impl.LotteryInfoBuilder;
import com.song.lottery.entity.LotteryInfo;

/**
 * @author willis
 * LotteryFactory用于创建LotteryBuilder
 */
public class LotteryFactory implements Factory {

	public LotteryInfoBuilder createLotteryBuilder(){
		LotteryInfo lotteryInfo = new LotteryInfo();
		return new LotteryInfoBuilder(lotteryInfo);
	}
	private LotteryFactory() {
		//..
	}
	private static class InnerLotteryFactory{
		private static LotteryFactory lotterInstance = new LotteryFactory();
	}
	public static LotteryFactory newInstance(){
		return InnerLotteryFactory.lotterInstance;
	}
	
	
}
