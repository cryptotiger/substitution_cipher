import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



public class InputTextPanel extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea inputArea;
	private JScrollPane inputScrollPane;
	
	public InputTextPanel()
	{
		initComponents();
	}
	
	private void initComponents()
	{
		this.setSize(400, 270);
		
		inputArea = new JTextArea(12, 30);
		//inputArea.setPreferredSize(new Dimension(400, 250));
		inputArea.setLineWrap(true);
		this.setLayout(new FlowLayout());
		
		inputScrollPane = new JScrollPane(inputArea);

		//inputScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		inputScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


		this.add(inputScrollPane);
		this.setVisible(true);
	}
	
	
	
	public String getText()
	{
		return this.inputArea.getText();
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
