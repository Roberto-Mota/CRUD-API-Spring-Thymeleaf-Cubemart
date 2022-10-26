package br.com.mvc.cubemart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mvc.cubemart.model.Pedido;
import br.com.mvc.cubemart.model.StatusPedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByStatus(StatusPedido statusPedido);
    
    //JPA com Spring Data usa os NamedQueries -?- para abstrair algumas queries mais comuns
}
