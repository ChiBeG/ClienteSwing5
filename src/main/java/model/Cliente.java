package model;

import services.domain.persistence.Entity;
import util.Result;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cliente extends Entity {

	private CPF cpf;
	private String nome;
	private LocalDate dataNasc; 
	private Endereco endereco;
	private Telefone telefone;

	/**
	 * Cria um cliente. Esse construtor é chamado pelo método create.
	 * 
	 * @param cpf CPF do cliente
	 * @param nome Nome do cliente
	 * @param dataNasc Data de nascimento do cliente
	 */
	Cliente(CPF cpf, String nome, LocalDate dataNasc, Endereco endereco, Telefone telefone) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.dataNasc = dataNasc;
		this.endereco = endereco;
		this.telefone = telefone;
	}

    public CPF getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNasc() {
        return dataNasc;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    /**
	 * Valida os dados em cria o cliente. Retorna o cliente ou uma lista de erros de validação.
	 * 
	 * @param cpf CPF do cliente
	 * @param nome Nome do cliente
	 * @param dataNasc Data de nascimento do cliente
	 * @return Cliente ou lista de erros
	 */
	public static Result<Cliente, ErroCliente> of(CPF cpf, String nome, LocalDate dataNasc, Endereco endereco, Telefone telefone) {
		
		List<ErroCliente> erros = new ArrayList<>();
		
		// Valida CPF		
		if (cpf == null)
			erros.add(ErroCliente.CPF_INVALIDO);
		
		// Valida nome
		if (nome == null || nome.length() < 4)
			erros.add(ErroCliente.NOME_INVALIDO);
		
		// Valida Data de Nascimento
		LocalDate hoje = LocalDate.now();
		
		if (dataNasc == null || dataNasc.isAfter(hoje))
			erros.add(ErroCliente.DATA_INVALIDA);
		else {
			// Se a dataNasc <= hoje então valida a idade do aluno
			LocalDate limite = hoje.minusYears(18);
			
			if (dataNasc.isAfter(limite))
				erros.add(ErroCliente.IDADE_INVALIDA);
		}
		
		// Valida endereco
		if (endereco == null)
			erros.add(ErroCliente.ENDERECO_INVALIDO);
		
		// Valida telefone
		if (telefone == null)
			erros.add(ErroCliente.TELEFONE_INVALIDO);
		
		return erros.isEmpty() ? Result.success(new Cliente(cpf, nome, dataNasc, endereco, telefone)) :
			                     Result.failure(erros);

	}

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + getId() +
                "cpf=" + cpf +
                ", nome='" + nome + '\'' +
                ", dataNasc=" + dataNasc +
                ", endereco=" + endereco +
                ", telefone=" + telefone +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(cpf, cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cpf);
    }
}
