package org.iiht.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockModel {
    private String cexchange;
    private String companyId;
    private Double sprice;
    private String priceDate;
    private String priceTime;

}
