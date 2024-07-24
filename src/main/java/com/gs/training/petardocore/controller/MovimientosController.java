package com.gs.training.petardocore.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.gs.training.petardocore.aspect.ValidatorHeaderAnnotation;
import com.gs.training.petardocore.constant.PetardoCoreConstants;
import com.gs.training.petardocore.service.ResultadoService;

@RestController
@RequestMapping("${basePath}")
public class MovimientosController {

    private final ResultadoService resultadoService;
    
    public MovimientosController(ResultadoService resultadoService) {
        this.resultadoService = resultadoService;
    }

    @ValidatorHeaderAnnotation(required = {PetardoCoreConstants.USUARIO_INTERNO_HEADER})
    @GetMapping("/validaciones")
    public String getValidaciones(@RequestHeader Map<String, String> headers,
                                  @RequestParam(required = true) String idConsumo) {
        // Validación de idConsumo
        if (idConsumo == null || idConsumo.isEmpty()) {
            throw new IllegalArgumentException("idConsumo is required");
        }
        return resultadoService.getStatus();
    }
}
