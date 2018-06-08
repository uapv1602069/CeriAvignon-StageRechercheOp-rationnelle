import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Classe gérant le Panel contenant les boutons dans la Frame.
 * @author Pablo
 *
 */
public class ButtonPanel extends JPanel {
	/**
	 * Constructeur modifiant le fond du panel.
	 */
	public void paintComponent(Graphics g)
	{
		g.setColor(this.getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}
