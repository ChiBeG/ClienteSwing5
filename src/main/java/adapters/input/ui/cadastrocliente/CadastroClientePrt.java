package adapters.input.ui.cadastrocliente;

import services.application.cep.CEPPort;
import services.application.cliente.CadastroClientePort;
import services.application.cliente.CadastroClienteRequest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Classe PRESENTER do cadastro de clientes
 */
public class CadastroClientePrt implements ActionListener {

	CadastroClienteView view;
	CadastroClientePort cadastroService;
	CEPPort cepService;

    public CadastroClientePrt(CadastroClienteView view, CadastroClientePort cadastroService, CEPPort cepService) {
    	// PRESENTER referencia a VIEW
        this.view = view;
        
        // VIEW referencia o PRESENTER
        view.setPresenter(this);
        
        // PRESENTER referencia o CONTROLLER DO UC
        this.cadastroService = cadastroService;
        
        // PRESENTER referencia o serviço do CEP
        this.cepService = cepService;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	
    	if (e.getSource() == view.getBtSalvar())
    		criarCliente();
    	else
    		buscarEnderecoPorCEP();
    }
    
    /**
     * Busca o endereço pelo CEP
     */
    private void buscarEnderecoPorCEP() {
    	
    	try {
    		// Converte o CEP
    		Integer cep = Integer.parseInt(view.getCEP());
    		
    		var result = cepService.buscaPorCEP(cep);
        	
    		// Se resultado é nulo, então não conseguiu executar o serviço
    		if (result == null) {
    			view.notifyCEPError();
    		}
    		else if (result.isSuccess()) {
        		var dados = result.payload();
        		 
        		view.notifyCEPExist(dados);
        	}
        	else if (result.isFailure())
        		view.notifyCEPNotExist();
    	} catch(Exception ex) {
    		// CEP inválido
    		view.notifyCEPIsInvalid();
    	}
	}

    /**
     * Cadastra um novo cliente
     */
	private void criarCliente() {
    	
    	Long cpf;
    	LocalDate dtNasc;
    	Integer numero, cep, ddd, numeroTel;
    	
    	// Converte os dados da VIEW para o DTO
    	
    	// Converte o CPF
    	try {
    		cpf = Long.parseLong(view.getCpf());
    	} catch(Exception ex) {
    		cpf =  null;
    	}
    	
    	// Converte a data de nascimento
    	try {
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
    		dtNasc = LocalDate.parse(view.getDtNasc(), formatter);
    	} catch(Exception ex) {
    		dtNasc = null;
    	}
    	
    	// Converte o número
    	try {
    		numero = Integer.parseInt(view.getNumero());
    	} catch(Exception ex) {
    		numero =  null;
    	}
    	
    	// Converte o CEP
    	try {
    		cep = Integer.parseInt(view.getCEP());
    	} catch(Exception ex) {
    		cep =  null;
    	}
    	
    	// Converte o DDD
		if (! view.getDDD().isBlank())
			try {
				ddd = Integer.parseInt(view.getDDD());
			} catch(Exception ex) {
	    		ddd =  0;
	    	}
		else
			ddd = null;
    	
    	// Converte o número de telefone
    	try {
    		numeroTel = Integer.parseInt(view.getNumeroTel());
    	} catch(Exception ex) {
    		numeroTel =  null;
    	}
    	
    	// Cria o DTO 
    	var dto = new CadastroClienteRequest(cpf, 
    			                             view.getNome(), 
    			                             dtNasc, 
    			                             view.getLogradouro(), 
    			                             numero, 
    			                             view.getComplemento(), 
    			                             view.getBairro(), 
    			                             view.getCidade(),
    			                             cep, 
    			                             view.getSiglaUF(),
    			                             ddd, 
    			                             numeroTel);
    	
    	// Solicita ao controller do UC que cadastre o novo cliente
    	var result = cadastroService.criarCliente(dto);
    	
    	// Avisa a VIEW sobre o sucesso ou fracasso da operação
    	if (result.isFailure())
    		view.notifyErrors(result.payload());
    	else
    		view.notifySuccess();
    }
   
}
