package org.shree;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchCompanyModel {
    private String cexchange;
    private String companyId;
    private String ccode;
    private String cceo;
    private String cturnover;
    private String startdate;
    private String enddate;
}
