package Client;

import Server.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Partisi implements Serializable {
    private ArrayList<Vertex> arr = new ArrayList<>();
    private ArrayList<Vertex> allArray = new ArrayList<Vertex>();
    private ArrayList<Vertex> Boundaries = new ArrayList<>();

    public Partisi(){}

    public void setAllarray() {
        for (Vertex v : arr) {
            if (!cekaallarr(v)) {
                allArray.add(v);
            }
            for (Edge e : v.getEdges()) {
                if (!cekaallarr(e.getVertexNeighbour())) {
                    allArray.add(e.getVertexNeighbour());
                }
            }
        }
    }

    public boolean cekaallarr(Vertex x) {
        for (Vertex v : allArray) {
            if (v.getId() == x.getId()) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Vertex> getAllarray() {
        return allArray;
    }

    public ArrayList<Vertex> getBoundaries() {
        return Boundaries;
    }

    public void setBoundaries(ArrayList<Vertex> Boundaries) {
        this.Boundaries = new ArrayList<>();
        this.Boundaries = Boundaries;
    }

    public ArrayList<Vertex> getArr() {
        return arr;
    }

    public void setArr(ArrayList<Vertex> arr) {
        this.arr = arr;
    }

    public String viewPartisi() {
        String a = "";
        for (Vertex i : arr) {
            a += i.getId() + ", ";
        }
        return a;
    }

    public String viewBoundaries() {
        String a = "";
        for (Vertex i : Boundaries) {
            a += i.getId() + ", ";
        }
        return a;
    }

    public String toString() {
        return "Partisi : " + viewPartisi() + "\nBoundaries : " + viewBoundaries();
    }

}
