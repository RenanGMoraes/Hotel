/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.DAO;
import enumeradores.TipoQuartoEnum;
import conexao.ConexaoUtil;
import model.Quarto;
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
public class QuartoDAO implements DAO<Quarto> {

    @Override
    public List<Quarto> exibirTodos() throws ClassNotFoundException, SQLException {

        ArrayList<Quarto> quartos = new ArrayList<>();

        Connection con = new ConexaoUtil().getConnection();
        String sql = "select * from quartos";
        PreparedStatement pstm = con.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            Quarto q = new Quarto();

            q.setId(rs.getInt("id"));
            q.setTipo(TipoQuartoEnum.valueOf(rs.getString("tipo")));
            q.setDisponivel(rs.getBoolean("disponivel"));//0 falso, 1 true
            q.setValor(rs.getFloat("valor"));
            quartos.add(q);
        }

        con.close();
        pstm.close();

        return quartos;
    }

    @Override
    public boolean cadastrar(Quarto obj) throws ClassNotFoundException, SQLException {
        Connection con = new ConexaoUtil().getConnection();

        String sql = "insert into quartos(tipo, disponivel, valor) values(?,?,?)";

        PreparedStatement pstm = con.prepareStatement(sql);

        pstm.setString(1, String.valueOf(obj.getTipo()));
        pstm.setBoolean(2, obj.isDisponivel());
        pstm.setFloat(3, obj.getValor());

        pstm.execute();

        con.close();
        pstm.close();

        return true;
    }

    @Override
    public boolean excluir(Quarto obj) throws ClassNotFoundException, SQLException {

        Connection con = new ConexaoUtil().getConnection();

        String sql = "delete from quartos where id=?";
        PreparedStatement pstm = con.prepareStatement(sql);

        pstm.setString(1, String.valueOf(obj.getId()));

        pstm.execute();

        con.close();
        pstm.close();

        return true;

    }

    @Override
    public boolean atualizar(Quarto obj) throws ClassNotFoundException, SQLException {
        Connection con = new ConexaoUtil().getConnection();

        String sql = "update quartos set tipo=?, disponivel=?, valor=?";

        PreparedStatement pstm = con.prepareStatement(sql);

        pstm.setString(1, String.valueOf(obj.getTipo()));
        pstm.setBoolean(2, obj.isDisponivel());
        pstm.setFloat(3, obj.getValor());

        pstm.execute();

        con.close();
        pstm.close();

        return true;
    }

    @Override
    public Quarto buscarPorId(int id) throws ClassNotFoundException, SQLException {

        Connection con = new ConexaoUtil().getConnection();
        String sql = "select * from quartos where id=?";
        PreparedStatement pstm = con.prepareStatement(sql);

        pstm.setString(1, String.valueOf(id));

        ResultSet rs = pstm.executeQuery();

        if (rs.first()) {
            Quarto q = new Quarto();

            q.setId(rs.getInt("id"));
            q.setTipo(TipoQuartoEnum.valueOf(rs.getString("tipo")));
            q.setDisponivel(rs.getBoolean("disponivel"));
            q.setValor(rs.getFloat("valor"));

            con.close();
            pstm.close();

            return q;
        }

        con.close();
        pstm.close();

        return null;

    }

    public void ocuparQuarto(Quarto q) throws ClassNotFoundException, SQLException {

        Connection con = new ConexaoUtil().getConnection();
        String sql = "update quartos set disponivel=false where id=?";
        PreparedStatement pstm = con.prepareStatement(sql);

        pstm.setInt(1, q.getId());
        pstm.execute();

        pstm.close();
        con.close();
    }

    public void desocuparQuarto(Quarto q) throws ClassNotFoundException, SQLException {

        Connection con = new ConexaoUtil().getConnection();
        String sql = "update quartos set disponivel=true where id=?";
        PreparedStatement pstm = con.prepareStatement(sql);

        pstm.setInt(1, q.getId());
        pstm.execute();

        pstm.close();
        con.close();

    }

    public int calculaChavePrimaria() throws ClassNotFoundException, SQLException {

        Connection con = new ConexaoUtil().getConnection();
        String sql = "select id from quartos order by id desc";

        PreparedStatement pstm = con.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();

        int chaveRetorno = 0;

        if (rs.next()) {
            chaveRetorno = rs.getInt("id");
        }

        return chaveRetorno + 1;
    }

}
