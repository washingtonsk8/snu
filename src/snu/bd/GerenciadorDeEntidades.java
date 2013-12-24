/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snu.bd;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Gerencia em GERAL as entidades do sistema
 *
 * @author Washington Luis
 */
public class GerenciadorDeEntidades {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("SNUPU");
    private static GerenciadorDeEntidades instancia;

    public GerenciadorDeEntidades() {
    }

    public EntityManagerFactory getFabrica() {
        return this.emf;
    }

    public static synchronized GerenciadorDeEntidades getInstancia() {
        if (instancia == null) {
            instancia = new GerenciadorDeEntidades();
        }
        return instancia;
    }

}
