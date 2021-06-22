package br.com.springcrawler.services.impl;

import br.com.springcrawler.dtos.RodadaDto;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class G1ServiceImpl {

    private static final String URL = "https://ge.globo.com/futebol/brasileirao-serie-a/";

    public static ChromeDriver getWebdriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/storage/drivers/chromedriver");
        System.setProperty("webdriver.chrome.whitelistedIps", "");

        ChromeOptions chromeOptions = new ChromeOptions();
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setBrowserName("chrome");

        desiredCapabilities.setPlatform(Platform.LINUX);

        chromeOptions.addArguments(
//                "start-maximized",
//                "--verbose",
//                "--headless",
//                "--disable-web-security",
//                "--ignore-certificate-errors",
//                "--allow-running-insecure-content",
//                "--allow-insecure-localhost",
//                "--no-sandbox",
//                "--no-startup-window",
//                "--allowed-ips"
//                "--disable-gpu"
        );

        chromeOptions.setExperimentalOption("excludeSwitches",
                Arrays.asList("disable-popup-blocking"));

        chromeOptions.merge(desiredCapabilities);

        return new ChromeDriver(chromeOptions);
    }

    public static Map<String, List<RodadaDto>> getRodadas() {
        ChromeDriver driver = getWebdriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(URL);

        SortedMap<String, List<RodadaDto>> listRodadaMap = new TreeMap<>();

        WebElement section = driver.findElementById("classificacao__wrapper");

        int numeroRodada;

        do {
            String numeroRodadaString = section.findElements(By.className("lista-jogos__navegacao--rodada")).get(0).getText().replaceAll("\\D+","");
            numeroRodada = !numeroRodadaString.isEmpty() ? Integer.parseInt(numeroRodadaString) : 1;
            List<RodadaDto> listRodada = new ArrayList<>();

            for (int i = 0; section.findElements(By.className("lista-jogos__jogo")).size() > i; i++) {

                String data = section.findElements(By.className("jogo__informacoes--data")).get(i).getText();
                String hora = section.findElements(By.className("jogo__informacoes--hora")).get(i).getText();
                String local = section.findElements(By.className("jogo__informacoes--local")).get(i).getText();

                String mandanteNome = section.findElements(By.className("equipes__escudo--mandante")).get(i).getAttribute("title");
                String mandanteEscudoUrl = section.findElements(By.className("equipes__escudo--mandante")).get(i).getAttribute("src");
                String mandantePlacar = section.findElements(By.className("placar-box__valor--mandante")).get(i).getText().replaceAll("\\D+","");
                Integer mandantePlacarInt = !mandantePlacar.isEmpty() ? Integer.parseInt(mandantePlacar) : null;

                String visitanteNome = section.findElements(By.className("equipes__escudo--visitante")).get(i).getAttribute("title");
                String visitanteEscudoUrl = section.findElements(By.className("equipes__escudo--visitante")).get(i).getAttribute("src");
                String visitantePlacar = section.findElements(By.className("placar-box__valor--visitante")).get(i).getText().replaceAll("\\D+","");
                Integer visitantePlacarInt = !visitantePlacar.isEmpty() ? Integer.parseInt(visitantePlacar) : null;

                listRodada.add(RodadaDto.builder()
                        .data(data)
                        .hora(hora)
                        .local(local)
                        .mandanteNome(mandanteNome)
                        .mandanteEscudoUrl(mandanteEscudoUrl)
                        .mandantePlacar(mandantePlacarInt)
                        .visitanteNome(visitanteNome)
                        .visitanteEscudoUrl(visitanteEscudoUrl)
                        .visitantePlacar(visitantePlacarInt)
                        .build());
            }

            listRodadaMap.put("Rodada " + numeroRodadaString, listRodada);

            section.findElements(By.className("lista-jogos__navegacao--seta-esquerda")).get(0).click();
        } while (numeroRodada > 1);

        driver.quit();

        return sortMapByKey(listRodadaMap);
    }

    public static Map<String, List<RodadaDto>> sortMapByKey(Map<String, List<RodadaDto>> unsortedMap) {
        Map<String, List<RodadaDto>> sortedMap = new LinkedHashMap<>();

        unsortedMap.entrySet().stream()
                .sorted(Map.Entry.<String, List<RodadaDto>>comparingByKey().reversed())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        return sortedMap;
    }

}
