package com.acmecorp.trader.repository;

import static org.junit.Assert.*;
import static org.hamcrest.core.IsEqual.equalTo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.acmecorp.trader.TraderApplication;
import com.acmecorp.trader.domain.LimitOrder;
import com.acmecorp.trader.domain.MarketOrder;
import com.acmecorp.trader.domain.Order;
import com.acmecorp.trader.domain.OrderType;
import com.acmecorp.trader.domain.Side;
import com.acmecorp.trader.domain.Symbol;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT, classes = { TraderApplication.class })
public class OrderRepositoryTest {

	private UUID marketOrderID = UUID.randomUUID();
	private UUID limitOrderID = UUID.randomUUID();
	
	private LocalDateTime now = LocalDateTime.now();
	
	@Autowired
	private OrderRepository marketOrderRepository;
	
	@Before
	public void setupDataBase(){
		
		Order marketOrder = new MarketOrder();
		marketOrder.setOrderId(marketOrderID);
		marketOrder.setTimestamp(now);
		marketOrder.setTraderId("trader1");
		marketOrder.setSymbol(Symbol.IBM);
		marketOrder.setShares(20);
		marketOrder.setSide(Side.SELL);
		marketOrderRepository.saveOrder(marketOrder);
		
		Order limitOrder = new LimitOrder();
		limitOrder.setOrderId(limitOrderID);
		limitOrder.setTimestamp(now);
		limitOrder.setTraderId("trader1");
		limitOrder.setSymbol(Symbol.IBM);
		limitOrder.setShares(20);
		limitOrder.setSide(Side.SELL);
		marketOrderRepository.saveOrder(limitOrder);
	}
	
	@Test
	public void insertOrderCompletesSuccessfully() {
	
		List<Order> orders = marketOrderRepository.findAllOrders();
		assertThat("market order size should be 2", 2, equalTo(orders.size()));
		Order marketOrder = orders.get(0);
		assertThat("Symbol should be IBM", Symbol.IBM, equalTo(marketOrder.getSymbol()));
		assertThat("Shares should be 20", 20, equalTo(marketOrder.getShares()));
		assertThat("side should be SELL", Side.SELL, equalTo(marketOrder.getSide()));
		assertThat("TraderId should be trader1", "trader1", equalTo(marketOrder.getTraderId()));
		assertThat("Ordertype should be MARKET", OrderType.MARKET, equalTo(marketOrder.getOrderType()));
		
		Order limitOrder = orders.get(1);
		assertThat("Symbol should be IBM", Symbol.IBM, equalTo(limitOrder.getSymbol()));
		assertThat("Shares should be 20", 20, equalTo(limitOrder.getShares()));
		assertThat("side should be SELL", Side.SELL, equalTo(limitOrder.getSide()));
		assertThat("TraderId should be trader1", "trader1", equalTo(limitOrder.getTraderId()));
		assertThat("Ordertype should be LIMIT", OrderType.LIMIT, equalTo(limitOrder.getOrderType()));
	}
	
	@Test
	public void cancelOrderCompletesSuccessfully() {
		
		Order order = marketOrderRepository.cancelOrder(marketOrderID, "trader1");
		List<Order> orders = marketOrderRepository.findAllOrders();
		assertThat("Order should be cancelled",false, equalTo(orders.contains(order)));		
	}
}
