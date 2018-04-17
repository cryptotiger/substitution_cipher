import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SubstitutionCipherSolverScreen extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4725874612324004521L;
	private JLabel label_encryption;
	private InputTextPanel inputPlainPanel;
	private OutputTextPanel outputCipherPanel;
	private JLabel label_ciphertext;
	
	private KeyPanel keyPanel;

	private JButton solveButton;
	private JButton exitButton;
	
	private JLabel numberOfCharsLabel;
	private JTextField numberOfCharsField;
	
	
	private JLabel timeLabel;
	private JTextField timeField;
	private JLabel secondsLabel;
	
	public SubstitutionCipherSolverScreen() {
		initcomponents();
	}
	
	
	public void initcomponents() {
		
	    this.setTitle("Substitution Cipher Solver");
		this.setSize(1200, 700);
		this.setResizable(false);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);  
		
	    this.label_encryption = new JLabel("                 Enter Your CipherText here");
	    this.label_encryption.setFont(new Font("Times New Roman", Font.PLAIN, 15) );
	    this.label_encryption.setBounds(10, 10, 420, 10);
	    this.add(label_encryption);
	    
	    this.inputPlainPanel = new InputTextPanel();
	    inputPlainPanel.setBounds(20, 20, 400, 250);
	    
	    this.add(inputPlainPanel);
	    
	    this.label_ciphertext = new JLabel("        Here is the solved text using Key");
	    this.label_ciphertext.setFont(new Font("Times New Roman", Font.PLAIN, 15) );
	    this.label_ciphertext.setBounds(20, 280, 420, 20);
	    this.add(label_ciphertext);
	    
	    this.outputCipherPanel = new OutputTextPanel();
	    outputCipherPanel.setBounds(20, 300, 400, 250);
	    
	    this.add(outputCipherPanel);
	    
	    this.keyPanel = new KeyPanel();
	    this.keyPanel.setBounds(450, 20, 250, 350);
	    this.add(keyPanel);
	    
	   
	   
	    
	    this.solveButton = new JButton("Solve Cipher");
	    this.solveButton.setBounds(500, 470, 100, 40);
	    this.solveButton.addActionListener(this);
	    this.add(solveButton);
	    
	    this.exitButton = new JButton("Exit");
	    this.exitButton.setBounds(500, 530, 100, 40);
	    this.exitButton.addActionListener(this);
	    this.add(exitButton);
	    
	    this.numberOfCharsLabel = new JLabel("Number of Characters");
	    this.numberOfCharsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15) );
	    this.numberOfCharsLabel.setBounds(10, 600, 200, 20);
	    this.add(numberOfCharsLabel);
	    
	    this.numberOfCharsField = new JTextField(1);
	    this.numberOfCharsField.setEditable(false);
	    this.numberOfCharsField.setBounds(210, 600, 100, 20);
	    this.add(numberOfCharsField);
	   
	    this.timeLabel = new JLabel("Time To Solve: ");
	    this.timeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15) );
	    this.timeLabel.setBounds(600, 600, 200, 20);
	    this.add(timeLabel);
	    
	    this.timeField = new JTextField(1);
	    this.timeField.setEditable(false);
	    this.timeField.setBounds(810, 600, 100, 20);
	    this.add(timeField);
	    
	    this.secondsLabel = new JLabel("seconds ");
	    this.secondsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15) );
	    this.secondsLabel.setBounds(950, 600, 200, 20);
	    this.add(secondsLabel);
	    
		
	}
	
	public static void main(String[] args) {
		SubstitutionCipherSolverScreen jtfTfDemo = new SubstitutionCipherSolverScreen();
		
		jtfTfDemo.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String inputCipher = "";
		String solvedCipher = "";
		int encryptionKey[] = new int[26];
		String modifiedInputCipher = "";
		
		if( e.getSource().equals(exitButton))
		{
			System.exit(0);
		}
		
		
		else if( e.getSource().equals(solveButton))
		{
			inputCipher = this.inputPlainPanel.getText();
			
			if( inputCipher.length() != 0 )
			{
				
				if(!checkInputValidity(inputCipher))
				{
					JOptionPane.showMessageDialog(this,
						    "Not a valid ciphertext entered",
						    "Ciphertext Error",
						    JOptionPane.ERROR_MESSAGE);
					
				}
				else
				{
				
					modifiedInputCipher = inputCipher.toUpperCase();

					this.inputPlainPanel.setText(modifiedInputCipher);
					this.numberOfCharsField.setText("" + modifiedInputCipher.length());
				
					long time_before = System.nanoTime();
					
					solvedCipher = SubstitutionCipherSolver.solve(modifiedInputCipher, encryptionKey);
				
					long time_after = System.nanoTime();
					
					double seconds_to_solve = (time_after - time_before) / (double)1000000000;
					
					BigDecimal bd = new BigDecimal(seconds_to_solve);
					bd.setScale(4, BigDecimal.ROUND_HALF_UP);
					
					DecimalFormat df = new DecimalFormat("#.00");
					
					
					this.timeField.setText("" + df.format(bd.doubleValue()) );
					this.keyPanel.setKey(encryptionKey);
				
					this.outputCipherPanel.setText(solvedCipher);
				}
			}
			
			
			
		}
		
		
	}
	
	private static boolean checkInputValidity(String input)
	{
		boolean valid_input = true;
		char current = 0;
		for(int i = 0; i < input.length() && valid_input; i++)
		{
			current = input.charAt(i);
			
			if( !( (current >= 'A' && current <= 'Z') || (current >= 'a' && current <= 'z') ) )
				valid_input = false;
		}
		
		return valid_input;
	}
}