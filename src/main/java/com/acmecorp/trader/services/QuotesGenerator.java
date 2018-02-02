package com.acmecorp.trader.services;

import java.util.List;
import java.util.Map;

import com.acmecorp.trader.domain.Quote;
import com.acmecorp.trader.domain.Symbol;

public interface QuotesGenerator {
	Map<Symbol, List<Quote>> getQuotes();
}
