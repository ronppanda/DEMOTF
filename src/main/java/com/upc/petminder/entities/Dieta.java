package com.upc.petminder.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "dieta")
@Data
@NoArgsConstructor
public class Dieta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Column(name = "indicaciones", nullable = false, length = 300)
    private String indicaciones;
    @CreationTimestamp
    private LocalDate fecha_creacion;

    @OneToMany(mappedBy = "dieta", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<RecomendacionDieta> recomendaciones_dieta;

    public Dieta(String nombre, String indicaciones, LocalDate fecha_creacion) {
        this.nombre = nombre;
        this.indicaciones = indicaciones;
        this.fecha_creacion = fecha_creacion;
    }
}
