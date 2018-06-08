import java.util.Iterator;
import java.util.Stack;
import java.util.Vector;

import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;

/**
 * Classe g�rant l'�x�cution de l'algorithme de plus court chemin ainsi que les modifications � apporter au graphe et � la Frame au cours de cet algorithme.
 * @author Pablo
 *
 */
public class Parcours {

	/**
	 * Graphe sur lequel l'algorithme sera �x�cut�
	 */
	Graph graph;
	/**
	 * Pile qui sera utilis�e par l'Algorithme
	 */
	Stack stack = new Stack();
	/**
	 * Vecteur qui contiendra les noeuds visit�s
	 */
	Vector visite = new Vector();
	/**
	 * Frame dans laquelle l'algorithme sera �x�cut�
	 */
	Frame frame;
	/**
	 * Bool�en permettant le passage � l'�tape suivant ou non.
	 */
	boolean nextStep = false;
	
	/**
	 * Constructeur prenant en param�tre la Frame sur laquelle l'algorithme sera ex�cut�.
	 * @param argFrame
	 */
	public Parcours(Frame argFrame)
	{
		stack = new Stack();
		visite = new Vector();
		frame = argFrame;
	}
	
	/**
	 * Constructeur prenant en param�tre un graphe, le noeud de d�part et la Frame.
	 * @param argGraph
	 * @param firstNode
	 * @param argFrame
	 */
	public Parcours(Graph argGraph, Node firstNode, Frame argFrame)
	{
		graph = argGraph;
		stack.push(firstNode.getId());
		frame = argFrame;
	}
	
	/**
	 * Fonction ex�cutant l'algorithme et modifiant le graphe et la Frame.
	 * Elle sera mise en pause entre les diff�rentes �tapes de l'algorithme et reprendra lors de l'action du bouton Suivant de la Frame.
	 */
	public void doParcours()
	{
		try        
		{
		    Thread.sleep(980);
		} 
		catch(InterruptedException ex) 
		{
		    Thread.currentThread().interrupt();
		}
		Node currentNode = null;
		String currentNodeId;
		Edge currentEdge;
		boolean firstIteration = true;
		while(nextStep == false)
		{
			try        
			{
			    Thread.sleep(300);
			} 
			catch(InterruptedException ex) 
			{
			    Thread.currentThread().interrupt();
			}
		}
		nextStep = false;
		while(!stack.isEmpty())
		{
			currentNodeId = (String)stack.pop();
			currentNode = graph.getNode(currentNodeId);
			currentNode.addAttribute("ui.class", "current");		
			visite.add(currentNodeId);
			frame.getVisiteModel().fireTableDataChanged();
			Iterator<Node> neighbor = graph.getNode(currentNodeId).getNeighborNodeIterator();
			while (neighbor.hasNext())
			{
				Node temp = neighbor.next();
				if(!visite.contains(temp.getId()) && !stack.contains(temp.getId()))
				{
					temp.addAttribute("ui.class", "neighbor");
					stack.push(temp.getId());
					frame.getStackModel().fireTableDataChanged();
					currentEdge = currentNode.getEdgeBetween(temp);
					currentEdge.addAttribute("ui.hide");
					currentEdge = graph.addEdge(graph.getEdge(graph.getEdgeCount()-1).getId() + 1, currentNode, temp, true);
					currentEdge.addAttribute("ui.class", "current");
				}
				frame.getStackModel().fireTableDataChanged();			
			}
			while(nextStep == false)
			{
				try        
				{
				    Thread.sleep(300);
				} 
				catch(InterruptedException ex) 
				{
				    Thread.currentThread().interrupt();
				}
			}
			nextStep = false;
			currentNode.removeAttribute("ui.class");
			currentNode.addAttribute("ui.class", "visited");
			for(int i = 0; i < stack.size(); i++)
			{
				graph.getNode((String)stack.get(i)).removeAttribute("ui.class");
				graph.getNode((String)stack.get(i)).addAttribute("ui.class", "stacked");
			}
			firstIteration = false;
		}
	}
	
	public Stack getStack()
	{
		return stack;
	}
	
	public Vector getVisite()
	{
		return visite;
	}
	
	public void setNextStep(boolean bool)
	{
		nextStep = bool;
	}

	
}
