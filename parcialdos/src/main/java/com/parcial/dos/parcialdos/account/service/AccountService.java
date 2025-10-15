package com.parcial.dos.parcialdos.account.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parcial.dos.parcialdos.account.dto.AccountOwnerBalanceDTO;
import com.parcial.dos.parcialdos.account.dto.AccountRequestDTO;
import com.parcial.dos.parcialdos.account.dto.AccountRespuestaDTO;
import com.parcial.dos.parcialdos.account.entity.Account;
import com.parcial.dos.parcialdos.account.repositorio.AccountRepositorio;

@Service
public class AccountService implements IAccountService {

    private final AccountRepositorio repositorio;

    @Autowired
    public AccountService(AccountRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public AccountRespuestaDTO crearNuevaCuenta(AccountRequestDTO request) {
        Account account = new Account(
            request.getNumeroCuenta(),
            request.getDueno(),
            request.getBalanceActual(),
            true
        );
        Account saved = repositorio.save(account);
        return AccountRespuestaDTO.createInstance(
            saved.getId(),
            saved.getAccountNumber(),
            saved.getOwnerName(),
            saved.getBalance(),
            saved.getActive()
        );
    }

    @Override
    public AccountRespuestaDTO[] obtenerTodasLasCuentas() {
        Account[] accounts = repositorio.findAll().toArray(new Account[0]);
        AccountRespuestaDTO[] result = new AccountRespuestaDTO[accounts.length];
        for (int i = 0; i < accounts.length; i++) {
            result[i] = AccountRespuestaDTO.createInstance(
                accounts[i].getId(),
                accounts[i].getAccountNumber(),
                accounts[i].getOwnerName(),
                accounts[i].getBalance(),
                accounts[i].getActive()
            );
        }
        return result;
    }

    @Override
    public AccountRespuestaDTO buscarCuentaPorId(Long id) {
        Account account = repositorio.findById(id).orElse(null);
        if (account == null) {
            throw new RuntimeException("Cuenta no encontrada");
        }
        return AccountRespuestaDTO.createInstance(
            account.getId(),
            account.getAccountNumber(),
            account.getOwnerName(),
            account.getBalance(),
            account.getActive()
        );
    }

    @Override
    public String actualizarSaldoCuenta(Long id, AccountRequestDTO request) {
        Account account = repositorio.findById(id).orElse(null);
        if (account == null) {
            throw new RuntimeException("Cuenta no encontrada");
        }
        BigDecimal oldBalance = account.getBalance();
        BigDecimal newBalance = request.getBalanceActual();
        account.setBalance(newBalance);
        repositorio.save(account);
        return String.format(
            "La cuenta %s fue actualizada: balanceAnterior=%s, balanceActual=%s",
            account.getAccountNumber(),
            oldBalance,
            newBalance
        );
    }

    @Override
    public void eliminarCuentaPorId(Long id) {
        repositorio.deleteById(id);
    }

    @Override
    public AccountOwnerBalanceDTO buscarPorNumeroDeCuenta(String numeroCuenta) {
        Account account = repositorio.findByAccountNumber(numeroCuenta);
        if (account == null) {
            throw new RuntimeException("Cuenta no encontrada");
        }
        return AccountOwnerBalanceDTO.createInstance(
            account.getOwnerName(),
            account.getBalance()
        );
    }
}
