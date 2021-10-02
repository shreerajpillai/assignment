package org.iiht.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
@DynamicUpdate
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {
    @Id
    private String id;
    @Column(name = "EXCHANGE")
    private String exchange;
    @Column(name = "COMPANYID")
    private String companyId;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "PRICEDATE")
    private String priceDate;
    @Column(name = "PRICETIME")
    private String priceTime;
}
