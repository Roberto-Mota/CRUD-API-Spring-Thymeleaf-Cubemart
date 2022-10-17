package br.com.mvc.cubemart.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.mvc.cubemart.model.Pedido;

@Controller
public class HomeController {
    
    @GetMapping("/home")
    public String home(Model model) {

        /*------------------------------------------------------------------
         * Criação de um exemplo de Objeto:
         */
        Pedido pedido = new Pedido();
        pedido.setNomeProduto("Produto genérico");
        pedido.setUrlImagem(null);
        pedido.setUrlProduto(null);
        pedido.setDescricao("Descrição genérica sobre o produto");
        //-------------------------------------------------------------------

        List<Pedido> pedidos = Arrays.asList(pedido);
        //AGAIN: pra não colocar como atributo do HttpServletRequest, jogamos no model
        model.addAttribute("pedidos", pedidos); //Se atentar aos parâmetros

        return "home";
    }
}
