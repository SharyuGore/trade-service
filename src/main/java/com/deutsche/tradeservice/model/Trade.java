package com.deutsche.tradeservice.model;

import java.time.LocalDate;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Trade {

	@EmbeddedId
	private TradeId tradeId;

	private String counterPartyId;
	private String bookId;
	private LocalDate maturityDate;
	private LocalDate createdDate;
	private Boolean expired;
	
	
}
