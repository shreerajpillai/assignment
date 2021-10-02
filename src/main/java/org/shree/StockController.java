package org.shree;

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

    @RequestMapping(value = "search-stock-index", method = RequestMethod.GET)
    public String searchStockIndex(Model model) {
        SearchCompanyModel searchCompanyModel = SearchCompanyModel.builder().build();
        model.addAttribute("searchcompanymodel", searchCompanyModel);
        return "showStockStatistics.jsp";
    }

    @RequestMapping(value = "search-company", method = RequestMethod.GET)
    public String searchCompany(Model model) {
        SearchCompanyModel searchCompanyModel = SearchCompanyModel.builder().build();
        model.addAttribute("searchcompanymodel", searchCompanyModel);
        return "displayStockDetails.jsp";
    }

    @RequestMapping(value = "list-stocks", method = RequestMethod.POST)
    public ModelAndView listStocks(@ModelAttribute("searchcompanymodel") SearchCompanyModel searchCompanyModel, @RequestParam String action) {
        List<CompanyModel> companyModels = new ArrayList<>();
        ModelAndView mv = new ModelAndView();
        List<Company> companies;
        if (action.equals("fetch")) {
            mv.addObject("result", false);
            Map<String,String> referenceData = getCompanies(searchCompanyModel.getCexchange());
            mv.addObject("companyModels", referenceData);
            if(StringUtils.isEmpty(searchCompanyModel.getCompanyId()) && referenceData!=null ){
                CompanyModel companyModel = getCompanyDetail(referenceData.entrySet().iterator().next().getKey());
                if(companyModel!=null){
                    searchCompanyModel.setCcode("test");
                    searchCompanyModel.setCceo(companyModel.getCceo());
                    searchCompanyModel.setCturnover(companyModel.getCturnover().toString());
                }
            }
            else{
                CompanyModel companyModel = getCompanyDetail(searchCompanyModel.getCompanyId());
                if(companyModel!=null){
                    searchCompanyModel.setCcode("test");
                    searchCompanyModel.setCceo(companyModel.getCceo());
                    searchCompanyModel.setCturnover(companyModel.getCturnover().toString());
                }
            }

        }
        else{
            List<StockModel> stockModels = getStockByCompany(searchCompanyModel.getCompanyId());
            mv.addObject("stockModels", stockModels);
            Map<String,String> referenceData = getCompanies(searchCompanyModel.getCexchange());
            mv.addObject("companyModels", referenceData);
            if(StringUtils.isEmpty(searchCompanyModel.getCompanyId()) && referenceData!=null ){
                CompanyModel companyModel = getCompanyDetail(referenceData.entrySet().iterator().next().getKey());
                if(companyModel!=null){
                    searchCompanyModel.setCcode("test");
                    searchCompanyModel.setCceo(companyModel.getCceo());
                    searchCompanyModel.setCturnover(companyModel.getCturnover().toString());
                }
            }
            else{
                CompanyModel companyModel = getCompanyDetail(searchCompanyModel.getCompanyId());
                if(companyModel!=null){
                    searchCompanyModel.setCcode("test");
                    searchCompanyModel.setCceo(companyModel.getCceo());
                    searchCompanyModel.setCturnover(companyModel.getCturnover().toString());
                }
            }
        }

        mv.setViewName("displayStockDetails.jsp");
        //mv.addObject("selectedValue",selectedValue);
        return mv;
    }

    @RequestMapping(value = "addStock", method = RequestMethod.GET)
    public String addStock(Model model) {
        List<CompanyModel> companyModels = new ArrayList<>();
        StockModel stockModel = StockModel.builder().build();
        model.addAttribute("stockmodel", stockModel);
        //model.addAttribute("companyModels", companyModels);
        return "addStockPrices.jsp";
    }

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
            Map<String,String> referenceData = getCompanies(stockmodel.getCexchange());
            mv.addObject("companyModels", referenceData);
            mv.addObject("result", true);
        }
        else{
            mv.addObject("result", false);
            Map<String,String> referenceData = getCompanies(stockmodel.getCexchange());
            mv.addObject("companyModels", referenceData);
        }
        System.out.println("Stock has been successfully added");

        mv.setViewName("addStockPrices.jsp");

        return mv;
    }

    private CompanyModel getCompanyDetail(String companyId) {
        Optional<Company> company = companyRepository.findById(companyId);
        CompanyModel companyModel=null;
        if(company!=null){
            companyModel = CompanyModel.builder()
                    .cturnover(company.get().getTurnover())
                    .cceo(company.get().getCeo())
                    .build();
        }
        return companyModel;
    }

    private Map<String, String> getCompanies(String selectedValue) {
        List<Company> companies;
        if (selectedValue != null && selectedValue.equals("ALL")) {
            companies = (List<Company>) companyRepository.findAll();
        } else {
            companies = companyRepository.findByExchange(selectedValue);
        }
        Map<String,String> company = new LinkedHashMap<String,String>();
        companies.forEach(c->{
            company.put(c.getId(),c.getName());
        });

        return company;
    }

    public List<StockModel> getStockByCompany(String companyId) {
        List<Stock> stocks;
        stocks = (List<Stock>) repository.findByCompanyId (companyId);
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
