package com.strategy.yogen;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.IOException;
import java.util.Scanner; 
import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.Random;
import java.util.Collections;
import java.lang.Object;
import java.net.InetAddress;
import java.net.UnknownHostException;
public class player
{
	private String players_name;// store this in a local file.
	private String players_email;
	private	String Host;
	private String player_addr;
	private int player_num;
	private List<Card> hand = new ArrayList<Card>(16);
	//private List<Card> playerDisCard= new ArrayList<Card>();// holds player Cards that tally up the score
	private int score = 0;// after 21 player existince is set to false, and player is booted from game.
	private boolean round_winner;// used for who goes first default player_one for round one
	private boolean existence;// player_one , and player_two automatically set to true, _three, _four, only if 3-4 player game 
	private boolean round_loser;// true = lost 
	public int this_player; 
	public int a(){
	return this.this_player;
}
	/*	int a(){}
 * 					Constructors	
 */
public void set_players_num(int num)
{
	this.player_num = num;
}
public int get_play_num()
{
return this.player_num;
}


	private void set_local_host() throws UnknownHostException
	{
		try {
			InetAddress inetAddr = InetAddress.getLocalHost();
			byte[] addr =inetAddr.getAddress();
			 player_addr = "";
			for (int i =0; i < addr.length; i++)
			{
				if( i > 0 ){player_addr += '.';}
				player_addr += addr[i] & 0xFF;
			}
		 
		Host = inetAddr.getHostName();
		
		}
		catch(UnknownHostException e)
		{
			System.out.println("couldn't get IP: " + e.getMessage());
		}
		}
	
	public String get_local_host()
	{
		return Host;
		
	}
	public void set_players_name_email(String n, String em)
	{
		try {
			 
			String name = n;
			String email = em;
			File file = new File("player_data.txt");
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(name + "\n");
			bw.write(email);
			
			bw.close();
 
			//System.out.println("Done");
 
		} catch (IOException e) {
			e.printStackTrace();
		}
}
	public void set_pname_pemail()throws IOException {
	    BufferedReader is = new BufferedReader(new FileReader("player_data.txt"));
	 String data = "";
	 while ( (data += is.toString())!= null)
	//	players_name = data.indexOf('\n', 0);
	 //	players_email =;
	    

	    is.close();
	  }
	
	public String get_player_name()
	{
		return this.players_name;
	}
	public void set_players_name(String n)
	{
		this.players_name = n;
	}
	
	public void death(){
		if(this.score >= 20){existence = false;}
	}
	
/* 						Getters
 * 
 */
	
	public Card players_smallest(){
		this.sort_hand();
		return this.hand.get(0);
	} 
	
	public int get_rank(int i){
		return hand.get(i).getCardRank();
	}
	public int get_size_of_hand(){
		return this.hand.size();
	}
	public Card get_Card_at(int i){
		
		System.out.println("i= " + i);
		
		return this.hand.get(i);
	
	}
	public boolean T()// used a lot to confirm player is still in the game dumbed down to T for convience
	{
		return this.round_winner;
	}
    public boolean E(){// used to return existence 
    	return existence;
    }
/*						Setters
 * 	
 */
	public void set_score(int i){
		score = i;
		if (this.score >= 20)
		{
		this.existence = false;
		}
	}
	public void set_score(Card c)
	{
		score += c.getCardValue();
		{
			this.existence = false;
		}
	}
	
	
	public void set_round_loss(int i){
		if(i == 0){ 
			this.round_loser=false;
		}
		else
		{ 
			this.round_loser = true;
		}
	}
	
	public void set_existence(int i)
	{
		if (i == 0)
			this.existence = false;
		else
			this.existence = true;
	}
	public void set_round_wins(int i)
	{
		if (i == 0)
			this.round_winner = false;
		else
			this.round_winner= true;	
	}

/*
 * 				Card Functions	
 */

	//On loss of a turn Card automatically goes to players disCard, and stays there for the game;


	public Card play_Card(int i)
	{
		return this.hand.get(i);
	}
	public Card remove_from_hand(int i)
	{
		Card temp = this.hand.get(i);
		this.hand.remove(i);
		return temp;
	}

	public void addToHand(Card a)
	{
	this.hand.add(a);
		}
	


	public int lastCard()
	{
		return hand.get(0).getCardRank();
	}
	
	public void play_doubles(){
		if (true){
			// this is where we will allow double -> quadrouples played
			// for now we'll just print a fix this later statement;
			System.out.println("add a double, triple, quad function jack ass");
		}
		
	}	

	public void show_hand(){
		
		for (int i=0; i <this.get_size_of_hand();i++){
		//System.out.println("Card : " + i+ " is: < " + hand.get(i).CardToString() + " > , " );
		}	
	}
	
	
	public int Card_comparator(Card a, Card b){
		  if (a.getCardRank() == b.getCardRank()){
			//  System.out.println("case -1 ");
			  return -1;
		  } else if (a.getCardRank() < b.getCardRank()){
			  //System.out.println("case 1");  
			  return 1;
		  } else 
			 // System.out.println("case 0");
			  return 0;
		  }
	
	  
	public void sort_hand(){
		for (int i = 0; i < hand.size(); i++){
			for(int j = i+1; j < hand.size(); j++){
				// Card A and B are equal
				if (Card_comparator(hand.get(i),hand.get(j)) == -1){
					
					Collections.swap(hand, i+1,  j);
				}// Card A > Card B
				else if (Card_comparator(hand.get(i),hand.get(j))== 0 ){
					Collections.swap(hand, i,j);
					
				}
			
			}
		}
	}
}
