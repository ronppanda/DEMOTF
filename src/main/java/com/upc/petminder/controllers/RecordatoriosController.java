package com.upc.petminder.controllers;

import com.upc.petminder.dtos.RecordatoriosDTO.RecordatorioPorMascotaDto;
import com.upc.petminder.dtos.RecordatoriosDTO.RecordatoriosDto;
import com.upc.petminder.dtos.RecordatoriosDTO.RecordatoriosPorPeriodoDeFechasDto;
import com.upc.petminder.dtos.RecordatoriosDTO.RecordatoriosPorTipoDto;
import com.upc.petminder.serviceinterfaces.RecordatoriosService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class RecordatoriosController {
    final RecordatoriosService recordatoriosService;

    public RecordatoriosController(RecordatoriosService recordatoriosService) {
        this.recordatoriosService = recordatoriosService;
    }

    @PostMapping("registrar-recordatorio")
    public ResponseEntity<RecordatoriosDto> create(@RequestBody RecordatoriosDto recordatoriosDto) {
        return new ResponseEntity<>(recordatoriosService.save(recordatoriosDto), HttpStatus.CREATED);
    }
    //Mostrar recordatprios entre un periodo de fechas
    @GetMapping("recordatorio-periodo")
    public ResponseEntity<List<RecordatoriosPorPeriodoDeFechasDto>> ListaRecordatoriosEnPeriodo (
            @RequestParam("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ){
        return ResponseEntity.ok(recordatoriosService.recordatoriosPorPeriodo(from, to));
    }
    //Mostrar recordatorios por tipo de recordatorio
    @GetMapping("recordatorio-tipo")
    public ResponseEntity<List<RecordatoriosPorTipoDto>> ListaRecordatoriosPorTipo(
            @RequestParam("tipoRecordatorioId") Long tipoRecordatorioId
    ) {
        return ResponseEntity.ok(recordatoriosService.recordatoriosPorTipo(tipoRecordatorioId));
    }

    //Recordatorios por Mascota
    @GetMapping("listar-recordatorio-por-mascota")
    public ResponseEntity<List<RecordatorioPorMascotaDto>> ListaRecordatoriosPorMascota(
            @RequestParam("mascotaId") Long mascotaId
    ) {
        return ResponseEntity.ok(recordatoriosService.recordatoriosPorMascota(mascotaId));
    }
}
