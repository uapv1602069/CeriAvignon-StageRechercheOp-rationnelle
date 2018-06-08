import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.graphstream.graph.Graph;
import org.graphstream.ui.swingViewer.ViewPanel;

/**
 * <b>Classe g�rant la cr�ation  et la gestion de la fen�tre et de ses composants ainsi que les �v�nements sur les boutons.</b>
 * <p>Une Frame contient les �l�ments suivants :</p>
 * <ul>
 * <li>Un graphe</li>
 * <li>Un panel qui englobera toute la fen�tre</li>
 * <li>Un panel pour les boutons qui se situera en bas de la fen�tre</li>
 * <li>Deux panels qui contiendront des tableaux � gauche de la fen�tre</li>
 * <li>Un panel contenant le graphe au centre de la fen�tre</li>
 * <li>Deux boutons suivant et nouveau graphe dans le panneau de boutons</li>
 * <li>Deux tables qui contiendront une pile et un tableau de visite ainsi que leur mod�les</li>
 * <li>Le mod�le de l'algorithme utlils�</li>
 * <li>Le thread dans lequel l'algo sera utlis�</li>
 * </ul>
 * @author Pablo
 *
 */
public class Frame extends JApplet
{
	/**
	 * Corresponds au nombre de noeuds que contiendra un nouveau graphe al�atoire
	 */
	private int nombreNoeuds = 10;
	/**
	 * Corresponds au degr� moyen des noeuds d'un nouveau graphe al�atoire (quand c'est possible pour le graphe utilis�)
	 */
	private int degreMoyen = 2;
	/**
	 * Corresponds au type de g�n�rateur qui sera utilis�
	 */
	private String currentGenerator = "random";
	/**
	 * Correspond � la Frame secondaire qui contiendra les param�tres de g�n�ration de graphe.
	 */
	private MenuFrame menuFrame = new MenuFrame(this);
	/**
	 * Graphe utilis� pour l'algorithme.
	 */
	private Graph graph = ExampleGraph.CreateRandomGraph(nombreNoeuds, degreMoyen);
	/**
	 * Panneau de contenu g�n�ral englobant toute la fen�tre.
	 */
	private JPanel panel = new Panel();
	/**
	 * Panneau contenant les boutons qui sera situ� en bas de la fen�tre.
	 */
	private JPanel buttonPanel = new ButtonPanel();
	/**
	 * Panneau qui contiendra les deux panneaux des tables et qui sera situ� � gauche de la fen�tre.
	 */
	private JPanel TablePanel = new Panel();
	/**
	 * Panneau scrollable qui contiendra le tableau correspondant � la pile.
	 */
	private JScrollPane StackPanel = new JScrollPane();
	/**
	 * Panneau scrollable qui contiendra le tableau correspondant aux noeuds visit�s.
	 */
	private JScrollPane VisitePanel = new JScrollPane();
	/**
	 * Panneau de vue du graphe qui sera situ� au centre de la Frame.
	 */
	private ViewPanel graphPanel = ExampleGraph.CreatePanel(graph);
	/**
	 * Bouton qui permettra de passer � l'�tape suivant de l'algo.
	 */
	private JButton button = new Button("Suivant");
	/**
	 * Bouton qui permettra de recommencer l'algorithme sur un nouveau graphe
	 */
	private JButton button2 = new Button("Nouveau Graphe");
	/**
	 * Bouton qui permettra d'ouvrir les param�tres du graphe
	 */
	private JButton button3 = new Button("Param�tres");
	/**
	 * Titre de la fen�tre
	 */
	private JLabel label1 = new JLabel();
	/**
	 * Tableau de visite.
	 */
	private JTable visite = new JTable();
	/**
	 * Tableau repr�sentant la Pile.
	 */
	private JTable stack = new JTable();
	/**
	 * Mod�le de tableau pour la pile.
	 */
	private StackModel stackModel;
	/**
	 * Mod�le de tableau pour les visites.
	 */
	private StackModel visiteModel;
	/**
	 * Mod�le d'algorithme utilis�
	 */
	private Parcours algoModel;
	/**
	 * Thread �x�cutant l'algorithme.
	 */
	private Thread algoThread;
	
	/**
	 * Constructeur de la fen�tre.
	 */
	public Frame() 
	{	
	    AlgoThread newThread = new AlgoThread("AlgoThread", this);
	    algoThread = newThread;
	    algoThread.start();
	    
		while (algoModel == null)
		{
			try        
			{
				System.out.println("Waiting for algoModel creation");
			    Thread.sleep(300);
			} 
			catch(InterruptedException ex) 
			{
			    Thread.currentThread().interrupt();
			}
		}
		stackModel = new StackModel(algoModel.stack, "Pile");
		visiteModel = new StackModel(algoModel.visite, "Visite");
		stack = new JTable(stackModel);
		visite = new JTable(visiteModel);
		
	    this.setSize(1000, 700);
	    
	    buttonPanel.setLayout(new FlowLayout());
	    buttonPanel.add(button3);
	    buttonPanel.add(button2);
	    buttonPanel.add(button);
	    
   
	    button2.setPreferredSize(new Dimension(150,30));	
	    button.setPreferredSize(new Dimension(150,30));
	    button3.setPreferredSize(new Dimension(150,30));
	    
	    label1.setText("Parcours en profondeur");
	    label1.setHorizontalAlignment(JLabel.CENTER);
	    label1.setFont(new Font("Calibri", Font.BOLD, 26));
	    
	    StackPanel = new JScrollPane(stack);
	    StackPanel.setPreferredSize(new Dimension(100,getHeight()));
	    
	    VisitePanel = new JScrollPane(visite);
	    VisitePanel.setPreferredSize(new Dimension(100,getHeight()));
	    
	    TablePanel.setLayout(new BorderLayout());
	    TablePanel.add(StackPanel, BorderLayout.WEST);
	    TablePanel.add(VisitePanel, BorderLayout.EAST);
	    
	    panel.setLayout(new BorderLayout());
	    panel.add(buttonPanel, BorderLayout.SOUTH);
	    panel.add(label1, BorderLayout.NORTH);
	    panel.add(graphPanel, BorderLayout.CENTER);
	    panel.add(TablePanel, BorderLayout.WEST);
	        
	    this.setContentPane(panel);
	    this.setVisible(true);
	    buttonPanel.repaint();
	    
	    button.addActionListener(new ButtonNextListener());
	    button2.addActionListener(new ButtonNewListener());
	    button3.addActionListener(new ButtonOptionsListener());
	    
	    stackModel.fireTableDataChanged();
	    this.repaint();
	}
	
	/**
	 * Classe interne de Frame g�rant l'action sur le bouton Suivant.
	 * @author Pablo
	 *
	 */
	class ButtonNextListener implements ActionListener{
		/**
		 * Action sur le bouton suivant
		 */
		public void actionPerformed(ActionEvent arg0) 
		{      
			algoModel.setNextStep(true);
		} 
	}
	
	/**
	 * Classe interne de Frame g�rant l'action de bouton permettant de g�n�rer un nouveau graphe.
	 * @author Pablo
	 *
	 */
	class ButtonNewListener implements ActionListener{
		/**
		 * Action sur bouton Nouveau Grpahe
		 */
		public void actionPerformed(ActionEvent arg0) 
		{      
			algoThread.stop();
			AlgoThread newThread = new AlgoThread("AlgoThread", Frame.this);
		    algoThread = newThread;
		    algoModel.stack.clear();
		    algoModel.visite.clear();
			stackModel.fireTableDataChanged();
			visiteModel.fireTableDataChanged();
			graph.clear();
			ExampleGraph.RegenerateRandomGraph(graph, nombreNoeuds, degreMoyen, currentGenerator);
		    algoThread.start();	
		    try        
			{
				System.out.println("Waiting for algoModel creation");
			    Thread.sleep(300);
			} 
			catch(InterruptedException ex) 
			{
			    Thread.currentThread().interrupt();
			}
		    stackModel.data = algoModel.stack;
			visiteModel.data = algoModel.visite;
		    System.out.println(currentGenerator);	    
		} 
	}
	
	/**
	 * Classe interne de Frame g�rant l'action de bouton permettant d'ouvrir le menu.
	 * @author Pablo
	 *
	 */
	class ButtonOptionsListener implements ActionListener{
		/**
		 * Action sur le bouton options qui se contente de rendre le menu visible.
		 */
		public void actionPerformed(ActionEvent arg0)
		{
			menuFrame.setVisible(true);
		}
	}
	
	public Graph getGraph()
	{
		return graph;
	}
	
	
	public Parcours getAlgoModel()
	{
		return algoModel;
	}
	
	public void setAlgoModel(Parcours model)
	{
		algoModel = model;
	}
	
	public StackModel getStackModel()
	{
		return stackModel;
	}
	
	public StackModel getVisiteModel()
	{
		return visiteModel;
	}
	
	public void setDegreMoyen(int n)
	{
		degreMoyen = n;
	}
	
	public void setNombreNoeuds(int n)
	{
		nombreNoeuds = n;
	}
	
	public void setCurrentGenerator(String s)
	{
		currentGenerator = s;
	}
	
	public void init() 
	{
		Frame applet = new Frame();
	}
	
	/*public static void main(String[] args)
	{
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		Frame applet = new Frame();
	}*/
}
