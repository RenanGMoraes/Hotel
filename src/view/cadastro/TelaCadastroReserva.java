/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.cadastro;

import view.cadastro.TelaCadastroHospede;
import model.Hospede;
import model.Reserva;
import model.Quarto;
import DAO.*;
import enumeradores.TipoQuartoEnum;
import java.awt.Container;
import java.awt.event.ItemEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import utilidade.Utilidade;
import view.MenuBar;

/**
 *
 * @author Renan
 */
public class TelaCadastroReserva extends javax.swing.JFrame {

    private DefaultComboBoxModel<String> modelStringComboBox = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<Integer> modelIntComboBox = new DefaultComboBoxModel<>();

    private void carregaComboBoxTipoQuarto() {

        cbTipoQuarto.setModel(modelStringComboBox);
        cbTipoQuarto.removeAllItems();

        for (TipoQuartoEnum itemEnum : TipoQuartoEnum.values()) {
            cbTipoQuarto.addItem(itemEnum.getDescricao());
        }
    }

    /**
     * Creates new form TelaCadastroReserva
     */
    private void formataDataTextField(JFormattedTextField txtFormata) {

        setLayout(null);
        MaskFormatter mascaraData;

        try {
            mascaraData = new MaskFormatter("##/##/####");

            mascaraData.setPlaceholderCharacter('_');

            txtFormata.setFormatterFactory(new DefaultFormatterFactory(mascaraData));
        } catch (ParseException ex) {
            Logger.getLogger(TelaCadastroReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void carregaComboBoxQuartosDisponiveis() {

        cbQuartosDisponiveis.setModel(modelIntComboBox);
        cbQuartosDisponiveis.removeAllItems();

        DAO<Quarto> dao = new QuartoDAO();

        try {

            for (Quarto quarto : dao.exibirTodos()) {
                if (quarto.getTipo().getDescricao().equals(cbTipoQuarto.getSelectedItem())) {
                    if (quarto.isDisponivel()) {
                        cbQuartosDisponiveis.addItem(quarto.getId());
                    }
                }

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaCadastroReserva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastroReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public TelaCadastroReserva() {
        super("Nova reserva");
        initComponents();

        Utilidade u = new Utilidade();

        try {
            u.aplicaMascaraCpf(txtCpfHospede);
            u.aplicaMascaraCpf(txtCpfHospede2);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao aplicar mascara aos campos de CPF");
        }

        MenuBar menuBar = new MenuBar();
        menuBar.carregaMenuBar(this);

        txtCpfHospede2.setVisible(false);
        lblCpfHospede2.setVisible(false);

        carregaComboBoxTipoQuarto();
        carregaComboBoxQuartosDisponiveis();

        formataDataTextField(txtDataEntrada);
        formataDataTextField(txtDataSaida);
    }

    private boolean hospedePossuiReserva(Hospede h, Date dataEntradaAtual) throws ClassNotFoundException, SQLException {

        ReservaDAO dao = new ReservaDAO();

        for (Reserva reserva : dao.exibirTodos()) {
            if (reserva.getHospede().equals(h)) {
                //Verifica se a reserva que o hospede está tem a data de saída antes
                //da nova reserva que irá ser feita
                if (reserva.getDataSaida().compareTo(dataEntradaAtual) >= 0) {
                    return true;
                }
            }
        }
        return false;

    }

    private boolean verificaCpfCadastrado() {

        if (txtCpfHospede.getText().isEmpty() || txtCpfHospede.getText().contains(" ")) {
            return false;
        }

        try {

            HospedeDAO daoHospede = new HospedeDAO();
            Hospede hospede = daoHospede.buscarPorCpf(txtCpfHospede.getText());

            if (hospede == null) {
                Object[] options = {"Confirmar", "Cancelar"};
                int op = JOptionPane.showOptionDialog(null, "CPF não encontrado.\n"
                        + "É necessário cadastrar o hóspede antes de realizar a reserva", "Reserva",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                if (op == 0) {
                    TelaCadastroHospede frame = new TelaCadastroHospede();
                    frame.setVisible(true);

                }
                return false;
            }
            return true;

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TelaCadastroReserva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastroReserva.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnFazerReserva = new javax.swing.JButton();
        txtDataEntrada = new javax.swing.JFormattedTextField();
        txtDataSaida = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbTipoQuarto = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cbQuartosDisponiveis = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        lblCpfHospede2 = new javax.swing.JLabel();
        txtCpfHospede = new javax.swing.JFormattedTextField();
        txtCpfHospede2 = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnFazerReserva.setText("Fazer reserva");
        btnFazerReserva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFazerReservaActionPerformed(evt);
            }
        });

        txtDataEntrada.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        txtDataEntrada.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDataEntradaFocusLost(evt);
            }
        });
        txtDataEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDataEntradaKeyReleased(evt);
            }
        });

        txtDataSaida.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        txtDataSaida.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDataSaidaFocusLost(evt);
            }
        });
        txtDataSaida.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtDataSaidaKeyReleased(evt);
            }
        });

        jLabel1.setText("Tipo do quarto");

        jLabel2.setText("Data de saida");

        cbTipoQuarto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTipoQuartoItemStateChanged(evt);
            }
        });
        cbTipoQuarto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbTipoQuartoFocusLost(evt);
            }
        });

        jLabel3.setText("Data de entrada");

        jLabel4.setText("CPF Hospede");

        jLabel5.setText("Quartos disponíveis");

        lblCpfHospede2.setText("CPF Hospede 2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(txtDataSaida)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbTipoQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(cbQuartosDisponiveis, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtCpfHospede, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnFazerReserva, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCpfHospede2)
                            .addComponent(txtCpfHospede2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTipoQuarto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbQuartosDisponiveis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDataEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtDataSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFazerReserva)
                            .addComponent(txtCpfHospede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(lblCpfHospede2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCpfHospede2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFazerReservaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFazerReservaActionPerformed

        //0800 011 9911 SABESP
        if (cbQuartosDisponiveis.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Quarto não selecionado!");
            return;
        } else if (txtDataEntrada.getText().contains("_") || txtDataSaida.getText().contains("_")) {
            JOptionPane.showMessageDialog(this, "Campos de data não preenchidos!");
            return;
        } else if (txtCpfHospede.getText().contains(" ")) {
            JOptionPane.showMessageDialog(this, "Campo de CPF vazio ou incompleto!");
            return;
        }

        try {

            if (verificaCpfCadastrado()) {

                ReservaDAO daoReserva = new ReservaDAO();
                HospedeDAO daoHospede = new HospedeDAO();
                QuartoDAO daoQuarto = new QuartoDAO();

                Reserva reserva = new Reserva();
                Hospede hospede = daoHospede.buscarPorCpf(txtCpfHospede.getText());
                Quarto quarto = daoQuarto.buscarPorId((Integer) cbQuartosDisponiveis.getSelectedItem());

                reserva.setDataEntrada(criaData(txtDataEntrada.getText()));
                reserva.setDataSaida(criaData(txtDataSaida.getText()));
                reserva.setHospede(hospede);
                reserva.setQuarto(quarto);
                reserva.setId(daoReserva.calculaChavePrimaria());

                if (hospedePossuiReserva(hospede, criaData(txtDataEntrada.getText()))) {
                    JOptionPane.showMessageDialog(this, "Hospede já possui reserva marcada entre essas datas!");
                    return;
                }

                //Cadastra o segundo hospede na mesma reserva caso o tipo de quarto seja Casal ou Solteiro 2 camas
                if (txtCpfHospede2.isVisible() && !txtCpfHospede2.getText().contains(" ")) {

                    Reserva reserva2 = new Reserva();

                    Hospede hospede2 = daoHospede.buscarPorCpf(txtCpfHospede2.getText());

                    if (hospedePossuiReserva(hospede2, criaData(txtDataEntrada.getText()))) {
                        JOptionPane.showMessageDialog(this, "Hospede 2 já possui reserva marcada entre essas datas!");
                        return;
                    }

                    reserva2.setDataEntrada(criaData(txtDataEntrada.getText()));
                    reserva2.setDataSaida(criaData(txtDataSaida.getText()));
                    reserva2.setHospede(hospede2);
                    reserva2.setQuarto(quarto);
                    reserva2.setId(reserva.getId());

                    daoReserva.cadastrar(reserva2);

                }

                daoReserva.cadastrar(reserva);

                daoQuarto.ocuparQuarto(quarto);
                carregaComboBoxQuartosDisponiveis();

                JOptionPane.showMessageDialog(this, "Reserva efetuada com sucesso!");

                txtCpfHospede.setText("");
                txtCpfHospede2.setText("");
                txtDataEntrada.setValue("");
                txtDataSaida.setValue("");

            } else {
                JOptionPane.showMessageDialog(this, "Hospede não encontrado!");
            }

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "Erro de classe não encontrada: " + ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(TelaCadastroReserva.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TelaCadastroReserva.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnFazerReservaActionPerformed

    private Date criaData(String data) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date dataCriada = sdf.parse(data);

        return dataCriada;
    }


    private void txtDataEntradaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataEntradaKeyReleased


    }//GEN-LAST:event_txtDataEntradaKeyReleased

    private boolean dataValida(String data) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            //setLenient() é usado para setar
            //sua escolha sobre datas estranhas, quando eu seto para "false" estou dizendo
            //que não aceito datas falsas como 31/02/2016
            sdf.setLenient(false);
            sdf.parse(data);
            //se nada deu errado retorna true (verdadeiro)
            return true;
        } catch (ParseException ex) {
            //se algum passo dentro do "try" der errado quer dizer que sua data é falsa, então,
            //retorna falso
            return false;
        }
    }

    private void txtDataEntradaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataEntradaFocusLost

        if (!dataValida(txtDataEntrada.getText()) && !txtDataEntrada.getText().contains("_")) {

            JOptionPane.showMessageDialog(this, "Data inválida");
            txtDataEntrada.requestFocus();

        }

    }//GEN-LAST:event_txtDataEntradaFocusLost

    private void txtDataSaidaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDataSaidaFocusLost
        if (!dataValida(txtDataSaida.getText()) && !txtDataSaida.getText().contains("_")) {
            JOptionPane.showMessageDialog(this, "Data inválida");
            txtDataSaida.requestFocus();
        }
    }//GEN-LAST:event_txtDataSaidaFocusLost

    private void txtDataSaidaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDataSaidaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataSaidaKeyReleased

    private void cbTipoQuartoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTipoQuartoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            carregaComboBoxQuartosDisponiveis();
            if (cbTipoQuarto.getSelectedItem() == "Casal"
                    || cbTipoQuarto.getSelectedItem() == "Solteiro 2 camas") {

                txtCpfHospede2.setVisible(true);
                lblCpfHospede2.setVisible(true);
            } else {
                txtCpfHospede2.setVisible(false);
                lblCpfHospede2.setVisible(false);
            }
        }

    }//GEN-LAST:event_cbTipoQuartoItemStateChanged

    private void cbTipoQuartoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbTipoQuartoFocusLost

    }//GEN-LAST:event_cbTipoQuartoFocusLost

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Windows look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroReserva.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroReserva.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroReserva.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaCadastroReserva.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaCadastroReserva().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFazerReserva;
    private javax.swing.JComboBox<Integer> cbQuartosDisponiveis;
    private javax.swing.JComboBox<String> cbTipoQuarto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel lblCpfHospede2;
    private javax.swing.JFormattedTextField txtCpfHospede;
    private javax.swing.JFormattedTextField txtCpfHospede2;
    private javax.swing.JFormattedTextField txtDataEntrada;
    private javax.swing.JFormattedTextField txtDataSaida;
    // End of variables declaration//GEN-END:variables
}
