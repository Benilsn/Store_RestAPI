package com.gft.store.models.forms;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {

    @NotEmpty(message = "Please inser provider's ID!")
    private Long provider_id;

    @Valid
    private List<TradeOrderItem> itens;
}
