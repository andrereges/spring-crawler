package br.com.springcrawler.services.impl;

import br.com.springcrawler.dtos.CovidDataDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlerWikipediaServiceImpl {

    public List<CovidDataDto> retrieveCovidData() {

        List<CovidDataDto> covidDataList = new ArrayList<>();

        try {
            Document webPage = Jsoup
                    .connect("https://en.wikipedia.org/wiki/COVID-19_pandemic_by_country_and_territory")
                    .get();

            Element tbody = webPage
                    .getElementById("thetable")
                    .getElementsByTag("tbody").get(0);

            Elements rows = tbody
                    .children()
                    .removeClass("sorttop");

            for (Element row : rows) {

                String country = row
                        .getElementsByTag("a").get(0)
                        .text();

                Elements tds = row
                        .getElementsByTag("td");

                if (tds.size() == 4) {
                    String brandUrl = "https:" + row
                            .getElementsByTag("img")
                            .get(0)
                            .attr("src");

                    String cases = tds.get(0).text().replaceAll("\\D+","");
                    Integer casesValue = !cases.isEmpty() ? Integer.parseInt(cases.replaceAll("\\D+","")) : null;

                    String deaths = tds.get(1).text().replaceAll("\\D+","");
                    Integer deathsValue = !deaths.isEmpty() ? Integer.parseInt(deaths.replaceAll("\\D+","")) : null;

                    String recoveries = tds.get(2).text().replaceAll("\\D+","");
                    Integer recoveriesValue = !recoveries.isEmpty() ? Integer.parseInt(recoveries.replaceAll("\\D+","")) : null;

                    covidDataList.add(new CovidDataDto(country, brandUrl, casesValue, deathsValue, recoveriesValue));
                }
            }

            return covidDataList;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            return covidDataList;
        }
    }
}
