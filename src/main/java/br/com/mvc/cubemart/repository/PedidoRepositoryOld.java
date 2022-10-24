package br.com.mvc.cubemart.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.mvc.cubemart.model.Pedido;

// Permitir com o que o Spring gerencie essa classe também
@Repository
public class PedidoRepositoryOld {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Pedido> recuperarTodosOsPedidos() {
        System.out.println("Recuperando todos os pedidos:");
        Query query = entityManager.createQuery("select p from Pedido p", Pedido.class);
        List<Pedido> pedidos = query.getResultList();
        System.out.println(pedidos);
        return pedidos;
    }

}
