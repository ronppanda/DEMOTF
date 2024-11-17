package com.upc.petminder.repositories;

import com.upc.petminder.entities.Mascota;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

//Muestra el total de mascotas por tipo de especie existente en el sistema
public interface MascotaRepository extends JpaRepository<Mascota, Long> {
    @Query(value="SELECT COUNT(DISTINCT especie) AS cantidad_mascotas_especie\n" +
            "            FROM mascota;", nativeQuery = true)
     Long TotalMascotasPorEspecie();

    //Muestra las mascotas por usuario
    @Query(value = "SELECT m.id AS id, " +
            "       m.nombre AS nombre, " +
            "       m.especie AS especie, " +
            "       m.raza AS raza, " +
            "       m.edad AS edad " +
            "FROM mascota m " +
            "WHERE m.usuario_id = :usuarioId", nativeQuery = true)
    List<Tuple> MascotasPorUsuario(@Param("usuarioId") Long usuario_id);

}
