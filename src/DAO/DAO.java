/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {

    List<T> exibirTodos() throws ClassNotFoundException, SQLException;

    boolean cadastrar(T obj) throws ClassNotFoundException, SQLException;

    boolean excluir(T obj) throws ClassNotFoundException, SQLException;

    boolean atualizar(T obj) throws ClassNotFoundException, SQLException;

    T buscarPorId(int id) throws ClassNotFoundException, SQLException;


}
