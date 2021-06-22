package br.com.springcrawler.controllers;

import br.com.springcrawler.services.impl.G1ServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/g1")
public class G1Controller {

    @GetMapping("rodadas")
    public ResponseEntity<?> getRodadas() {
        return ResponseEntity.ok(G1ServiceImpl.getRodadas());
    }
}
