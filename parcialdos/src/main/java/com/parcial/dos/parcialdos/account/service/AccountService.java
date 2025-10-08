package com.parcial.dos.parcialdos.account.service;

import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parcial.dos.parcialdos.account.dto.AccountRequestDTO;
import com.parcial.dos.parcialdos.account.dto.AccountResponseDTO;
import com.parcial.dos.parcialdos.account.entity.Account;
import com.parcial.dos.parcialdos.account.repository.AccountRepository;
import com.parcial.dos.parcialdos.account.dto.AccountOwnerBalanceDTO;

@Service
@Transactional
public class AccountService implements IAccountService {

    private final AccountRepository repo;

    public AccountService(AccountRepository repo) {
        this.repo = repo;
    }

    private AccountResponseDTO toDTO(Account a) {
        return new AccountResponseDTO(a.getId(), a.getAccountNumber(), a.getOwnerName(), a.getBalance(), a.getActive());
    }

    private Account fromRequest(AccountRequestDTO r) {
        BigDecimal bal = r.getBalanceActual() == null ? BigDecimal.ZERO : r.getBalanceActual();
        return new Account(r.getNumeroCuenta(), r.getDueno(), bal, true);
    }

    @Override
    public AccountResponseDTO create(AccountRequestDTO request) {
        Account acc = fromRequest(request);
        Account saved = repo.save(acc);
        return toDTO(saved);
    }

    @Override
    public List<AccountResponseDTO> getAll() {
        return repo.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public AccountResponseDTO getById(Long id) {
        return repo.findById(id).map(this::toDTO).orElse(null);
    }

    @Override
    public String update(Long id, AccountRequestDTO request) {
        return repo.findById(id).map(existing -> {
            // solo actualizamos balanceActual
            java.math.BigDecimal oldBalance = existing.getBalance();
            java.math.BigDecimal newBalance = request.getBalanceActual() == null ? oldBalance : request.getBalanceActual();
            existing.setBalance(newBalance);
            repo.save(existing);
            return String.format("La cuenta %s fue actualizada: balanceAnterior=%s, balanceActual=%s", existing.getAccountNumber(), oldBalance.toString(), newBalance.toString());
        }).orElse("Cuenta no encontrada");
    }

    @Override
    public void delete(Long id) {
        repo.findById(id).ifPresent(acc -> repo.delete(acc));
    }

    @Override
    public AccountOwnerBalanceDTO findByNumeroCuenta(String numeroCuenta) {
        return repo.findByAccountNumber(numeroCuenta)
                .map(a -> new AccountOwnerBalanceDTO(a.getOwnerName(), a.getBalance()))
                .orElse(null);
    }
}
