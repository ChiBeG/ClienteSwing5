package services.domain.persistence;

import model.Cliente;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Classe usada para trafegar dados entre a aplicação e o BD
 */
public class ClienteDTO extends EntityDTO {
    public final Long cpf;
    public final String nome;
    public final LocalDate dataNasc;
    public final String logradouro;
    public final Integer numero;
    public final String complemento;
    public final String bairro;
    public final String cidade;
    public final Integer cep;
    public final String uf;
    public final Integer ddd;
    public final Integer numeroTel;

    public ClienteDTO(UUID id,
                      Long cpf,
                      String nome,
                      LocalDate dataNasc,
                      String logradouro,
                      Integer numero,
                      String complemento,
                      String bairro,
                      String cidade,
                      Integer cep,
                      String uf,
                      Integer ddd,
                      Integer numeroTel) {
        super(id);
        this.cpf = cpf;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.cep = cep;
        this.uf = uf;
        this.ddd = ddd;
        this.numeroTel = numeroTel;
    }

    /**
     * ria o DTO a partir do cliente
     * @param cliente Cliente
     * @return DTO do cliente
     */
    public static ClienteDTO fromEntity(Cliente cliente) {
        return new ClienteDTO(cliente.getId(),
                cliente.getCpf().numero(),
                cliente.getNome(),
                cliente.getDataNasc(),
                cliente.getEndereco().getLogradouro(),
                cliente.getEndereco().getNumero(),
                cliente.getEndereco().getComplemento(),
                cliente.getEndereco().getBairro(),
                cliente.getEndereco().getCidade(),
                cliente.getEndereco().getCEP(),
                cliente.getEndereco().getUf().getSigla(),
                cliente.getTelefone().getDdd(),
                cliente.getTelefone().getNumero());
    }
}
