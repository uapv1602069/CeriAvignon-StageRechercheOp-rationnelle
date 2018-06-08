/**
 * Constructeur du Thread �x�cutant l'algorithme.
 * @author Pablo
 *
 */
public class AlgoThread extends Thread 
{
	/**
	 * Frame dans lequel le thread sera �x�cut�.
	 */
	Frame frame;
	
	/**
	 * Constructeur du Thread qui aura un nom de une Frame associ�e.
	 * @param name
	 * @param argFrame
	 */
	public AlgoThread(String name, Frame argFrame)
	{
	  super(name);
	  frame = argFrame;
	}
	
	/**
	 * Fonction appel�e � l'�x�cution du Thread initialisant l'algorithme dans la Frame et lan�ant l'algorithme.
	 */
	public void run()
	{
		Parcours model = new Parcours(frame.getGraph(), frame.getGraph().getNode(0), frame);
		frame.setAlgoModel(model);
		frame.getAlgoModel().doParcours();
	}
}
