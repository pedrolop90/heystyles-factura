package com.heystyles.factura.api.controller;

import com.heystyles.common.response.Responses;
import com.heystyles.factura.api.service.GestionProductoService;
import com.heystyles.factura.core.domain.GestionProductoExtended;
import com.heystyles.factura.core.dto.GestionProductoExtendedListResponse;
import com.heystyles.factura.core.dto.GestionProductoExtendedResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping(value = "/gestion-producto")
@RestController
@Api(value = "Controller de Gestion de Productos",
        description = "Controller para el maneje de las Gestiones de Productos.")
public class GestionProductoController {

    @Autowired
    private GestionProductoService gestionProductoService;

    @ApiOperation(value = "Permite Listar todas las Marcas Producto dado una facturaId de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Marcas Producto Encontradas."),
            @ApiResponse(code = 404, message = "Marcas Producto no encontradas.")
    })
    @GetMapping(value = "{facturaId}/extended", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GestionProductoExtendedListResponse> getMarcasProductos(
            @NotNull @PathVariable(name = "facturaId") Long facturaId) {
        List<GestionProductoExtended> gestionProductoExtendeds = gestionProductoService
                .getGestionProductoExtendedByFacturaId(facturaId);
        return Responses.responseEntity(new GestionProductoExtendedListResponse(gestionProductoExtendeds));
    }

    @ApiOperation(value = "Permite buscar la gestion de producto dado un id.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Marcas Producto Encontradas."),
            @ApiResponse(code = 404, message = "Marcas Producto no encontradas.")
    })
    @GetMapping(value = "{gestionProductoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GestionProductoExtendedResponse> getGestionProducto(
            @NotNull @PathVariable(name = "gestionProductoId") Long gestionProductoId) {
        GestionProductoExtended gestionProductoExtended = gestionProductoService
                .getGestionProductoExtended(gestionProductoId);
        return Responses.responseEntity(new GestionProductoExtendedResponse(gestionProductoExtended));
    }
}
