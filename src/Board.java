import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Board implements ActionListener
{
	private BoardView view;
	private BoardController board;
	private int turn;
	private int lastx;
	private int lasty;
	private boolean play;

	Board(BoardView view, boolean classic)
	{
		this.view = view;
        board = new BoardController(classic, this);
        play = classic;
     	this.view.addButtons(board.getBoard());
     	this.view.addMenu(board.getMenu());
		turn = 1;
		lastx = -1;
		lasty = -1;
		if(!play){view.displayMesg("White Places Checkers First");}
		else{view.displayMesg("White Plays First");} // leaving if statement here for later use
	}
	
	public void doAction(int x, int y){
		if(play){
			// if stack empty
			if(lastx < 0 && lasty < 0){ // No Previous command
			//fill first -- must belong to player of turn
				lastx = x;
				lasty = y;
				//second move
			}else { //resolve lastx y and new x y
				//check move
				//view.displayMesg("Last X: " + lastx + " last Y: " + lasty + " New X : "+ x + " New Y "+ y  );
				switch(board.canMove(turn, lastx, lasty, x, y)){
				case 0:
					board.doMove(lastx, lasty, x, y);
					turn = (turn == 1) ? 2 : 1;
					break;
				case 1:
					view.displayMesg("Illegal Direction: Checker can not move backwards");
					break;
				case 2:
					view.displayMesg("Illegal Second Selection: Must move to empy space");
					break;

				case 3:
					String tempturn = (turn == 1) ? "White's turn Black selected" : "Black's turn White selected";
					view.displayMesg("Illegal First Selection: " + tempturn);
					break;
				case 4:
					view.displayMesg("Illegal Direction: Checker must move Diagonally");
					break;
				case 5:
					view.displayMesg("Illegal Move: Distance");
					break;
				case 6:
					view.displayMesg("Illegal Jump: Must Jump Enemy");
					break;
				case 7:
					view.displayMesg("Illegal Move");
					break;
				default:
					view.displayMesg("Error");
					break;
						
				}
				lastx = -1;
				lasty = -1;
				
			}
			
			
		}else{
			
			//make spot turn persons team
			
			board.addTeam(turn, x, y);
			//if team full, switch teams
			if(board.teamFull(turn)){ //force over
				if(turn == 1){
					turn = 2;
					view.displayMesg("Black Place Checkers");
				}else{
					turn = 1;
				}
			}
			turn = (board.teamFull(turn)) ? (turn == 1) ? 2 : 1 : turn;
			
			if (board.teamFull(turn)){
				view.displayMesg("Game Begins: White goes first");
				play = true;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		//do action
		String cmd = e.getActionCommand();
		if(cmd.contains("Load")){
			int loadfile = Integer.parseInt(cmd.replace("Load ", ""));
			load(loadfile);
		}else if(cmd.contains("Save")){
			int savefile = Integer.parseInt(cmd.replace("Save ", ""));
			save(savefile);
		}else{
			CheckerPiece temp = (CheckerPiece)e.getSource();
			//view.displayMesg("X: " + temp.getX() + " Y: " + temp.getY() + " Team: " + temp.getTeam());
			doAction(temp.getX(), temp.getY());
		}
		/*
	    int row = ((CheckerPiece)e.getSource()).getRow();
        int column = ((BoardButton)e.getSource()).getColumn();
        
	    if(row == 0 && column == 0)
	    {
	        if(((BoardButton)e.getSource()).getText().equals("Switch Colour")) switchColour();
            else if(((BoardButton)e.getSource()).getText().equals("Done")) 
                view.displayMesg("Board setup complete! Press okay to start playing!!!.... (but not really..)");
	    }
	    
	    else
	    {
	        if(model.freeSpace(row, column))
            {
                if(model.numberOfCheckers(currentColour) < 12)
                {
                    switch(currentColour)
                    {
                    case 0: ((BoardButton)e.getSource()).setText("White");
                    		break;
                    case 1: ((BoardButton)e.getSource()).setText("Black");
                    		break;
                    default: System.out.println("Button colour switch fail");
                    		break;
                    }
                    
                    CheckerPiece checker = new CheckerPiece(currentColour, false);
                    model.addChecker(row, column, checker);
                    view.addChecker(row, column, checker);
                    model.printModel();
                    System.out.println();
                }
                else 
                {
                    view.displayMesg("12 checkers of this colour have already been placed."
                            + " Move on to next colour or finish setting the board");
                }

            }
            else
            {
                ((BoardButton)e.getSource()).setText("        ");
                model.removeChecker(row, column);
                view.removeChecker(row, column);
                model.printModel();
                System.out.println();
            }
	    }
	    */
	    
	}
	public void save(int game)
	{
		String fileName = "game" + String.valueOf(game) + ".txt";
		CheckerPiece[][] temp = board.getBoard();
		String row = "";
		try
		{
			File saveFile = new File(fileName);
			FileWriter fw = new FileWriter(saveFile);
			BufferedWriter writer = new BufferedWriter(fw);
			
			for(int i = 0; i < 8; i++)
			{
				for(int j = 0; j < 8; j++)
				{
					row += String.valueOf(temp[i][j].getPiece());
					
				}
				writer.write(row);
				writer.newLine();
				row = "";
			}
			writer.write(String.valueOf(turn)); //Whose turn it is
			writer.close();
		}
		catch(IOException error)
		{
			error.printStackTrace();
		}
		
		
		
	}
	public void load(int game)
	{
		String fileName = "game" + String.valueOf(game) + ".txt";
		String row = "";
		int[][] tempBoard = new int[8][8];
		try
		{
			FileReader fr = new FileReader(fileName);
			BufferedReader reader = new BufferedReader(fr);
			for(int i = 0; i < 8; i++)
			{
				row = reader.readLine();
				for(int j = 0; j < 8; j++)
				{
					tempBoard[i][j] = Character.getNumericValue(row.charAt(j));
				}
			}
			row = reader.readLine();
			turn = Character.getNumericValue(row.charAt(0)); //this will be whose turn it is.
			board.setBoard(tempBoard);
			reader.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}
}
