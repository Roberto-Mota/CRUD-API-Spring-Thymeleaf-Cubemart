package br.com.mvc.cubemart.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.mvc.cubemart.model.Pedido;
import br.com.mvc.cubemart.repository.PedidoRepository;

@Controller
public class HomeController {

    // @PersistenceContext
    // private EntityManager entityManager;
    // Vamos organizar esse EM numa pasta para evitar manter tantas dependências em
    // um mesmo local

    @Autowired
    private PedidoRepository repository;

    @GetMapping("/home")
    public String home(Model model) {

        /*------------------------------------------------------------------
         * Criação de um exemplo de Objeto:
         */
        // Pedido pedido = new Pedido();
        // pedido.setNomeProduto("Produto genérico");
        // pedido.setUrlImagem(null);
        // pedido.setUrlProduto(null);
        // pedido.setDescricao("Descrição genérica sobre o produto");
        // List<Pedido> pedidos = Arrays.asList(pedido);
        // -------------------------------------------------------------------

        List<Pedido> pedidos = repository.findAll(); // recuperarTodosOsPedidos();

        // AGAIN: pra não colocar como atributo do HttpServletRequest, jogamos no model
        model.addAttribute("pedidos", pedidos); // Se atentar aos parâmetros

        return "home";
    }
    /*
     * Uma forma de aglutinar o model e view:
     * 
     * @GetMapping("/home")
     * public ModelAndView home(Assim nao precisa mais de parametros) {
     * List<Pedido> pedidos = repository.findAll();
     * ModelAndView mv = new ModelAndView("home");
     * mv.addObject("pedidos", pedidos);
     * return mv;
     * }
     */
}
