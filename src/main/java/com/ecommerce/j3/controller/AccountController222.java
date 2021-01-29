package com.ecommerce.j3.controller;

import com.ecommerce.j3.domain.entity.Account;
import com.ecommerce.j3.domain.entity.Address;
import com.ecommerce.j3.service.AccountService;
import com.ecommerce.j3.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
@Controller
@RequiredArgsConstructor

@Slf4j
public class AccountController222 {
    private final AccountService accountService;
    private  final AddressService addressService;
    @GetMapping(value = "/accounts/new")
    public String createForm(Model model) {
        model.addAttribute("accountForm", new AccountForm());
        return "createAccountForm";
    }
    @PostMapping(value = "/accounts/new")
    public String create(@Valid AccountForm form, BindingResult result) {
        if (result.hasErrors()) {
            return "createAccountForm";
        }
        Account account = Account.builder()
                .email(form.getEmail())
                .passwordHash(form.getPassword())
                .registeredAt(LocalDateTime.now())
                .build();

        log.info("account set ok");

        accountService.join(account);  // em.persist(account)
        log.info("acc join ok");

        Address address = Address.createAddress(account,form.getAddress(),form.getZipcode());
        addressService.save(address);
        account.getAddresses().add(address);
        log.info(account.getAddresses().get(0).getRoadAddress());
        return "redirect:/";
    }
}