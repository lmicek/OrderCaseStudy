package com.acmecorp.trader.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.acmecorp.trader.domain.Quote;
import com.acmecorp.trader.domain.Symbol;

public class QuotesGeneratorImpl implements QuotesGenerator{
	
	private Map<Symbol, List<Quote>> mapOfquotes = new HashMap<>();
	
	private final Map<Symbol, Double> symbolsStartPriceMap;
	
	private final long duration;	// Defaults to half a second

	
	private LocalDateTime currentDate;
		
	public static class Generator {

		private Map<Symbol, Double> symbolsMap = new HashMap<>();
		
		private long duration = 10;	// Defaults to 10 days

		
		public Generator() {}
		
		public Generator symbols(List<Symbol> listSymbols) {
			
			listSymbols.forEach(symbol -> {
				symbolsMap.put(symbol, 100.0);
			});
			
			return this;
		}
		
		public Generator symbols(Map<Symbol, Double> symbolsMap) {
			if(symbolsMap != null && !symbolsMap.isEmpty()) {
				this.symbolsMap = symbolsMap;				
			}
						
			return this;
		}
		
		public Generator price(Symbol symbol, double price) {
			symbolsMap.put(symbol, price);
			
			return this;
		}
		
		public Generator duration(long duration) {
			this.duration = duration;
			
			return this;
		}
		
		
		public QuotesGeneratorImpl build() {
			if(symbolsMap.isEmpty()) {
				List<Symbol> listSymbols = Arrays.asList(Symbol.values());
				
				listSymbols.forEach(symbol -> {
					symbolsMap.put(symbol, 100.0);
				});
			}
			return new QuotesGeneratorImpl(this);
		}
	}

	private QuotesGeneratorImpl(Generator generator) {
		this.symbolsStartPriceMap = generator.symbolsMap;
		
		this.duration = generator.duration;

		
		symbolsStartPriceMap.forEach((symbol,value) -> {
			mapOfquotes.put(symbol, new ArrayList<Quote>());
		});
		
		startStream();
	}
	
	private void startStream() {	
		LocalDateTime endDate = LocalDateTime.now();
		LocalDateTime startDate = endDate.minusDays(duration);
		currentDate = startDate;
		
		
		
		while(currentDate.isBefore(endDate)) {
			symbolsStartPriceMap.forEach((symbol, value) -> {

				double priceDelta = getPrice(symbol);
				
				mapOfquotes.get(symbol).add(new Quote(symbol
						, truncateRound(value)
						, numberOfShares()
						, currentDate));
				
				value = marketUp() ? value + priceDelta : value - priceDelta;
				
				symbolsStartPriceMap.put(symbol, value);
				
			});
			
			currentDate = currentDate.plusDays(1);
		}
	}
	
	/**
	 * Random generator that checks if the market should go up
	 * @return
	 */
	private boolean marketUp() {
		return Math.random() < 0.5;
	}
	
	/**
	 * Randomly generates the number of shares for a symbol
	 * @return
	 */
	private int numberOfShares() {
		
		return (int) (Math.random() * 50) + 1;
	}
	
	/**
	 * Randomly generated price
	 * <p>
	 * Hard coded 15% market change
	 * @param symobl
	 * @return
	 */
	private double getPrice(Symbol symbol) {
		return symbolsStartPriceMap.get(symbol) * ((Math.random() % .08) + .01); // Get current price * by function that returns
																						// between .01 - .09 to simulate market ROR
	}
	
	private double truncateRound(double valueToRound) {
	    double scale = Math.pow(10, 2);
	    return Math.round(valueToRound * scale) / scale;
	}

	/**
	 * Returns map of quotes
	 */
	@Override
	public Map<Symbol, List<Quote>> getQuotes() {
		return mapOfquotes;
	}
	
}
