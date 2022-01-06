/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import conexao.ConexaoUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Estadia;

/**
 *
 * @author Renan
 */
public class EstadiaDAO implements DAO<Estadia>{

    @Override
    public List<Estadia> exibirTodos() throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean cadastrar(Estadia obj) throws ClassNotFoundException, SQLException {
        Connection con = new ConexaoUtil().getConnection();
        
        String sql = "insert into estadia(pk_idEstadia, fk_idReserva, dataEntrada) values (?,?,?)";
        
        PreparedStatement pstm = con.prepareStatement(sql);
        
        pstm.setInt(1, obj.getIdEstadia());
        pstm.setInt(2, obj.getReserva().getId());
        pstm.setDate(3, new java.sql.Date(obj.getDataEntrada().getTime()));
        
        boolean execute = pstm.execute();
        
        pstm.close();
        con.close();
        
        return execute;
        
    }

    @Override
    public boolean excluir(Estadia obj) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean atualizar(Estadia obj) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Estadia buscarPorId(int id) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Estadia criaEstadia(ResultSet rs) throws SQLException, ClassNotFoundException {

        Estadia e = new Estadia();

        e.setIdEstadia(rs.getInt("pk_idEstadia"));
        e.setDataEntrada(rs.getDate("dataEntrada"));
        e.setDataEntrada(rs.getDate("dataSaida"));
        e.setValorTotalServicos(rs.getFloat("valorTotalServicos"));
        e.setReserva(new ReservaDAO().buscarPorId(rs.getInt("fk_idReserva")));

        return e;
    }
    
    public int calculaChavePrimaria() throws ClassNotFoundException, SQLException {

        Connection con = new ConexaoUtil().getConnection();
        String sql = "select pk_idEstadia from estadia order by pk_idEstadia";

        PreparedStatement pstm = con.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();

        int chaveRetorno = 0;

        if (rs.first()) {
            chaveRetorno = rs.getInt("pk_idEstadia");
        }

        while (rs.next()) {

            if (chaveRetorno + 1 < rs.getInt("pk_idEstadia")) {
                return chaveRetorno + 1;
            }
            chaveRetorno = rs.getInt("pk_idEstadia");
        }

        return chaveRetorno + 1;
    }
    
}
