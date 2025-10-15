package com.parcial.dos.parcialdos.account.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parcial.dos.parcialdos.account.dto.AccountOwnerBalanceDTO;
import com.parcial.dos.parcialdos.account.dto.AccountRequestDTO;
import com.parcial.dos.parcialdos.account.dto.AccountResponseDTO;
import com.parcial.dos.parcialdos.account.entity.Account;
import com.parcial.dos.parcialdos.account.repository.AccountRepository;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository repository;

    @Override
    public AccountResponseDTO create(AccountRequestDTO request) {
        Account account = new Account(request.getNumeroCuenta(), request.getDueno(), request.getBalanceActual(), true);
        Account saved = repository.save(account);
        return mapToResponseDTO(saved);
    }

    @Override
    public List<AccountResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(this::mapToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO getById(Long id) {
        Account account = repository.findById(id).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        return mapToResponseDTO(account);
    }

    @Override
    public String update(Long id, AccountRequestDTO request) {
        Account account = repository.findById(id).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        BigDecimal oldBalance = account.getBalance();
        account.setBalance(request.getBalanceActual());
        repository.save(account);
        return "La cuenta " + account.getAccountNumber() + " fue actualizada: balanceAnterior=" + oldBalance + ", balanceActual=" + request.getBalanceActual();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public AccountOwnerBalanceDTO findByNumeroCuenta(String numeroCuenta) {
        Account account = repository.findByAccountNumber(numeroCuenta);
        if (account == null) {
            throw new RuntimeException("Cuenta no encontrada");
        }
        return new AccountOwnerBalanceDTO(account.getOwnerName(), account.getBalance());
    }

    private AccountResponseDTO mapToResponseDTO(Account account) {
        return new AccountResponseDTO(
                account.getId(),
                account.getAccountNumber(),
                account.getOwnerName(),
                account.getBalance(),
                account.getActive()
        );
    }
}