package services.application.cliente;

import java.time.LocalDate;

/**
 * Dados do cliente para a requisiçao de cadastro
 */
public record CadastroClienteRequest(Long cpf, 
		                             String nome, 
		                             LocalDate dataNasc, 
		                             String logradouro, 
		                             Integer numero, 
		                             String complemento, 
		                             String bairro, 
		                             String cidade,
		                             Integer cep, 
		                             String uf, 
		                             Integer DDD, 
		                             Integer numeroTel) {

}

//public class CadastroClienteRequest {
//	private Long cpf;
//	private String nome; 
//	private LocalDate dataNasc; 
//	private String logradouro; 
//	private Integer numero; 
//	private String complemento; 
//	private String bairro; 
//	private String cidade;
//	private Integer cep; 
//	private String uf; 
//	private Integer DDD; 
//	private Integer numeroTel;
//	
//	public CadastroClienteRequest(Long cpf, String nome, LocalDate dataNasc, String logradouro, Integer numero,
//			String complemento, String bairro, String cidade, Integer cep, String uf, Integer ddd, Integer numeroTel) {
//		super();
//		this.cpf = cpf;
//		this.nome = nome;
//		this.dataNasc = dataNasc;
//		this.logradouro = logradouro;
//		this.numero = numero;
//		this.complemento = complemento;
//		this.bairro = bairro;
//		this.cidade = cidade;
//		this.cep = cep;
//		this.uf = uf;
//		this.DDD = ddd;
//		this.numeroTel = numeroTel;
//	}
//
//	public Long cpf() {
//		return cpf;
//	}
//
//	public String nome() {
//		return nome;
//	}
//
//	public LocalDate dataNasc() {
//		return dataNasc;
//	}
//
//	public String logradouro() {
//		return logradouro;
//	}
//
//	public Integer numero() {
//		return numero;
//	}
//
//	public String complemento() {
//		return complemento;
//	}
//
//	public String bairro() {
//		return bairro;
//	}
//
//	public String cidade() {
//		return cidade;
//	}
//
//	public Integer cep() {
//		return cep;
//	}
//
//	public String uf() {
//		return uf;
//	}	
//
//	public Integer DDD() {
//		return DDD;
//	}
//
//	public Integer numeroTel() {
//		return numeroTel;
//	}
//
//}


