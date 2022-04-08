package com.example.PageAndFilterHateos.controller;

import com.example.PageAndFilterHateos.entity.Customer;
import com.example.PageAndFilterHateos.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerResource {

    @Autowired
    private CustomerService customerService;


    @RequestMapping("api/v0/customers")
    public List<Customer> fetchCustomerAsList(){
        return customerService.fetchCustomerDataList();
    }

    @RequestMapping("api/v1/customers")
    public List<Customer> fetchCustomerAsFilteredList(@RequestParam(defaultValue = "") String firstNameFilter,
                                                      @RequestParam(defaultValue = "") String lastNameFilter){
        return customerService.fetchFilteredCustomerDataAsList(firstNameFilter, lastNameFilter);
        }

    @RequestMapping("api/v2/customers")
    public Page<Customer> fetchCustomerAsFilteredPage(@RequestParam(defaultValue = "") String firstNameFilter,
                                                      @RequestParam(defaultValue = "") String lastNameFilter,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "30") int size){
        return customerService.fetchFilteredCustomerDataAsPage(firstNameFilter, lastNameFilter, page, size);
    }

    @RequestMapping("api/v3/customers")
    public Page<Customer> fetchCustomersWithPageInterfaceAndSorted(@RequestParam(defaultValue = "") String firstNameFilter,
                                                                   @RequestParam(defaultValue = "") String lastNameFilter,
                                                                   @RequestParam(defaultValue = "0") int page,
                                                                   @RequestParam(defaultValue = "30") int size,
                                                                   @RequestParam(defaultValue = "") List<String> sortList,
                                                                   @RequestParam(defaultValue = "DESC") Sort.Direction sortOrder){
        return customerService.fetchCustomerDataAsPageWithFilterAndSort(firstNameFilter, lastNameFilter, page, size, sortList, sortOrder.toString());
    }

}
