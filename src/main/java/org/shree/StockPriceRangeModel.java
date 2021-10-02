package org.shree;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockPriceRangeModel {
    private Double min;
    private Double max;
    private Double avg;
}
