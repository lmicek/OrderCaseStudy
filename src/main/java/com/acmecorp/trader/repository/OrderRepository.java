package com.acmecorp.trader.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.acmecorp.trader.domain.LimitOrder;
import com.acmecorp.trader.domain.MarketOrder;
import com.acmecorp.trader.domain.Order;
import com.acmecorp.trader.domain.OrderType;
import com.acmecorp.trader.domain.Side;
import com.acmecorp.trader.domain.Symbol;

@Component
public class OrderRepository {
	private final JdbcTemplate jdbcTemplate;

	public OrderRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void saveOrder(Order order) {
		if (order instanceof MarketOrder)
			jdbcTemplate.update(
					"insert into ORDERS(OID, SYMBOL, SHARES, SIDE, ORDERTIMESTAMP, TRADERID, ORDERTYPE) values (?,?,?,?,?,?,?)",
					order.getOrderId(), order.getSymbol().name(), order.getShares(), order.getSide().name(),
					order.getTimestamp(), order.getTraderId(), order.getOrderType().name());
		else {
			LimitOrder limitOrder = (LimitOrder) order;
			jdbcTemplate.update(
					"insert into ORDERS(OID, SYMBOL, SHARES, SIDE, ORDERTIMESTAMP, TRADERID, ORDERTYPE, LIMITPRICE) values (?,?,?,?,?,?,?,?)",
					limitOrder.getOrderId(), limitOrder.getSymbol().name(), limitOrder.getShares(),
					limitOrder.getSide().name(), limitOrder.getTimestamp(), limitOrder.getTraderId(),
					limitOrder.getOrderType().name(), limitOrder.getLimit());
		}

	}

	@Transactional(readOnly = true)
	public List<Order> findAllOrders() {
		return jdbcTemplate.query(
				"select OID, SYMBOL, SHARES, SIDE, ORDERTIMESTAMP, TRADERID, ORDERTYPE, LIMITPRICE from ORDERS",
				(new RowMapper<Order>() {
					@Override
					public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
						Order order;
						if (rs.getString("ORDERTYPE").equals(OrderType.MARKET.name())) {
							order = new MarketOrder();
						} else {
							order = new LimitOrder();
							((LimitOrder) order).setLimit(rs.getDouble("LIMITPRICE"));

						}

						order.setOrderId(UUID.fromString(rs.getString("OID")));
						order.setSymbol(Symbol.valueOf(rs.getString("SYMBOL")));
						order.setShares(rs.getInt("SHARES"));
						order.setSide(Side.valueOf(rs.getString("SIDE")));
						LocalDateTime dateTime = LocalDateTime.parse(rs.getString("ORDERTIMESTAMP").split("\\.")[0],
								DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
						order.setTimestamp(dateTime);
						order.setTraderId(rs.getString("TRADERID"));
						order.setOrderType(OrderType.valueOf(rs.getString("ORDERTYPE")));
						return order;
					}
				}));
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Order cancelOrder(UUID orderID, String traderID) {

		String sql = "select OID, SYMBOL, SHARES, SIDE, ORDERTIMESTAMP, TRADERID, ORDERTYPE, LIMITPRICE from ORDERS where OID = ?";
		Order orderToCancel = (Order) jdbcTemplate.queryForObject(sql, (new RowMapper<Order>() {
			@Override
			public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
				Order order;
				if (rs.getString("ORDERTYPE").equals(OrderType.MARKET.name())) {
					order = new MarketOrder();
				} else {
					order = new LimitOrder();
					((LimitOrder) order).setLimit(rs.getDouble("LIMITPRICE"));

				}

				order.setOrderId(UUID.fromString(rs.getString("OID")));
				order.setSymbol(Symbol.valueOf(rs.getString("SYMBOL")));
				order.setShares(rs.getInt("SHARES"));
				order.setSide(Side.valueOf(rs.getString("SIDE")));
				LocalDateTime dateTime = LocalDateTime.parse(rs.getString("ORDERTIMESTAMP").split("\\.")[0],
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
				order.setTimestamp(dateTime);
				order.setTraderId(rs.getString("TRADERID"));
				order.setOrderType(OrderType.valueOf(rs.getString("ORDERTYPE")));
				return order;
			}
		}), orderID);
		if (orderToCancel.getTraderId().equals(traderID)) {
			jdbcTemplate.update("delete from ORDERS where OID = ?", new Object[] { orderID });
			return orderToCancel;
		}
		return null;

	}

	@Transactional(readOnly = true)
	public List<Order> getOrdersByTrader(String traderId) {
		return jdbcTemplate.query(
				"select OID, SYMBOL, SHARES, SIDE, ORDERTIMESTAMP, TRADERID, ORDERTYPE, LIMITPRICE from ORDERS where TRADERID = ?",
				(new RowMapper<Order>() {
					@Override
					public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
						Order order;
						if (rs.getString("ORDERTYPE").equals(OrderType.MARKET.name())) {
							order = new MarketOrder();
						} else {
							order = new LimitOrder();
							((LimitOrder) order).setLimit(rs.getDouble("LIMITPRICE"));

						}

						order.setOrderId(UUID.fromString(rs.getString("OID")));
						order.setSymbol(Symbol.valueOf(rs.getString("SYMBOL")));
						order.setShares(rs.getInt("SHARES"));
						order.setSide(Side.valueOf(rs.getString("SIDE")));
						LocalDateTime dateTime = LocalDateTime.parse(rs.getString("ORDERTIMESTAMP").split("\\.")[0],
								DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
						order.setTimestamp(dateTime);
						order.setTraderId(rs.getString("TRADERID"));
						order.setOrderType(OrderType.valueOf(rs.getString("ORDERTYPE")));
						return order;
					}
				}), traderId);
	}

}
