package com.gft.store.models.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TradeOrderItem {

    private Long product_id;

    private Integer amount;

    private BigDecimal trade_value;
}
