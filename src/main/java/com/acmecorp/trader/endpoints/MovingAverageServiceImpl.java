/**
 * 
 */
package com.acmecorp.trader.endpoints;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.acmecorp.trader.domain.MovingAverage;
import com.acmecorp.trader.domain.Period;
import com.acmecorp.trader.domain.Quote;
import com.acmecorp.trader.domain.Symbol;
import com.acmecorp.trader.services.QuoteService;
import com.acmecorp.trader.services.QuotesGenerator;
import com.acmecorp.trader.services.QuotesGeneratorImpl;
import com.google.common.base.Enums;

import ch.qos.logback.classic.Logger;

/**
 * @author a419845
 *
 */
@RestController
@RequestMapping("/marketanalysis")
public class MovingAverageServiceImpl implements MovingAverageService {

	private final Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private QuoteService quotesGenerator;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.acmecorp.trader.endpoints.Service#isAlive()
	 */
	@Override
	@RequestMapping("/isAlive")
	public Map<String, String> isAlive() {
		return Collections.singletonMap("message", "MovingAverageService is up and running!");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.acmecorp.trader.endpoints.MovingAverageService#getMovingAverage(com.
	 * acmecorp.trader.domain.Symbol, com.acmecorp.trader.domain.Period, int, int)
	 */
	@Override
	@RequestMapping(value="/movingaverage/{symbol}/days/{numPeriods}", method = RequestMethod.GET)
	public List<MovingAverage> getMovingAverages(@PathVariable("symbol") String argSymbol, @PathVariable("numPeriods") int numPeriods) {
		
		Symbol symbol = Enums.getIfPresent(Symbol.class, argSymbol).orNull();
		
		if (symbol == null)
		{
			throw new RuntimeException("Symbol not traded currently on this exchange");
		}
		
		Period period = Period.DAY;
		switch (period) {
		case DAY:
		default:
			Map<Symbol, List<Quote>> quoteMap = quotesGenerator.getQuotes();
			List<Quote> quoteList = quoteMap.get(symbol);
			
			if (quoteList != null && !quoteList.isEmpty())
			{
				// Get list of quotes with closingPrice
				return calculateMovingAverages(quoteList, numPeriods);
			}
			else
			{
				throw new RuntimeException("Sorry no quote data available for the symbol: " + symbol.toString());
			}
		}
	}

	public List<MovingAverage> calculateMovingAverages(List<Quote> quoteList, int windowSize) {
		List<MovingAverage> movingAverageList = new ArrayList<MovingAverage>();
		double sum = 0.0;
		int currentQueueDepth;

		// queue used to store list so that we get the average
		Queue<Quote> quotesQueue = new LinkedList<Quote>();

		for (Quote quote : quoteList) {
			sum += quote.getPrice();
			quotesQueue.add(quote);
			currentQueueDepth = quotesQueue.size();

			MovingAverage movingAverage;

			// Queue depth should match windowSize at all times
			if (currentQueueDepth > windowSize) {
				sum -= quotesQueue.remove().getPrice();
				movingAverage = getMovingAverageItem(windowSize, sum);
				movingAverage.setAsOfDate(quote.getDateTime());
				movingAverageList.add(movingAverage);
			} else if (currentQueueDepth == windowSize) {
				log.info("Queue depth matches windowSize");
				movingAverage = getMovingAverageItem(windowSize, sum);
				movingAverage.setAsOfDate(quote.getDateTime());
				movingAverageList.add(movingAverage);
			} else if (currentQueueDepth < windowSize) {
				log.info("Skipping moving average calculation until window is filled");
			}
		}

		return movingAverageList;
	}

	private MovingAverage getMovingAverageItem(int windowSize, double sum) {
		double average;
		MovingAverage movingAverage;
		average = sum / windowSize;
		movingAverage = new MovingAverage();
		movingAverage.setPrice(average);
		return movingAverage;
	}
}
