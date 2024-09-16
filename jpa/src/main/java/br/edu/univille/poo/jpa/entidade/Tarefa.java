package br.edu.univille.poo.jpa.entidade;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Tarefa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String titulo;
    private String descricao;
    @Column(nullable = false)
    private Boolean finalizado = false;
    @Column(nullable = false)
    private LocalDate dataPrevistaFinalizacao;
    private LocalDate dataFinalizacao;
}
