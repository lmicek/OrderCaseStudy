package com.acmecorp.trader.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = LimitOrder.class)
public class LimitOrder extends Order {
	private double limit;

	@Override
	public boolean match(Order order) {
		if (this.getOrderId().equals(order.getOrderId()))
			return true;
		return false;
	}
	
	public LimitOrder(){
		super.setOrderType(OrderType.LIMIT);
	}

	@JsonCreator
	public LimitOrder(@JsonProperty("symbol") Symbol symbol, @JsonProperty("shares") int shares,
			@JsonProperty("side") Side side, @JsonProperty("ordertype") OrderType orderType, @JsonProperty("limit") double limit) {
		super(symbol, shares, side);
		this.setOrderType(OrderType.LIMIT);
		this.setLimit(limit);
	}

	public double getLimit() {
		return limit;
	}

	public void setLimit(double limit) {
		this.limit = limit;
	}

}
