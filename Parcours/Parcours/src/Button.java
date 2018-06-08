import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

/**
 * Classe gérant les boutons dans la Frame.
 * @author Pablo
 *
 */
public class Button extends JButton implements MouseListener 
{
	/**
	 * Nom du bouton.
	 */
	private String name;
	
	/**
	 * Constructeur du bouton.
	 * @param text
	 */
	public Button(String text) 
	{
		super(text);
		this.name = text;
		this.addMouseListener(this);
	}
	
	
	public void mouseClicked(MouseEvent event) { }
	public void mouseEntered(MouseEvent event) { }
	public void mouseExited(MouseEvent event) { }
	public void mousePressed(MouseEvent event) { }
	public void mouseReleased(MouseEvent event) { }  
}

