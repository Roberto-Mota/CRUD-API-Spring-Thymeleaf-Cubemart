package br.com.mvc.cubemart.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.mvc.cubemart.dto.RequisicaoNovoPedido;
import br.com.mvc.cubemart.model.Pedido;
import br.com.mvc.cubemart.repository.PedidoRepository;

@Controller
@RequestMapping("pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;
    
    @GetMapping("formulario")
    public String formulario(RequisicaoNovoPedido requisicao) {
        // Posso brincar cm a requisicao, pode ser importante já adiciona-la a todos os endpoints para evitar futuros erros
        return "pedido/formulario"; //Se trata, especificamente, do diretorio dentro de templates
    }

    @PostMapping("novo")
    public String novo(/*@ModelAttribute("requisicaoNovoPedido") RequisicaoNovoPedido requisicao*/@Valid RequisicaoNovoPedido requisicao, BindingResult result) {
        System.out.println("Descricao: " + requisicao.getDescricao());
        //result.getModel()
        /*
         * @ModelAttribute("tarefa") 

No form():

Tarefa tarefa = new Tarefa();
modelAndView.addObject("tarefa", tarefa);
         */

        if (result.hasErrors()) {
            return "pedido/formulario";
        }

        Pedido pedido = requisicao.toPedido(); //syntax convenção, similar ao mapperObject
        pedidoRepository.save(pedido);

        /*
         * Com o name dos inputs (no html) batendo certinho com os atributos da classe, já sai mapeado certinho
         * o Spring olha o parâmetro name do input, e com isso ele sabe qual atributo é o certo para popular.
         * 
         * A anotação Valid integra o Spring com o pedido de execução de invalidação -?-
         * emp: o Valid liga os pontos com o NotBlank pra já fazer a validação antes de enviar a requisição e depois se emaranhar no back 
         * 
         * Já o bindingResult é o resultado da validação anterior
         */
        return "pedido/formulario";
    }
}
