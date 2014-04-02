import java.awt.event.ActionListener;

import javax.swing.*;


public class BoardController 
{
	private CheckerPiece[][] tiles = new CheckerPiece[8][8];
	private int whiteCheckers;
	private int blackCheckers;
	private JMenu[] menu = new JMenu[2];
	private JMenuItem[] menuItem= new JMenuItem[3];
	
	BoardController(boolean newGame, ActionListener listener)
	{
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				tiles[i][j] = new CheckerPiece(i, j, 0, false, listener);
				if(newGame)
				{
					if((j+i) % 2 != 0){
	        			if(i<3){
	        				tiles[i][j].setPiece(2);
	        			}else if(i<5){
		        			tiles[i][j].setPiece(0);
	        			}else{
			        		tiles[i][j].setPiece(1);
	        			}
					}
					
			}
			}
		}
		menu[0] = new JMenu("Save");
		for(int i = 0; i< 3; i ++){
			String temp = "Save " + String.valueOf(i);
			menuItem[i] = new JMenuItem(temp);
			menuItem[i].addActionListener(listener);
			menu[0].add(menuItem[i]);
		}
		menu[1] = new JMenu("Load");
		for(int i = 0; i< 3; i ++){
			String temp = "Load "+ String.valueOf(i);
			menuItem[i] = new JMenuItem(temp);
			menuItem[i].addActionListener(listener);
			menu[1].add(menuItem[i]);
		}
		
		if(newGame){
			whiteCheckers = 12;
			blackCheckers = 12;
		}
		else
		{
			whiteCheckers = 0;
			blackCheckers = 0;
		}
	}
	
	public CheckerPiece[][] getBoard() {return tiles;}
	
	public void addTeam(int team, int x, int y){
		tiles[x][y].setPiece(team);
		whiteCheckers += (team == 1) ? 1:0;
		blackCheckers += (team == 2) ? 1:0;
	}
	public boolean teamFull(int team){
		if(team == 1){ return whiteCheckers == 12;
		}else if(team == 2) {return blackCheckers == 12;
		}
		return true;
	}
	
	public void doMove(int oldx,int oldy,int newx,int newy){
		int difx = newx - oldx;
		int dify = newy - oldy;
		//set new
		tiles[newx][newy].setPiece(tiles[oldx][oldy].getPiece());
		//set old piece
		tiles[oldx][oldy].setPiece(0);
		if(dify == -2 || dify == 2){ //if we were jumping a piece
			//set jumped piece
			tiles[newx - difx/2][newy - dify/2].setPiece(0);
		}
		
		if(newx == 0 && tiles[newx][newy].getTeam() == 1 || newx == 7 && tiles[newx][newy].getTeam() == 2){
			if(!tiles[newx][newy].isKing()){
				tiles[newx][newy].kingMe();
			}
		}
	}
	
	public int canMove(int team, int oldx,int oldy,int newx,int newy){
		if(tiles[oldx][oldy].getTeam() == team){ // player owns it and is turn destination is empty
			//dif x dify have to be in allowable direction
			if(tiles[newx][newy].getTeam() == 0){
				int difx = newx - oldx;
				int dify = newy - oldy;
				//squares of direction must equal to maintain diagonal
				int ddirx = difx * difx;
				int ddiry = dify * dify; 
				
				if(dify == 0 || difx == 0 || ddirx != ddiry){ // Not moving Diagonally
					return 4;
				}
				//jumping enemy
				if(dify == -2 || dify == 2){ //if we were jumping a piece
					//set jumped piece
					int teamJumping = tiles[newx - difx/2][newy - dify/2].getTeam();
					if (teamJumping == 0 || teamJumping == team){
						return 6; // can't jump nothing or own team
					}
				}
				//check directions
				if(tiles[oldx][oldy].isKing()){return 0;}
				else if(((difx == -1 || difx == -2) && team == 1) || ((difx == 1 || difx == 2) && team == 2)){
					//going -1 or -2 in y direction (direction for team white) and opposite for team black
					return 0;
					
				}else{
					//illegal move
					return 7;
				}
			}
			return 2; //illegal second selection
			
		}
		//illegal first selection
		return 3;
	}
	
	public void setBoard(int[][] iBoard)
	{
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				tiles[i][j].setPiece(iBoard[i][j]);
			}
		}
	}
	public int numberOfCheckers(int colour)
	{
		switch(colour)
		{
			case 0: return whiteCheckers;
			case 1: return blackCheckers;
			default: return blackCheckers + whiteCheckers;
		}
	}
	public int numberOfBlack() {return blackCheckers;}
	public int numberOfWhite() {return whiteCheckers;}
	public int totalNumberOfCheckers() {return blackCheckers + whiteCheckers;}
	
	public void printModel()
	{
		String txt = "";
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if(tiles[i][j] == null) txt += "n  ";
				else txt += tiles[i][j].toString() + "  ";
				
				if(j == 7) 
				{
					System.out.println(txt);
					txt = "";
				}
			}
		}
	}
	
	public JMenu[] getMenu(){
		return menu;
	}
}
