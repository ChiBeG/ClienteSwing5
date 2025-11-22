package adapters.infrastructure.persistence;

import services.domain.persistence.ClienteDTO;
import services.domain.persistence.IClienteDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClienteSQLDAO implements IClienteDAO {

    @Override
    public ClienteDTO findByCPF(Long cpf) {
        try (var conn = DBConnection.get();
             var stmt = conn.prepareStatement("select * from cliente where cpf=?")) {

            stmt.setLong(1, cpf);

            try (var rs = stmt.executeQuery()) {
                if (rs.next())
                    return createDTO(rs);
                else
                    return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(ClienteDTO dto) {
        String sqlInsert = """
                insert into cliente (id, cpf, nome, datanasc, logradouro, numero, complemento,
                bairro, cidade, cep, uf, ddd, telefone) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";

        try (var conn = DBConnection.get();
             var stmt = conn.prepareStatement(sqlInsert)) {

            // Preenche os parâmetros do SQL
            stmt.setString(1, dto.id.toString());
            stmt.setLong(2, dto.cpf);
            stmt.setString(3, dto.nome);
            stmt.setString(4, dto.dataNasc.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            stmt.setString(5, dto.logradouro);
            stmt.setInt(6, dto.numero);
            stmt.setString(7, dto.complemento);
            stmt.setString(8, dto.bairro);
            stmt.setString(9, dto.cidade);
            stmt.setInt(10, dto.cep);
            stmt.setString(11, dto.uf);
            stmt.setInt(12, dto.ddd);
            stmt.setInt(13, dto.numeroTel);

            // Executa o INSERT
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(ClienteDTO dto) {
        String sqlUpdate = """
                update cliente set cpf=?, nome=?, datanasc=?, logradouro=?, numero=?,
                complemento=?, bairro=?, cidade=?, cep=?, uf=?, ddd=?, telefone=? where id=?""";

        try (var conn = DBConnection.get();
             var stmt = conn.prepareStatement(sqlUpdate)) {

            // Preenche os parâmetros do SQL
            stmt.setLong(1, dto.cpf);
            stmt.setString(2, dto.nome);
            stmt.setString(3, dto.dataNasc.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            stmt.setString(4, dto.logradouro);
            stmt.setInt(5, dto.numero);
            stmt.setString(6, dto.complemento);
            stmt.setString(7, dto.bairro);
            stmt.setString(8, dto.cidade);
            stmt.setInt(9, dto.cep);
            stmt.setString(10, dto.uf);
            stmt.setInt(11, dto.ddd);
            stmt.setInt(12, dto.numeroTel);
            stmt.setString(13, dto.id.toString());

            // Executa o UPDATE
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(ClienteDTO dto) {
        String sqlDelete = "delete from cliente where id=?";

        try (var conn = DBConnection.get();
             var stmt = conn.prepareStatement(sqlDelete)) {

            // Define o ID do SQL
            stmt.setString(1, dto.id.toString());

            // Executa o DELETE
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ClienteDTO> findAll() {
        try (var conn = DBConnection.get();
             var stmt = conn.prepareStatement("select * from cliente")) {
            try (var rs = stmt.executeQuery()) {
                List<ClienteDTO> dtos = new ArrayList<>();
                while (rs.next()) {
                    dtos.add(createDTO(rs));
                }
                return dtos;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ClienteDTO createDTO(ResultSet rs) throws SQLException {
        return new ClienteDTO(UUID.fromString(rs.getString("id")),
                rs.getLong("cpf"),
                rs.getString("nome"),
                LocalDate.parse(rs.getString("datanasc")),
                rs.getString("logradouro"),
                rs.getInt("numero"),
                rs.getString("complemento"),
                rs.getString("bairro"),
                rs.getString("cidade"),
                rs.getInt("cep"),
                rs.getString("uf"),
                rs.getInt("ddd"),
                rs.getInt("telefone"));
    }
}
