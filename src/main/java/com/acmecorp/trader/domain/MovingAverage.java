/**
 * 
 */
package com.acmecorp.trader.domain;

import java.time.LocalDateTime;

import com.acmecorp.trader.utilities.LocalDateTimeDeserializer;
import com.acmecorp.trader.utilities.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author a419845
 *
 */
public class MovingAverage {
	
	@Override
	public String toString() {
		return "MovingAverage [price=" + price + ", asOfDate=" + asOfDate + "]";
	}

	private double price;
	
	@JsonSerialize(using=LocalDateTimeSerializer.class)
	@JsonDeserialize(using=LocalDateTimeDeserializer.class)
	private LocalDateTime asOfDate;

	public LocalDateTime getAsOfDate() {
		return asOfDate;
	}

	public void setAsOfDate(LocalDateTime asOfDate) {
		this.asOfDate = asOfDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
