package br.edu.pds.piloto.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="duplas")
@Data
public class Dupla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nomeDupla;

    @ManyToOne
    private Usuario primeiro;

    @ManyToOne
    private Usuario segundo;
}
