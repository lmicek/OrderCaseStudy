/**
 * 
 */
package com.acmecorp.trader.endpoints;

import java.util.List;

import com.acmecorp.trader.domain.MovingAverage;
import com.acmecorp.trader.domain.Period;
import com.acmecorp.trader.domain.Symbol;

/**
 * @author a419845
 *
 */
public interface MovingAverageService extends Service {

	public List<MovingAverage> getMovingAverages(String symbol, int numPeriods);
	
}
