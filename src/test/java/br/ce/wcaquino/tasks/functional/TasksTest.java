package br.ce.wcaquino.tasks.functional;

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

public class TasksTest {
	
	@SuppressWarnings("rawtypes")
	public WebDriver acessarAplicacao() throws MalformedURLException {
//		WebDriver driver = new ChromeDriver();
		AbstractDriverOptions cap = new ChromeOptions();
		WebDriver driver;
		driver = new RemoteWebDriver(URL.of(URI.create("http://localhost:4444/wd/hub"), null), cap);
		driver.navigate().to("http://172.23.32.1:8001/tasks/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			//Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever descricao
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!", message);
		} finally {
			//Fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			//Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description", message);
		} finally {
			//Fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			//Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever descricao
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date", message);
		} finally {
			//Fechar o browser
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		try {
			//Clicar em Add Todo
			driver.findElement(By.id("addTodo")).click();
			
			//escrever descricao
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//escrever a data
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
			
			//clicar em salvar
			driver.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String message = driver.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past", message);
		} finally {
			//Fechar o browser
			driver.quit();
		}
	}
}
