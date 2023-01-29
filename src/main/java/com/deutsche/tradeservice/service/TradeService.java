package com.deutsche.tradeservice.service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.deutsche.tradeservice.data.TradeUI;
import com.deutsche.tradeservice.dateutils.DateUtility;
import com.deutsche.tradeservice.mapper.TradeMapper;
import com.deutsche.tradeservice.model.Trade;
import com.deutsche.tradeservice.model.TradeId;
import com.deutsche.tradeservice.repository.TradeRepository;

@Service
public class TradeService {

	@Autowired
	TradeRepository tradeRepository;

	public Trade save(Trade tradeRec) {
		return tradeRepository.save(tradeRec);
	}

	public void updateTrades() {
		tradeRepository.findAll().parallelStream().forEach(e -> updateExpired(e));
	}

	private void updateExpired(Trade trade) {
		if (DateUtility.hasMaturityDateCrossToday(trade.getMaturityDate())) {
			trade.setExpired(true);
			tradeRepository.save(trade);
		}

	}

	public boolean isTradeVersionCorrect(TradeUI trade) {

		Trade tradeObj = new Trade();
		TradeId tradeId = new TradeId();
		tradeId.setTradeid(trade.getTradeid());
		tradeObj.setTradeId(tradeId);

		Comparator<Trade> tradeComparator = Comparator.comparing(Trade::getTradeId, (s1, s2) -> {
			return s1.getVersion().compareTo(s2.getVersion());
		});

		Example<Trade> tradeExample = Example.of(tradeObj);
		List<Trade> listofTrades = tradeRepository.findAll(tradeExample);
		if (listofTrades.size() > 1) {

			Trade tr = listofTrades.stream().max(tradeComparator).get();
			if (tr.getTradeId().getVersion() > Long.valueOf(trade.getVersion())) {
				return false;
			}
		} else if (listofTrades.size() == 1) {
			Trade tr = listofTrades.get(0);
			if (tr.getTradeId().getVersion() > Long.valueOf(trade.getVersion())) {
				return false;
			}
		}
		return true;
	}

	public boolean isMaturityDateCorrect(String maturityDateAsString) {
		LocalDate maturityDate = DateUtility.getLocalDateFromString(maturityDateAsString);
		return DateUtility.isDateLaterThanToday(maturityDate);
	}

	public List<TradeUI> getAllTradeUI() {

		List<Trade> trades = tradeRepository.findAll();
		return trades.stream().map(trade -> TradeMapper.getTradeUI(trade)).collect(Collectors.toList());

	}

}
