import java.awt.*;

import javax.swing.*;


public class BoardView extends JFrame
{
	private JPanel[][] panel;
	private int columns = 8;
	private int rows = 8;
	private JMenuBar menuBar;
	private JMenu menu;
	
	public BoardView() 
	{
		panel = new JPanel[rows][columns];
		makeBoard();
	}
	public BoardView(CheckerPiece[][] board)
	{
		panel = new JPanel[rows][columns];
		makeBoard();
		
	}
	
 	private void makeBoard() {
		this.setTitle("Checkers");
		this.setSize(800, 800);
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		Container grid = this.getContentPane();
		//double array panel to be able to access each box
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++){
				panel[i][j] = new JPanel();
				
				grid.add(panel[i][j]);
			}
		}
		
		grid.setLayout(new GridLayout(rows, columns));
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	

	public void displayMesg(String mesg) 
	{
		JOptionPane.showMessageDialog(this, mesg);
	}
	
	public void addButtons(CheckerPiece[][] button)
	{
	    
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				panel[i][j].add(button[i][j]);
			}
		}
	}
	public void addMenu(JMenu[] menu){

		menuBar.add(menu[0]);
		menuBar.add(menu[1]);
	}
	
}


	