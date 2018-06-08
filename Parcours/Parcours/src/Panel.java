import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Classe gérant les panneaux généraux pour la Frame.
 * @author Pablo
 *
 */
public class Panel extends JPanel {
	/**
	 * Méthode initalisant la couleur du fond du panneau en blanc.
	 */
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
