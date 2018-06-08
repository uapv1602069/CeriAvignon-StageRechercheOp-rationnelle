import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Classe g�rant les panneaux g�n�raux pour la Frame.
 * @author Pablo
 *
 */
public class Panel extends JPanel {
	/**
	 * M�thode initalisant la couleur du fond du panneau en blanc.
	 */
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
