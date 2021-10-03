package org.iiht.controllers;

import lombok.SneakyThrows;
import org.iiht.models.*;
import org.iiht.repositories.CompanyRepository;
import org.iiht.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class StockController {
    @Autowired
    private StockRepository repository;
    @Autowired
    private CompanyRepository companyRepository;

    @SneakyThrows
    @RequestMapping(value = "stock-index", method = RequestMethod.GET)
    public String stockIndex(Model model) {
        SearchCompanyModel searchCompanyModel = SearchCompanyModel.builder().build();
        model.addAttribute("searchcompanymodel", searchCompanyModel);
        return "showStockStatistics.jsp";
    }

    @SneakyThrows
    @RequestMapping(value = "search-stock-index", method = RequestMethod.POST)
    public ModelAndView searchStockIndex(@ModelAttribute("searchcompanymodel") SearchCompanyModel searchCompanyModel,
                                         @RequestParam String action) {
        List<CompanyModel> companyModels = new ArrayList<>();
        ModelAndView mv = new ModelAndView();
        List<Company> companies;
        CompanyModel companyModel=null;
        if (action.equals("fetch")) {
            mv.addObject("result", false);
            Map<String, String> referenceData = getCompanies(searchCompanyModel.getCexchange());
            mv.addObject("companyModels", referenceData);
            if (StringUtils.isEmpty(searchCompanyModel.getCompanyId()) && referenceData != null) {
                companyModel = getCompanyDetail(referenceData.entrySet().iterator().next().getKey());
            } else {
                companyModel = getCompanyDetail(searchCompanyModel.getCompanyId());
            }

        } else {
            List<StockModel> stockModels = getStockByCompany(searchCompanyModel.getCompanyId(),
                    searchCompanyModel.getStartdate(), searchCompanyModel.getEnddate());
            if (stockModels != null && stockModels.stream().count() > 0) {
                Double max = stockModels.stream().mapToDouble(v -> v.getSprice()).max().getAsDouble();
                Double min = stockModels.stream().mapToDouble(v -> v.getSprice()).min().getAsDouble();
                Double avg = stockModels.stream().mapToDouble(v -> v.getSprice()).average().getAsDouble();
                StockPriceRangeModel rangeModel = StockPriceRangeModel.builder()
                        .min(min)
                        .max(max)
                        .avg(avg)
                        .build();
                mv.addObject("rangemodel", rangeModel);
                mv.addObject("stockModels", stockModels);
            }
            Map<String, String> referenceData = getCompanies(searchCompanyModel.getCexchange());
            mv.addObject("companyModels", referenceData);
            if (StringUtils.isEmpty(searchCompanyModel.getCompanyId()) && referenceData != null) {
                companyModel = getCompanyDetail(referenceData.entrySet().iterator().next().getKey());
            } else {
                companyModel = getCompanyDetail(searchCompanyModel.getCompanyId());
            }
        }
        if (companyModel != null) {
            searchCompanyModel.setCcode("CODE0001");
            searchCompanyModel.setCceo(companyModel.getCceo());
            searchCompanyModel.setCturnover(companyModel.getCturnover().toString());
        }
        mv.setViewName("showStockStatistics.jsp");
        return mv;
    }

    @SneakyThrows
    @RequestMapping(value = "search-company", method = RequestMethod.GET)
    public String searchCompany(Model model) {
        SearchCompanyModel searchCompanyModel = SearchCompanyModel.builder().build();
        model.addAttribute("searchcompanymodel", searchCompanyModel);
        return "displayStockDetails.jsp";
    }

    @SneakyThrows
    @RequestMapping(value = "list-stocks", method = RequestMethod.POST)
    public ModelAndView listStocks(@ModelAttribute("searchcompanymodel") SearchCompanyModel searchCompanyModel, @RequestParam String action) {
        List<CompanyModel> companyModels = new ArrayList<>();
        ModelAndView mv = new ModelAndView();
        CompanyModel companyModel;
        List<Company> companies;
        if (action.equals("fetch")) {
            mv.addObject("result", false);
            Map<String, String> referenceData = getCompanies(searchCompanyModel.getCexchange());
            mv.addObject("companyModels", referenceData);
            if (StringUtils.isEmpty(searchCompanyModel.getCompanyId()) && referenceData != null) {
                companyModel = getCompanyDetail(referenceData.entrySet().iterator().next().getKey());

            } else {
                companyModel = getCompanyDetail(searchCompanyModel.getCompanyId());
            }
        } else {
            List<StockModel> stockModels = getStockByCompany(searchCompanyModel.getCompanyId(),
                    null, null);
            mv.addObject("stockModels", stockModels);
            Map<String, String> referenceData = getCompanies(searchCompanyModel.getCexchange());
            mv.addObject("companyModels", referenceData);
            if (StringUtils.isEmpty(searchCompanyModel.getCompanyId()) && referenceData != null) {
                companyModel = getCompanyDetail(referenceData.entrySet().iterator().next().getKey());
            } else {
                companyModel = getCompanyDetail(searchCompanyModel.getCompanyId());
            }
        }
        if (companyModel != null) {
            searchCompanyModel.setCcode("CODE0001");
            searchCompanyModel.setCceo(companyModel.getCceo());
            searchCompanyModel.setCturnover(companyModel.getCturnover().toString());
        }
        mv.setViewName("displayStockDetails.jsp");
        return mv;
    }

    @SneakyThrows
    @RequestMapping(value = "addStock", method = RequestMethod.GET)
    public String addStock(Model model) {
        List<CompanyModel> companyModels = new ArrayList<>();
        StockModel stockModel = StockModel.builder().build();
        model.addAttribute("stockmodel", stockModel);
        return "addStockPrices.jsp";
    }

    @SneakyThrows
    @RequestMapping(value = "save-stock", method = RequestMethod.POST)
    public ModelAndView add(@ModelAttribute("stockmodel") StockModel stockmodel, @RequestParam String action) {
        List<CompanyModel> companyModels = new ArrayList<>();
        ModelAndView mv = new ModelAndView();
        if (action.equals("save")) {
            Stock stock = new Stock();
            stock.setId(UUID.randomUUID().toString());
            stock.setCompanyId(stockmodel.getCompanyId());
            stock.setPrice(stockmodel.getSprice());
            stock.setPriceDate(stockmodel.getPriceDate());
            stock.setExchange(stockmodel.getCexchange());
            stock.setPriceTime(stockmodel.getPriceTime());
            repository.save(stock);
            Map<String, String> referenceData = getCompanies(stockmodel.getCexchange());
            mv.addObject("companyModels", referenceData);
            mv.addObject("result", true);
        } else {
            mv.addObject("result", false);
            Map<String, String> referenceData = getCompanies(stockmodel.getCexchange());
            mv.addObject("companyModels", referenceData);
        }
        System.out.println("Stock has been successfully added");
        mv.setViewName("addStockPrices.jsp");
        return mv;
    }

    @SneakyThrows
    private CompanyModel getCompanyDetail(String companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        CompanyModel companyModel = null;
        if (company != null) {
            companyModel = CompanyModel.builder()
                    .cturnover(company.get().getTurnover())
                    .cceo(company.get().getCeo())
                    .build();
        }
        return companyModel;
    }

    @SneakyThrows
    private Map<String, String> getCompanies(String selectedValue) {
        List<Company> companies;
        if (selectedValue != null && selectedValue.equals("ALL")) {
            companies = (List<Company>) companyRepository.findAll();
        } else {
            companies = companyRepository.findByExchange(selectedValue);
        }
        Map<String, String> company = new LinkedHashMap<String, String>();
        companies.forEach(c -> {
            company.put(c.getId(), c.getName());
        });
        return company;
    }

    @SneakyThrows
    public List<StockModel> getStockByCompany(String companyId, String startDate, String endDate) {
        List<Stock> stocks;
        if (startDate == null && endDate == null) {
            stocks = repository.findByCompanyIdOrderByPriceDateAscPriceTimeAsc(companyId);
        } else {
            stocks = repository.findByCompanyIdAndPriceDateBetweenOrderByPriceDateAscPriceTimeAsc(companyId, startDate, endDate);
        }
        List<StockModel> stockModels = stocks.stream().map(c -> {
            return StockModel.builder()
                    .cexchange(c.getExchange())
                    .companyId(c.getCompanyId())
                    .priceDate(c.getPriceDate())
                    .priceTime(c.getPriceTime())
                    .sprice(c.getPrice())
                    .build();
        }).collect(Collectors.toList());
        return stockModels;
    }
}
