package br.edu.pds.piloto.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name="usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Preencha o campo Nome")
    private String nome;

    @Column
    @NotBlank(message = "Preencha o campo Senha")
    private String senha;


}
