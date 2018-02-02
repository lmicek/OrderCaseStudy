package com.acmecorp.trader.endpoints;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.acmecorp.trader.domain.MarketOrder;
import com.acmecorp.trader.domain.Order;
import com.acmecorp.trader.repository.OrderRepository;

import ch.qos.logback.classic.Logger;

@RestController
public class OrderEndpoint {
	private final Logger log = (Logger) LoggerFactory.getLogger(this.getClass());
	@Autowired
	private OrderRepository orderRepo;
	
	@RequestMapping(value = "/orders/trader", method = RequestMethod.GET)
	public List<Order> listOrdersByTrader(Authentication authentication){
		String traderId = authentication.getName();
		log.info("Retriving orders for " + traderId);
		return orderRepo.getOrdersByTrader(traderId);
		
		
	}

	@RequestMapping(value = "/order", method = RequestMethod.POST)
	public Order placeOrder(@RequestBody Order newOrder, Authentication authentication) {
		newOrder.setOrderId(UUID.randomUUID());
		newOrder.setTimestamp(LocalDateTime.now());
		newOrder.setTraderId(authentication.getName());
		log.info("Order received: " + newOrder);
		orderRepo.saveOrder(newOrder);
		return newOrder;
	}
	
	@RequestMapping(value = "/orders", method = RequestMethod.GET)
	public List<Order> listOrders() {
		return orderRepo.findAllOrders();
	}
	
	@RequestMapping(value = "/orders/{orderId}", method = RequestMethod.DELETE)
	public String cancelOrder(@PathVariable UUID orderId, Authentication authentication) {
		log.info("Cancelling order:"+orderId);
	if(orderRepo.cancelOrder(orderId, authentication.getName()).getOrderId() != null) {
		return "Order cancelled: "+ orderId;
	}
	else {
		return "Cancelling Order request failed";
	}
	
}}
