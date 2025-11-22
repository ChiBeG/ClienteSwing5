package main;

import adapters.infrastructure.opencep.OpenCEPFactory;
import adapters.infrastructure.persistence.DBConnection;
import adapters.infrastructure.persistence.SQLDAOFactory;
import adapters.input.ui.cadastrocliente.CadastroClientePrt;
import adapters.input.ui.cadastrocliente.CadastroClienteView;
import services.application.cep.CEPService;
import services.application.cep.ServicoCEPFactory;
import services.application.cliente.CadastroClienteCtrl;
import services.application.uf.UFService;
import services.domain.persistence.DAOFactory;
import services.domain.persistence.RepositoryPool;

import java.io.File;

public class Main {

    private static final String DB_PATH = "../../locadora.db";

	public static void main(String[] args) {
        // Verifica se o arquivo SQLite existe
        if (! new File(DB_PATH).exists()) {
            System.out.printf("Erro: arquivo '%s' não encontrado!", DB_PATH);
            return;
        }

        // Define a URL de conexão com o BD
        DBConnection.setUrl("jdbc:sqlite:" + DB_PATH);

        // Testa se o BD está no ar
        try {
            DBConnection.get();
            DBConnection.close();
        } catch(Exception e) {
            System.out.println("Erro: problemas na conexão com o BD");
            System.exit(1);
        }

        // Cria a fábrica de DAOs e injeta na fábrica da aplicação
        var factory = new SQLDAOFactory();
        DAOFactory.register(factory);
        
        // Inicializa o pool de repositórios (maxIdle, reapIntervalSeconds)
        RepositoryPool.setup(5, 60);

		// Cria a fábrica do serviço de busca do endereço pelo CEP
		ServicoCEPFactory cepServiceFactory = new OpenCEPFactory();
		
		// Cria o serviço da UF
		var ufService = new UFService();
		
		// Cria a view injetando o serviço da UF
		var view = new CadastroClienteView(ufService);
	
		// Cria o serviço de CEP injetando o serviço de busca do endereço pelo CEP
		var cepService = new CEPService(cepServiceFactory.cria());
		
		// Cria o controller do serviço de cadastro de clientes
		var controller = new CadastroClienteCtrl();
		
		// Cria o presenter, injetando a view e o controller
		new CadastroClientePrt(view, controller, cepService);
		
		// Abre a view
		view.setVisible(true);
	}
}
