package com.parcial.dos.parcialdos.account.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.parcial.dos.parcialdos.account.dto.AccountOwnerBalanceDTO;
import com.parcial.dos.parcialdos.account.dto.AccountRequestDTO;
import com.parcial.dos.parcialdos.account.dto.AccountRespuestaDTO;
import com.parcial.dos.parcialdos.account.service.IAccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final IAccountService service;

    public AccountController(IAccountService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AccountRespuestaDTO> create(
        @RequestBody AccountRequestDTO request
    ) {
        AccountRespuestaDTO dto = service.crearNuevaCuenta(request);
        URI location = URI.create("/api/accounts/" + dto.getId());
        return ResponseEntity.created(location).body(dto);
    }

    @GetMapping
    public ResponseEntity<List<AccountRespuestaDTO>> getAll() {
        AccountRespuestaDTO[] array = service.obtenerTodasLasCuentas();
        List<AccountRespuestaDTO> list = Arrays.asList(array);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountRespuestaDTO> getById(
        @PathVariable Long id
    ) {
        AccountRespuestaDTO dto = service.buscarCuentaPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(
        @PathVariable Long id,
        @RequestBody AccountRequestDTO request
    ) {
        service.actualizarSaldoCuenta(id, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
        @PathVariable Long id
    ) {
        service.eliminarCuentaPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/number/{numeroCuenta}")
    public ResponseEntity<AccountOwnerBalanceDTO> getByNumeroCuenta(
        @PathVariable String numeroCuenta
    ) {
        AccountOwnerBalanceDTO dto = service.buscarPorNumeroDeCuenta(numeroCuenta);
        return ResponseEntity.ok(dto);
    }
}
