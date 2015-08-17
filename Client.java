package Client;

import Server.Edge;
import Server.Vertex;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client implements Serializable, Runnable {

    private static final Logger LOG = Logger.getLogger(Client.class.getName());
    private static Random r = new Random();
    public final int port = 1000 + r.nextInt(1000);
    public String ip = "";
    private static Partisi partisi = new Partisi();

    @Override
    public void run() {
        Client client = new Client();
        Thread thread = new Thread(client);
        thread.start();

//        try {
//            ServerSocket ss = new ServerSocket(port);
////            ip = ss.getInetAddress().getHostAddress();
//            ip = "localhost";
//            LOG.info("client listenning at: " + ip + " port:" + port);
//            while (true) {
//                Socket server = ss.accept();
//                BufferedReader br = new BufferedReader(
//                        new InputStreamReader(server.getInputStream()));
//                LOG.info(br.readLine());
//            }
//        } catch (IOException ex) {
//            LOG.log(Level.SEVERE, null, ex);
//        }
    }

    public static void main(String[] args) {
        BufferedReader bis = null;
        try {
            Socket server = new Socket("localhost", 9991);
            LOG.info("Client Connected");
            while (true) {
//                bis = new BufferedReader(new InputStreamReader(server.getInputStream()));
//                String output = bis.readLine();
//                if (output.equals("jalan")) {
//                    ObjectInputStream is = new ObjectInputStream(server.getInputStream());
//                    Vertex x = (Vertex) is.readObject();
//                    DBFS(x);
//                } else {
                ObjectInputStream is = new ObjectInputStream(server.getInputStream());
                partisi = new Partisi();
                partisi = (Partisi) is.readObject();
                System.out.println(partisi.toString());
//                }
            }
        } catch (ClassNotFoundException | IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public static void DBFS(Vertex vs) {
        ArrayList<Vertex> visited = new ArrayList<Vertex>();
        ArrayList<Vertex> vcurrent = new ArrayList<Vertex>();
        ArrayList<Vertex> next = new ArrayList<Vertex>();
        /*
         * Subset vertex diinisialisasi 
         * vcurrent = vs
         */
        if (cekVs(vs, partisi.getArr())) {
            vcurrent.add(vs);
        } else {
            vcurrent.clear();
        }
        while (!vcurrent.isEmpty()) {
            for (Vertex v : vcurrent) {
                ArrayList<Vertex> Nv = new ArrayList<Vertex>();
                Nv = cariNv(v, Union(visited, vcurrent));
                for (Vertex u : subset(Nv, partisi.getBoundaries())) {
                    //send u to all Nx such that u is Nx
                    
                System.out.println(Nv);
                visited = updateVvisited(visited, v);
                next = updateNext(next, Nv);
                }
                for(Vertex u : partisi.getBoundaries()){
                /*send by othernodes Nx*/
                    
                }
                //wait until all nodes have processed their corresponding current
            }
            vcurrent = next; 
            next.clear();
        }
    }

    public static boolean cekNv(Vertex v, ArrayList<Vertex> V) {
        for (Vertex x : V) {
            if (x.getId() == x.getId()) {
                return true;
            }
        }
        return false;
    }

    public static ArrayList<Vertex> Union(ArrayList<Vertex> Vvisited, ArrayList<Vertex> Vcurrent) {
        ArrayList<Vertex> union = new ArrayList<>();
        union.addAll(Vvisited);
        union.addAll(Vcurrent);
        return union;
    }

    public static ArrayList<Vertex> cariNv(Vertex v, ArrayList<Vertex> union) {
        ArrayList<Vertex> Nv = new ArrayList<>();
        for (Edge e : v.getEdges()) {
            if (!cekNv(e.getVertexNeighbour(), union)) {
                Nv.add(e.getVertexNeighbour());
            }
        }
        return Nv;
    }

    public static boolean cekVs(Vertex v, ArrayList<Vertex> V) {
        for (Vertex u : V) {
            if (u.getId() == v.getId()) {
                return true;
            }
            for (Edge e : u.getEdges()) {
                if (v.getId() == e.getVertexNeighbour().getId()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static ArrayList<Vertex> subset(ArrayList<Vertex> nv, ArrayList<Vertex> b) {
        ArrayList<Vertex> nvb = new ArrayList<>(nv);
        nvb.retainAll(b);
        return nvb;
    }
    
    //update Vvisited line 12
    public static ArrayList<Vertex> updateVvisited(ArrayList<Vertex> Vvisited, Vertex v){
        ArrayList<Vertex> updateVvisited = new ArrayList<>();
        updateVvisited.retainAll(Vvisited);
        return updateVvisited ;
    }
    //line 12
    public static ArrayList<Vertex> updateNext(ArrayList<Vertex> next, ArrayList<Vertex> nv){
        return null;
    }

}
