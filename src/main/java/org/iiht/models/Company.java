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
public class Company {

    @Id
    private String id;
    @Column(name="NAME")
    private String name;
    @Column(name="EXCHANGE")
    private String exchange;
    @Column(name="CEO")
    private String ceo;
    @Column(name="TURNOVER")
    private Long turnover;
    @Column(name="DIRECTORS")
    private String directors;
    @Column(name="PROFILE")
    private String profile;


}
