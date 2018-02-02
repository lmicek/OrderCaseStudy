package com.acmecorp.trader.repository;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.acmecorp.trader.TraderApplication;
import com.acmecorp.trader.domain.MarketOrder;
import com.acmecorp.trader.domain.Quote;
import com.acmecorp.trader.domain.Side;
import com.acmecorp.trader.domain.Symbol;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT, classes = { TraderApplication.class })
public class PriceQuoteRepositoryTest {
	
	private LocalDateTime now = LocalDateTime.now();
	
	@Autowired
	private PriceQuoteRepository priceQuoteRepository;
	
	@Before
	public void setupDataBase(){
		
		Quote quoteOne = new Quote(Symbol.AAPL,20.10,22,now);
		Quote quoteTwo = new Quote(Symbol.IBM,22.12,24,now);
		Quote quoteThree = new Quote(Symbol.AAPL,21.13,25,now);
		priceQuoteRepository.saveQuote(quoteOne);
		priceQuoteRepository.saveQuote(quoteTwo);
		priceQuoteRepository.saveQuote(quoteThree);	
	}
	
	@Test
	public void insertSinglePriceQuoteCompletesSuccessfully() {
	
		List<Quote> quote = priceQuoteRepository.findAllQuotes();
		assertThat("quote size should be 3", 3, equalTo(quote.size()));
		Quote priceQuote  = quote.get(0);
		assertThat("Symbol should be AAPL", Symbol.AAPL, equalTo(priceQuote.getSymbol()));
		assertThat("price should be 20.10", 20.10, equalTo(priceQuote.getPrice()));
		assertThat("number of shares should be 22", 22, equalTo(priceQuote.getNumberShares()));
	}
	
	@Test
	public void findQuotesBySymbolCompletesSucessfully() {
		List<Quote> quote = priceQuoteRepository.findQuotesBySymbol(Symbol.IBM);
		Quote priceQuote  = quote.get(0);
		assertThat("Symbol should be IBM", Symbol.IBM, equalTo(priceQuote.getSymbol()));
	}

}
