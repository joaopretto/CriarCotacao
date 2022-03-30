import org.openqa.selenium.WebDriver;

public class cotacaoPage {

	private DSL dsl;

	public cotacaoPage(WebDriver driver) {
		dsl = new DSL(driver);
	}
	
	/************************************/
	/*********** ENTRAR ADM *************/
	/************************************/
	
	public void setLogin(String login) {
		dsl.login("frmLogin:username", login);
	}
	public void setSenha(String senha) {
		dsl.senha("frmLogin:password", senha);
	}
	public void entrarADM() {
		dsl.logar("frmLogin:loginButton");
	}
	
	/****************************************************/
	/************* BUSCAR PELO USUÁRIO ******************/
	/****************************************************/
	
	public void setNome(String nome) {
		dsl.buscar("//*[@id=\"pesquisarUsuarios:pesquisa\"]/tbody/tr/td/input", nome);
	}
	public void buscarCli() {
		dsl.pesquisar("pesquisarUsuarios:btnPesquisar");
	}
	public void entrarCli() {
		dsl.entrar("//*[@id=\"pesquisarUsuarios:dtUsuarios:tb\"]//tr//td[7]//a");
	}
	
	/***************************************************/
	/************** CRIANDO COTAÇÃO ********************/
	/***************************************************/
	
	public void criarCotacao() {
		dsl.criarCotacao("//*[@id=\"root\"]/div/div/div/div[1]/div[@class=\"jss7\"]");
	}
	public void disableAll() {
		dsl.disableAll("novaCotacao:selecionaRepresentantes");
	}
	public void selectForn(String fornecedor) {
		dsl.selectForn("//*[@id=\"novaCotacao:arvoreRepresentantes\"]//td[contains(text(), '"+fornecedor+"')]/input");
	}
	public void tituloCotacao(String titulo) {
		dsl.tituloCot("//*[@id=\"novaCotacao:titulo\"]", titulo);
	}
	/************* SELECIONANDO DATA ******************/
	public void selectData() {
		dsl.selectData("novaCotacao:vencimentoInputDate");
	}
	public void calendario() {
		dsl.verificaData("//*[@id=\"novaCotacao:vencimento\"]/tbody");
	}
	public void escolhaData(String data) {
		dsl.escolherDia("//*[@id=\"novaCotacao:vencimento\"]//tbody//tr//td[text() = '"+data+"']");
	}
	/**************************************************/
	public void horaVencimento(String horas) {
		dsl.horario("novaCotacao:hora", "2000");
	}
	public void condPagamento(String pagamento) {
		dsl.condPagamento("novaCotacao:condicaoPagamento", pagamento);
	}
	public void cadastrarCotacao() {
		dsl.cadastrarCot("novaCotacao:j_id756");
	}
	
	/*************************************************************/
	/********** SELECIONANDO PRODUTO E ENVIANDO COTACAO **********/
	/*************************************************************/
	
	public void radioButton() {
		dsl.radioButton("//*[@id=\"novaCotacao:j_id795:0\"]");
	}
	public void carregarListaProd(String lista) {
		dsl.listaProd("//*[@id=\"novaCotacao:file\"]/table/tbody/tr/td/div/div/div/input", lista);
	}
	public void confirmar() {
		dsl.confirmar("novaCotacao:j_id1103");
	}
	public void enviar() {
		dsl.enviar("novaCotacao:j_id1293");
	}
}
