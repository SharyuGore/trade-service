package com.deutsche.tradeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deutsche.tradeservice.data.TradeUI;
import com.deutsche.tradeservice.mapper.TradeMapper;
import com.deutsche.tradeservice.model.Trade;
import com.deutsche.tradeservice.service.TradeService;

import jakarta.validation.Valid;

@RestController
public class TradeController {

	@Autowired
	TradeService trService;

	@PostMapping("/createTrade")
	public ResponseEntity<TradeUI> createTrade(@RequestBody @Valid TradeUI trade) {

		Trade tradeObj = TradeMapper.getTrade(trade);

		Trade tradeSaved = trService.save(tradeObj);

		TradeUI tradeUIData = TradeMapper.getTradeUI(tradeSaved);

		return new ResponseEntity<>(tradeUIData, HttpStatus.OK);

	}

	

	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping("/getAllTrades")
	public List<TradeUI> getAllTrades1() {
		return trService.getAllTradeUI();

	}

}
