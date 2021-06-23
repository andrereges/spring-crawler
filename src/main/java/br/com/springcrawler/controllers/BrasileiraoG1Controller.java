package br.com.springcrawler.controllers;

import br.com.springcrawler.services.BrasileiraoG1Service;
import br.com.springcrawler.services.impl.BrasileiraoG1ServiceImpl;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/brasileirao")
public class BrasileiraoG1Controller {

    private BrasileiraoG1Service service;

    @GetMapping("/chrome/rodadas")
    public ResponseEntity<?> getRodadasByChrome() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/storage/drivers/chromedriver");
        System.setProperty("webdriver.chrome.whitelistedIps", "");

//        ChromeOptions chromeOptions = new ChromeOptions();
//        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
//        desiredCapabilities.setBrowserName("chrome");
//
//        desiredCapabilities.setPlatform(Platform.LINUX);
//
//        chromeOptions.addArguments(
//                "start-maximized",
//                "--verbose",
//                "--headless",
//                "--disable-web-security",
//                "--ignore-certificate-errors",
//                "--allow-running-insecure-content",
//                "--allow-insecure-localhost",
//                "--no-sandbox",
//                "--no-startup-window",
//                "--allowed-ips",
//                "--disable-gpu"
//        );
//
//        chromeOptions.setExperimentalOption("excludeSwitches",
//                Arrays.asList("disable-popup-blocking"));
//
//        chromeOptions.merge(desiredCapabilities);

        service = new BrasileiraoG1ServiceImpl(new ChromeDriver());
        return ResponseEntity.ok(service.getRodadas());
    }

    @GetMapping("/firefox/rodadas")
    public ResponseEntity<?> getRodadasByFirefox() {
        System.setProperty("webdriver.gecko.driver","src/main/resources/storage/drivers/geckodriver");
        System.setProperty("webdriver.gecko.whitelistedIps", "");

//        FirefoxOptions firefoxOptions = new FirefoxOptions();
//        DesiredCapabilities desiredCapabilities = DesiredCapabilities.firefox();
//        desiredCapabilities.setBrowserName("firefox");
//
//        desiredCapabilities.setPlatform(Platform.LINUX);
//
//        firefoxOptions.addArguments(
//                "start-maximized",
//                "--verbose",
//                "--headless",
//                "--disable-web-security",
//                "--ignore-certificate-errors",
//                "--allow-running-insecure-content",
//                "--allow-insecure-localhost",
//                "--no-sandbox",
//                "--no-startup-window",
//                "--allowed-ips",
//                "--disable-gpu"
//        );

//        firefoxOptions.merge(desiredCapabilities);
        service = new BrasileiraoG1ServiceImpl(new FirefoxDriver());
        return ResponseEntity.ok(service.getRodadas());
    }
}
