package org.iiht.repositories;

import org.iiht.models.Stock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StockRepository extends CrudRepository<Stock,String> {
    List<Stock> findByExchange(String exchange);
    List<Stock> findByCompanyIdOrderByPriceDateAscPriceTimeAsc(String companyId);
    List<Stock> findByCompanyIdAndPriceDateBetweenOrderByPriceDateAscPriceTimeAsc
            (String companyId, String startDate, String endDate);
}
