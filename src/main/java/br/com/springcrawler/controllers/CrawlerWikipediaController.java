package br.com.springcrawler.controllers;

import br.com.springcrawler.services.impl.CrawlerWikipediaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/covid")
public class CrawlerWikipediaController {

    @Autowired
    private CrawlerWikipediaServiceImpl crawlerService;

    @GetMapping("data")
    public ResponseEntity<?> getCovidData() {
        return ResponseEntity.ok(crawlerService.retrieveCovidData());
    }

}
