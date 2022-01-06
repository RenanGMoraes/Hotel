package view;

import view.cadastro.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * On OS X, with a screen menu bar, you can "hot-swap" a JMenuBar between
 * multiple JFrames when each is activated. However, there is a brief flash each
 * time the active window changes, where the menu bar disappears momentarily.
 * But it is a small price to pay to be able to reuse the same menu bar!
 */
public class MenuBar {

    public void carregaMenuBar(JFrame tela) {
        System.setProperty("apple.laf.useScreenMenuBar", "true");

        final JMenuBar menuBar = new JMenuBar();

        JMenu menuReserva = new JMenu("Reserva");
        menuBar.add(menuReserva);
        JMenuItem opMenuReserva = new JMenuItem("Ver todas reservas");
        menuReserva.add(opMenuReserva);
        JMenuItem opNovaReserva = new JMenuItem("Nova reserva");
        menuReserva.add(opNovaReserva);

        JMenu menuHospede = new JMenu("Hospede");
        menuBar.add(menuHospede);
        JMenuItem opMenuHospede = new JMenuItem("Ver todos hospedes");
        menuHospede.add(opMenuHospede);
        JMenuItem opNovoHospede = new JMenuItem("Novo hospede");
        menuHospede.add(opNovoHospede);

        JMenu menuQuarto = new JMenu("Quarto");
        menuBar.add(menuQuarto);
        JMenuItem opMenuQuarto = new JMenuItem("Ver todos quartos");
        menuQuarto.add(opMenuQuarto);
        JMenuItem opNovoQuarto = new JMenuItem("Novo quarto");
        menuQuarto.add(opNovoQuarto);
        
        JMenu menuEstadia = new JMenu("Estadia");
        menuBar.add(menuEstadia);
        JMenuItem opMenuEstadia = new JMenuItem("Ver todas as estadias");
        menuEstadia.add(opMenuEstadia);
        JMenuItem opNovaEstadia = new JMenuItem("Nova estadia");
        menuEstadia.add(opNovaEstadia);
        JMenuItem opEncerrarEstadia = new JMenuItem("Encerrar estadia");
        menuEstadia.add(opEncerrarEstadia);

        // hot-swap the menu bar to newly activated windows
        final WindowListener listener = new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                ((JFrame) e.getWindow()).setJMenuBar(menuBar);
            }
        };

        opMenuReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaReserva frame = new TelaReserva();
                if (tela.getTitle().equals("Reservas")) {
                    JOptionPane.showMessageDialog(tela, "Você já está na Menu de Reservas!");
                } else {
                    tela.setVisible(false);
                    frame.setVisible(true);
                }
            }
        });

        opNovaReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastroReserva frame = new TelaCadastroReserva();
                if (tela.getTitle().equals("Nova reserva")) {
                    JOptionPane.showMessageDialog(tela, "Você já está na tela de Nova Reserva!");
                } else {
                    tela.setVisible(false);
                    frame.setVisible(true);
                }
            }
        });

        opMenuHospede.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaHospede frame = new TelaHospede();
                if (tela.getTitle().equals("Hospedes")) {
                    JOptionPane.showMessageDialog(tela, "Você já está no Menu de Hospedes!");
                } else {
                    tela.setVisible(false);
                    frame.setVisible(true);
                }
            }
        });

        opNovoHospede.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastroHospede frame = new TelaCadastroHospede();
                if (tela.getTitle().equals("Novo Hospede")) {
                    JOptionPane.showMessageDialog(tela, "Você já está na tela de Cadastro de Hospede!");
                } else {
                    tela.setVisible(false);
                    frame.setVisible(true);
                }
            }
        });
        
        opMenuQuarto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaQuarto frame = new TelaQuarto();
                if (tela.getTitle().equals("Quartos")) {
                    JOptionPane.showMessageDialog(tela, "Você já está no Menu de Quartos!");
                } else {
                    tela.setVisible(false);
                    frame.setVisible(true);
                }
            }
        });
        
        opNovoQuarto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastroQuarto frame = new TelaCadastroQuarto();
                if (tela.getTitle().equals("Novo quarto")) {
                    JOptionPane.showMessageDialog(tela, "Você já está na tela de Cadastro de Quartos!");
                } else {
                    tela.setVisible(false);
                    frame.setVisible(true);
                }
            }
        });

        opNovaEstadia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastroEstadia frame = new TelaCadastroEstadia();
                if (tela.getTitle().equals("Nova estadia")) {
                    JOptionPane.showMessageDialog(tela, "Você já está na tela de Nova Estadia!");
                } else {
                    tela.setVisible(false);
                    frame.setVisible(true);
                }
            }
        });
        
        
        tela.addWindowListener(listener);

    }

}
