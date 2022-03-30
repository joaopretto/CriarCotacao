import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class TesteGoogle {
	
	private WebDriver driver;
	private cotacaoPage page;
	private BufferedReader br;
	
	@BeforeEach
	public void Inicializar() {
		System.setProperty("webdriver.chrome.driver","Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://52.201.113.49:8084/CTFLLogan-webapp/login.jsf");
		driver.manage().window().maximize();
		page = new cotacaoPage(driver);
		
	}
	
	@AfterEach
	public void finalizar() throws InterruptedException{
		Thread.sleep(2000);
		driver.quit();
	}
	
	@Test
	public void teste() throws InterruptedException, Exception{
		
		/******* LOGIN COMO ADM **********/
		
		page.setLogin("adm.demo");
		page.setSenha("jae98nju7");
		page.entrarADM();
		
		/******* BUSCAR PELO USUÁRIO ********/
		
		Thread.sleep(2000);
		page.setNome("joao.simples");
		page.buscarCli();
		
		Thread.sleep(2000);
		Assert.assertTrue("JOAO COMPRA SIMPLES", true);
		page.entrarCli();
		
		
		/******* MUDANDO DE TELA *********/
		
		String identAtual = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for(String actual: handles) {
			if(!actual.equalsIgnoreCase(identAtual)) {
				driver.switchTo().window(actual);
			}
		}
		
		/******* CRIANDO COTAÇÃO *********/
		
		Thread.sleep(2000);
		driver.switchTo().frame(0);
		page.criarCotacao();
		driver.switchTo().defaultContent();
		
		Thread.sleep(2000);
		page.disableAll();
		Thread.sleep(3000);
		page.selectForn("PANPHARMA");
		
		page.tituloCotacao("Teste pt1");
		page.selectData();
		page.calendario();
		page.escolhaData("29");
		page.horaVencimento("2000");
		page.condPagamento("À vista");
		page.cadastrarCotacao();
		
		/********** SELECIONAR PRODUTO E ENVIAR COTAÇÃO ************/
		
		Thread.sleep(2000);
		page.radioButton();
		Thread.sleep(4000);
		page.carregarListaProd("C:\\Users\\João Pretto\\Desktop\\Backup\\arquivos txt\\Cotacao_100.txt");
		
		Thread.sleep(4000);
		WebElement botao = driver.findElement(By.xpath("//*[@id=\"novaCotacao\"]//div//input[contains(@class, 'botao3')]"));
		String texto = botao.getAttribute("onclick");
		String idCotacao = texto.split("idCotacao: ")[1].split(" ")[0];
		System.out.println("Id da Cotação: " + idCotacao);
		
		Thread.sleep(2000);
		page.confirmar();
		Thread.sleep(2000);
		page.enviar();
		
		Thread.sleep(2000);
		FileInputStream stream = new FileInputStream("C:\\Users\\João Pretto\\Desktop\\Backup\\arquivos txt\\Cotacao_100.txt");
		InputStreamReader reader = new InputStreamReader(stream);
		br = new BufferedReader(reader);
		String itens = "";
		String ean = null;
		for(String linha = br.readLine(); linha != null; linha = br.readLine()){
			float preco, precoFabrica, valorSemST;
			float inicio = 1F;
			float total = 100F;
			preco = inicio + new Random().nextFloat()* (total - inicio);
			precoFabrica = inicio + new Random().nextFloat()* (total - inicio);
			valorSemST = inicio + new Random().nextFloat()* (total - inicio);
			
			ean = linha.split(";")[0];
			if(!itens.equals("")) {
				itens += ",";
			}
			itens +=  "			{\r\n"
				      + "            \"analisePreco\": 1,\r\n"
				      + "            \"codigoBarras\": \""+ean+"\",\r\n"
				      + "            \"controlePreco\": \"L\",\r\n"
				      + "            \"descontoInformado\": 5,\r\n"
				      + "            \"preco\": "+preco+",\r\n"
				      + "            \"precoFabrica\": "+precoFabrica+",\r\n"
				      + "            \"qtde\": 10,\r\n"
				      + "            \"qtdeCaixa\": 10,\r\n"
				      + "            \"tipoEmbalagem\": \"U\",\r\n"
				      + "            \"tipoLista\": \"P\",\r\n"
				      + "            \"valorSemST\": "+valorSemST+",\r\n"
				      + "             \"minimoUnidades\": \"\"\r\n"
				      + "          }";
		}
		
		String json = "{\r\n"
					+ "    \"cnpjFornecedor\": \"01206820000520\",\r\n"
					+ "    \"codigoCotacao\": \""+idCotacao+"\",\r\n"
					+ "    \"minimoFaturamento\": 10.00,\r\n"
					+ "    \"validade\": \"31032022\",\r\n"
					+ "    \"versaoArquivo\": \"4.2\",\r\n"
					+ "    \"prazoPagamento\": 7,\r\n"
					+ "    \"codigoCondicaoPagamento\": \"V14\",\r\n"
					+ "    \"filiais\": [\r\n"
					+ "        {\r\n"
					+ "            \"atende\": \"S\",\r\n"
					+ "            \"cnpj\": \"79965407000103\",\r\n"
					+ "            \"motivo\": \"sucesso\"\r\n"
					+ "        }\r\n"
					+ "    ],\r\n"
					+ "    \"itens\": ["+ itens +"]}";
		
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost request = new HttpPost("http://54.242.230.161:8888/resposta/cliente/responder");
		request.setHeader("Content-Type", "application/json");
		request.setEntity(new StringEntity(json));
		CloseableHttpResponse response = client.execute(request);
		
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println("Status code: " + statusCode);
	}
}