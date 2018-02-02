package com.acmecorp.trader.repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.acmecorp.trader.domain.Quote;
import com.acmecorp.trader.domain.Symbol;

@Component
public class PriceQuoteRepository {
	private final JdbcTemplate jdbcTemplate;
	public PriceQuoteRepository (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Transactional(propagation = Propagation.REQUIRED)
	public void saveQuote(Quote quote) {
		jdbcTemplate.update("insert into PRICE_QUOTES(SYMBOL, PRICE, SHARES, QUOTETIMESTAMP) values (?,?,?,?)",
		quote.getSymbol().name(), quote.getPrice(), quote.getNumberShares(), quote.getDateTime());
	}
	@Transactional(readOnly=true)
	public List<Quote> findAllQuotes() {
		return jdbcTemplate.query("select SYMBOL, PRICE, SHARES, QUOTETIMESTAMP from PRICE_QUOTES",
			(new RowMapper<Quote>() {
			@Override
			public Quote mapRow(ResultSet rs, int rowNum) throws SQLException {
			LocalDateTime dateTime = LocalDateTime.parse(rs.getString("QUOTETIMESTAMP").split("\\.")[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));	
			Quote quote = new Quote(Symbol.valueOf(rs.getString("SYMBOL")),rs.getDouble("PRICE"),rs.getInt("SHARES"),dateTime);
			return quote;
			}
		}));
	}
	@Transactional(readOnly=true)
	public List<Quote> findQuotesBySymbol(Symbol sym) {
		return jdbcTemplate.query("select SYMBOL, PRICE, SHARES, QUOTETIMESTAMP from PRICE_QUOTES where SYMBOL = ?",
			new Object[] { sym.name() }, 
			(new RowMapper<Quote>() {
			@Override
			public Quote mapRow(ResultSet rs, int rowNum) throws SQLException {
			LocalDateTime dateTime = LocalDateTime.parse(rs.getString("QUOTETIMESTAMP").split("\\.")[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));	
			Quote quote = new Quote(Symbol.valueOf(rs.getString("SYMBOL")),rs.getDouble("PRICE"),rs.getInt("SHARES"),dateTime);
			return quote;
			}
		}));
	}
}
