package com.progra3.javaMST.back.application.utils;

public class Edge {

  private final Integer x;
  private final Integer y;
  private final Integer weight;

  public Edge(final Integer x, final Integer y, final Integer weight) {
    this.x = x;
    this.y = y;
    this.weight = weight;
  }

  public Integer getX() {
    return x;
  }

  public Integer getY() {
    return y;
  }

  public Integer getWeight() {
    return weight;
  }

  @Override
  public String toString() {
    return "Edge{" +
      "x=" + x +
      ", y=" + y +
      ", weight=" + weight +
      '}';
  }
}
