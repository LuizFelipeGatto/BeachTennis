package br.edu.pds.piloto.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name="estados")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank(message = "Preencha o campo Estado")
    private String nome;

    @Column
    @Length(min = 2, max = 2, message = "O nome deverá ter no máximo {max} caracteres")
    private String sigla;

}
