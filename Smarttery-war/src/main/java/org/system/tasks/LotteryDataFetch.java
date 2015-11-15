package org.system.tasks;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.song.commontools.util.LotteryUtil;
import com.song.lottery.crawler.Lottery_FL_Crawler;
import com.song.lottery.entity.LotteryBonusInfo;
import com.song.lottery.entity.LotteryInfo;
/**
 * 该类的作用主要是完成彩票数据的定期获取任务，依赖于springquartz技术
 * @author willis
 *
 */
public class LotteryDataFetch extends QuartzJobBean {

	private String lotterySort;
	private EntityManagerFactory entityManagerFactory;
	private static Logger logger = LoggerFactory.getLogger(LotteryDataFetch.class);
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public String getLotterySort() {
		return lotterySort;
	}

	public void setLotterySort(String lotterySort) {
		this.lotterySort = lotterySort;
	}

	@Override
	protected synchronized void  executeInternal(JobExecutionContext context) throws JobExecutionException {
		LotteryDataFetch.logger.debug("Getting data from lottery website");
		LotteryDataFetch.logger.debug("lottery sort is " + this.getLotterySort());
//		System.out.println(String.format("%tH-%<tM-%<tS",System.currentTimeMillis()));
		
		Lottery_FL_Crawler crawlerInstance = Lottery_FL_Crawler.getInstance();
		
		EntityManager entityManager_ = this.getEntityManagerFactory().createEntityManager();
		entityManager_.getTransaction().begin();
		LotteryInfo lotteryInfo = crawlerInstance.getLotteryInfo(entityManager_);
		//判断有没有当期彩票的数据
		Map<String,Object> fieldMap = new HashMap<String,Object>();
		fieldMap.put("lottery_expect", lotteryInfo.getExpect());
		Object lottery_info = LotteryUtil.chargeExist(entityManager_, "lottery_info","lottery_info_id", fieldMap);
		if(lottery_info == null){
			entityManager_.persist(lotteryInfo);
		}else{//update entity
//			LotteryInfo info = entityManager_.find(LotteryInfo.class, ((Object[])lottery_info)[0]);
			//update lottery_info
			StringBuffer sqlSb = new StringBuffer();
			sqlSb.append("UPDATE lottery_info SET LOTTERY_NAME=:lotteryname ")
			.append(",DRAWING_NUM=:drawing_num ")
			.append(",BONUS_POOL=:bonus_pool")
			.append(",OPEN_DATE=:open_date")
			.append(",OPEN_TIME=:open_time")
			.append(",LOTTERY_SALES=:lottery_sales")
			.append(",WON_CONDITION=:won_condition");
			
			sqlSb.append(" WHERE LOTTERY_INFO_ID=:id");
			
			Query query = entityManager_.createNativeQuery(sqlSb.toString());
			query.setParameter("lotteryname", "ssq")
			.setParameter("drawing_num", lotteryInfo.getDrawingNum())
			.setParameter("bonus_pool", lotteryInfo.getBonusPool())
			.setParameter("open_date", lotteryInfo.getOpenDate())
			.setParameter("open_time", lotteryInfo.getOpenTime())
			.setParameter("lottery_sales", lotteryInfo.getLotterySales())
			.setParameter("won_condition", lotteryInfo.getWonCondition())
			.setParameter("id", lottery_info);
			int rows = query.executeUpdate();
			
			LotteryDataFetch.logger.debug(rows + " lines were updated");
			
			//update lottery_bonus_info
			Set<LotteryBonusInfo> wonDetails = lotteryInfo.getWonDetail();
			Iterator<LotteryBonusInfo> iterator = wonDetails.iterator();
			while(iterator.hasNext()){
				LotteryBonusInfo lotteryBonusInfo = iterator.next() ;
				String bonus_level = lotteryBonusInfo.getBonusLevel();
				LotteryDataFetch.logger.debug("bonus_level = " + bonus_level);
				LotteryDataFetch.logger.debug("lottery_info_id = " + lotteryInfo.getLotteryInfoId());
				Map<String,Object> fieldMap_ = new HashMap<String,Object>();
				fieldMap_.put("bonus_level", bonus_level);
				fieldMap_.put("lottery_info_id", lottery_info);
				Object lottery_bonus_info = LotteryUtil.chargeExist(entityManager_, "lottery_bonus_info", "lottery_bonus_info_id", fieldMap_);
				
				LotteryDataFetch.logger.debug("lottery_bonus_info_id = " + lottery_bonus_info);
				StringBuffer sqlSb_ = new StringBuffer();
				sqlSb_.append("UPDATE lottery_bonus_info SET WON_COUNT=:won_count ")
				.append(",BONUS_AMOUNT=:bonus_amount")
				.append(" WHERE LOTTERY_BONUS_INFO_ID=:lottery_bonus_info_id");
				
				Query query_ = entityManager_.createNativeQuery(sqlSb_.toString());
				query_.setParameter("bonus_amount", lotteryBonusInfo.getBonusAmount())
				.setParameter("won_count", lotteryBonusInfo.getWonCount())
				.setParameter("lottery_bonus_info_id", lottery_bonus_info);
				
				int row_ = query_.executeUpdate();
				LotteryDataFetch.logger.debug("LOTTERY_BONUS_INFO " + row_ + " lines update");
				
			}
			StringBuffer sqlSb_ = new StringBuffer();
//			info = lotteryInfo;
//			entityManager_.flush();
		}
		/*System.out.println("open date is " + lotteryInfo.getOpenDate());
		System.out.println("open time is " + lotteryInfo.getOpenTime());
		System.out.println("id is  " + lotteryInfo.getLotteryInfoId());
		System.out.println("期数 ： "+lotteryInfo.getExpect());*/
		
		
		entityManager_.getTransaction().commit();
		System.out.println(this.getEntityManagerFactory().createEntityManager());
		
	}

}
