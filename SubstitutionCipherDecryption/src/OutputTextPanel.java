import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;


public class OutputTextPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea inputArea;
	private JScrollPane inputScrollPane;
	
	public OutputTextPanel()
	{
		initComponents();
	}
	
	private void initComponents()
	{
		this.setSize(400, 270);
		
		inputArea = new JTextArea(12, 30);
		inputArea.setEditable(false);
		inputArea.setLineWrap(true);
		//inputArea.setPreferredSize(new Dimension(400, 250));
		
		
		this.setLayout(new FlowLayout());
		
		inputScrollPane = new JScrollPane(inputArea);

		//inputScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		inputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


		this.add(inputScrollPane);
		this.setVisible(true);
	}
	
	public JTextArea createTextAreaFitToText(String message, Dimension minimalSize) throws BadLocationException{

        JTextArea aMessagePanel = new JTextArea();
        aMessagePanel.setText(message);

        /*for modelToView to work, the text area has to be sized. It doesn't matter if it's visible or not.*/
        aMessagePanel.setPreferredSize(minimalSize);
        aMessagePanel.setSize(minimalSize);            

        Rectangle r = aMessagePanel.modelToView(aMessagePanel.getDocument().getLength()); 

        Dimension d = new Dimension(minimalSize.width, r.y + r.height);
        aMessagePanel.setPreferredSize(d);
        return aMessagePanel;

	}
	
	public void setText(String text)
	{
		this.inputArea.setText(text);
	}
	
	public static void main(String args[])
	{
		InputTextPanel tPanel = new InputTextPanel();
		JFrame sample = new JFrame();
		sample.setSize(500, 500);
		tPanel.setBounds(10, 10, 400, 400);
		sample.add(tPanel);
		sample.setVisible(true);
	}
	
}