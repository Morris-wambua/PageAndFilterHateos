package com.example.PageAndFilterHateos.service;

import com.example.PageAndFilterHateos.entity.Customer;
import com.example.PageAndFilterHateos.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Page<Customer> fetchCustomerDataAsPageWithFilterAndSort(String firstNameFilter,
                                                                   String lastNameFilter,
                                                                   int page,
                                                                   int size,
                                                                   List<String> sortList,
                                                                   String sortOrder){
        Pageable pageable= PageRequest.of(page, size, Sort.by(CreateSortOrder(sortList, sortOrder)));

        return customerRepository.findByFirstNameLikeAndLastNameLike(firstNameFilter, lastNameFilter, pageable);
    }

    private List<Sort.Order> CreateSortOrder(List<String> sortList, String sortDirection) {

        List<Sort.Order> sorts = new ArrayList<>();
        Sort.Direction direction;

        for(String sort:sortList){
            if(sortDirection !=null){
                direction=Sort.Direction.fromString(sortDirection);
            } else {
                direction=Sort.Direction.DESC;
            }
            sorts.add(new Sort.Order(direction, sort));
        }

        return sorts;
    }
}
