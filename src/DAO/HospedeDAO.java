/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import conexao.ConexaoUtil;
import model.Hospede;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Renan
 */
public class HospedeDAO implements DAO<Hospede> {

    public Hospede buscarPorCpf(String cpf) throws ClassNotFoundException, SQLException {

        Connection con = new ConexaoUtil().getConnection();

        String sql = "select * from hospedes where cpf=?";

        PreparedStatement pstm = con.prepareStatement(sql);

        pstm.setString(1, String.valueOf(cpf));
        ResultSet rs = pstm.executeQuery();

        if (rs.first()) {
            Hospede h = criaHospede(rs);

            con.close();
            pstm.close();

            return h;
        }

        con.close();
        pstm.close();
        rs.close();

        return null;
    }

    @Override
    public boolean cadastrar(Hospede obj) throws ClassNotFoundException, SQLException {

        Connection con = new ConexaoUtil().getConnection();

        String sql = "INSERT INTO HOSPEDES(NOME, CPF, TELEFONE, EMAIL) VALUES(?,?,?,?)";

        PreparedStatement statement = con.prepareStatement(sql);

        statement.setString(1, obj.getNome());
        statement.setString(2, obj.getCpf());
        statement.setString(3, obj.getTelefone());
        statement.setString(4, obj.getEmail());

        statement.execute();
        con.close();
        statement.close();

        return true;

    }

    @Override
    public boolean excluir(Hospede obj) throws ClassNotFoundException, SQLException {
        Connection connection = new ConexaoUtil().getConnection();

        String sql = "DELETE FROM HOSPEDES WHERE id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, obj.getId());
        statement.execute();
        
        statement.close();
        connection.close();
        
        return true;
    }

    @Override
    public boolean atualizar(Hospede obj) throws ClassNotFoundException, SQLException {

        Connection con = new ConexaoUtil().getConnection();
        String sql = "UPDATE HOSPEDES SET NOME = ?, TELEFONE = ?, EMAIL = ?, CPF = ? WHERE id = ?";
        PreparedStatement statement = con.prepareStatement(sql);

        statement.setString(1, obj.getNome());
        statement.setString(2, obj.getTelefone());
        statement.setString(3, obj.getEmail());
        statement.setString(4, obj.getCpf());
        statement.setInt(5, obj.getId());

        statement.execute();
        
        statement.close();
        con.close();

        return true;

    }

    @Override
    public Hospede buscarPorId(int id) throws ClassNotFoundException, SQLException {

        Connection con = new ConexaoUtil().getConnection();
        String sql = "select * from hospedes where id=?";

        PreparedStatement pstm = con.prepareStatement(sql);

        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();

        if (rs.first()) {
            Hospede h = criaHospede(rs);

            con.close();
            pstm.close();

            return h;
        }

        con.close();
        pstm.close();
        rs.close();

        return null;
    }

    @Override
    public List<Hospede> exibirTodos() throws ClassNotFoundException, SQLException {

        ArrayList<Hospede> hospedes = new ArrayList<>();
        Connection con = new ConexaoUtil().getConnection();

        PreparedStatement pstm = con.prepareStatement("select * from hospedes");
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            Hospede h = criaHospede(rs);
            hospedes.add(h);
        }

        con.close();
        pstm.close();
        rs.close();

        return hospedes;
    }

    public List<Hospede> buscarPorCpfParecido(String cpf) throws ClassNotFoundException, SQLException {

        ArrayList<Hospede> hospedes = new ArrayList<>();
        Connection con = new ConexaoUtil().getConnection();
        String sql = "select * from hospedes where cpf like ?";

        PreparedStatement pstm = con.prepareStatement(sql);

        pstm.setString(1, cpf + '%');
        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {

            Hospede h = criaHospede(rs);
            hospedes.add(h);
        }

        con.close();
        pstm.close();
        rs.close();

        return hospedes;
    }

    private Hospede criaHospede(ResultSet rs) throws SQLException {

        Hospede h = new Hospede();

        h.setId(rs.getInt("id"));
        h.setNome(rs.getString("nome"));
        h.setCpf(rs.getString("cpf"));
        h.setTelefone(rs.getString("telefone"));
        h.setEmail(rs.getString("email"));

        return h;
    }

}
