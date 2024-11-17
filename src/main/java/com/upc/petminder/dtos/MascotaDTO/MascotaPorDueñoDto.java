package com.upc.petminder.dtos.MascotaDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MascotaPorDueñoDto {   //Lista todas las mascotas por dueño
    private Long id;
    private String nombre;
    private String especie;
    private String raza;
    private Integer edad;
}
