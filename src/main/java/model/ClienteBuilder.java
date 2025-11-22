package model;

import services.domain.persistence.ClienteDTO;
import services.domain.persistence.ClienteRepository;
import services.domain.persistence.RepositoryPool;
import util.Result;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe BUILDER do Cliente e suas partes
 */
public class ClienteBuilder {
	
	private Long cpf;
	private String nome;
	private LocalDate dataNasc;
	private String logradouro;
	private Integer numero;
	private String complemento;
	private String bairro;
	private String cidade;
	private Integer cep;
	private String uf;
	private Integer ddd;
	private Integer numeroTel;
	
	public ClienteBuilder setCpf(Long cpf) {
		this.cpf = cpf;
		return this;
	}
	
	public ClienteBuilder setNome(String nome) {
		this.nome = nome;
		return this;
	}
	
	
	public ClienteBuilder setDataNasc(LocalDate dataNasc) {
		this.dataNasc = dataNasc;
		return this;
	}

	public ClienteBuilder setLogradouro(String logradouro) {
		this.logradouro = logradouro;
		return this;
	}
	
	public ClienteBuilder setNumero(Integer numero) {
		this.numero = numero;
		return this;
	}
	
	public ClienteBuilder setComplemento(String complemento) {
		this.complemento = complemento;
		return this;
	}
	
	public ClienteBuilder setBairro(String bairro) {
		this.bairro = bairro;
		return this;
	}
	
	public ClienteBuilder setCidade(String cidade) {
		this.cidade = cidade;
		return this;
	}
	
	public ClienteBuilder setCEP(Integer cep) {
		this.cep = cep;
		return this;
	}
	
	public ClienteBuilder setUF(String uf) { 
		this.uf = uf;
		return this;
	}
	
	public ClienteBuilder setDDD(Integer ddd) {
		this.ddd = ddd;
		return this;
	}
	
	public ClienteBuilder setNumeroTel(Integer numeroTel) {
		this.numeroTel = numeroTel;
		return this;
	}
	
	/**
	 * Cria o Cliente e suas partes
	 * 
	 * @return Cliente ou uma lista de erros de validação
	 */
	public Result<Cliente, ErroCliente> build()  { 
		// Cria o CPF
		CPF cpf = CPF.of(this.cpf);
		
		// Cria a UF
		UF uf = UF.getBySigla(this.uf);
		
		// Cria o Endereço
		var resultEndereco = Endereco.of(logradouro, numero, complemento, bairro, cidade, cep, uf);
		
		// Cria o Telefone
		var resultTelefone = Telefone.of(ddd, numeroTel);
		
		// Cria o cliente
		var resultCliente  = Cliente.of(cpf, nome, dataNasc, resultEndereco.value(), resultTelefone.value());
		
		List<ErroCliente> erros = new ArrayList<>();
		
		// Cria uma lista única juntando os erros das 3 listas: telefone, endereco e cliente
		if (resultEndereco.errors() != null)
			// Converte ErroEndereco em ErroCliente e adiciona na lista de erros
			erros.addAll(resultEndereco.errors().stream().map(e -> ErroCliente.valueOf(e.name())).toList());
		
		if (resultTelefone.errors() != null)
			// Converte ErroTelefone em ErroCliente e adiciona na lista de erros
			erros.addAll(resultTelefone.errors().stream().map(e -> ErroCliente.valueOf(e.name())).toList());
		
		if (resultCliente.errors() != null)
			// Adiciona ErroCliente na lista de erros
			erros.addAll(resultCliente.errors());

        // Verifica CPF duplicado
        if (cpf != null) {
            var repo = RepositoryPool.acquire(ClienteRepository.class);

            if (repo.findByCPF(cpf.numero()) != null) {
                erros.add(ErroCliente.CPF_JA_EXISTE);
            }
        }
		
		// Retorna o cliente criado ou a lista de erros
		return erros.isEmpty() ? Result.success(resultCliente.value()) : Result.failure(erros);
	}

    public static Cliente buildFromDTO(ClienteDTO dto) {
        var cpf = CPF.of(dto.cpf);
        var endereco = new Endereco(dto.logradouro,
                dto.numero, dto.complemento, dto.bairro, dto.cidade,
                dto.cep, UF.getBySigla(dto.uf));
        var telefone = new Telefone(dto.ddd, dto.numeroTel);

        var cliente = new Cliente(cpf, dto.nome, dto.dataNasc, endereco, telefone);

        cliente.setId(dto.id);

        return cliente;
    }
}
