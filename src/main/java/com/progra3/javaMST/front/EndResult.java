package com.progra3.javaMST.front;

import com.progra3.javaMST.back.interfaces.GraphController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class EndResult {

  private final GraphController g;
  private JFrame frame;
  private JPanel panel;
  private JSpinner regionCounter;
  private JLabel result;
  private int[][] endResult;

  public EndResult(JFrame frame, GraphController g) {
    this.frame = frame;
    this.g = g;
    initPanel(frame);
    initComponents();
  }

  private void initComponents() {
    JPanel resultPanel = new JPanel();
    resultPanel.setBounds(10, 11, 764, 330);
    panel.add(resultPanel);
    resultPanel.setLayout(new GridLayout(1, 0, 0, 0));

    result = new JLabel("pepe");
    resultPanel.add(result);

    regionCounter = new JSpinner();
    regionCounter.setBounds(352, 400, 201, 20);
    panel.add(regionCounter);

    JLabel regionCounterLabel = new JLabel("Cantidad de regiones:");
    regionCounterLabel.setBounds(214, 403, 127, 14);
    panel.add(regionCounterLabel);

    JButton btnStartProcess = new JButton("Empezar");
    btnStartProcess.setBounds(296, 454, 143, 33);
    btnStartProcess.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        startProcess();
      }
    });
    panel.add(btnStartProcess);

    JButton btnStartOver = new JButton("Volver al Inicio");
    btnStartOver.setBounds(296, 498, 143, 33);
    btnStartOver.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        displayGraphCreation(getFrame());
      }
    });
    panel.add(btnStartOver);
  }

  private void startProcess() {
    endResult = g.divideInRegions((Integer) regionCounter.getValue());

    parseResponse();
  }

  private void parseResponse() {
    String response ="";
    for (int i=0; i< endResult.length; i++){
      response = response + Arrays.toString(endResult[i])+"<br>";
    }
    result.setText("<html>"+response+"</html>");
  }

  private void initPanel(JFrame frame) {
    panel = new JPanel();
    panel.setLayout(null);
    panel.setBackground(Color.GRAY);
    panel.setBounds(0, 0, 784, 561);
    frame.getContentPane().add(panel);
  }

  private void displayGraphCreation(JFrame frame) {
    GraphCreation gc = new GraphCreation(frame);
    this.panel.setVisible(false);
    panel.setEnabled(false);
  }

  private JFrame getFrame() {
    return this.frame;
  }

}
