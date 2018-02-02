package com.acmecorp.trader.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.acmecorp.trader.domain.Quote;
import com.acmecorp.trader.domain.Symbol;
import com.acmecorp.trader.repository.PriceQuoteRepository;

@Service
public class QuoteService implements QuotesGenerator{

	private final PriceQuoteRepository priceQuoteRepository;
	
	public QuoteService(PriceQuoteRepository priceQuoteRepository) {
		this.priceQuoteRepository = priceQuoteRepository;
	}
	
	public void saveQuote(Quote quote) {
		this.priceQuoteRepository.saveQuote(quote);
	}
	
	public List<Quote> findAllQuotes() {
		return this.priceQuoteRepository.findAllQuotes();
	}
	
	public List<Quote> findQuotesBySymbol(Symbol symbol) {
		return this.priceQuoteRepository.findQuotesBySymbol(symbol);
	}
	
	@Override
	public Map<Symbol, List<Quote>> getQuotes() {

		return this.priceQuoteRepository.findAllQuotes().stream()
					.collect(Collectors.groupingBy(Quote::getSymbol));
	}

}
