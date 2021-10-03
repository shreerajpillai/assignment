package org.iiht.controllers;

import lombok.SneakyThrows;
import org.iiht.models.Company;
import org.iiht.models.CompanyModel;
import org.iiht.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class CompanyController {

    @Autowired
    private CompanyRepository repository;

    @SneakyThrows
    @RequestMapping(value = "list-companies", method = RequestMethod.GET)
    public ModelAndView listCompanies(@RequestParam String selectedValue) {
        List<Company> companies;
        if (selectedValue != null && selectedValue.equals("ALL")) {
            companies = (List<Company>) repository.findAll();
        } else {
            companies = repository.findByExchange(selectedValue);
        }
        List<CompanyModel> companyModels = companies.stream().map(c -> {
            return CompanyModel.builder()
                    .cceo(c.getCeo())
                    .cdirectors(c.getDirectors())
                    .cexchange(c.getExchange())
                    .cname(c.getName())
                    .cprofile(c.getProfile())
                    .cturnover(c.getTurnover())
                    .build();
        }).collect(Collectors.toList());

        ModelAndView mav = new ModelAndView("listcompanies.jsp");
        mav.addObject("companyModels", companyModels);
        mav.addObject("selectedValue", selectedValue);
        return mav;
    }

    @SneakyThrows
    @RequestMapping(value = "addCompanyInfo", method = RequestMethod.GET)
    public String addCompanyInfo(Model model) {
        CompanyModel companyModel = CompanyModel.builder().build();
        model.addAttribute("companymodel", companyModel);
        return "addCompanyInfo.jsp";
    }

    @SneakyThrows
    @RequestMapping(value = "add-company", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("companymodel") CompanyModel companyModel) {
        Company company = new Company();
        company.setId(UUID.randomUUID().toString());
        company.setName(companyModel.getCname());
        company.setCeo(companyModel.getCceo());
        company.setDirectors(companyModel.getCdirectors());
        company.setExchange(companyModel.getCexchange());
        company.setTurnover(companyModel.getCturnover());
        company.setProfile(companyModel.getCprofile());
        repository.save(company);
        System.out.println("Company has been successfully added");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("addCompanyInfo.jsp");
        mv.addObject("result", true);
        return mv;
    }
}
