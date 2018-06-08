import java.io.File;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.*;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.ui.swingViewer.ViewPanel;
import org.graphstream.ui.view.Viewer;
	
/**
 * Classe statique contenant les méthodes de gestion et de génération de graphe.
 * @author Pablo
 *
 */
public class ExampleGraph 
{
	/**
	 * Méthode retournant un graphe prédéfini.
	 * @return Graph
	 */
	public static Graph CreateGraph() 
	{
		Graph graph = new MultiGraph("newGraph");
		graph.addNode("A");
		graph.addNode("B");
		graph.addNode("C");
		graph.addNode("D");
		graph.addNode("E");
		graph.addNode("F");
		graph.addNode("G");
		graph.addNode("H");
		graph.addNode("I");
		graph.addNode("J");
		graph.addEdge("AB", "A", "B");
		graph.addEdge("BC", "B", "C");
		graph.addEdge("CD", "C", "D");
		graph.addEdge("CE", "C", "E");
		graph.addEdge("AF", "A", "F");
		graph.addEdge("BF", "B", "F");
		graph.addEdge("FG", "F", "G");
		graph.addEdge("FH", "F", "H");
		graph.addEdge("HJ", "H", "J");
		graph.addEdge("JI", "J", "I");
		graph.addEdge("FI", "F", "I");
		
	    for (Node node : graph) 
	    {
	        node.addAttribute("ui.label", node.getId());
	    }
		
		graph.addAttribute("ui.antialias");
		graph.addAttribute("ui.stylesheet", "url('file:///../style/GraphSheet.txt')");
		
		return graph;
	}
	
	/**
	 * Méthode retournant un graphe aléatoire prenant en argument le degré moyen de chaque noeud ainsi que le nombre de noeuds.
	 * @param nodeCount
	 * @param avgDegree
	 * @return Random Graph
	 */
	public static Graph CreateRandomGraph(int nodeCount, int avgDegree)
	{
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
	    Graph graph = new MultiGraph("Random");
	    Generator gen = new RandomGenerator(avgDegree);
	    gen.addSink(graph);
	    gen.begin();
	    for(int i=0; i<nodeCount; i++)
	    	gen.nextEvents();
	    gen.end();
	    
	    for (Node node : graph) 
	    {
	        node.addAttribute("ui.label", node.getId());
	    }
		graph.addAttribute("ui.antialias");
		graph.addAttribute("ui.stylesheet", "node " + 
				"{" + 
				"	size: 30px;" + 
				"	shape: circle;" + 
				"	fill-color: white;" + 
				"	stroke-mode: plain;" + 
				"	stroke-color: black;" + 
				"	text-size: 20;" + 
				"}" + 
				"" + 
				"node:clicked " + 
				"{" + 
				"	fill-color: red;" + 
				"}" + 
				"" + 
				"node.current" + 
				"{" + 
				"	fill-color: #4486ff;" + 
				"}" + 
				"" + 
				"node.neighbor" + 
				"{" + 
				"	fill-color: #2bff19;" + 
				"}" + 
				"" + 
				"node.stacked" + 
				"{" + 
				"	fill-color: #a3ff9b;" + 
				"}" + 
				"" + 
				"node.visited" + 
				"{" + 
				"	fill-color: #ccdeff;" + 
				"}" + 
				"" + 
				"edge.current" + 
				"{" + 
				"	fill-color: #4486ff; " + 
				"}");
	    return graph;
	}
	
	/**
	 * Méthode retournant un Panel pour un graphe passé en argument.
	 * @param graph
	 * @return Panel de vue du graphe
	 */
	public static ViewPanel CreatePanel(Graph graph)
	{
		Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_GUI_THREAD);
		viewer.enableAutoLayout();
        ViewPanel viewPanel = viewer.addDefaultView(false);
        return viewPanel;
	}
	
	/**
	 * Méthode modifiant un graphe pour le regénérer de façon aléatoire selon le nombre de noeuds et le degré moyen passés en argument.
	 * Il utilisera également un générateur différent en fonction de celui passé en argument.
	 * Si le nombre de noeuds ou le degré spécifié est trop élevé, il utilisera une valeur par défaut.
	 * Certains générateurs ne prennent pas de degré moyen en argument. l'argument correspondant- sera alors ignoré. 
	 * @param graph
	 * @param nodeCount
	 * @param avgDegree
	 */
	public static void RegenerateRandomGraph(Graph graph, int nodeCount, int avgDegree, String generator)
	{
		Generator gen = new RandomGenerator(avgDegree);
		if(nodeCount > 50)
			nodeCount = 50;
		if(avgDegree > 5)
			avgDegree = 5;
		if(generator == "Random")
			gen = new RandomGenerator(avgDegree);
		if(generator == "Barabasi")
			gen = new BarabasiAlbertGenerator(avgDegree);
		if(generator == "Watts")
			gen = new WattsStrogatzGenerator(nodeCount, 2, 1);
		if(generator == "Euclidean")
			gen = new RandomEuclideanGenerator();
		if(generator == "Banana Tree")
		{
			gen = new BananaTreeGenerator();
			if(nodeCount > 10)
				nodeCount = 10;
		}
		if(generator == "Dorogovtsev")
			gen = new DorogovtsevMendesGenerator();
		if(generator =="Grid")
		{
			gen = new GridGenerator();
			if(nodeCount > 10)
				nodeCount = 10; 
		}
	    gen.addSink(graph);
	    gen.begin();
	    for(int i=0; i<nodeCount; i++)
	    	gen.nextEvents();
	    gen.end();
	    
	    for (Node node : graph) 
	    {
	        node.addAttribute("ui.label", node.getId());
	    }
		graph.addAttribute("ui.antialias");
		graph.addAttribute("ui.stylesheet", "node " + 
				"{" + 
				"	size: 30px;" + 
				"	shape: circle;" + 
				"	fill-color: white;" + 
				"	stroke-mode: plain;" + 
				"	stroke-color: black;" + 
				"	text-size: 20;" + 
				"}" + 
				"" + 
				"node:clicked " + 
				"{" + 
				"	fill-color: red;" + 
				"}" + 
				"" + 
				"node.current" + 
				"{" + 
				"	fill-color: #4486ff;" + 
				"}" + 
				"" + 
				"node.neighbor" + 
				"{" + 
				"	fill-color: #2bff19;" + 
				"}" + 
				"" + 
				"node.stacked" + 
				"{" + 
				"	fill-color: #a3ff9b;" + 
				"}" + 
				"" + 
				"node.visited" + 
				"{" + 
				"	fill-color: #ccdeff;" + 
				"}" + 
				"" + 
				"edge.current" + 
				"{" + 
				"	fill-color: #4486ff; " + 
				"}");
	}
}