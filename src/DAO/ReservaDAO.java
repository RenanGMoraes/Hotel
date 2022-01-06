/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import conexao.ConexaoUtil;
import model.Reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Quarto;

/**
 *
 * @author Renan
 */
public class ReservaDAO implements DAO<Reserva> {

    /*private final String SELECT = "select pk_idReserva, fk_idHospede, fk_idQuarto,\n"
            + "date_format(dataEntrada, '%d/%m/%Y') as dataEntrada,\n"
            + "date_format(dataSaida, '%d/%m/%Y') as dataSaida from reservas";
     */
    @Override
    public List<Reserva> exibirTodos() throws ClassNotFoundException, SQLException {
        ArrayList<Reserva> reservas = new ArrayList<>();

        Connection con = new ConexaoUtil().getConnection();
        String sql = "select * from reservas";

        PreparedStatement pstm = con.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            Reserva r = criaReserva(rs);
            reservas.add(r);
        }

        con.close();
        pstm.close();

        return reservas;
    }

    public List<Reserva> buscarPorQuarto(Quarto q) throws SQLException, ClassNotFoundException {

        ArrayList<Reserva> reservas = new ArrayList<>();

        Connection con = new ConexaoUtil().getConnection();
        String sql = "select * from reservas where fk_idQuarto=?";
        PreparedStatement pstm = con.prepareStatement(sql);

        pstm.setInt(1, q.getId());

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            reservas.add(this.criaReserva(rs));
        }
        return reservas;
    }

    @Override
    public boolean cadastrar(Reserva obj) throws ClassNotFoundException, SQLException {

        Connection con = new ConexaoUtil().getConnection();
        String sql = "insert into reservas values(?,?,?,?,?,?)";

        PreparedStatement pstm = con.prepareStatement(sql);

        pstm.setString(1, String.valueOf(obj.getId()));
        pstm.setString(2, String.valueOf(obj.getHospede().getId()));
        pstm.setString(3, String.valueOf(obj.getQuarto().getId()));

        pstm.setDate(4, new java.sql.Date(obj.getDataEntrada().getTime()));
        pstm.setDate(5, new java.sql.Date(obj.getDataSaida().getTime()));
        pstm.setBoolean(6, false);

        boolean execute = pstm.execute();

        pstm.close();
        con.close();

        return execute;

    }

    public void alocaEstadiaReserva(Reserva r) throws SQLException, ClassNotFoundException {
        
        Connection con = new ConexaoUtil().getConnection();
        String sql = "update reservas set temEstadia=true where pk_idReserva=?";
        PreparedStatement pstm = con.prepareStatement(sql);

        pstm.setBoolean(1, true);
        
        pstm.execute();

        pstm.close();
        con.close();
        
    }

    public List<Reserva> exibirViewTabelaReserva() throws ClassNotFoundException, SQLException {
        ArrayList<Reserva> reservas = new ArrayList<>();

        Connection con = new ConexaoUtil().getConnection();
        String sql = "select * from tabelareservas";
        PreparedStatement pstm = con.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();

        while (rs.next()) {
            Reserva r = new Reserva();

            r.setId(rs.getInt("pk_idReserva"));
            r.setHospede(new HospedeDAO().buscarPorCpf(rs.getString("cpfHospede")));
            r.setQuarto(new QuartoDAO().buscarPorId(rs.getInt("fk_idQuarto")));
            r.setDataEntrada(rs.getDate("dataEntrada"));
            r.setDataSaida(rs.getDate("dataSaida"));
            r.setTemEstadia(rs.getBoolean("temEstadia"));
            reservas.add(r);
        }

        con.close();
        pstm.close();

        return reservas;
    }

    public int calculaChavePrimaria() throws ClassNotFoundException, SQLException {

        Connection con = new ConexaoUtil().getConnection();
        String sql = "select pk_idReserva from reservas order by pk_idReserva";

        PreparedStatement pstm = con.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();

        int chaveRetorno = 0;

        if (rs.first()) {
            chaveRetorno = rs.getInt("pk_idReserva");
        }

        while (rs.next()) {

            if (chaveRetorno + 1 < rs.getInt("pk_idReserva")) {
                return chaveRetorno + 1;
            }
            chaveRetorno = rs.getInt("pk_idReserva");
        }

        return chaveRetorno + 1;
    }

    @Override
    public boolean excluir(Reserva obj) throws ClassNotFoundException, SQLException {

        Connection con = new ConexaoUtil().getConnection();
        String sql = "delete from reservas where pk_idReserva=?";

        PreparedStatement pstm = con.prepareStatement(sql);

        pstm.setInt(1, obj.getId());

        pstm.execute();

        pstm.close();
        con.close();

        return true;
    }

    @Override
    public boolean atualizar(Reserva obj) throws ClassNotFoundException, SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Reserva buscarPorId(int id) throws ClassNotFoundException, SQLException {
        Connection con = new ConexaoUtil().getConnection();
        String sql = "select * from reservas where pk_idReserva=?";

        PreparedStatement pstm = con.prepareStatement(sql);

        pstm.setInt(1, id);
        ResultSet rs = pstm.executeQuery();

        if (rs.first()) {
            Reserva r = criaReserva(rs);

            con.close();
            pstm.close();

            return r;
        }

        con.close();
        pstm.close();
        rs.close();

        return null;
    }

    private Reserva criaReserva(ResultSet rs) throws SQLException, ClassNotFoundException {

        Reserva r = new Reserva();

        r.setId(rs.getInt("pk_idReserva"));
        r.setHospede(new HospedeDAO().buscarPorId(rs.getInt("fk_idHospede")));
        r.setQuarto(new QuartoDAO().buscarPorId(rs.getInt("fk_idQuarto")));
        r.setDataEntrada(rs.getDate("dataEntrada"));
        r.setDataSaida(rs.getDate("dataSaida"));

        return r;
    }

}

/*
try {

            String start_dt = rs.getDate("dataEntrada").toString();

            DateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
            Date date = parser.parse(start_dt);

            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String finalForm = formatter.format(date);
            System.out.println(finalForm);
            
            
            
            
        } catch (ParseException ex) {
            Logger.getLogger(ReservaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
 */
