import java.awt.event.ActionListener;
import javax.swing.JButton;

public class BoardButton extends JButton
{
	int row;
	int column;
	
	BoardButton(int row, int column, ActionListener listener)
	{
		this.row = row;
		this.column = column;
		this.addActionListener(listener);
	}
	BoardButton(int row, int column, String text, ActionListener listener)
	{
		this.row = row;
		this.column = column;
		this.setText(text);
		this.addActionListener(listener);
	}
	BoardButton(int row, int column, String text)
	{
		this.row = row;
		this.column = column;
		this.setText(text);
	}
	
	public int getRow()
	{
		return row;
	}
	public int getColumn()
	{
		return column;
	}
	
}
