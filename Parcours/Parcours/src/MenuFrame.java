import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * <b>Classe g�rant la cr�ation  et la gestion de la fen�tre secondaire contenant les param�tres de g�n�ration des graphes.</b>
 * <p>Une MenuFrame contient les �l�ments suivants :</p>
 * <ul>
 * <li>Un panel qui englobera toute la fen�tre</li>
 * <li>Une zone de texte pr�c�d�e d'un JLabel correspondant au nombre de noeuds</li>
 * <li>Une zone de texte pr�c�d�e d'un JLabel correspondant au degr� moyen</li>
 * <li>Un menu d�roulant pr�c�d� d'un JLabel correspondant au type de g�n�rateur utilis�</li>
 * <li>Deux boutons annuler et apppliquer en bas de la fen�tre</li>
 * </ul>
 * @author Pablo
 *
 */
public class MenuFrame extends JFrame 
{
	/**
	 * Conteneur global
	 */
	private JPanel container = new JPanel();
	/**
	 * Zone de texte contenant le nombre de noeuds qu'un nouveau graphe utilisera
	 */
	private JTextField nombreNoeuds = new JTextField("10");
	/**
	 * Texte de titre d�crivant la zone de texte correspondant au nombre de noeuds
	 */
	private JLabel label1 = new JLabel("Nombre de noeuds min");
	/**
	 * Zone de texte contenant le degr� moyen des noeuds dans un nouveau graphe (quand il est modifiable)
	 */
	private JTextField degreMoyen = new JTextField("2");
	/**
	 * Texte de titre du degr� moyen
	 */
	private JLabel label2 = new JLabel("   Degr� moyen noeuds");
	/**
	 * Bouton annuler qui rendra la fen�tre invisible
	 */
	private JButton cancelButton = new Button("Annuler");
	/**
	 * Bouton appliquer qui modifiera les attributs correspondants dans la frame principale et rendra cette frame invisible
	 */
	private JButton applyButton = new Button("Appliquer");
	/**
	 * Menu d�roulant permettant la s�l�ction du g�n�rateur
	 */
	private JComboBox generatorCombo;
	/**
	 * Texte de titre du menu d�roulant
	 */
	private JLabel label3 = new JLabel("Type de graphe");
	/**
	 * Frame principale de l'application
	 */
	Frame frame;
	  
	/**
	 * Constructeur d'une MenuFrame prenant en param�tre la Frame principale associ�e.
	 * @param argframe
	 */
	public MenuFrame(Frame argframe)
	{
		this.setVisible(false);
		frame = argframe;
	    container.setBackground(Color.white);
	    container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
	    
	    JPanel l1 = new JPanel();
	    l1.setLayout(new FlowLayout());
	    
	    Font police = new Font("Arial", Font.BOLD, 14);
	    nombreNoeuds.setFont(police);
	    nombreNoeuds.setPreferredSize(new Dimension(150, 30));
	    
	    l1.add(label1);
	    l1.add(nombreNoeuds);
	    
	    container.add(l1);
	    
	    JPanel l2 = new JPanel();
	    l2.setLayout(new FlowLayout());
	    
	    degreMoyen.setFont(police);
	    degreMoyen.setPreferredSize(new Dimension(150, 30));
	    
	    l2.add(label2);
	    l2.add(degreMoyen);
	    
	    container.add(l2);
	    
	    JPanel l3 = new JPanel();
	    l3.setLayout(new FlowLayout());
	    
	    String[] tab = {"Random","Barabasi","Watts","Euclidean","Banana Tree","Dorogovtsev","Grid"};
	    generatorCombo = new JComboBox(tab);
	    l3.add(label3);
	    l3.add(generatorCombo);
	    
	    container.add(l3);
	    
	    JPanel l4 = new JPanel();
	    l4.setLayout(new FlowLayout());
	    
	    l4.add(cancelButton);
	    l4.add(applyButton);
	    
	    container.add(l4);
	    
	    applyButton.addActionListener(new ButtonApplyListener());
	    cancelButton.addActionListener(new ButtonCancelListener());
	    
	    this.setUndecorated(true);
	    this.setContentPane(container);
		this.setTitle("Menu");
	    this.setSize(300, 150);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	}
	
	/**
	 * Classe interne de MenuFrame g�rant l'action de bouton permettant de confirmer les valeurs entr�es par l'utilisateur.
	 * @author Pablo
	 *
	 */
	class ButtonApplyListener implements ActionListener
	{
		/**
		 * Action sur le bouton Appliquer de la frame.
		 * Ce bouton va modifier les valeurs utilis�es par les g�n�rateurs dans la frame principale, puis rendre cette MenuFrame invisible.
		 */
		public void actionPerformed(ActionEvent arg0) 
		{ 
			int degre = 2;
			int n = 10;
			
		    try 
		    { 
		    	degre = Integer.parseInt(degreMoyen.getText()); 
		    } 
		    catch(NumberFormatException e) 
		    { 
		        degre = 2; 
		    } catch(NullPointerException e) {
		        degre = 2;
		    }
		    
		    try 
		    { 
		    	n = Integer.parseInt(nombreNoeuds.getText()); 
		    } 
		    catch(NumberFormatException e) 
		    { 
		        n = 10; 
		    } catch(NullPointerException e) {
		        degre = 10;
		    }
		    
		    if(degre != 0)
		    	frame.setDegreMoyen(degre);
		    else
		    	frame.setDegreMoyen(2);
		    frame.setNombreNoeuds(n);
		    frame.setCurrentGenerator((String)generatorCombo.getSelectedItem());
		    MenuFrame.this.setVisible(false);
		} 
	}
	
	/**
	 * Classe interne de MenuFrame g�rant l'action de bouton permettant d'annuler.
	 * @author Pablo
	 *
	 */
	class ButtonCancelListener implements ActionListener
	{
		/**
		 * Action sur le bouton Annuler de la frame.
		 * Il se contentera de rendre la Frame invisible.
		 */
		public void actionPerformed(ActionEvent arg0) 
		{      
			MenuFrame.this.setVisible(false);
		} 
	}
}
