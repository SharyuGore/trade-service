package com.deutsche.tradeservice.mapper;

import java.time.LocalDate;

import com.deutsche.tradeservice.data.TradeUI;
import com.deutsche.tradeservice.dateutils.DateUtility;
import com.deutsche.tradeservice.model.Trade;
import com.deutsche.tradeservice.model.TradeId;

public class TradeMapper {

	public static Trade getTrade(TradeUI trade) {
		
		TradeId tradeId = new TradeId(trade.getTradeid(), Long.valueOf(trade.getVersion()));

		LocalDate matLocalDate = DateUtility.getLocalDateFromString(trade.getMaturityDate());

		Trade tradeRec = Trade.builder().tradeId(tradeId).createdDate(LocalDate.now()).maturityDate(matLocalDate)
				.bookId(trade.getBookId()).counterPartyId(trade.getCounterPartyId()).expired(false).build();

		return tradeRec;
	}

	public static TradeUI getTradeUI(Trade trade) {

		TradeUI tradeUIRec = TradeUI.builder().tradeid(trade.getTradeId().getTradeid())
				.version(trade.getTradeId().getVersion().toString()).createdDate(trade.getCreatedDate().toString())
				.maturityDate(trade.getMaturityDate().toString()).bookId(trade.getBookId())
				.expired(trade.getExpired())
				.counterPartyId(trade.getCounterPartyId()).build();

		return tradeUIRec;
	}

}
