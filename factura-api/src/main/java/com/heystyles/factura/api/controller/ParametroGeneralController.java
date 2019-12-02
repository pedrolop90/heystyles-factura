package com.heystyles.factura.api.controller;

import com.heystyles.common.response.Responses;
import com.heystyles.common.types.BaseResponse;
import com.heystyles.common.types.IdResponse;
import com.heystyles.factura.api.service.ParametroGeneralService;
import com.heystyles.factura.core.domain.ParametroGeneral;
import com.heystyles.factura.core.dto.ParametroGeneralListResponse;
import com.heystyles.factura.core.dto.ParametroGeneralResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequestMapping(value = "/parametro-general")
@RestController
@Api(value = "Parametro General Controller",
        description = "Controller para el manejo de los parametros generales")
public class ParametroGeneralController {

    @Autowired
    private ParametroGeneralService parametroGeneralService;

    @ApiOperation(value = "Permite Crear un ParametroGeneral en la base de datos.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ParametroGeneral Creado.")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdResponse> insert(
            @NotNull @Valid @RequestBody ParametroGeneral parametroGeneral) {
        return Responses.responseEntity(new IdResponse(parametroGeneralService.insert(parametroGeneral)));
    }

    @ApiOperation(value = "Permite actualizar un ParametroGeneral en la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ParametroGeneral Actualizada."),
            @ApiResponse(code = 404, message = "ParametroGeneral no encontrada.")
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> update(@NotNull @Valid @RequestBody ParametroGeneral parametroGeneral) {
        parametroGeneralService.update(parametroGeneral);
        return Responses.successEntity("Actualizacion correcta");
    }

    @ApiOperation(value = "Permite Eliminar un ParametroGeneral de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ParametroGeneral Eliminado."),
            @ApiResponse(code = 404, message = "ParametroGeneral no encontrado.")
    })
    @DeleteMapping(value = "/{parametroGeneralId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> delete(
            @NotNull @PathVariable(name = "parametroGeneralId") Long parametroGeneralId) {
        parametroGeneralService.delete(parametroGeneralId);
        return Responses.successEntity("Eliminado correcto");
    }

    @ApiOperation(value = "Permite Buscar un ParametroGeneral de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ParametroGeneral Encontrada."),
            @ApiResponse(code = 404, message = "ParametroGeneral no encontrada.")
    })
    @GetMapping(value = "/{parametroGeneralId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParametroGeneralResponse> getParametroGeneral(
            @NotNull @PathVariable(name = "parametroGeneralId") Long parametroGeneralId) {
        return Responses.responseEntity(new ParametroGeneralResponse(
                parametroGeneralService.getParametroGeneral(parametroGeneralId)));
    }

    @ApiOperation(value = "Permite Listar todos las ParametroGeneral")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ParametroGeneral Encontradas."),
            @ApiResponse(code = 404, message = "ParametroGeneral no encontradas.")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ParametroGeneralListResponse> getParametrosGenerales() {
        return Responses.responseEntity(new ParametroGeneralListResponse(parametroGeneralService.findAll()));
    }


}
