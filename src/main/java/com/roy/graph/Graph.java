package com.roy.graph;

import java.util.*;

/**
 * This class models a simple, undirected graph using an 
 * incidence list representation. Vertices are identified 
 * uniquely by their labels, and only unique vertices are allowed.
 * At most one Edge per vertex pair is allowed in this Graph.
 * 
 * Note that the Graph is designed to manage the Edges. You
 * should not attempt to manually add Edges yourself.
 * 
 * @author Michael Levet
 * @date June 09, 2015
 */
public class Graph {
    
    private Map<String, Vertex> vertices;
    private Map<Edge, Integer> edges;
    
    public Graph(){
        this.vertices = new HashMap<String, Vertex>();
        this.edges = new HashMap<Edge, Integer>();
    }
    
    /**
     * This constructor accepts an ArrayList<Vertex> and populates
     * this.vertices. If multiple Vertex objects have the same label,
     * then the last Vertex with the given label is used. 
     * 
     * @param vertices The initial Vertices to populate this Graph
     */
    public Graph(ArrayList<Vertex> vertices){
        this.vertices = new HashMap<String, Vertex>();
        this.edges = new HashMap<Edge, Integer>();
        
        for(Vertex v: vertices){
            this.vertices.put(v.getLabel(), v);
        }
        
    }
    
    /**
     * This method adds am edge between Vertices one and two
     * of weight 1, if no Edge between these Vertices already
     * exists in the Graph.
     * 
     * @param one The first vertex to add
     * @param two The second vertex to add
     * @return true iff no Edge relating one and two exists in the Graph
     */
    public boolean addEdge(Vertex one, Vertex two){
        return addEdge(one, two, 1);
    }
    
    
    /**
     * Accepts two vertices and a weight, and adds the edge 
     * ({one, two}, weight) iff no Edge relating one and two 
     * exists in the Graph.
     * 
     * @param one The first Vertex of the Edge
     * @param two The second Vertex of the Edge
     * @param weight The weight of the Edge
     * @return true iff no Edge already exists in the Graph
     */
    public boolean addEdge(Vertex one, Vertex two, int weight){
        if(one.equals(two)){
            return false;   
        }
       
        //ensures the Edge is not in the Graph
        Edge e = new Edge(one, two, weight);
        if(edges.containsKey(e.hashCode())){
            return false;
        }
       
        //and that the Edge isn't already incident to one of the vertices
        else if(one.containsNeighbor(e) || two.containsNeighbor(e)){
            return false;
        }
            
        edges.put(e, e.getWeight());
        one.addNeighbor(e);
        two.addNeighbor(e);
        return true;
    }
    
    /**
     * 
     * @param e The Edge to look up
     * @return true iff this Graph contains the Edge e
     */
    public boolean containsEdge(Edge e){
        if(e.getOne() == null || e.getTwo() == null){
            return false;
        }
        
        return this.edges.containsKey(e.hashCode());
    }
    
    
    /**
     * This method removes the specified Edge from the Graph,
     * including as each vertex's incidence neighborhood.
     * 
     * @param e The Edge to remove from the Graph
     * @return Edge The Edge removed from the Graph
     */
    public Edge removeEdge(Edge e){
       e.getOne().removeNeighbor(e);
       e.getTwo().removeNeighbor(e);
       this.edges.remove(e);
       
       return e;
    }
    
    /**
     * 
     * @param vertex The Vertex to look up
     * @return true iff this Graph contains vertex
     */
    public boolean containsVertex(Vertex vertex){
        return this.vertices.get(vertex.getLabel()) != null;
    }
    
    /**
     * 
     * @param label The specified Vertex label
     * @return Vertex The Vertex with the specified label
     */
    public Vertex getVertex(String label){
        return vertices.get(label);
    }
    
    /**
     * This method adds a Vertex to the graph. If a Vertex with the same label
     * as the parameter exists in the Graph, the existing Vertex is overwritten
     * only if overwriteExisting is true. If the existing Vertex is overwritten,
     * the Edges incident to it are all removed from the Graph.
     * 
     * @param vertex
     * @param overwriteExisting
     * @return true iff vertex was added to the Graph
     */
    public boolean addVertex(Vertex vertex, boolean overwriteExisting){
        Vertex current = this.vertices.get(vertex.getLabel());
        if(current != null){
            if(!overwriteExisting){
                return false;
            }
            
            while(current.getNeighborCount() > 0){
                this.removeEdge(current.getNeighbor(0));
            }
        }
   
        vertices.put(vertex.getLabel(), vertex);
        return true;
    }
    
    public boolean addVertex(Vertex vertex) {
    	return addVertex(vertex, true);
    }
    
    /**
     * 
     * @param label The label of the Vertex to remove
     * @return Vertex The removed Vertex object
     */
    public Vertex removeVertex(String label){
        Vertex v = vertices.remove(label);
        
        while(v.getNeighborCount() > 0){
            this.removeEdge(v.getNeighbor((0)));
        }
        
        return v;
    }
    
    /**
     * 
     * @return Set<String> The unique labels of the Graph's Vertex objects
     */
    public Set<String> vertexKeys(){
        return this.vertices.keySet();
    }
    
    /**
     * 
     * @return Set<Edge> The Edges of this graph
     */
    public Set<Edge> getEdges(){
        return this.edges.keySet();
    }
    
    /**
     * Visit all possible vertex only once from Vertex startpoint
     * print the vertex's label when visiting the vertex
     * 
     * @param startpoint
     */
    public void visitAllVertexes(Vertex startpoint) {
    	//
    }
/*
       San Francisco ---  Oakland
           |                 \
           Daly City         Alameda
              \                \
             San Mateo \     San Leandro
                \        \       \
               Menlo Park  \___ Hayward  ---- Pleasanton ----- Livermore
                  \                \              |             /
                 Palo Alto ----- Union City      /            /
                    \                \        /             /
                 Montain View      Fremont /--------------/
                      \                \
        Cupertino -- Santa Clara      Milpitas
            |           \            /
          Saratoga -------- San Jos
 */
    public static void main(String[] args) {
    	Graph bay = new Graph();
    	Vertex sf = new Vertex("San Francisco");
    	bay.addVertex(sf);
    	Vertex oa = new Vertex("Oakland");
    	bay.addVertex(oa);    	
    	Vertex dc = new Vertex("Daly City");
    	bay.addVertex(dc);
    	Vertex al = new Vertex("Alameda");
    	bay.addVertex(al);
    	Vertex sm = new Vertex("San Mateo");
    	bay.addVertex(sm);
    	Vertex sl = new Vertex("San Leandro");
    	bay.addVertex(sl);
    	Vertex mp = new Vertex("Menlo Park");
    	bay.addVertex(mp);
    	Vertex ha = new Vertex("Hayward");
    	bay.addVertex(ha);
    	Vertex pl = new Vertex("Pleasanton");
    	bay.addVertex(pl);
    	Vertex li = new Vertex("Livermore");
    	bay.addVertex(li);
    	Vertex pa = new Vertex("Palo Alto");
    	bay.addVertex(pa);
    	Vertex uc = new Vertex("Union City");
    	bay.addVertex(uc);
    	Vertex mv = new Vertex("Montain View");
    	bay.addVertex(mv);
    	Vertex fr = new Vertex("Fremont");
    	bay.addVertex(fr);
    	Vertex cu = new Vertex("Cupertino");
    	bay.addVertex(cu);
    	Vertex sc = new Vertex("Santa Clara");
    	bay.addVertex(sc);
    	Vertex mi = new Vertex("Milpitas");
    	bay.addVertex(mi);
    	Vertex sa = new Vertex("Saratoga");
    	bay.addVertex(sa);
    	Vertex sj = new Vertex("San Jose");
    	bay.addVertex(sj);
    	
    	// bay
    	bay.addEdge(sf, oa, 5);
    	bay.addEdge(sf, dc, 2);
    	bay.addEdge(dc, sm, 1);
    	bay.addEdge(sm, mp, 2);
    	bay.addEdge(mp, pa, 1);
    	bay.addEdge(pa, mv, 3);
    	bay.addEdge(mv, sc, 2);
    	bay.addEdge(sc, cu, 3);
    	bay.addEdge(cu, sa, 2);
    	bay.addEdge(sa, sj, 2);
    	bay.addEdge(sc, sj, 2);
    	
    	//east bay
    	bay.addEdge(oa, al, 1);
    	bay.addEdge(al, sl, 2);
    	bay.addEdge(sl, ha, 1);
    	bay.addEdge(ha, pl, 8);
    	bay.addEdge(pl, li, 9);
    	bay.addEdge(pl, fr, 10);
    	bay.addEdge(li, fr, 16);
    	bay.addEdge(ha, uc, 3);
    	bay.addEdge(uc, fr, 3);
    	bay.addEdge(fr, mi, 2);
    	bay.addEdge(mi, sj, 5);
    	
    	bay.visitAllVertexes(sf);
    }
    
}
