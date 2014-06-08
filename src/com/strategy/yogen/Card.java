package com.strategy.yogen;





public class Card
	{
		 // private String name;//2,3,4,...,King,Ace
		  //private char suit;//Heart, Diamond, Club, Spade 
		  private int rank;//1->13: 2==1 3==2,...,King==12, Ace==13
		  private int value;//Value for the Card in game, and for tallying the endgame 2,3,4,...10,11
		  private int num;
		
		  public static int WIDTH =45;
		  public static int HEIGHT = 64;
		  private float Cx;
		  private float Cy;
		  
		 
		  
		  public Card create_card(int a)
		  {
			  Card temp = new Card();
			  temp.num = a;
			  temp.rank = a % 13;
			  if (this.rank == 0)
			  {this.rank = 13;}
			  switch (rank)
			  {
			  case 9: case 10: case 11: case 12:
				  value = 10;
				  break;
			  case 13:
				  value = 11;
				  break;
			  default:
					 value = rank + 1;
					 break;
			  }
			  return temp;
		  }
		  
		 
		  public int getCardValue()
		  {
			
			return this.value;
		  }
		  public int getCardRank()
		  {
			  
			  return this.rank;
		  }

		  public int getCardNum()
		  {
			  return this.num;
			  
		  }

		  public Card cloneCard(Card a)
		  {
			  Card temp = a;
			  return temp;
		  }  

		  public static void set_Card_size(int type){
			  if (type == 0)
			  {
				  WIDTH = 51;
				  HEIGHT = 72;
			 } else if( type == 1){
				 WIDTH =49;
				 HEIGHT = 68;
			 }
			  
		  }
		  public float get_X(){return Cx;}
		  public float getY(){ return Cy;}
		  public void set_axis(float x, float y)
		  {
			  Cx=x;
			  Cy =y;
		  }
		  public void move_Card(float x, float y)
		  {
			  Cx -= x;
			  Cy -= y;
		  }
		  
	
	
	}
