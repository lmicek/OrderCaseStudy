package com.acmecorp.trader.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.acmecorp.trader.TraderApplication;
import com.acmecorp.trader.domain.Quote;
import com.acmecorp.trader.domain.Symbol;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes = { TraderApplication.class })
public class QuotesGeneratorImplTest {

	private static Map<Symbol, List<Quote>> defaultListOfQuotes;
	
	@BeforeClass
	public static void setup() {
		defaultListOfQuotes = new QuotesGeneratorImpl.Generator().build().getQuotes();
	}
	
	@Test
	public void checkThatRandomSymbolSelectWorks() {
		Map<Symbol, Double> symbolMap = new HashMap<>();

		symbolMap.put(Symbol.AAPL, (double) 100);
		symbolMap.put(Symbol.GOOGL, (double) 75);
		symbolMap.put(Symbol.IBM, (double) 65);

		ArrayList<Integer> testArray = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			int test = (int) ((Math.random() * 10) % symbolMap.size());

			testArray.add(test);
		}

		assertThat(testArray).isSubsetOf(0, 1, 2);
	}
	
	@Test
	public void checkMathForPriceMultiplier() {
		ArrayList<Double> testArray = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			double value = (Math.random() % .08) + .01;
			testArray.add(truncateRound(value));
		}

		assertThat(testArray).isSubsetOf(.01, .02, .03, .04, .05, .06, .07, .08, .09);
	}
	
	@Test
	public void testThatQuoteGenerationReturnsCorrectMapSize() {
		assertThat(defaultListOfQuotes.size()).isEqualTo(3);
	}
	
	@Test
	public void testThatQuoteGenerationReturnsCorrectMapSizeWhenUpdated() {
		List<Symbol> listOfSymbols = new ArrayList<>();
		listOfSymbols.add(Symbol.AAPL);
		
		QuotesGenerator qgen = new QuotesGeneratorImpl.Generator().symbols(listOfSymbols).build();
		
		assertThat(qgen.getQuotes().size()).isEqualTo(1);
	}
	
	@Test
	public void testThatQuoteGenerationReturnsCorrectListLength() {
		assertThat(defaultListOfQuotes.get(Symbol.AAPL).size()).isEqualTo(10);
	}
	
	@Test
	public void testThatQuoteGenerationReturnsCorrectListLengthWhenValueUpdated() {
		QuotesGenerator qgen = new QuotesGeneratorImpl.Generator().duration(11).build();
		assertThat(qgen.getQuotes().get(Symbol.AAPL).size()).isEqualTo(11);
	}
	
	@Test
	public void testThatQuoteGenerationReturnsCorrectValues() {
		System.out.println(defaultListOfQuotes.toString());
	}
	
	@Test
	public void testThatQuoteGenerationDatesIncrease() {
		LocalDateTime firstDateTime = defaultListOfQuotes.get(Symbol.AAPL).get(0).getDateTime();
		LocalDateTime secondDateTime = defaultListOfQuotes.get(Symbol.AAPL).get(1).getDateTime();
		
		assertThat(firstDateTime).isEqualTo(secondDateTime.minusDays(1));
	}
	
	@Test
	public void testThatQuoteGenerationStartsWithDefaultPrice() {
		
		assertThat(defaultListOfQuotes.get(Symbol.GOOGL).get(0).getPrice()).isEqualTo(100);
	}
	
	@Test
	public void testThatQuoteGenerationPriceStartsWithGivenValue() {
		QuotesGenerator qgen = new QuotesGeneratorImpl.Generator().price(Symbol.AAPL, 10).build();
		
		assertThat(qgen.getQuotes().get(Symbol.AAPL).get(0).getPrice()).isEqualTo(10);
	}
	
	@Test
	public void testThatQuoteGenerationPriceChanges() {
		
		assertThat(defaultListOfQuotes.get(Symbol.GOOGL).get(1).getPrice()).isNotEqualTo(100);
	}
	
	private double truncateRound(double valueToRound) {
	    double scale = Math.pow(10, 2);
	    return Math.round(valueToRound * scale) / scale;
	}
}