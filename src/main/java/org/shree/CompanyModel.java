package org.shree;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyModel {
    private String id;
    private String cexchange;
    private String cname;
    private String cceo;
    private Long cturnover;
    private String cdirectors;
    private String cprofile;

    public String getCexchange() {
        return cexchange;
    }

    public void setCexchange(String cexchange) {
        this.cexchange = cexchange;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCceo() {
        return cceo;
    }

    public void setCceo(String cceo) {
        this.cceo = cceo;
    }

    public Long getCturnover() {
        return cturnover;
    }

    public void setCturnover(Long cturnover) {
        this.cturnover = cturnover;
    }

    public String getCdirectors() {
        return cdirectors;
    }

    public void setCdirectors(String cdirectors) {
        this.cdirectors = cdirectors;
    }

    public String getCprofile() {
        return cprofile;
    }

    public void setCprofile(String cprofile) {
        this.cprofile = cprofile;
    }

    @Override
    public String toString() {
        return "CompanyModel{" +
                "cexchange='" + cexchange + '\'' +
                ", cname='" + cname + '\'' +
                ", cceo='" + cceo + '\'' +
                ", cturnover=" + cturnover +
                ", cdirectors='" + cdirectors + '\'' +
                ", cprofile='" + cprofile + '\'' +
                '}';
    }
}
