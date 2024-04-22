package br.com.fiap.simuladospringpjunidades.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "TB_CHEFE",
// UK para garantir que não se tenha mais de um usuário (chefe),
// na mesma unidade com a data fim. Ou seja devemos evitar de ter
// para o mesmo usuário dois registros ativos de chefia para uma unidade.
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_CHEFE_UNIDADE", columnNames = {"USUARIO", "UNIDADE", "DT_FIM"})
        }
)
public class Chefe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_CHEFE")
    @SequenceGenerator(name = "SQ_CHEFE", sequenceName = "SQ_CHEFE", allocationSize = 1)
    @Column(name = "ID_CHEFE")
    private Long id;

    @Column(name = "IS_SUBSTITUTO")
    private Boolean substituto;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "USUARIO",
            referencedColumnName = "ID_USUARIO",
            foreignKey = @ForeignKey(name = "FK_USUARIO_CHEFE"),
            nullable = false
    )
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(
            name = "UNIDADE",
            referencedColumnName = "ID_UNIDADE",
            foreignKey = @ForeignKey(name = "FK_CHEFE_UNIDADE"),
            nullable = false
    )
    private Unidade unidade;

    @Column(name = "DT_INICIO")
    private LocalDateTime inicio;

    @Column(name = "DT_FIM")
    private LocalDateTime fim;

}
