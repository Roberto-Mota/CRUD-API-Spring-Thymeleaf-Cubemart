package br.com.mvc.cubemart.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mvc.cubemart.model.Pedido;
import br.com.mvc.cubemart.model.StatusPedido;
import br.com.mvc.cubemart.repository.PedidoRepository;

@Controller
@RequestMapping("/home")
public class HomeController {

    // @PersistenceContext
    // private EntityManager entityManager;
    // Vamos organizar esse EM numa pasta para evitar manter tantas dependências em
    // um mesmo local

    @Autowired
    private PedidoRepository repository;

    @GetMapping("")
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

    @GetMapping("/{status}")
    public String status(@PathVariable("status") String status, Model model) {
        List<Pedido> pedidos = repository.findByStatus(StatusPedido.valueOf(status.toUpperCase()));
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status", status); // Adicionar essa variavel para o model para adicionar o "active" do nav
                                              // lá no HTML
        return "home";
    }

    // Forma de tratar exception específicas:
    @ExceptionHandler(IllegalArgumentException.class)
    public String onErrorUsuarioBuscaPathInexistente() {
        return "redirect:/home";
    }
    /*
     * No caso do redirect o Spring MVC executará o redirecionamento client-side.
     * Isto é, ele devolve uma resposta HTTP para pedir uma nova requisição para a
     * URL /home.
     * Também existe o redirecionamento server-side, mas devemos usar o prefixo
     * forward.
     * Nesse caso, o fluxo volta apenas para o Front-Controller do Spring MVC e ele
     * chama a nova action. Enquanto o redirecionamento client-side causa uma nova
     * requisição, o server-side continua dentro da mesma requisição HTTP.
     * 
     * Nesse exemplo, o melhor seria usar redirect, pois estamos trabalhando com uma
     * requisição POST, seguindo a regra: "always redirect after post".
     */

}