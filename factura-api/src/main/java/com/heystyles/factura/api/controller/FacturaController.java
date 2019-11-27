package com.heystyles.factura.api.controller;

import com.heystyles.common.response.Responses;
import com.heystyles.common.types.BaseResponse;
import com.heystyles.common.types.IdResponse;
import com.heystyles.factura.api.service.FacturaService;
import com.heystyles.factura.api.service.GestionProductoService;
import com.heystyles.factura.core.domain.FacturaExtended;
import com.heystyles.factura.core.dto.FacturaExtendedListResponse;
import com.heystyles.factura.core.dto.FacturaExtendedResponse;
import com.heystyles.factura.core.dto.FacturaRequest;
import com.heystyles.producto.core.dto.MarcaProductoDto;
import com.heystyles.producto.core.dto.MarcaProductoListResponse;
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
import java.util.List;

@RequestMapping(value = "/factura")
@RestController
@Api(value = "Controller de Factura",
        description = "Controller para el maneje de las Facturas.")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @Autowired
    private GestionProductoService gestionProductoService;

    @ApiOperation(value = "Permite Crear una Factura en la base de datos.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Factura Creada.")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IdResponse> insert(
            @NotNull @Valid @RequestBody FacturaRequest re) {
        return Responses.responseEntity(new IdResponse(facturaService.insert(re)));
    }

    @ApiOperation(value = "Permite actualizar una Factura en la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Factura Actualizada."),
            @ApiResponse(code = 404, message = "Factura no encontrada.")
    })
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> update(@NotNull @Valid @RequestBody FacturaRequest request) {
        facturaService.update(request);
        return Responses.successEntity("Actualizacion correcta");
    }

    @ApiOperation(value = "Permite Eliminar una Factura de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Factura Eliminada."),
            @ApiResponse(code = 404, message = "Factura no encontrada.")
    })
    @DeleteMapping(value = "/{facturaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> delete(
            @NotNull @PathVariable(name = "facturaId") Long facturaId) {
        facturaService.delete(facturaId);
        return Responses.successEntity("Eliminado correcto");
    }

    @ApiOperation(value = "Permite Buscar una Factura de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Factura Encontrada."),
            @ApiResponse(code = 404, message = "Factura no encontrada.")
    })
    @GetMapping(value = "/{facturaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FacturaExtendedResponse> getFactura(
            @NotNull @PathVariable(name = "facturaId") Long facturaId) {
        return Responses.responseEntity(new FacturaExtendedResponse(facturaService.getFactura(facturaId)));
    }

    @ApiOperation(value = "Permite Listar todas las Facturas de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Facturas Encontradas."),
            @ApiResponse(code = 404, message = "Facturas no encontradas.")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FacturaExtendedListResponse> getFacturas() {
        List<FacturaExtended> facturas = facturaService.getFacturas();
        return Responses.responseEntity(new FacturaExtendedListResponse(facturas));
    }

    @ApiOperation(value = "Permite Listar todas las Marcas Producto dado una facturaId de la base de datos")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Marcas Producto Encontradas."),
            @ApiResponse(code = 404, message = "Marcas Producto no encontradas.")
    })
    @GetMapping(value = "{facturaId}/marcas-productos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MarcaProductoListResponse> getMarcasProductos(
            @NotNull @PathVariable(name = "facturaId") Long facturaId) {
        List<MarcaProductoDto> marcasProductos = gestionProductoService.findMarcaProductoByFacturaId(facturaId);
        return Responses.responseEntity(new MarcaProductoListResponse(marcasProductos));
    }
}
