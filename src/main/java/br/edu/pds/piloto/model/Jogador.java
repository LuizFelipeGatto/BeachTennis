package br.edu.pds.piloto.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="jogadores")
@Data
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Usuario usuario;

}
