/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taller07;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author NN
 */
import java.util.*; 
public abstract class Grafo {
	protected int size;

	public Grafo(int vertices) {
		size = vertices;
	}

	public abstract void addArc(int source, int destination, int weight);

	public abstract ArrayList<Integer> getSuccessors(int vertex);

	public abstract int getWeight(int source, int destination);

	public int size() {
		return size;
	}
}