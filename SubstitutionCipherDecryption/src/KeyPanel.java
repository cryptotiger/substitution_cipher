import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Panel which includes Key values
public class KeyPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel letterLabel[];
	private JTextField letterField[];
	
	public KeyPanel()
	{
		this.setSize(100, 300);
		this.setLayout(null);
		
		this.letterLabel = new JLabel[26];
		this.letterField = new JTextField[26];
		
		for(int i = 0; i < 13; i++)
		{
			this.letterLabel[i] = new JLabel("" + (char)('A' + i));
			this.letterLabel[i].setBounds(10, 25 * i , 50, 20);
			this.add(this.letterLabel[i]);
			
			this.letterField[i] = new JTextField(1);
			this.letterField[i].setBounds(50, 25 * i, 50, 20);
			this.letterField[i].setDocument(new JTextFieldLimit(1));
			this.add(letterField[i]);
		}
		
		
		for(int i = 13; i < 26; i++)
		{
			this.letterLabel[i] = new JLabel("" + (char)('A' + i));
			this.letterLabel[i].setBounds(110, 25 * (i - 13) , 50, 20);
			this.add(this.letterLabel[i]);
			
			this.letterField[i] = new JTextField(1);
			this.letterField[i].setBounds(160, 25 * (i - 13), 50, 20);
			this.letterField[i].setDocument(new JTextFieldLimit(1));
			this.add(letterField[i]);
			
		}
		
	}
	
	public static void main(String args[])
	{
	
		KeyPanel kPanel = new KeyPanel();
		JFrame sample = new JFrame();
		sample.setSize(500, 500);
		kPanel.setBounds(10, 10, 100, 400);
		sample.add(kPanel);
		sample.setVisible(true);
	}
	
	public boolean checkInputValidity()
	{
		// assume key is valid
		boolean is_valid = true;
		char entered_char;
		
		for(int i = 0; i < 26 && is_valid; i++)
		{
			if( this.letterField[i].getText().length() != 1)
				is_valid = false;
			else
			{
				entered_char = this.letterField[i].getText().toUpperCase().charAt(0);
				
				if( !(entered_char >= 'A' && entered_char <= 'Z') )
				{
					is_valid = false;
				}
			}
		}
		
		return is_valid;
	}
	
	public void setKey(int enteredKey[])
	{
		for(int i = 0; i < 26; i++)
		{
			letterField[i].setText( (char)('A' + enteredKey[i]) + ""); 
		}
	}
	
	// retrieve entered key from panel
	public int[] getKey()
	{
		int returnedKey[] = new int[26];
		char current_char;
		
		for(int i = 0; i < 26; i++)
		{
			current_char = letterField[i].getText().toUpperCase().charAt(0);
			returnedKey[i] = (int)(current_char - 'A');
		}
		
		return returnedKey;
	}
}
