/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.controladores;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import snu.entidades.Integrante;
import snu.entidades.Sexo;
import snu.entidades.TipoIntegrante;

/**
 *
 * @author Washington Luis
 */
public class SNU {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("SNUPU");
        EntityManager em = emf.createEntityManager();
        
        IntegranteJpaController integranteController = new IntegranteJpaController(emf);
        
      Integrante integrante = new Integrante("Washington Luis de Souza Ramos",
                Sexo.MASCULINO, 
                new Date(91, 2, 5), 
                23, 
                "(31) 35670739", 
                "(31) 86167574", 
                null,
                "Rua Ipanema, 18 - Urca", 
                TipoIntegrante.GUITARRISTA_SOLO,
                TipoIntegrante.GUITARRISTA_BASE);
               
        integranteController.create(integrante);
        
        System.out.println(integranteController.findIntegrante(1L).toString());
       
    }

}
