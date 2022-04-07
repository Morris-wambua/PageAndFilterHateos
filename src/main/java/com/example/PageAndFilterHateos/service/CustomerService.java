package com.example.PageAndFilterHateos.service;

import com.example.PageAndFilterHateos.entity.Customer;
import com.example.PageAndFilterHateos.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> fetchCustomerDataList(){
        return customerRepository.findAll();
    }

    public List<Customer> fetchFilteredCustomerDataAsList(String firstNameFilter, String lastnameFilter){
        return customerRepository.findByFirstNameLikeAndLastNameLike(firstNameFilter, lastnameFilter);
    }

    public Page<Customer> fetchFilteredCustomerDataAsPage(String firstNameFilter, String lastNameFilter, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return customerRepository.findByFirstNameLikeAndLastNameLike(firstNameFilter, lastNameFilter, pageable);
    }
}
