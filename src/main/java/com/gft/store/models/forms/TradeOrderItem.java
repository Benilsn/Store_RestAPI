package com.gft.store.models.forms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TradeOrderItem {

    private Long product_id;

    @NotEmpty(message = "Please choose a valid amount!")
    private Integer amount;

    @NotEmpty(message = "Please choose a valid item value!")
    private BigDecimal trade_value;
}
