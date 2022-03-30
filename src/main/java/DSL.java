import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL {
	
	private WebDriver driver;
	
	public DSL(WebDriver driver) {
		this.driver = driver;
	}
	
	/******************************************/
	/************* ENTRAR NO ADM **************/
	/******************************************/
	
	public void login(String id_campo, String usuario) {
		driver.findElement(By.id(id_campo)).sendKeys(usuario);
	}
	public void senha(String id_campo, String senha) {
		driver.findElement(By.id(id_campo)).sendKeys(senha);
	}
	public void logar(String id_click) {
		driver.findElement(By.id(id_click)).click();
	}
	
	/****************************************************/
	/************* BUSCAR PELO USUÁRIO ******************/
	/****************************************************/
	
	public void buscar(String xpath_campo, String nome_usuario) {
		driver.findElement(By.xpath(xpath_campo)).sendKeys(nome_usuario);
	}
	public void pesquisar(String id_click) {
		driver.findElement(By.id(id_click)).click();
	}
	public void entrar(String xpath_click) {
		driver.findElement(By.xpath(xpath_click)).click();
	}
	
	/***************************************************/
	/************** CRIANDO COTAÇÃO ********************/
	/***************************************************/
	
	public void criarCotacao(String xpath_click) {
		driver.findElement(By.xpath(xpath_click)).click();
	}
	public void disableAll(String id_click) {
		driver.findElement(By.id(id_click)).click();
	}
	public void selectForn(String xpath_fornecedor) {
		driver.findElement(By.xpath(xpath_fornecedor)).click();
	}
	public void tituloCot(String xpath_titulo, String titulo) {
		driver.findElement(By.xpath(xpath_titulo)).sendKeys(titulo);
	}
	/************ ESCOLHENDO DATA ****************/
	public void selectData(String id_date) {
		driver.findElement(By.id(id_date)).click();
	}
	public void verificaData(String xpath_data) {
		driver.findElements(By.xpath(xpath_data));
	}
	public void escolherDia(String xpath_dia) {
		driver.findElement(By.xpath(xpath_dia)).click();
	}
	/*********************************************/
	public void horario(String id_hora, String horas) {
		driver.findElement(By.id(id_hora)).sendKeys(horas);
	}
	public void condPagamento(String id_condPag, String valor) {
		WebElement selectElement = driver.findElement(By.id(id_condPag));
		Select selectObjeto = new Select(selectElement);
		selectObjeto.selectByVisibleText(valor);
	}
	public void cadastrarCot(String id_click) {
		driver.findElement(By.id(id_click)).click();
	}
	
	/********** SELECIONANDO PRODUTO E ENVIANDO COTACAO **********/
	public void radioButton(String xpath_radio) {
		driver.findElement(By.xpath(xpath_radio)).click();
	}
	public void listaProd(String xpath, String caminhoArquivo) {
		WebElement uploadElement = driver.findElement(By.xpath(xpath));
		uploadElement.sendKeys(caminhoArquivo);
	}
	public void confirmar(String id_confirm) {
		driver.findElement(By.id(id_confirm)).click();
	}
	public void enviar(String id_send) {
		driver.findElement(By.id(id_send)).click();
	}
}
