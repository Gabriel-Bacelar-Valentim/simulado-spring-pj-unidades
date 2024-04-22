package br.com.fiap.simuladospringpjunidades.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "TB_UNIDADE")
public class Unidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_UNIDADE")
    @SequenceGenerator(name = "SQ_UNIDADE", sequenceName = "SQ_UNIDADE", allocationSize = 1)
    @Column(name = "ID_UNIDADE")
    private Long id;

    @Column(name = "NM_UNIDADE")
    private String nome;

    @Column(name = "SG_UNIDADE")
    private String sigla;

    @Column(name = "DS_UNIDADE")
    private String descricao;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(
            name = "MACRO",
            referencedColumnName = "ID_UNIDADE",
            foreignKey = @ForeignKey(name = "FK_UNIDADE_MACRO")
    )
    private Unidade macro;

}
