import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.text.Position;
public class BingoCard {
	
	private Random random;
	public static int[][] card;
	public static int [][] displayCard;
	public static ArrayList<Integer> rolling = new ArrayList<>();
	public static ArrayList<Integer> oldasf = new ArrayList<>();
	ArrayList<position> markedPositions = new ArrayList<>();
	private ArrayList<SNDWIE> bingoCards = new ArrayList<>();
	private BufferedImage bingoImages;
	private int count;
	private BufferedImage cardPic;
	
	public BingoCard(int seed, int seedCards) {
		count =0;
		random = new Random(seed);
		card = new int[5][5];
		displayCard = new int[5][5];
		seedCards = (seedCards+3)/4 *4;
		for(int i=0;i<seedCards;i++) {
			generateCard();
		}
		
		bingoImages = new BufferedImage(2550,2000,BufferedImage.TYPE_INT_ARGB);
		
		try {
			cardPic = ImageIO.read(BingoPanel.class.getResource("/images/BigBingoCard.jpg"));
		}
		catch(Exception E) {
			System.out.println("Exception Error");
			return;
		}
		
		System.out.println(Arrays.deepToString(bingoCards.get(0).getBoard()));
		System.out.println(Arrays.deepToString(bingoCards.get(1).getBoard()));
		System.out.println(Arrays.deepToString(bingoCards.get(2).getBoard()));
		System.out.println(Arrays.deepToString(bingoCards.get(3).getBoard()));
		
		paintImage(seedCards);
	}
	
	
	
	public void generateCard() {
		int min = 1;
		int max = 16;
		displayCard = new int[5][5];
		ArrayList<Integer> ttroud = new ArrayList<>();
		
		for(int col=0;col<card[0].length;col++) {
			for(int row=0;row<card.length;row++) {
				
				int rand = random.nextInt(max-min)+min;
				while(ttroud.contains(rand)) {
					rand=random.nextInt(max-min)+min;
					
				}
				displayCard[row][col] = rand;
				card[row][col] = rand;
				ttroud.add(rand);
			}
		min+=15;
		max+=15;
		}
		displayCard[2][2] = 0;
		
		card[2][2] = 0;
		
		bingoCards.add(new SNDWIE(count,displayCard,card));
		count++;
	}
	
	
	public void paintImage(int seedCards) {
		
		Graphics g = bingoImages.getGraphics();
		g.drawImage(cardPic,0,0,800,800,null);
		g.drawImage(cardPic,800,0,800,800,null);
		g.drawImage(cardPic,0,800,800,800,null);
		g.drawImage(cardPic,800,800,800,800,null);
		
		int x = 32;
		int y = 100;
		int width = 130;
		int height = 142;
		
		//i is what page im on
		// j is the id of my card
		for(int j=0,i=0;j < seedCards/4;j++,i+=4) {
			g.setColor(Color.WHITE);
			g.fillRect(0,0,1800,1800);
			g.drawImage(cardPic,0,0,800,800,null);
			g.drawImage(cardPic,800,0,800,800,null);
			g.drawImage(cardPic,0,800,800,800,null);
			g.drawImage(cardPic,800,800,800,800,null);
			g.setColor(new Color(0,0,0));
			
			int[][] holdsBingo = bingoCards.get(i).getBoard(); 
			//height is 82
			int card1x = 24;
			int card1y = 90;
			int card1width = 148;
			int card1height = 141;
			Font font1 = new Font("SANS_SERIF", Font.PLAIN, 50);
			g.setFont(font1);
			g.drawString("ID: "+Integer.toString(bingoCards.get(i).getID()+1),350,810);
			for(int card1row=0;card1row<5;card1row++) {
				for(int card1col=0;card1col<5;card1col++) {
					if(card1row == 2 && card1col == 2) {
						
						Rectangle rect = new Rectangle(card1x,card1y, card1width, card1height);
						Font font = new Font("SANS_SERIF", Font.BOLD, 60);
						g.setFont(font);
						drawCenteredString(g, " Free ", rect, font);
						card1y+=card1height;
					}
					
					else 
					{
						Rectangle rect = new Rectangle(card1x, card1y, card1width, card1height);
						Font font = new Font("SANS_SERIF", Font.BOLD, 120);
						g.setFont(font);
						drawCenteredString(g, Integer.toString(holdsBingo[card1col][card1row]), rect, font);
						
						card1y+=card1height;
					}
					
					
				}
				card1y=82;
				card1x+=card1width;
				
			}
			int[][] holdsBingo2 = bingoCards.get(i+1).getBoard(); 
			//height is 82
			int card2x = 820;
			int card2y = 90;
			int card2width = 148;
			int card2height = 142;
			Font font2 = new Font("SANS_SERIF", Font.PLAIN, 50);
			g.setFont(font2);
			g.drawString("ID: "+Integer.toString(bingoCards.get(i+1).getID()+1),1150,810);
			for(int card2row=0;card2row<5;card2row++) {
				for(int card2col=0;card2col<5;card2col++) {
					if(card2row == 2 && card2col == 2) {
						
						Rectangle rect = new Rectangle(card2x,card2y, card2width, card2height);
						Font font = new Font("SANS_SERIF", Font.BOLD, 60);
						g.setFont(font);
						drawCenteredString(g, " Free ", rect, font);
						card2y+=card2height;
					}
					
					else 
					{
						Rectangle rect = new Rectangle(card2x, card2y, card2width, card2height);
						Font font = new Font("SANS_SERIF", Font.BOLD, 120);
						g.setFont(font);
						drawCenteredString(g, Integer.toString(holdsBingo2[card2col][card2row]), rect, font);
						
						card2y+=card2height;
					}
					
					
				}
				card2y=90;
				card2x+=card2width;
				
			}
			int[][] holdsBingo3 = bingoCards.get(i+2).getBoard(); 
			//height is 83
			int card3x = 24;
			int card3y = 870;
			int card3width = 148;
			int card3height = 142; 
			Font font3 = new Font("SANS_SERIF", Font.PLAIN, 50);
			g.setFont(font3);
			g.drawString("ID: "+Integer.toString(bingoCards.get(i+2).getID()+1),350,1610);
			for(int card3row=0;card3row<5;card3row++) {
				for(int card3col=0;card3col<5;card3col++) {
					if(card3row == 2 && card3col == 2) {
						
						Rectangle rect = new Rectangle(card3x,card3y, card3width, card3height);
						Font font = new Font("SANS_SERIF", Font.BOLD, 60);
						g.setFont(font);
						drawCenteredString(g, " Free ", rect, font);
						card3y+=card3height;
					}
					
					else 
					{
						Rectangle rect = new Rectangle(card3x, card3y, card3width, card3height);
						Font font = new Font("SANS_SERIF", Font.BOLD, 120);
						g.setFont(font);
						drawCenteredString(g, Integer.toString(holdsBingo3[card3col][card3row]), rect, font);
						
						card3y+=card3height;
					}
					
					
				}
				card3y=870;
				card3x+=card3width;
				
			}
			int[][] holdsBingo4 = bingoCards.get(i+3).getBoard(); 
			//height is 84
			int card4x = 820;
			int card4y = 870;
			int card4width = 148;
			int card4height = 142; 
			Font font4 = new Font("SANS_SERIF", Font.PLAIN, 50);
			g.setFont(font4);
			g.drawString("ID: "+Integer.toString(bingoCards.get(i+3).getID()+1),1150,1610);
			for(int card4row=0;card4row<5;card4row++) {
				for(int card4col=0;card4col<5;card4col++) {
					if(card4row == 2 && card4col == 2) {
						
						Rectangle rect = new Rectangle(card4x,card4y, card4width, card4height);
						Font font = new Font("SANS_SERIF", Font.BOLD, 60);
						g.setFont(font);
						drawCenteredString(g, " Free ", rect, font);
						card4y+=card4height;
					}
					
					else 
					{
						Rectangle rect = new Rectangle(card4x, card4y, card4width, card4height);
						Font font = new Font("SANS_SERIF", Font.BOLD, 120);
						g.setFont(font);
						drawCenteredString(g, Integer.toString(holdsBingo4[card4col][card4row]), rect, font);
						
						card4y+=card4height;
					}
					
					
				}
				card4y=870;
				card4x+=card4width;
				
			}
			
			
			
			//holdsBingo=bingoCards.get(j+2).getBoard();
			//holdsBingo=bingoCards.get(j+3).getBoard();
			try {
				//"C:\\Users\\slhscs218\\Downloads\\card"
				
				//String home = System.getProperty("user.home");
				//ImageIO.write(bingoImages, "png", new File(home+"/Downloads/card"));
				
				String home = System.getProperty("user.home");
				ImageIO.write(bingoImages, "png", new File(home+"/Downloads/card"+i+".png"));
			} catch(IOException e) {
				e.printStackTrace();
			}	
		}
		
		
		
		
		
		//height is 100
				//width is 32
				//heihgt 130
				//width 142
			
			
		
		
		/*try {
			//"C:\\Users\\slhscs218\\Downloads\\card"
			
			//String home = System.getProperty("user.home");
			//ImageIO.write(bingoImages, "png", new File(home+"/Downloads/card"));
			
			String home = System.getProperty("user.home");
			ImageIO.write(bingoImages, "png", new File(home+"/Downloads/card"+i+".png"));
		} catch(IOException e) {
			e.printStackTrace();
		}	*/
	}
	
	
	
	
	//Width - 182 HEIGHT - 164
	//40- width 132 - height
	public void fillCard(Graphics g) {
		//g.fillRect(40,132,182,164);
		int x = 40;
		int y = 132;
		int width = 182;
		int height = 164;
		for(int i=0;i<5;i++) {
			for(int j=0;j<5;j++) {
				if(i == 2 && j == 2) {
					
					Rectangle rect = new Rectangle(x, y, width, height);
					Font font = new Font("SANS_SERIF", Font.BOLD, 80);
					g.setFont(font);
					drawCenteredString(g, " ", rect, font);
					y+=height;
				}
				
				else 
				{
					Rectangle rect = new Rectangle(x, y, width, height);
					Font font = new Font("SANS_SERIF", Font.BOLD, 150);
					g.setFont(font);
					drawCenteredString(g, Integer.toString(displayCard[j][i]), rect, font);
					y+=height;
				}
				
				
			}
			
			y=132;
			x+=width;
		}
		
		
		
		
	}
	/*public void trespass(int hit) {
		
	}*/
	public int callBingoBall() {
		int rand = 0;
		while(oldasf.size()<75) {
			rand =  random.nextInt(75)+1;
			if ( oldasf.contains(rand) ) {
				continue;
			} else {
				oldasf.add(rand);
				break;
			}
		}
		
		for(int i = 0; i<card.length;i++) {
			for(int j=0;j<card[0].length;j++) {
				if(card[i][j]==rand) {
					if(rolling.contains(card[i][j])) {
						continue;
					}
					
					else{
						rolling.add(card[i][j]);
						card[i][j]=0;
						position newPos = new position(i,j);
						markedPositions.add(newPos);
						System.out.println(Arrays.deepToString(card));
						Collections.sort(rolling);
						System.out.println(rolling);
						
						return rand;
					}
		
		}
			}
		}
		
		//System.out.println(markedPositions);
		return rand;
		
	}

	
	public boolean checkWinners() {
		boolean won  = false;
		for(int x = 0; x<5;x++) {
            if((card[x][0] ==0&& card[x][1] ==0&& card[x][2]==0 && card[x][3]==0 && card[x][4]==0) || (card[0][x]==0 && card[1][x]==0 && card[2][x]==0 && card[3][x]==0 && card[4][x]==0)) {
                won = true;
                System.out.println("wonners");
                                
                return true;
                                
                
            }
            
        }
        if((card[0][0] == 0 && card[1][1] ==0 && card[2][2]==0 && card[3][3]==0 && card[4][4]==0) || (card[0][4]==0 && card[1][3]==0 && card[2][2]==0 && card[3][1]==0 && card[4][0]==0)) {
            won = true;
            System.out.println("wonners");
                        
            return true;
        }
        return false;
		
	}
	
	
	public ArrayList<position> getMarkedPositions() {
		return markedPositions;
	}
		
	
	public void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
	    // Get the FontMetrics
	    FontMetrics metrics = g.getFontMetrics(font);
	    // Determine the X coordinate for the text
	    int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
	    // Determine the Y coordinate for the text (note we add the ascent, as in java 2d 0 is top of the screen)
	    int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
	    // Set the font
	    g.setFont(font);
	    //c Draw the String
	    g.drawString(text, x, y);
	}
	
	public String toString() {
		return Arrays.deepToString(card);
			}
			

}