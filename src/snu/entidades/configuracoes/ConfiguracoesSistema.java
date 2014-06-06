/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.entidades.configuracoes;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entidade que gerencia as variáveis de configuração geral do sistema.
 *
 * @author Washington Luis
 */
@Entity
@Table(name = "sys")
public class ConfiguracoesSistema implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double versao;

    @Column(name = "diretorio_sgbd")
    private String diretorioSGBD;

    @Column(columnDefinition = "LONGTEXT", name = "template_descricao_email")
    private String templateDescricaoEmail;
    
    @Column(name="preferencia_Csus_Dbem", columnDefinition = "bit(1) default 0")
    private Boolean preferenciaCsusDbem;
    
    @Column(name="preferencia_Dsus_Ebem", columnDefinition = "bit(1) default 0")
    private Boolean preferenciaDsusEbem;
    
    @Column(name="preferencia_Fsus_Gbem", columnDefinition = "bit(1) default 0")
    private Boolean preferenciaFsusGbem;
    
    @Column(name="preferencia_Gsus_Abem", columnDefinition = "bit(1) default 0")
    private Boolean preferenciaGsusAbem;
    
    @Column(name="preferencia_Asus_Bbem", columnDefinition = "bit(1) default 0")
    private Boolean preferenciaAsusBbem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getVersao() {
        return versao;
    }

    public void setVersao(Double versao) {
        this.versao = versao;
    }

    public String getDiretorioSGBD() {
        return diretorioSGBD;
    }

    public void setDiretorioSGBD(String diretorioSGBD) {
        this.diretorioSGBD = diretorioSGBD;
    }

    public String getTemplateDescricaoEmail() {
        return templateDescricaoEmail;
    }

    public void setTemplateDescricaoEmail(String templateDescricaoEmail) {
        this.templateDescricaoEmail = templateDescricaoEmail;
    }

    public Boolean isPreferenciaCsusDbem() {
        return preferenciaCsusDbem;
    }

    public void setPreferenciaCsusDbem(Boolean preferenciaCsusDbem) {
        this.preferenciaCsusDbem = preferenciaCsusDbem;
    }

    public Boolean isPreferenciaDsusEbem() {
        return preferenciaDsusEbem;
    }

    public void setPreferenciaDsusEbem(Boolean preferenciaDsusEbem) {
        this.preferenciaDsusEbem = preferenciaDsusEbem;
    }

    public Boolean isPreferenciaFsusGbem() {
        return preferenciaFsusGbem;
    }

    public void setPreferenciaFsusGbem(Boolean preferenciaFsusGbem) {
        this.preferenciaFsusGbem = preferenciaFsusGbem;
    }

    public Boolean isPreferenciaGsusAbem() {
        return preferenciaGsusAbem;
    }

    public void setPreferenciaGsusAbem(Boolean preferenciaGsusAbem) {
        this.preferenciaGsusAbem = preferenciaGsusAbem;
    }

    public Boolean isPreferenciaAsusBbem() {
        return preferenciaAsusBbem;
    }

    public void setPreferenciaAsusBbem(Boolean preferenciaAsusBbem) {
        this.preferenciaAsusBbem = preferenciaAsusBbem;
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
        if (!(object instanceof ConfiguracoesSistema)) {
            return false;
        }
        ConfiguracoesSistema other = (ConfiguracoesSistema) object;
        return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "ConfiguracoesSistema{" + "id=" + id + ", templateDescricaoEmail=" + templateDescricaoEmail + '}';
    }
}
