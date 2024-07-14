package com.eazybytes.accounts.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.eazybytes.accounts.constants.AccountsConstants;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountsService;

@Service
public class AccountServiceImpl implements IAccountsService{
	
	private AccountsRepository accountRepository;
	private CustomerRepository customerRepository;
	
	

	public AccountServiceImpl(AccountsRepository accountRepository, CustomerRepository customerRepository) {
		super();
		this.accountRepository = accountRepository;
		this.customerRepository = customerRepository;
	}

	@Override
	public void createAccount(CustomerDto customerDto) {
		
		Customer customer = CustomerMapper.mapToCustomer(customerDto,new Customer());
		Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
		if(optionalCustomer.isPresent()) {
			throw new CustomerAlreadyExistsException("Customer Already Registered with given mobileNumber" + customerDto.getMobileNumber());
		}
		customer.setCreatedAt(LocalDateTime.now());
		customer.setCreatedBy("Rituj");
		Customer savedCustomer = customerRepository.save(customer);
		accountRepository.save(createNewAccount(savedCustomer));
		
		
		
		
	}
	
    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerID(customer.getCustomerID());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Rituj");
        return newAccount;
    }
    
    
	
	
}
