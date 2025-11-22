package adapters.input.ui.cadastrocliente;

import model.ErroCliente;
import services.application.cep.DadosEndereco;
import services.application.uf.UFPort;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Classe VIEW do cadastro de clientes
 */
public class CadastroClienteView extends JFrame {

	private final JPanel painel;
	
	private final JTextField tfCPF;
	private final JTextField tfNome;
	private final JTextField tfDtNasc;
    private final JTextField tfLogradouro;
    private final JTextField tfNumero;
    private final JTextField tfComplemento;
    private final JTextField tfBairro;
    private final JTextField tfCidade;
    private final JTextField tfCEP;
    private final JTextField tfDDD;
    private final JTextField tfNumeroTel;
    private final JComboBox<String> cbUF;
    
	private final JLabel lbErroCPF;
	private final JLabel lbErroNome;
	private final JLabel lbErroDtNasc;
    private final JLabel lbErroLogradouro;
    private final JLabel lbErroNumero;
    private final JLabel lbErroComplemento;
    private final JLabel lbErroBairro;
    private final JLabel lbErroCidade;
    private final JLabel lbErroCEP;
    private final JLabel lbErroTelefone;
    private final JLabel lbErroUF;
	
	private final JButton btSalvar;
	private final JButton btBuscar;

	private final Border bordaErro;
	private final Border bordaNormal;
	
	private final Map<String, String> ufMap;
	private final String[] ufs;

	/**
     * Cria a view e seus componentes
     */
	public CadastroClienteView(UFPort ufService) {
		super("Cadastro de Clientes - versão 6");

		ufMap = ufService.getUFs();

        // Recupera a lista com as UFs, ordena e acrescenta "- Selecione uma UF -" na
        // primeira posição
        List<String> descricaoUF = new ArrayList<>(ufMap.values());
        descricaoUF.sort((s1, s2) -> s1.compareTo(s2));
        descricaoUF.add(0, "- Selecione uma UF -");
        ufs = descricaoUF.toArray(new String[0]);
		
		// Cria os componentes da UI
		painel = new JPanel(new GridBagLayout());

		tfCPF = new JTextField(11);
		tfNome = new JTextField(30);
		tfDtNasc = new JTextField(10);
        tfLogradouro = new JTextField(50);
        tfNumero = new JTextField(8);
        tfComplemento = new JTextField(20);
        tfBairro = new JTextField(30);
        tfCidade = new JTextField(40);
        tfCEP = new JTextField(8);
        tfDDD = new JTextField(3);
        tfNumeroTel = new JTextField(10);
        cbUF = new JComboBox<>(ufs);
        
		lbErroCPF = new JLabel("");
		lbErroNome = new JLabel("");
		lbErroDtNasc = new JLabel("");
        lbErroLogradouro = new JLabel("");
        lbErroNumero = new JLabel("");
        lbErroComplemento = new JLabel("");
        lbErroBairro = new JLabel("");
        lbErroCidade = new JLabel("");
        lbErroCEP = new JLabel("");
        lbErroTelefone = new JLabel("");
        lbErroUF = new JLabel("");
        
		btSalvar = new JButton("Salvar");
		btBuscar = new JButton("Buscar");
		
		cbUF.setSelectedIndex(0);

		lbErroCPF.setForeground(Color.red);
		lbErroNome.setForeground(Color.red);
		lbErroDtNasc.setForeground(Color.red);
        lbErroLogradouro.setForeground(Color.red);
        lbErroNumero.setForeground(Color.red);
        lbErroComplemento.setForeground(Color.red);
        lbErroBairro.setForeground(Color.red);
        lbErroCidade.setForeground(Color.red);
        lbErroCEP.setForeground(Color.red);
        lbErroTelefone.setForeground(Color.red);
        lbErroUF.setForeground(Color.red);
        
		bordaNormal = tfCPF.getBorder();
		bordaErro = BorderFactory.createLineBorder(Color.RED, 2);

		// Posiciona os componentes da UI
		setupWidgets();

		// Define o que vai acontecer com a app quando o usuário pressionar 
		// o botão de fechar a janela: EXIT
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Adiciona o painel na janela
		add(painel, BorderLayout.NORTH);

		// Redimensiona a janela para ajustar aos componentes
		pack();
	}

	public void setPresenter(CadastroClientePrt presenter) {
		// O PRESENTER vai tratar os eventos dos botões
		btSalvar.addActionListener(presenter);
		btBuscar.addActionListener(presenter);
	}

	public String getCpf() {
		return tfCPF.getText();
	}

	public String getNome() {
		return tfNome.getText();
	}

	public String getDtNasc() {
		return tfDtNasc.getText();
	}
	
    public String getLogradouro() {
        return tfLogradouro.getText();
    }

    public String getNumero() {
        return tfNumero.getText();
    }

    public String getComplemento() {
        return tfComplemento.getText();
    }
    
    public String getBairro() {
        return tfBairro.getText();
    }
    
    public String getCidade() {
        return tfCidade.getText();
    }
    
    public String getCEP() {
        return tfCEP.getText();
    }
    
    public String getDDD() {
        return tfDDD.getText();
    }
    
    public String getNumeroTel() {
        return tfNumeroTel.getText();
    }

    public String getSiglaUF() {
        int pos = cbUF.getSelectedIndex();

        if (pos > 0) {
            String descricao = ufs[pos];
            var uf = ufMap.entrySet().stream().filter(entry -> entry.getValue().equals(descricao) ).findFirst().orElse(null);
            return uf == null ? null : uf.getKey();
        }
        else
            return null;
    }

    public JButton getBtSalvar() {
		return btSalvar;
	}

	/**
     * Apresenta os erros de cadastramento ao usuários
     * 
     * @param errors Lista de erros
     */
	public void notifyErrors(List<ErroCliente> errors) {
		clearErrors();

		for (ErroCliente erro : errors) {
			switch (erro) {
				case CPF_INVALIDO -> setCPFInvalido();
				case CPF_JA_EXISTE -> setCPFJaExiste();
				case NOME_INVALIDO -> setNomeInvalido();
				case DATA_INVALIDA -> setDataInvalida();
				case IDADE_INVALIDA -> setIdadeInvalida();
				case LOGRADOURO_INVALIDO -> setLogradouroInvalido(); 
				case NUMERO_INVALIDO -> setNumeroInvalido(); 
				case BAIRRO_INVALIDO -> setBairroInvalido(); 
				case CIDADE_INVALIDA -> setCidadeInvalida(); 
				case CEP_INVALIDO -> setCEPInvalido(); 
				case UF_INVALIDA -> setUFInvalida(); 
				case DDD_INVALIDO -> setDDDInvalido(); 
				case NUMERO_TEL_INVALIDO -> setNumeroTelInvalido();
				default -> {}
			}
		}

		// Redimensiona a janela para ajustar aos componentes
		pack();
	}

	/**
     * Apresenta a mensagem de sucesso na operação
     */
	public void notifySuccess() {
		clearForm();
		pack();

		JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Cadastro de Clientes",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Notifica que não foi possível recuperar o endereço pelo CEP.
	 * A partir daí, o usuário pode escolher digitar o endereço.
	 */
	public void notifyCEPError() {
		clearAddressError();
        
		if (JOptionPane.showConfirmDialog(null, "Não foi possível recuperar os dados do CEP.\nDeseja digitar os dados?", "Cadastro de Clientes",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			blockAdddress(false);
			btBuscar.setEnabled(false);
		}
		
		pack();
	}
	
	/**
	 * Notifica que o CEP digitado para busca é inválido
	 */
	public void notifyCEPIsInvalid() {
		clearAddressForm();
		setCEPInvalido();
		pack();
	}

	/**
	 * Apresenta os dados do endereço correspondente ao CEP.
	 * 
	 * @param dadosEndereco Dados do endereço recuperados a partir do CEP
	 */
	public void notifyCEPExist(DadosEndereco dadosEndereco) {
		clearAddressError();
        
    	var descricao = ufMap.get(dadosEndereco.uf());
    	
		tfLogradouro.setText(dadosEndereco.logradouro());
		tfBairro.setText(dadosEndereco.bairro());
		tfCidade.setText(dadosEndereco.cidade());
		cbUF.setSelectedItem(descricao);

    	pack();
	}
	
	/**
	 * Notifica que o CEP informado não existe
	 */
	public void notifyCEPNotExist() {
		clearAddressForm();
		clearAddressError();
		
		if (JOptionPane.showConfirmDialog(null, "CEP não encontrado.\nDeseja digitar os dados?", "Cadastro de Clientes",
				JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
			blockAdddress(false);
			btBuscar.setEnabled(false);
		}
		else
			setCEPInexistente();
		
		pack();
	}

	/**
     * Monta o formulário de cadastro
     */
	private void setupWidgets() {
		GridBagConstraints c = new GridBagConstraints();

		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(2, 5, 2, 5);

        // CPF
        c.gridx = 0;
        c.gridy = 0;
        painel.add(new JLabel("CPF:"), c);

        c.gridx = 1;
        c.gridwidth = 2;
        painel.add(tfCPF, c);
        c.gridwidth = 1;
        
        c.gridx = 3;
        painel.add(lbErroCPF, c);
        
        // Nome
        c.gridx = 0;
        c.gridy = 1;
        painel.add(new JLabel("Nome:"), c);
        
        c.gridx = 1;
        c.gridwidth = 2;
        painel.add(tfNome, c);
        c.gridwidth = 1;
        
        c.gridx = 3;
        painel.add(lbErroNome, c);
        
        // Data de Nascimento
        c.gridx = 0;
        c.gridy = 2;
        painel.add(new JLabel("Dt. nascimento (DDMMAAAA):"), c);
        
        c.gridx = 1;
        c.gridwidth = 2;
        painel.add(tfDtNasc, c);
        c.gridwidth = 1;
        
        c.gridx = 3;
        painel.add(lbErroDtNasc, c);
        
        // CEP
        c.gridx = 0;
        c.gridy = 3;
        painel.add(new JLabel("CEP:"), c);
        
        c.gridx = 1;
        c.gridwidth = 1;
        painel.add(tfCEP, c);
        c.gridx = 2;
        painel.add(btBuscar, c);
        c.gridwidth = 1;
        
        c.gridx = 3;
        painel.add(lbErroCEP, c);
        
        // Logradouro
        c.gridx = 0;
        c.gridy = 4;
        painel.add(new JLabel("Logradouro:"), c);
        
        c.gridx = 1;
        c.gridwidth = 2;
        painel.add(tfLogradouro, c);
        c.gridwidth = 1;
        
        c.gridx = 3;
        painel.add(lbErroLogradouro, c);
        
        // Número
        c.gridx = 0;
        c.gridy = 5;
        painel.add(new JLabel("Número:"), c);
        
        c.gridx = 1;
        c.gridwidth = 2;
        painel.add(tfNumero, c);
        c.gridwidth = 2;
        
        c.gridx = 3;
        painel.add(lbErroNumero, c);
        
        // Complemento
        c.gridx = 0;
        c.gridy = 6;
        painel.add(new JLabel("Complemento:"), c);
        
        c.gridx = 1;
        c.gridwidth = 2;
        painel.add(tfComplemento, c);
        c.gridwidth = 1;
        
        c.gridx = 3;
        painel.add(lbErroComplemento, c);
        
        // Bairro
        c.gridx = 0;
        c.gridy = 7;
        painel.add(new JLabel("Bairro:"), c);
        
        c.gridx = 1;
        c.gridwidth = 2;
        painel.add(tfBairro, c);
        c.gridwidth = 1;
        
        c.gridx = 3;
        painel.add(lbErroBairro, c);
        
        // Cidade
        c.gridx = 0;
        c.gridy = 8;
        painel.add(new JLabel("Cidade:"), c);
        
        c.gridx = 1;
        c.gridwidth = 2;
        painel.add(tfCidade, c);
        c.gridwidth = 1;
        
        c.gridx = 3;
        painel.add(lbErroCidade, c);
        
        // UF
        c.gridx = 0;
        c.gridy = 9;
        painel.add(new JLabel("UF:"), c);
        
        c.gridx = 1;
        c.gridwidth = 2;
        painel.add(cbUF, c);
        c.gridwidth = 1;
        
        c.gridx = 3;
        painel.add(lbErroUF, c);
        
        // DDD e Telefone
        c.gridx = 0;
        c.gridy = 10;
        painel.add(new JLabel("Telefone:"), c);
        
        c.gridx = 1;
        painel.add(tfDDD, c);
        
        c.gridx = 2;
        painel.add(tfNumeroTel, c);
        
        c.gridx = 3;
        painel.add(lbErroTelefone, c);
        
        // Botão
        c.gridx = 1;
        c.gridy = 11;
        c.gridwidth = 2;
        painel.add(btSalvar, c);
        
        blockAdddress(true);
	}

	/**
     * Limpa o formulário
     */
	private void clearForm() {
		clearErrors();
		tfCPF.setText("");
		tfNome.setText("");
		tfDtNasc.setText("");
		
		clearAddressForm();
		
        tfNumero.setText("");
        tfComplemento.setText("");
        tfCEP.setText("");
        tfDDD.setText("");
        tfNumeroTel.setText("");
	}
	
	/**
	 * Limpa somente os campos do endereço
	 */
	private void clearAddressForm() {
		tfLogradouro.setText("");
		tfBairro.setText("");
		tfCidade.setText("");
		cbUF.setSelectedIndex(0);
	}

	/**
     * Limpa os erros do formulário
     */
	private void clearErrors() {
		lbErroCPF.setText("");
		tfCPF.setBorder(bordaNormal);
		lbErroNome.setText("");
		tfNome.setBorder(bordaNormal);
		lbErroDtNasc.setText("");
		tfDtNasc.setBorder(bordaNormal);
		
        lbErroNumero.setText("");
        tfNumero.setBorder(bordaNormal);
        lbErroComplemento.setText("");
        tfComplemento.setBorder(bordaNormal);

        clearAddressError();
        
        lbErroTelefone.setText("");
        tfDDD.setBorder(bordaNormal);
        tfNumeroTel.setBorder(bordaNormal);
	}
	
	/**
	 * Limpa somente os erros do endereço
	 */
	private void clearAddressError() {
		lbErroLogradouro.setText("");
        tfLogradouro.setBorder(bordaNormal);
        lbErroBairro.setText("");
        tfBairro.setBorder(bordaNormal);
        lbErroCidade.setText("");
        tfCidade.setBorder(bordaNormal);
		lbErroCEP.setText("");
        tfCEP.setBorder(bordaNormal);
        lbErroUF.setText("");
        cbUF.setBorder(bordaNormal);
	}

	private void setCPFInvalido() {
		lbErroCPF.setText("Digite um CPF válido");
		tfCPF.setBorder(bordaErro);
	}

	private void setCPFJaExiste() {
		lbErroCPF.setText("Já existe outro cliente com esse CPF");
		tfCPF.setBorder(bordaErro);
	}

	private void setDataInvalida() {
		lbErroDtNasc.setText("Digite uma data de nascimento válida");
		tfDtNasc.setBorder(bordaErro);
	}

	private void setNomeInvalido() {
		lbErroNome.setText("Digite um nome com ao menos 4 caracteres");
		tfNome.setBorder(bordaErro);
	}

	private void setIdadeInvalida() {
		lbErroDtNasc.setText("Cliente deve ter pelo menos 18 anos");
		tfDtNasc.setBorder(bordaErro);
	}
	
	private void setLogradouroInvalido() {
    	lbErroLogradouro.setText("Digite um logradouro");
    	tfLogradouro.setBorder(bordaErro);
    }
    
	private void setNumeroInvalido() {
    	lbErroNumero.setText("Digite um número");
    	tfNumero.setBorder(bordaErro);
    }
    
	private void setBairroInvalido() {
    	lbErroBairro.setText("Digite um bairro");
    	tfBairro.setBorder(bordaErro);
    }
    
	private void setCidadeInvalida() {
    	lbErroCidade.setText("Digite uma cidade");
    	tfCidade.setBorder(bordaErro);
    }
    
	private void setCEPInvalido() {
    	lbErroCEP.setText("Digite um CEP com 8 dígitos");
    	tfCEP.setBorder(bordaErro);
    }
	
	private void setCEPInexistente() {
    	lbErroCEP.setText("CEP não encontrado");
    	tfCEP.setBorder(bordaErro);
    }
    
    private void setUFInvalida() {
    	lbErroUF.setText("Selecione uma UF");
    	cbUF.setBorder(bordaErro);
    }
    
    private void setDDDInvalido() {
    	if (lbErroTelefone.getText().isEmpty())
    		lbErroTelefone.setText("Digite um DDD válido");
    	else
    		lbErroTelefone.setText("Digite um DDD e Telefone válidos");
    	tfDDD.setBorder(bordaErro);
    }
    
    private void setNumeroTelInvalido() {
    	if (lbErroTelefone.getText().isEmpty())
    		lbErroTelefone.setText("Digite um telefone válido");
    	else
    		lbErroTelefone.setText("Digite um DDD e telefone válidos");
    	tfNumeroTel.setBorder(bordaErro);
    }
    
	private void blockAdddress(boolean block) {
		tfLogradouro.setEditable(!block);
		tfBairro.setEditable(!block);
		tfCidade.setEditable(!block);
		cbUF.setEnabled(!block);
	}
}
