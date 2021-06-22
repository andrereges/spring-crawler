package br.com.springcrawler.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CovidDataDto {
    private String country;
    private String brandUrl;
    private Integer cases;
    private Integer deaths;
    private Integer recoveries;
}
