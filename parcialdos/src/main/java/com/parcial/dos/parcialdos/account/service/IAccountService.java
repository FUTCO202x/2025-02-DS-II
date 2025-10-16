package com.parcial.dos.parcialdos.account.service;

import com.parcial.dos.parcialdos.account.dto.AccountOwnerBalanceDTO;
import com.parcial.dos.parcialdos.account.dto.AccountRequestDTO;
import com.parcial.dos.parcialdos.account.dto.AccountRespuestaDTO;

public interface IAccountService {

    AccountRespuestaDTO crearNuevaCuenta(AccountRequestDTO request);

    AccountRespuestaDTO[] obtenerTodasLasCuentas();

    AccountRespuestaDTO buscarCuentaPorId(Long id);

    String actualizarSaldoCuenta(Long id, AccountRequestDTO request);

    void eliminarCuentaPorId(Long id);

    AccountOwnerBalanceDTO buscarPorNumeroDeCuenta(String numeroCuenta);
}
