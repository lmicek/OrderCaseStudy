/**
 * 
 */
package com.acmecorp.trader.endpoints;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.acmecorp.trader.domain.MovingAverage;
import com.acmecorp.trader.domain.Quote;
import com.acmecorp.trader.domain.Symbol;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Enums;

/**
 * @author a419845
 *
 */
@RunWith(SpringRunner.class)
public class MovingAverageServiceImplTest {

	List<Quote> quoteList;
	
	MovingAverageServiceImpl movingAverageServiceImpl;

	@Test
	public void testCalculateMovingAverages() throws Exception {
		
		assertTrue("quoteList is not empty", quoteList.size() > 0);
		
		List<MovingAverage> movingAveragesList = movingAverageServiceImpl.calculateMovingAverages(quoteList, 100);

		assertNotNull(movingAveragesList);
		assertTrue(movingAveragesList.size() > 0);
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.writeValue(new File("target/movingaverages.json"), movingAveragesList);
	}

	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		movingAverageServiceImpl = new MovingAverageServiceImpl();
		quoteList = new ArrayList<Quote>();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu HH:mm");

		quoteList.add(new Quote(Symbol.AAPL, 167.43, 1, LocalDateTime.parse("01/31/2018 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 166.97, 1, LocalDateTime.parse("01/30/2018 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 167.96, 1, LocalDateTime.parse("01/29/2018 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 171.51, 1, LocalDateTime.parse("01/26/2018 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 171.11, 1, LocalDateTime.parse("01/25/2018 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 174.22, 1, LocalDateTime.parse("01/24/2018 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 177.04, 1, LocalDateTime.parse("01/23/2018 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 177, 1, LocalDateTime.parse("01/22/2018 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 178.46, 1, LocalDateTime.parse("01/19/2018 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 179.26, 1, LocalDateTime.parse("01/18/2018 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 179.1, 1, LocalDateTime.parse("01/17/2018 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 176.19, 1, LocalDateTime.parse("01/16/2018 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 177.09, 1, LocalDateTime.parse("01/12/2018 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 175.28, 1, LocalDateTime.parse("01/11/2018 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 174.29, 1, LocalDateTime.parse("01/10/2018 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 174.33, 1, LocalDateTime.parse("01/09/2018 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 174.35, 1, LocalDateTime.parse("01/08/2018 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 175, 1, LocalDateTime.parse("01/05/2018 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 173.03, 1, LocalDateTime.parse("01/04/2018 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 172.23, 1, LocalDateTime.parse("01/03/2018 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 172.26, 1, LocalDateTime.parse("01/02/2018 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 169.23, 1, LocalDateTime.parse("12/29/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 171.08, 1, LocalDateTime.parse("12/28/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 170.6, 1, LocalDateTime.parse("12/27/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 170.57, 1, LocalDateTime.parse("12/26/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 175.01, 1, LocalDateTime.parse("12/22/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 175.01, 1, LocalDateTime.parse("12/21/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 174.35, 1, LocalDateTime.parse("12/20/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 174.54, 1, LocalDateTime.parse("12/19/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 176.42, 1, LocalDateTime.parse("12/18/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 173.97, 1, LocalDateTime.parse("12/15/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 172.22, 1, LocalDateTime.parse("12/14/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 172.27, 1, LocalDateTime.parse("12/13/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 171.7, 1, LocalDateTime.parse("12/12/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 172.67, 1, LocalDateTime.parse("12/11/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 169.37, 1, LocalDateTime.parse("12/08/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 169.32, 1, LocalDateTime.parse("12/07/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 169.01, 1, LocalDateTime.parse("12/06/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 169.64, 1, LocalDateTime.parse("12/05/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 169.8, 1, LocalDateTime.parse("12/04/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 171.05, 1, LocalDateTime.parse("12/01/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 171.85, 1, LocalDateTime.parse("11/30/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 169.48, 1, LocalDateTime.parse("11/29/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 173.07, 1, LocalDateTime.parse("11/28/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 174.09, 1, LocalDateTime.parse("11/27/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 174.97, 1, LocalDateTime.parse("11/24/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 174.96, 1, LocalDateTime.parse("11/22/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 173.14, 1, LocalDateTime.parse("11/21/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 169.98, 1, LocalDateTime.parse("11/20/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 170.15, 1, LocalDateTime.parse("11/17/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 171.1, 1, LocalDateTime.parse("11/16/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 169.08, 1, LocalDateTime.parse("11/15/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 171.34, 1, LocalDateTime.parse("11/14/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 173.97, 1, LocalDateTime.parse("11/13/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 174.67, 1, LocalDateTime.parse("11/10/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 175.88, 1, LocalDateTime.parse("11/09/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 176.24, 1, LocalDateTime.parse("11/08/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 174.81, 1, LocalDateTime.parse("11/07/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 174.25, 1, LocalDateTime.parse("11/06/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 172.5, 1, LocalDateTime.parse("11/03/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 168.11, 1, LocalDateTime.parse("11/02/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 166.89, 1, LocalDateTime.parse("11/01/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 169.04, 1, LocalDateTime.parse("10/31/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 166.72, 1, LocalDateTime.parse("10/30/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 163.05, 1, LocalDateTime.parse("10/27/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 157.41, 1, LocalDateTime.parse("10/26/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 156.41, 1, LocalDateTime.parse("10/25/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 157.1, 1, LocalDateTime.parse("10/24/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 156.17, 1, LocalDateTime.parse("10/23/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 156.25, 1, LocalDateTime.parse("10/20/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 155.98, 1, LocalDateTime.parse("10/19/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 159.76, 1, LocalDateTime.parse("10/18/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 160.47, 1, LocalDateTime.parse("10/17/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 159.88, 1, LocalDateTime.parse("10/16/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 156.99, 1, LocalDateTime.parse("10/13/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 156, 1, LocalDateTime.parse("10/12/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 156.55, 1, LocalDateTime.parse("10/11/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 155.9, 1, LocalDateTime.parse("10/10/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 155.84, 1, LocalDateTime.parse("10/09/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 155.3, 1, LocalDateTime.parse("10/06/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 155.39, 1, LocalDateTime.parse("10/05/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 153.48, 1, LocalDateTime.parse("10/04/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 154.48, 1, LocalDateTime.parse("10/03/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 153.81, 1, LocalDateTime.parse("10/02/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 154.12, 1, LocalDateTime.parse("09/29/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 153.28, 1, LocalDateTime.parse("09/28/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 154.23, 1, LocalDateTime.parse("09/27/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 153.14, 1, LocalDateTime.parse("09/26/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 150.55, 1, LocalDateTime.parse("09/25/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 151.89, 1, LocalDateTime.parse("09/22/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 153.39, 1, LocalDateTime.parse("09/21/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 156.07, 1, LocalDateTime.parse("09/20/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 158.73, 1, LocalDateTime.parse("09/19/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 158.67, 1, LocalDateTime.parse("09/18/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 159.88, 1, LocalDateTime.parse("09/15/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 158.28, 1, LocalDateTime.parse("09/14/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 159.65, 1, LocalDateTime.parse("09/13/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 160.86, 1, LocalDateTime.parse("09/12/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 161.5, 1, LocalDateTime.parse("09/11/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 158.63, 1, LocalDateTime.parse("09/08/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 161.26, 1, LocalDateTime.parse("09/07/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 161.91, 1, LocalDateTime.parse("09/06/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 162.08, 1, LocalDateTime.parse("09/05/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 164.05, 1, LocalDateTime.parse("09/01/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 164, 1, LocalDateTime.parse("08/31/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 163.35, 1, LocalDateTime.parse("08/30/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 162.91, 1, LocalDateTime.parse("08/29/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 161.47, 1, LocalDateTime.parse("08/28/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 159.86, 1, LocalDateTime.parse("08/25/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 159.27, 1, LocalDateTime.parse("08/24/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 159.98, 1, LocalDateTime.parse("08/23/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 159.78, 1, LocalDateTime.parse("08/22/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 157.21, 1, LocalDateTime.parse("08/21/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 157.5, 1, LocalDateTime.parse("08/18/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 157.86, 1, LocalDateTime.parse("08/17/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 160.95, 1, LocalDateTime.parse("08/16/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 161.6, 1, LocalDateTime.parse("08/15/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 159.85, 1, LocalDateTime.parse("08/14/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 157.48, 1, LocalDateTime.parse("08/11/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 155.32, 1, LocalDateTime.parse("08/10/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 161.06, 1, LocalDateTime.parse("08/09/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 160.08, 1, LocalDateTime.parse("08/08/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 158.81, 1, LocalDateTime.parse("08/07/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 156.39, 1, LocalDateTime.parse("08/04/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 155.57, 1, LocalDateTime.parse("08/03/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 157.14, 1, LocalDateTime.parse("08/02/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 150.05, 1, LocalDateTime.parse("08/01/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 148.73, 1, LocalDateTime.parse("07/31/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 149.5, 1, LocalDateTime.parse("07/28/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 150.56, 1, LocalDateTime.parse("07/27/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 153.46, 1, LocalDateTime.parse("07/26/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 152.74, 1, LocalDateTime.parse("07/25/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 152.09, 1, LocalDateTime.parse("07/24/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 150.27, 1, LocalDateTime.parse("07/21/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 150.34, 1, LocalDateTime.parse("07/20/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 151.02, 1, LocalDateTime.parse("07/19/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 150.08, 1, LocalDateTime.parse("07/18/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 149.56, 1, LocalDateTime.parse("07/17/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 149.04, 1, LocalDateTime.parse("07/14/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 147.77, 1, LocalDateTime.parse("07/13/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 145.74, 1, LocalDateTime.parse("07/12/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 145.53, 1, LocalDateTime.parse("07/11/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 145.06, 1, LocalDateTime.parse("07/10/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 144.18, 1, LocalDateTime.parse("07/07/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 142.73, 1, LocalDateTime.parse("07/06/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 144.09, 1, LocalDateTime.parse("07/05/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 143.5, 1, LocalDateTime.parse("07/03/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 144.02, 1, LocalDateTime.parse("06/30/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 143.68, 1, LocalDateTime.parse("06/29/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 145.83, 1, LocalDateTime.parse("06/28/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 143.73, 1, LocalDateTime.parse("06/27/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 145.82, 1, LocalDateTime.parse("06/26/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 146.28, 1, LocalDateTime.parse("06/23/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 145.63, 1, LocalDateTime.parse("06/22/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 145.87, 1, LocalDateTime.parse("06/21/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 145.01, 1, LocalDateTime.parse("06/20/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 146.34, 1, LocalDateTime.parse("06/19/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 142.27, 1, LocalDateTime.parse("06/16/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 144.29, 1, LocalDateTime.parse("06/15/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 145.16, 1, LocalDateTime.parse("06/14/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 146.59, 1, LocalDateTime.parse("06/13/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 145.42, 1, LocalDateTime.parse("06/12/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 148.98, 1, LocalDateTime.parse("06/09/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 154.99, 1, LocalDateTime.parse("06/08/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 155.37, 1, LocalDateTime.parse("06/07/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 154.45, 1, LocalDateTime.parse("06/06/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 153.93, 1, LocalDateTime.parse("06/05/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 155.45, 1, LocalDateTime.parse("06/02/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 153.18, 1, LocalDateTime.parse("06/01/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 152.76, 1, LocalDateTime.parse("05/31/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 153.67, 1, LocalDateTime.parse("05/30/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 153.61, 1, LocalDateTime.parse("05/26/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 153.87, 1, LocalDateTime.parse("05/25/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 153.34, 1, LocalDateTime.parse("05/24/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 153.8, 1, LocalDateTime.parse("05/23/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 153.99, 1, LocalDateTime.parse("05/22/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 153.06, 1, LocalDateTime.parse("05/19/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 152.54, 1, LocalDateTime.parse("05/18/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 150.25, 1, LocalDateTime.parse("05/17/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 155.47, 1, LocalDateTime.parse("05/16/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 155.7, 1, LocalDateTime.parse("05/15/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 156.1, 1, LocalDateTime.parse("05/12/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 153.95, 1, LocalDateTime.parse("05/11/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 153.26, 1, LocalDateTime.parse("05/10/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 153.99, 1, LocalDateTime.parse("05/09/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 153.01, 1, LocalDateTime.parse("05/08/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 148.96, 1, LocalDateTime.parse("05/05/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 146.53, 1, LocalDateTime.parse("05/04/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 147.06, 1, LocalDateTime.parse("05/03/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 147.51, 1, LocalDateTime.parse("05/02/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 146.58, 1, LocalDateTime.parse("05/01/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 143.65, 1, LocalDateTime.parse("04/28/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 143.79, 1, LocalDateTime.parse("04/27/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 143.68, 1, LocalDateTime.parse("04/26/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 144.53, 1, LocalDateTime.parse("04/25/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 143.64, 1, LocalDateTime.parse("04/24/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 142.27, 1, LocalDateTime.parse("04/21/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 142.44, 1, LocalDateTime.parse("04/20/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 140.68, 1, LocalDateTime.parse("04/19/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 141.2, 1, LocalDateTime.parse("04/18/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 141.83, 1, LocalDateTime.parse("04/17/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 141.05, 1, LocalDateTime.parse("04/13/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 141.8, 1, LocalDateTime.parse("04/12/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 141.63, 1, LocalDateTime.parse("04/11/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 143.17, 1, LocalDateTime.parse("04/10/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 143.34, 1, LocalDateTime.parse("04/07/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 143.66, 1, LocalDateTime.parse("04/06/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 144.02, 1, LocalDateTime.parse("04/05/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 144.77, 1, LocalDateTime.parse("04/04/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 143.7, 1, LocalDateTime.parse("04/03/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 143.66, 1, LocalDateTime.parse("03/31/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 143.93, 1, LocalDateTime.parse("03/30/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 144.12, 1, LocalDateTime.parse("03/29/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 143.8, 1, LocalDateTime.parse("03/28/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 140.88, 1, LocalDateTime.parse("03/27/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 140.64, 1, LocalDateTime.parse("03/24/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 140.92, 1, LocalDateTime.parse("03/23/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 141.42, 1, LocalDateTime.parse("03/22/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 139.84, 1, LocalDateTime.parse("03/21/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 141.46, 1, LocalDateTime.parse("03/20/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 139.99, 1, LocalDateTime.parse("03/17/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 140.69, 1, LocalDateTime.parse("03/16/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 140.46, 1, LocalDateTime.parse("03/15/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 138.99, 1, LocalDateTime.parse("03/14/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 139.2, 1, LocalDateTime.parse("03/13/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 139.14, 1, LocalDateTime.parse("03/10/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 138.68, 1, LocalDateTime.parse("03/09/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 139, 1, LocalDateTime.parse("03/08/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 139.52, 1, LocalDateTime.parse("03/07/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 139.34, 1, LocalDateTime.parse("03/06/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 139.78, 1, LocalDateTime.parse("03/03/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 138.96, 1, LocalDateTime.parse("03/02/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 139.79, 1, LocalDateTime.parse("03/01/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 136.99, 1, LocalDateTime.parse("02/28/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 136.93, 1, LocalDateTime.parse("02/27/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 136.66, 1, LocalDateTime.parse("02/24/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 136.53, 1, LocalDateTime.parse("02/23/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 137.11, 1, LocalDateTime.parse("02/22/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 136.7, 1, LocalDateTime.parse("02/21/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 135.72, 1, LocalDateTime.parse("02/17/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 135.34, 1, LocalDateTime.parse("02/16/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 135.51, 1, LocalDateTime.parse("02/15/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 135.02, 1, LocalDateTime.parse("02/14/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 133.29, 1, LocalDateTime.parse("02/13/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 132.12, 1, LocalDateTime.parse("02/10/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 132.42, 1, LocalDateTime.parse("02/09/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 132.04, 1, LocalDateTime.parse("02/08/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 131.53, 1, LocalDateTime.parse("02/07/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 130.29, 1, LocalDateTime.parse("02/06/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 129.08, 1, LocalDateTime.parse("02/03/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 128.53, 1, LocalDateTime.parse("02/02/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 128.75, 1, LocalDateTime.parse("02/01/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 121.35, 1, LocalDateTime.parse("01/31/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 121.63, 1, LocalDateTime.parse("01/30/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 121.95, 1, LocalDateTime.parse("01/27/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 121.94, 1, LocalDateTime.parse("01/26/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 121.88, 1, LocalDateTime.parse("01/25/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 119.97, 1, LocalDateTime.parse("01/24/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 120.08, 1, LocalDateTime.parse("01/23/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 120, 1, LocalDateTime.parse("01/20/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 119.78, 1, LocalDateTime.parse("01/19/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 119.99, 1, LocalDateTime.parse("01/18/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 120, 1, LocalDateTime.parse("01/17/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 119.04, 1, LocalDateTime.parse("01/13/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 119.25, 1, LocalDateTime.parse("01/12/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 119.75, 1, LocalDateTime.parse("01/11/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 119.11, 1, LocalDateTime.parse("01/10/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 118.99, 1, LocalDateTime.parse("01/09/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 117.91, 1, LocalDateTime.parse("01/06/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 116.61, 1, LocalDateTime.parse("01/05/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 116.02, 1, LocalDateTime.parse("01/04/2017 16:30", formatter)));
		quoteList.add(new Quote(Symbol.AAPL, 116.15, 1, LocalDateTime.parse("01/03/2017 16:30", formatter)));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		quoteList = null;
	}

}
