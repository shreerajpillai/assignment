package org.shree;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompanyRepository extends CrudRepository<Company,String> {
    List<Company> findByExchange(String exchange);
}
