import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SubstitutionCipherScreen extends JFrame implements ActionListener {


	private static final long serialVersionUID = 1L;
	private JLabel label_encryption;
	private InputTextPanel inputPlainPanel;
	private OutputTextPanel outputCipherPanel;
	private JLabel label_ciphertext;
	
	private KeyPanel keyPanel;

	private JButton encryptButton;
	private JButton decryptButton;
	private JButton randomKeyButton;
	private JButton exitButton;
	
	private JLabel labelCiphertextInput;
	private InputTextPanel inputCipherPanel;
	private OutputTextPanel outputPlainPanel;
	private JLabel labelPlainOutput;
	
	private JLabel numberOfCharsLabel;
	private JTextField numberOfCharsField;
	
	private JLabel numberOfCharsLabelCipher;
	private JTextField numberOfCharsCipherField;
	
	public SubstitutionCipherScreen() {
		initcomponents();
	}
	
	
	public void initcomponents() {
		
	    this.setTitle("Substitution Cipher");
		this.setSize(1200, 700);
		this.setResizable(false);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(EXIT_ON_CLOSE);  
		
	    this.label_encryption = new JLabel("                 Enter Your Plaintext here");
	    this.label_encryption.setFont(new Font("Times New Roman", Font.PLAIN, 15) );
	    this.label_encryption.setBounds(10, 10, 420, 10);
	    this.add(label_encryption);
	    
	    this.inputPlainPanel = new InputTextPanel();
	    inputPlainPanel.setBounds(20, 20, 400, 250);
	    
	    this.add(inputPlainPanel);
	    
	    this.label_ciphertext = new JLabel("        Here is the ciphertext using Key");
	    this.label_ciphertext.setFont(new Font("Times New Roman", Font.PLAIN, 15) );
	    this.label_ciphertext.setBounds(20, 280, 420, 20);
	    this.add(label_ciphertext);
	    
	    this.outputCipherPanel = new OutputTextPanel();
	    outputCipherPanel.setBounds(20, 300, 400, 250);
	    
	    this.add(outputCipherPanel);
	    
	    this.keyPanel = new KeyPanel();
	    this.keyPanel.setBounds(450, 20, 250, 350);
	    this.add(keyPanel);
	    
	   
	    // encrypt decrypt and random key Buttons
	    this.encryptButton = new JButton("Encrypt");
	    this.encryptButton.setBounds(500, 400, 100, 40);
	    this.encryptButton.addActionListener(this);
	    this.add(encryptButton);
	    
	    this.decryptButton = new JButton("Decrypt");
	    this.decryptButton.setBounds(500, 460, 100, 40);
	    this.decryptButton.addActionListener(this);
	    this.add(decryptButton);
	    
	    this.randomKeyButton = new JButton("Random Key");
	    this.randomKeyButton.setBounds(500, 520, 100, 40);
	    this.randomKeyButton.addActionListener(this);
	    this.add(randomKeyButton);
	    
	    this.exitButton = new JButton("Exit");
	    this.exitButton.setBounds(500, 570, 100, 40);
	    this.exitButton.addActionListener(this);
	    this.add(exitButton);
	    
	    this.labelCiphertextInput = new JLabel("                 Enter Your Ciphertext here");
	    this.labelCiphertextInput.setFont(new Font("Times New Roman", Font.PLAIN, 15) );
	    this.labelCiphertextInput.setBounds(700, 10, 420, 10);
	    this.add(labelCiphertextInput);
	    
	    this.inputCipherPanel = new InputTextPanel();
	    inputCipherPanel.setBounds(700, 20, 400, 250);
	    
	    this.add(inputCipherPanel);
	    
	    this.labelPlainOutput = new JLabel("        Here is the plaintext using Key");
	    this.labelPlainOutput.setFont(new Font("Times New Roman", Font.PLAIN, 15) );
	    this.labelPlainOutput.setBounds(700, 280, 420, 20);
	    this.add(labelPlainOutput);
	    
	    this.outputPlainPanel = new OutputTextPanel();
	    outputPlainPanel.setBounds(700, 300, 400, 250);
	    
	    this.add(outputPlainPanel);
	    
	    this.numberOfCharsLabel = new JLabel("Number of Characters Plaintext: ");
	    this.numberOfCharsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15) );
	    this.numberOfCharsLabel.setBounds(10, 600, 300, 20);
	    this.add(numberOfCharsLabel);
	    
	    this.numberOfCharsField = new JTextField(1);
	    this.numberOfCharsField.setEditable(false);
	    this.numberOfCharsField.setBounds(210, 600, 100, 20);
	    this.add(numberOfCharsField);
	    
		
	    this.numberOfCharsLabelCipher = new JLabel("Number of Characters Ciphertext: ");
	    this.numberOfCharsLabelCipher.setFont(new Font("Times New Roman", Font.PLAIN, 15) );
	    this.numberOfCharsLabelCipher.setBounds(700, 600, 300, 20);
	    this.add(numberOfCharsLabelCipher);
	    
	    this.numberOfCharsCipherField = new JTextField(1);
	    this.numberOfCharsCipherField.setEditable(false);
	    this.numberOfCharsCipherField.setBounds(920, 600, 100, 20);
	    this.add(numberOfCharsCipherField);
	    
	    
		
	}
	
	public static void main(String[] args) {
		SubstitutionCipherScreen jtfTfDemo = new SubstitutionCipherScreen();
		
		jtfTfDemo.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String inputText = "";
		String outputText = "";
		int encryptionKey[];
		
		if( e.getSource().equals(exitButton))
		{
			System.exit(0);
		}
		
		else if( e.getSource().equals(encryptButton))
		{
			// check if key is valid
			if(this.keyPanel.checkInputValidity() != true)
			{
				JOptionPane.showMessageDialog(this,
					    "Not a Valid Key Entered",
					    "Key Input error",
					    JOptionPane.ERROR_MESSAGE);
			}
			else 
			{
				// now get the key entered
				encryptionKey = this.keyPanel.getKey();
				
				// check key validity
				// check if key is valid
				if(SubstitutionCipher.checkKeyValidity(encryptionKey) != true)
				{
					JOptionPane.showMessageDialog(this,
						    "Key contains duplicate items, can not be used",
						    "Key validity error",
						    JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					
					
					inputText = this.inputPlainPanel.getText();
					outputText = SubstitutionCipher.encrypt(inputText, encryptionKey);
					this.numberOfCharsField.setText("" + outputText.length());
					this.outputCipherPanel.setText(outputText);

				}
				
				
			}
			
		}
		
		else if ( e.getSource().equals(randomKeyButton))
		{
			encryptionKey = SubstitutionCipher.generateRandomKeyForEncryption();
			
			// set key
			this.keyPanel.setKey(encryptionKey);
			
		}
		else if( e.getSource().equals(decryptButton))
		{
			// check if key is valid
			if(this.keyPanel.checkInputValidity() != true)
			{
				JOptionPane.showMessageDialog(this,
					    "Not a Valid Key Entered",
					    "Key Input error",
					    JOptionPane.ERROR_MESSAGE);
			}
			
			
			
			else 
			{
				// now get the key entered
				encryptionKey = this.keyPanel.getKey();
				
				// check key validity
				// check if key is valid
				if(SubstitutionCipher.checkKeyValidity(encryptionKey) != true)
				{
					JOptionPane.showMessageDialog(this,
						    "Key contains duplicate items, can not be used",
						    "Key validity error",
						    JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					
					
					inputText = this.inputCipherPanel.getText();
					
					boolean isValid = true;
					char current_char = 0;
					for(int i = 0; i < inputText.length() && isValid; i++)
					{
						current_char = inputText.charAt(i);
						
						if( !(current_char >= 'A' && current_char <= 'Z') )
						{
							isValid = false;
						}
					}
					
					if(!isValid)
					{
						JOptionPane.showMessageDialog(this,
							    "Not a valid ciphertext entered",
							    "Ciphertext Error",
							    JOptionPane.ERROR_MESSAGE);
						
					}
					else
					{
						outputText = SubstitutionCipher.decrypt(inputText, encryptionKey);
						this.outputPlainPanel.setText(outputText);
						
						this.numberOfCharsCipherField.setText("" + outputText.length());
						//this.outputCipherPanel.setText(outputText);
					
					}
				}
				
				
			}
			
		}
		
		
		
	}
}