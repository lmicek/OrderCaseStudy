package com.acmecorp.trader.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import com.acmecorp.trader.utilities.OrderDeserializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using=OrderDeserializer.class)
public abstract class Order implements Comparable<Order> {
	private UUID oid;
	private Symbol symbol;
	private int shares;
	private Side side;
	private LocalDateTime timestamp;
	
	private String traderId;
	private OrderType orderType;

	public Order() {
		// this.setOrderId(UUID.randomUUID());
		// this.setTimestamp(LocalDateTime.now());
	}
	@JsonCreator
	public Order(@JsonProperty("symbol")Symbol symbol, @JsonProperty("shares")int shares, @JsonProperty("side")Side side) {
		this.symbol = symbol;
		this.shares = shares;
		this.side = side;
//		this.setOrderId(UUID.randomUUID());
//		this.setTimestamp(LocalDateTime.now());
	}

	public abstract boolean match(Order order);

	@Override
	public int compareTo(Order o) {

		return 0;
	}

	@Override
	public String toString() {
		return "Order{" + "symbol=" + symbol + ", shares=" + shares + ", side=" + side + ", traderid= " + traderId +"}";
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public int getShares() {
		return shares;
	}

	public Side getSide() {
		return side;
	}

	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

	public void setShares(int shares) {
		this.shares = shares;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public UUID getOrderId() {
		return oid;
	}

	public void setOrderId(UUID oid) {
		this.oid = oid;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getTraderId() {
		return traderId;
	}

	public void setTraderId(String traderId) {
		this.traderId = traderId;
	}
	public OrderType getOrderType() {
		return orderType;
	}
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}

}