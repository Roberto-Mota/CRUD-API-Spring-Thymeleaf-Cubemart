package br.com.mvc.cubemart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.mvc.cubemart.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    //JPA com Spring Data usa os NamedQueries -?- para abstrair algumas queries mais comuns
}
