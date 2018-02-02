package com.acmecorp.trader.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(as = MarketOrder.class)
public class MarketOrder extends Order{
	private double fullFilledPrice ;
	
    @Override
    public boolean match(Order order) {
        if (this.getOrderId().equals(order.getOrderId()))
        	return true;
        return false;
    }

    public MarketOrder(){
    	super.setOrderType(OrderType.MARKET);
    }

    @JsonCreator
    public MarketOrder(@JsonProperty("symbol")Symbol symbol, @JsonProperty("shares")int shares, @JsonProperty("side")Side side, @JsonProperty("ordertype")OrderType orderType) {
        super(symbol, shares, side);
        super.setOrderType(OrderType.MARKET);
    }

	public double getFullFilledPrice() {
		return fullFilledPrice;
	}

	public void setFullFilledPrice(double fullFilledPrice) {
		this.fullFilledPrice = fullFilledPrice;
	}

}
