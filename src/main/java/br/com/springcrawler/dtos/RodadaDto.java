package br.com.springcrawler.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class RodadaDto {

    private String data;
    private String hora;
    private String local;

    private String mandanteNome;
    private String mandanteEscudoUrl;
    private Integer mandantePlacar;

    private String visitanteNome;
    private String visitanteEscudoUrl;
    private Integer visitantePlacar;
}
