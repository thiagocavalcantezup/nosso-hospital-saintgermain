package br.com.zup.edu.saintgermain.leito;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import br.com.zup.edu.saintgermain.exceptions.LeitoNaoLivreException;

@Entity
@Table(name = "leitos")
@OptimisticLocking(type = OptimisticLockType.ALL)
@DynamicUpdate
public class Leito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOcupacao status;

    @Column(nullable = false)
    private LocalDateTime criadoEm = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime atualizadoEm = LocalDateTime.now();

    /**
     * @deprecated Construtor de uso exclusivo do Hibernate
     */
    @Deprecated
    public Leito() {}

    public Leito(String titulo) {
        this.titulo = titulo;
        this.status = StatusOcupacao.LIVRE;
    }

    public void reservar() {
        if (!isLivre()) {
            throw new LeitoNaoLivreException("O leito não está livre para ser reservado.");
        }

        atualizadoEm = LocalDateTime.now();
        status = StatusOcupacao.OCUPADO;
    }

    public Long getId() {
        return id;
    }

    public boolean isLivre() {
        return status.equals(StatusOcupacao.LIVRE);
    }

}
