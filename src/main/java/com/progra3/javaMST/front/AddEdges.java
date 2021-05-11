package com.progra3.javaMST.front;

import com.progra3.javaMST.back.application.utils.Edge;
import com.progra3.javaMST.back.interfaces.GraphController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddEdges {

  private final JFrame frame;
  private JPanel panel;
  private final int maxVertexIndex;
  private JSpinner vertex1, vertex2, weight;
  private Integer vX, vY, vWeight;
  private JLabel edgesRecord;
  private String record;
  private final ArrayList<Edge> edges;
  private final GraphController g;

  AddEdges(JFrame frame, int vertexCount, GraphController g) {
    record = "";
    this.frame = frame;
    this.g = g;
    maxVertexIndex = vertexCount == 1 ? 1 : vertexCount - 1;
    edges = new ArrayList<Edge>();
    initPanel(frame);
    initComponents();
  }

  private void initPanel(JFrame frame) {
    panel = new JPanel();
    panel.setBackground(Color.GRAY);
    panel.setBounds(0, 0, 784, 561);
    frame.getContentPane().add(panel);
    panel.setLayout(null);
  }

  private void initComponents() {

    JButton btnDone = new JButton("Aceptar");
    btnDone.setBounds(305, 398, 167, 36);
    btnDone.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        addEdgeToRecord();
      }
    });
    panel.add(btnDone);

    JButton btnGoBack = new JButton("<< Anterior");
    btnGoBack.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        displayGraphCreation(getFrame());
      }
    });
    btnGoBack.setBounds(10, 497, 136, 36);
    panel.add(btnGoBack);

    vertex1 = new JSpinner();
    vertex1.setBounds(92, 322, 128, 20);
    vertex1.setModel(new SpinnerNumberModel(0, 0, maxVertexIndex, 1));
    panel.add(vertex1);

    JLabel vertex1Label = new JLabel("Vertice 1:");
    vertex1Label.setBounds(22, 325, 60, 14);
    panel.add(vertex1Label);

    vertex2 = new JSpinner();
    vertex2.setBounds(356, 322, 128, 20);
    vertex2.setModel(new SpinnerNumberModel(0, 0, maxVertexIndex, 1));
    panel.add(vertex2);
    JLabel vertex2Label = new JLabel("Vertice 2:");
    vertex2Label.setBounds(286, 325, 60, 14);
    panel.add(vertex2Label);

    weight = new JSpinner();
    weight.setBounds(608, 322, 128, 20);
    weight.setModel(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE - 1, 1));
    panel.add(weight);
    JLabel weightLabel = new JLabel("Peso:");
    weightLabel.setBounds(564, 325, 34, 14);
    panel.add(weightLabel);

    JLabel edgesRecordLabel = new JLabel("Registro de aristas:");
    edgesRecordLabel.setBounds(22, 11, 150, 14);
    panel.add(edgesRecordLabel);

    edgesRecord = new JLabel("");
    edgesRecord.setVerticalAlignment(SwingConstants.TOP);
    edgesRecord.setBounds(144, 11, 612, 111);
    panel.add(edgesRecord);

    JButton btnNext = new JButton("Siguiente >>");
    btnNext.setBounds(638, 497, 136, 36);
    btnNext.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        displayEndResult(getFrame(), g);
      }
    });
    panel.add(btnNext);

    JButton btnDeleteLast = new JButton("Borrar anterior");
    btnDeleteLast.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        deleteLast();
      }
    });
    btnDeleteLast.setBounds(157, 425, 136, 36);
    panel.add(btnDeleteLast);

    JButton btnDeleteOne = new JButton("Borrar uno");
    btnDeleteOne.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        deleteOne();
      }
    });
    btnDeleteOne.setBounds(480, 425, 136, 36);
    panel.add(btnDeleteOne);

  }

  private Edge createEdge() {
    vX = (Integer) vertex1.getValue();
    vY = (Integer) vertex2.getValue();
    vWeight = (Integer) weight.getValue();

    if (vX != vY)
      return new Edge(vX, vY, vWeight);
    else {
      JOptionPane.showMessageDialog(this.panel, "no se admiten loops"); //mensajito de error
      return null;
    }
  }

  private void addEdgeToRecord() {
    Edge edge = createEdge();
    if ((edge != null) && !edges.contains(edge)) {
      edges.add(edge);
      String text = String.format("(%s,%s,%s);", edge.getX(), edge.getY(), edge.getWeight());
      record = record + text;
      edgesRecord.setText("[" + record + "]");
    } else
      JOptionPane.showMessageDialog(this.panel, "ya existe la arista dada");
  }

  private void deleteOne() {
    Edge edge = createEdge();
    if (edges.contains(edge)) {
      removeOneRecord(edge);
      edges.remove(edge);
    } else {
      String text = String.format("No existe arista (%s,%s,%s)", vX, vY, vWeight);
      JOptionPane.showMessageDialog(this.panel, text); //mensajito de error
    }
  }

  private void deleteLast() {
    if (edges.size() > 0) {
      removeLastRecord();
      edges.remove(edges.size() - 1);
    }
  }

  private void removeOneRecord(Edge edge) {
    String edgeRecord = String.format("(%s,%s,%s)", edge.getX(), edge.getY(), edge.getWeight());

    String[] recordString = record.split(";");
    for (int i=0; i<recordString.length; i++) {
      if (recordString[i].equals(edgeRecord)) {
        recordString[i] = "";
        break;
      }
    }
    record = String.join(";", recordString);
    edgesRecord.setText(String.format("[%s]", record));
  }

  private void removeLastRecord() {
    String[] recordString = record.split(";");
    recordString[recordString.length - 1] = "";
    record = String.join(";", recordString);
    edgesRecord.setText(String.format("[%s]", record));
  }

  private void displayGraphCreation(Object frame) {
    GraphCreation gc = new GraphCreation(getFrame());
    this.panel.setVisible(false);
    panel.setEnabled(false);
  }

  private void displayEndResult(JFrame frame, GraphController g) {
    if (edges.size() > 0) {
      for (Edge edge : edges) {
        g.addEdge(edge.getX(), edge.getY(), edge.getWeight());
      }
      EndResult er = new EndResult(frame, g, edges.size());
      this.panel.setVisible(false);
      panel.setEnabled(false);
    } else JOptionPane.showMessageDialog(this.panel, "no hay aristas en este grafo");
  }

  private JFrame getFrame() {
    return this.frame;
  }
}