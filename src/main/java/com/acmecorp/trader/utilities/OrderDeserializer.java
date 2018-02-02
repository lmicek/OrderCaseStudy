package com.acmecorp.trader.utilities;

import java.io.IOException;

import com.acmecorp.trader.domain.LimitOrder;
import com.acmecorp.trader.domain.MarketOrder;
import com.acmecorp.trader.domain.Order;
import com.acmecorp.trader.domain.OrderType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class OrderDeserializer extends JsonDeserializer<Order> {

	@Override
	public Order deserialize(JsonParser arg0, DeserializationContext arg1) throws IOException, JsonProcessingException {
		// TODO Auto-generated method stub
		JsonNode node = arg0.getCodec().readTree(arg0);
		if (node.has("ordertype") && node.get("ordertype").asText().equals(OrderType.LIMIT.name())) {
			return arg0.getCodec().treeToValue(node, LimitOrder.class);
		}
		else
			return arg0.getCodec().treeToValue(node, MarketOrder.class);
	}

}
