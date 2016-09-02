package dao;

import util.ConexaoApi;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ProprietarioDTO;
import util.Persistible;

public class ProprietarioDAO implements Persistible<ProprietarioDTO> {

    private ProprietarioDTO makeDto(ConexaoApi conexao) throws SQLException {
        ProprietarioDTO resultDto = new ProprietarioDTO();
        resultDto.setId(conexao.getInt("id"));
        resultDto.setNome(conexao.getString("nome"));
        resultDto.setSobrenome(conexao.getString("sobrenome"));
        resultDto.setSexo(conexao.getString("sexo"));
        resultDto.setIdEndereco(conexao.getInt("id_endereco"));
        resultDto.setCpf(conexao.getString("cpf"));
        resultDto.setTelefone(conexao.getString("telefone"));
        resultDto.setEmail(conexao.getString("email"));
        return resultDto;
    }

    @Override
    public ProprietarioDTO getOne(ConexaoApi conn, int id) throws SQLException, ClassNotFoundException {
        conn.setQuery("select * from proprietarios where id = ?");
        conn.setInt(1, id);
        conn.executeQuery();
        ProprietarioDTO result = conn.next() ? makeDto(conn) : null;
        return result;
    }

    @Override
    public List<ProprietarioDTO> getAll(ConexaoApi conn) throws SQLException {
        conn.setQuery("select * from proprietarios");
        conn.executeQuery();
        List<ProprietarioDTO> result = new ArrayList<>();
        while (conn.next()) {
            result.add(makeDto(conn));
        }
        return result;
    }

    @Override
    public void save(ConexaoApi conn, ProprietarioDTO p) throws SQLException {
        conn.setQuery("update proprietarios set nome= ?, sobrenome = ?, "
                + "cpf = ?, sexo = ?, id_endereco = ?, telefone = ?, email = ? where id = ?");
        conn.setString(1, p.getNome());
        conn.setString(2, p.getUltimoNome());
        conn.setString(3, p.getCpf());
        conn.setString(4, p.getSexo());
        conn.setInt(5, p.getIdEndereco());
        conn.setString(6, p.getTelefone());
        conn.setString(7, p.getEmail());
        conn.setInt(8, p.getId());
        if (conn.executeUpdate() == 0) {
            conn.setQuery("insert into proprietarios "
                    + "(nome, sobrenome, cpf, sexo, id_endereco, telefone, email) "
                    + "values (?,?,?,?,?,?,?)");
            conn.setString(1, p.getNome());
            conn.setString(2, p.getUltimoNome());
            conn.setString(3, p.getCpf());
            conn.setString(4, p.getSexo());
            conn.setInt(5, p.getIdEndereco());
            conn.setString(6, p.getTelefone());
            conn.setString(7, p.getEmail());
            conn.execute();
        }
    }

    @Override
    public void delete(ConexaoApi conn, ProprietarioDTO p) throws SQLException {
        conn.setQuery("delete from proprietarios where id = ?");
        conn.setInt(1, p.getId());
        conn.execute();
    }

}
