package com.parcial.dos.parcialdos.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.parcial.dos.parcialdos.account.dto.AccountRequestDTO;
import com.parcial.dos.parcialdos.account.dto.AccountResponseDTO;
import com.parcial.dos.parcialdos.account.dto.AccountOwnerBalanceDTO;
import com.parcial.dos.parcialdos.account.entity.Account;
import com.parcial.dos.parcialdos.account.repository.AccountRepository;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public AccountResponseDTO create(AccountRequestDTO request) {
        Account account = new Account();
        account.setAccountNumber(request.getNumeroCuenta());
        account.setOwnerName(request.getDueno());
        account.setBalance(request.getBalanceActual() != null ? java.math.BigDecimal.valueOf(request.getBalanceActual())
                : java.math.BigDecimal.ZERO);
        account.setActive(true);
        Account saved = accountRepository.save(account);
        AccountResponseDTO response = new AccountResponseDTO(
                saved.getId(),
                saved.getAccountNumber(),
                saved.getOwnerName(),
                saved.getBalance().doubleValue(),
                saved.getActive());
        return response;
    }

    @Override
    public List<AccountResponseDTO> getAll() {
        List<Account> accounts = accountRepository.findAll();
        List<AccountResponseDTO> response = new java.util.ArrayList<>();
        for (Account acc : accounts) {
            response.add(new AccountResponseDTO(
                    acc.getId(),
                    acc.getAccountNumber(),
                    acc.getOwnerName(),
                    acc.getBalance().doubleValue(),
                    acc.getActive()));
        }
        return response;
    }

    @Override
    public AccountResponseDTO getById(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account == null) {
            return null;
        }
        return new AccountResponseDTO(
            account.getId(),
            account.getAccountNumber(),
            account.getOwnerName(),
            account.getBalance().doubleValue(),
            account.getActive()
        );
    }

    @Override
    public String update(Long id, AccountRequestDTO request) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account == null) {
            return null;
        }
        double balanceAnterior = account.getBalance().doubleValue();
        double balanceActual = request.getBalanceActual() != null ? request.getBalanceActual() : balanceAnterior;
        account.setBalance(java.math.BigDecimal.valueOf(balanceActual));
        accountRepository.save(account);
        return String.format("La cuenta %s fue actualizada: balanceAnterior=%.2f, balanceActual=%.2f", account.getAccountNumber(), balanceAnterior, balanceActual);
    }

    @Override
    public boolean delete(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account == null) {
            return false;
        }
        accountRepository.delete(account);
        return true;
    }

    @Override
    public AccountOwnerBalanceDTO findByNumeroCuenta(String numeroCuenta) {
        Account account = accountRepository.findByAccountNumber(numeroCuenta);
        if (account == null) {
            return null;
        }
        AccountOwnerBalanceDTO dto = new AccountOwnerBalanceDTO();
        dto.setDueno(account.getOwnerName());
        dto.setBalanceActual(account.getBalance());
        return dto;
    }
}
