package br.com.springcrawler.services;

import br.com.springcrawler.dtos.RodadaDto;

import java.util.List;
import java.util.Map;

public interface BrasileiraoG1Service {
    Map<String, List<RodadaDto>> getRodadas();
}
