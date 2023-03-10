package com.deutsche.tradeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deutsche.tradeservice.model.Trade;
import com.deutsche.tradeservice.model.TradeId;

@Repository
public interface TradeRepository extends JpaRepository<Trade, TradeId> {

}
