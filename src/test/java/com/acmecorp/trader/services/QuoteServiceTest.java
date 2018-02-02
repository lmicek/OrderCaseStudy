package com.acmecorp.trader.services;

import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.acmecorp.trader.domain.Quote;
import com.acmecorp.trader.domain.Symbol;
import com.acmecorp.trader.repository.PriceQuoteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class QuoteServiceTest {

	@Mock
	private PriceQuoteRepository quoteRepository;
	
	@InjectMocks
	private QuoteService service;

	@Test
	public void saveQuoteShouldCallRepositorySaveQuote() {
		service.saveQuote(new Quote(Symbol.AAPL, 10.5, 25));
		
		verify(quoteRepository,times(1)).saveQuote(forClass(Quote.class).capture());
		
	}
	
	@Test
	public void listAllQuotesShouldCallRepositoryFindAllQuotes() {
		service.findAllQuotes();
		
		verify(quoteRepository,times(1)).findAllQuotes();
		
	}
	
	@Test
	public void findQuotesBySymbolShouldCallRepositoryFindQuotesBySymbol() {
		service.findQuotesBySymbol(Symbol.AAPL);
		
		verify(quoteRepository,times(1)).findQuotesBySymbol(Symbol.AAPL);
		
	}
	
	@Test
	public void getQuotesShouldCallRepositoryGetQuotes() {
		service.getQuotes();
		
		verify(quoteRepository,times(1)).findAllQuotes();
		
	}
}
