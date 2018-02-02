package com.acmecorp.trader;

import java.util.List;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.acmecorp.trader.domain.Quote;
import com.acmecorp.trader.domain.Symbol;
import com.acmecorp.trader.repository.PriceQuoteRepository;
import com.acmecorp.trader.services.QuotesGeneratorImpl;

@SpringBootApplication
public class TraderApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(TraderApplication.class, args);
		PriceQuoteRepository quoteRepository = context.getBean(PriceQuoteRepository.class);
		
		Map<Symbol, List<Quote>> mapOfQuotes = new QuotesGeneratorImpl.Generator()
			.price(Symbol.AAPL, 116.5).duration(365)
			.build()
			.getQuotes();
		
		mapOfQuotes.forEach((symbol, list) -> {
			list.forEach(q -> quoteRepository.saveQuote(q));
		});
	}
}
