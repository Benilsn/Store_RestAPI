package com.gft.store.models.forms;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleOrder {

    @NotEmpty(message = "Please insert branch ID!")
    private Long branch_id;

    @NotEmpty(message = "Please insert client ID!")
    private Long client_id;

    @Valid
    private List<TradeOrderItem> itens;

    public SaleOrder(Long branch_id, Long client_id) {
        this.branch_id = branch_id;
        this.client_id = client_id;
    }
}
