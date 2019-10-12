package br.alfa.arduino.controller;

import br.alfa.arduino.component.ControlePorta;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;

@Controller
public class HomeController {

    private ControlePorta arduino;

    @PostConstruct
    private void initArduino() {
        arduino = new ControlePorta("/dev/ttyUSB0", 9600); //Linux - porta e taxa de transmissão
    }

    @GetMapping(value = "/teste", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> teste(){
        return new ResponseEntity<>("Deu certo!", HttpStatus.OK);
    }

    @GetMapping(value = "/enviarComando", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    private ResponseEntity<String> enviarComando(@RequestParam(value = "comando") Integer comando) {
        arduino.enviaDados(comando);

        return new ResponseEntity<>("Comando enviado!", HttpStatus.OK);
    }
}