/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.entidades.integrante;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entidade que define um integrante do ministério de música Nova Unção
 *
 * @author Washington Luis
 */
@Entity
public class Integrante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sexo sexo;

    private String email;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_entrada")
    private Date dataEntrada;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @Column(name = "telefone_residencial")
    private String telefoneResidencial;

    @Column(name = "telefone_celular")
    private String telefoneCelular;

    @Column(name = "telefone_comercial")
    private String telefoneComercial;

    private String endereco;

    @Column(name = "funcao_primaria", nullable = false)
    @Enumerated(EnumType.STRING)
    private FuncaoIntegrante funcaoPrimaria;

    @Column(name = "funcao_secundaria")
    @Enumerated(EnumType.STRING)
    private FuncaoIntegrante funcaoSecundaria;

    private String ministerio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getTelefoneComercial() {
        return telefoneComercial;
    }

    public void setTelefoneComercial(String telefoneComercial) {
        this.telefoneComercial = telefoneComercial;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public FuncaoIntegrante getFuncaoPrimaria() {
        return funcaoPrimaria;
    }

    public void setFuncaoPrimaria(FuncaoIntegrante funcaoPrimaria) {
        this.funcaoPrimaria = funcaoPrimaria;
    }

    public FuncaoIntegrante getFuncaoSecundaria() {
        return funcaoSecundaria;
    }

    public void setFuncaoSecundaria(FuncaoIntegrante funcaoSecundaria) {
        this.funcaoSecundaria = funcaoSecundaria;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getMinisterio() {
        return ministerio;
    }

    public void setMinisterio(String ministerio) {
        this.ministerio = ministerio;
    }

    public String getPrimeiroNome() {
        return this.nome.split(" ")[0];
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Integrante)) {
            return false;
        }
        Integrante other = (Integrante) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "Integrante{" + "id=" + id + ", nome=" + nome + ", sexo=" + sexo + ", email=" + email + ", dataEntrada=" + dataEntrada + ", dataNascimento=" + dataNascimento + ", telefoneResidencial=" + telefoneResidencial + ", telefoneCelular=" + telefoneCelular + ", telefoneComercial=" + telefoneComercial + ", endereco=" + endereco + ", funcaoPrimaria=" + funcaoPrimaria + ", funcaoSecundaria=" + funcaoSecundaria + '}';
    }
}
