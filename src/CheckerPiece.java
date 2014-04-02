import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;



public class CheckerPiece extends JButton
{
	//private final JButton tile = new JButton();
	private int team; // (1 - Black, 0 - white)
	private int x;
	private int y;
	private boolean king;
	private ImageIcon _blank = new ImageIcon(CheckerPiece.class.getResource("/BlackTile.png"));
	private ImageIcon _blankr = new ImageIcon(CheckerPiece.class.getResource("/RedTile.png"));
	private ImageIcon _white = new ImageIcon(CheckerPiece.class.getResource("/RedTiles_WhitePieces.png"));
	private ImageIcon _black = new ImageIcon(CheckerPiece.class.getResource("/RedTile_BlackPieces.png"));
	private ImageIcon _whiteK = new ImageIcon(CheckerPiece.class.getResource("/RedTile_WhiteKing.png"));
	private ImageIcon _blackK = new ImageIcon(CheckerPiece.class.getResource("/RedTile_BlackKing.png"));
	
	
	CheckerPiece(int posx, int posy, int team, boolean king, ActionListener listener)
	{
		//this.coordinates = coordinates;
		this.team = team;
		this.king = king;
		x = posx;
		y = posy;
		if ((x+y)%2 != 1){
			this.setIcon(_blank);
		}else{
			this.setIcon(_blankr);
		}
		//de-spacing:
		this.setBorder(null);
		this.setBorderPainted(false);
		this.setMargin(new Insets(0,0,0,0));
		 
		//other stuff I always do to my image-thiss:
		this.setFocusPainted(false);
		this.setContentAreaFilled(false);
		this.addActionListener(listener);
		this.setOpaque(false);
		this.setVisible(true);
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		 
		//not sure exactly if this even does anything...
		this.setVerticalAlignment(SwingConstants.TOP);
		this.setAlignmentX(TOP_ALIGNMENT);
	}
	
	
	public void setPiece(int piece)
	{
		switch(piece){
		case 0:
			team = 0;
			king = false;
			this.setIcon(_blankr);
			break;
		case 1:
			team = 1;
			king = false;
			this.setIcon(_white);
			break;
		case 2:
			team = 2;
			king = false;
			this.setIcon(_black);
			break;
		case 3:
			team = 1;
			king = true;
			this.setIcon(_whiteK);
			break;
		case 4:
			team = 2;
			king = true;
			this.setIcon(_blackK);
			break;
		case 5:
			team = 0;
			king = false;
			this.setIcon(_blank);
			break;
		default:
			team = 0;
			king = false;
			this.setIcon(_blank);
			break;
		}
	}
	public int getPiece(){
		if(king){
			if(team ==1){
				return 3;
			}else if(team == 2){
				return 4;
			}
		}else{
			if(team ==1){
				return 1;
			}else if(team == 2){
				return 2;
			}
		}
		return ((x+y)%2 == 0 ) ? 5 : 0 ;
	}
	public void kingMe(){
		king = true;
		setPiece(getPiece());
	}
	public boolean isKing()
	{
		return king;
	}
	public int getTeam()
	{
		return team;
	}

	public String toString()
	{
		return String.valueOf(team);
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}