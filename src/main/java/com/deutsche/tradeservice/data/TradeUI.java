package com.deutsche.tradeservice.data;

import com.deutsche.tradeservice.constraint.DatePresentOrFutureConstraint;
import com.deutsche.tradeservice.constraint.TradeVersionLowerConstraint;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TradeVersionLowerConstraint
public class TradeUI {

	@NotNull
	@NotBlank
	String tradeid;
	@NotNull
	@NotBlank
	String version;

	String counterPartyId;
	String bookId;

	@NotNull
	@DatePresentOrFutureConstraint
	String maturityDate;

	String createdDate;

	Boolean expired;
}
