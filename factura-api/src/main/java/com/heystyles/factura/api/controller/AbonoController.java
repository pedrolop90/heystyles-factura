package com.heystyles.factura.api.controller;

import com.heystyles.common.response.Responses;
import com.heystyles.common.types.BaseResponse;
import com.heystyles.common.types.IdResponse;
import com.heystyles.factura.api.service.AbonoService;
import com.heystyles.factura.core.domain.Abono;
import com.heystyles.factura.core.dto.AbonoListResponse;
import com.heystyles.factura.core.dto.AbonoResponse;
import com.heystyles.factura.core.filter.AbonoFilter;
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

@RequestMapping(value = "/abonos")
@RestController
@Api(value = "Abono Controller",
        description = "Controller para el manejo de los Abonos.")
public class AbonoController {

    @Autowired
    private AbonoService abonoService;

    @ApiOperation(value = "Permite Crear un Abono en la base de datos.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Abono Creado.")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdResponse> insert(
            @NotNull @Valid @RequestBody Abono abono) {
        return Responses.responseEntity(new IdResponse(abonoService.insert(abono)));
    }

    @ApiOperation(value = "Permite actualizar un Abono en la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Abono Actualizada."),
            @ApiResponse(code = 404, message = "Abono no encontrada.")
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> update(@NotNull @Valid @RequestBody Abono abono) {
        abonoService.update(abono);
        return Responses.successEntity("Actualizacion correcta");
    }

    @ApiOperation(value = "Permite Eliminar un Abono de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Abono Eliminado."),
            @ApiResponse(code = 404, message = "Abono no encontrado.")
    })
    @DeleteMapping(value = "/{abonoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> delete(
            @NotNull @PathVariable(name = "abonoId") Long abonoId) {
        abonoService.delete(abonoId);
        return Responses.successEntity("Eliminado correcto");
    }

    @ApiOperation(value = "Permite Buscar un Abono de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Abono Encontrada."),
            @ApiResponse(code = 404, message = "Abono no encontrada.")
    })
    @GetMapping(value = "/{abonoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AbonoResponse> getAbono(
            @NotNull @PathVariable(name = "abonoId") Long abonoId) {
        return Responses.responseEntity(new AbonoResponse(abonoService.getAbono(abonoId)));
    }

    @ApiOperation(value = "Permite Listar todos las Abonos, dado un filtro de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Abonos Encontradas."),
            @ApiResponse(code = 404, message = "Abonos no encontradas.")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AbonoListResponse> getAbonos(AbonoFilter abonoFilter) {
        return Responses.responseEntity(abonoService.getFilter(abonoFilter));
    }

}
