package br.com.mvc.cubemart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pedido")
public class PedidoController {
    
    @GetMapping("formulario")
    public String formulario() {
        return "pedido/formulario"; //Se trata, especiicamente, do diretorio dentro de templates
    }
}
