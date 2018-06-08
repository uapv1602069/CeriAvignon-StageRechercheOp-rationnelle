/**
 * Constructeur du Thread éxécutant l'algorithme.
 * @author Pablo
 *
 */
public class AlgoThread extends Thread 
{
	/**
	 * Frame dans lequel le thread sera éxécuté.
	 */
	Frame frame;
	
	/**
	 * Constructeur du Thread qui aura un nom de une Frame associée.
	 * @param name
	 * @param argFrame
	 */
	public AlgoThread(String name, Frame argFrame)
	{
	  super(name);
	  frame = argFrame;
	}
	
	/**
	 * Fonction appelée à l'éxécution du Thread initialisant l'algorithme dans la Frame et lançant l'algorithme.
	 */
	public void run()
	{
		Parcours model = new Parcours(frame.getGraph(), frame.getGraph().getNode(0), frame);
		frame.setAlgoModel(model);
		frame.getAlgoModel().doParcours();
	}
}
