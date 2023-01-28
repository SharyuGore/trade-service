package com.deutsche.tradeservice.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;

import com.deutsche.tradeservice.data.TradeUI;
import com.deutsche.tradeservice.model.Trade;
import com.deutsche.tradeservice.model.TradeId;
import com.deutsche.tradeservice.repository.TradeRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class TradeServiceTest {

	private static final String DD_MM_YYYY = "dd/MM/yyyy";

	@Autowired
	private TradeService tradeService;

	@MockBean
	private TradeRepository tradeRepository;

	@Test
	public void testIsMaturityDateCorrect() throws Exception {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DD_MM_YYYY);
		String dateearlier = LocalDate.now().minusDays(300).format(formatter);
		assertFalse(tradeService.isMaturityDateCorrect(dateearlier));

		String dateLater = LocalDate.now().plusDays(3).format(formatter);
		assertTrue(tradeService.isMaturityDateCorrect(dateLater));

		String dateToday = LocalDate.now().format(formatter);
		assertTrue(tradeService.isMaturityDateCorrect(dateToday));

	}

	@Test
	public void testIsVersionLessThanFails() throws Exception {

		List<Trade> listofTrades = new ArrayList<>();
		listofTrades.add(Trade.builder().tradeId(new TradeId("T1", 1L)).build());
		listofTrades.add(Trade.builder().tradeId(new TradeId("T1", 2L)).build());

		Mockito.when(tradeRepository.findAll(Mockito.any(Example.class))).thenReturn(listofTrades);

		TradeUI tradeFromUI = TradeUI.builder().tradeid("T1").version("1").build();
		assertFalse(tradeService.isTradeVersionCorrect(tradeFromUI));

	}

	@Test
	public void testIsVersionGreaterThanPasses() throws Exception {
		List<Trade> listofTrades = new ArrayList<>();
		listofTrades.add(Trade.builder().tradeId(new TradeId("T1", 1L)).build());
		listofTrades.add(Trade.builder().tradeId(new TradeId("T1", 2L)).build());

		Mockito.when(tradeRepository.findAll(Mockito.any(Example.class))).thenReturn(listofTrades);

		TradeUI tradeFromUINext = TradeUI.builder().tradeid("T1").version("3").build();
		assertTrue(tradeService.isTradeVersionCorrect(tradeFromUINext));
	}

	@Test
	public void testIsVersionEqualPasses() throws Exception {
		List<Trade> listofTrades = new ArrayList<>();
		listofTrades.add(Trade.builder().tradeId(new TradeId("T1", 1L)).build());
		listofTrades.add(Trade.builder().tradeId(new TradeId("T1", 2L)).build());

		Mockito.when(tradeRepository.findAll(Mockito.any(Example.class))).thenReturn(listofTrades);

		TradeUI tradeFromUINext = TradeUI.builder().tradeid("T1").version("2").build();
		assertTrue(tradeService.isTradeVersionCorrect(tradeFromUINext));
	}

	@Test
	public void testUpdateTrade() throws Exception {
		List<Trade> listofTrades = new ArrayList<>();
		listofTrades.add(Trade.builder().tradeId(new TradeId("T1", 1L)).maturityDate(LocalDate.now().plusDays(3)).createdDate(LocalDate.now()).build());
		listofTrades.add(Trade.builder().tradeId(new TradeId("T1", 2L)).maturityDate(LocalDate.now().minusDays(3)).createdDate(LocalDate.now()).build());

		Mockito.when(tradeRepository.findAll()).thenReturn(listofTrades);

		assertDoesNotThrow(() -> {
			tradeService.updateTrades();
		});
		
		Mockito.verify(tradeRepository,Mockito.times(1)).save(Mockito.any());
		

	}

}
