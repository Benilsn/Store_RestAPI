package com.gft.store.models.forms;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SaleOrder {

    private Long branch_id;

    private Long client_id;

    private List<TradeOrderItem> itens;

    public SaleOrder(Long branch_id, Long client_id) {
        this.branch_id = branch_id;
        this.client_id = client_id;
    }
}
