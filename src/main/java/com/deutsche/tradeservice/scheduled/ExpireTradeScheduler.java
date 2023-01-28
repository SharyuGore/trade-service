package com.deutsche.tradeservice.scheduled;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.deutsche.tradeservice.service.TradeService;

@Component
public class ExpireTradeScheduler {

	private static final Logger log = LoggerFactory.getLogger(ExpireTradeScheduler.class);

	@Autowired
	TradeService tradeService;

	@Scheduled(cron = "${expire.trades.schedule}")
	public void scheduleTaskUsingCronExpression() {
		log.info("Starting to update all trades at time {}", LocalDateTime.now());
		tradeService.updateTrades();
		log.info("Finished updating all trades at time {}", LocalDateTime.now());
	}
}
