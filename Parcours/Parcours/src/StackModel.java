import java.util.Vector;

import javax.swing.table.AbstractTableModel;

/**
 * Mod�le des tableaux utilis�s dans la Frame.
 * @author Pablo
 *
 */
public class StackModel extends AbstractTableModel 
{	
	/**
	 * Vecteur qui contiendra les donn�es de la table.
	 */
    public Vector data;
    /**
     * Nom de la table qui appara�tra en Header.
     */
    private String title;
    
	public StackModel(Vector data, String title)
	{
		this.data = data;
	    this.title = title;
	}

	/**
	 * Retourne 1 car les tableaux que nous utiliserons ne contiendrons qu'une seule colonne.
	 * @return 1
	 */
	public int getColumnCount() 
	{
		return 1;
	}

	public int getRowCount() 
	{
		return data.size();
	}

	public Object getValueAt(int row, int col) 
	{
		return this.data.elementAt(row);
	}
	
	public String getColumnName(int col) 
	{
		return title;
	}
}
