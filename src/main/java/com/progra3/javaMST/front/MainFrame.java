package com.progra3.javaMST.front;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.ComponentOrientation;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JSpinner;

public class MainFrame {
  private JFrame frmJavaagm;
  JPanel panel;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          MainFrame window = new MainFrame();
          window.frmJavaagm.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public MainFrame() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {

    frmJavaagm = new JFrame();
    frmJavaagm.setTitle("javaAgm");
    frmJavaagm.getContentPane().setSize(new Dimension(800, 600));
    frmJavaagm.getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
    frmJavaagm.getContentPane().setLayout(null);
    frmJavaagm.setResizable(false);
    frmJavaagm.setBounds(100, 100, 800, 600);
    frmJavaagm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    panel = new JPanel();
    panel.setBounds(0, 0, 784, 561);
    frmJavaagm.getContentPane().add(panel);
    panel.setBackground(Color.GRAY);
    panel.setLayout(null);

    JButton btnStart = new JButton("Empezar");
    btnStart.setBounds(251, 218, 259, 78);
    btnStart.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        displayCreateGraph();
      }
    });
    panel.add(btnStart);
  }

  private void displayCreateGraph(){
    GraphCreation gc = new GraphCreation(getFrame());
    this.panel.setVisible(false);
    panel.setEnabled(false);
  }

  public JFrame getFrame() {
    return frmJavaagm;
  }

}
