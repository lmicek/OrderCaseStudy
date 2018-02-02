package com.acmecorp.trader.domain;

import java.time.LocalDateTime;

import com.acmecorp.trader.utilities.LocalDateTimeDeserializer;
import com.acmecorp.trader.utilities.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Quote {

	private final Symbol symbol;
	private final double price;
	private final int numberShares;
	
	@JsonSerialize(using=LocalDateTimeSerializer.class)
	@JsonDeserialize(using=LocalDateTimeDeserializer.class)
	private final LocalDateTime dateTime;
	
	public Quote(Symbol symbol, double price, int numberShares) {
		this.symbol = symbol;
		this.price = price;
		this.numberShares = numberShares;
		
		this.dateTime = LocalDateTime.now();
	}
	
	public Quote(Symbol symbol, double price, int numberShares, LocalDateTime dateTime) {
		this.symbol = symbol;
		this.price = price;
		this.numberShares = numberShares;
		
		this.dateTime = dateTime;
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public double getPrice() {
		return price;
	}

	public int getNumberShares() {
		return numberShares;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	@Override
	public String toString() {
		return "Quote [symbol=" + symbol + ", price=" + price + ", numberShares=" + numberShares + ", dateTime="
				+ dateTime + "]";
	}
	
	
}
