package br.ce.wcaquino.tasks.prod;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.Duration;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HealthCheckIT {

	@Test
	public void healthCheck() throws MalformedURLException {
		AbstractDriverOptions cap = new ChromeOptions();
		WebDriver driver = new RemoteWebDriver(URL.of(URI.create("http://localhost:4444/wd/hub"), null), cap);
		try {
			driver.navigate().to("http://172.23.32.1:9999/tasks/");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			String version = driver.findElement(By.id("version")).getText();
			Assert.assertTrue(version.startsWith("build"));
			
		} finally {
			driver.quit();
		}
		
	}
}
