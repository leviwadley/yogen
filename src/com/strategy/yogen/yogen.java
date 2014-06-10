package com.strategy.yogen;
import java.io.BufferedReader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;






import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import android.annotation.SuppressLint;
class yogen
{

/*
*				Global Variables
*/
	//PowerManager pwrmng = (PowerManager)context.getSystemService(Context.POWER_SERVICE); 
	//WakeLock wklk = pwrmng.newWakeLock(PowerManager.FULL_WAKE_LOCK, "MyLock"); 
	
	private static int JSON_Deck[] = new int[52];
	
	private Deck game_Deck = new Deck();// This holds the 52 Cards
	private Card round_winning_Card = new Card();// This stores the immediate round winning Card;
	private List<Card> session_disCard = new ArrayList<Card>(52);
	private List<Card> round_disCard = new ArrayList<Card>(52);
	private player player_one = new player();
	private player player_two = new player();
	private player player_three = new player();
	private player player_four = new player();
	private int game_number;// game ID NUM
	private int num_players;// number of active players for dealing purposes
	private int Cards_this_round; // number of cards to deal this round
	private int itr;// location in the JSON_Deck the cards are at.
	private int current_players_turn;// name speaks for itself

	private int current_round_winner;// name speaks for itself


	
	
	
	public boolean is_round_one;
	public Scanner std = new Scanner(System.in);
	public Card get_round_winning_Card(){
		return round_winning_Card;
	}
	private void transfer_from_round_to_session(){
		while(!(session_disCard.isEmpty())){
			
			 session_disCard.add(round_disCard.get(0));
			
		}
	 
	 }
	/*This is how the game gets player ID's*/
	public void get_all_player_information(JSONArray n) throws JSONException
	{
		try {
		player_one.set_players_name(n.getString(0));
		player_two.set_players_name(n.getString(1));
		player_three.set_players_name(n.getString(2));
		player_four.set_players_name(n.getString(3));
		}
		catch(JSONException e){
			throw new RuntimeException(e);
		}
		
	}
	/*This will be used to update every 5 seconds*/
	public void update_data(JSONArray info)throws JSONException
	{
	try{
		game_number = info.getInt(0);
		itr = info.getInt(1);
		current_players_turn = info.getInt(2);
		if(player_one.E()){player_one.set_score(info.getInt(3));}
		if(player_two.E()){player_two.set_score(info.getInt(4));}
		if(player_three.E()){player_three.set_score(info.getInt(5));}
		if(player_four.E()){player_four.set_score(info.getInt(6));}
		current_round_winner = info.getInt(7);
		Cards_this_round = info.getInt(8);
		num_players = info.getInt(9);
	}
	catch (JSONException e)
	{
		throw new RuntimeException(e);
	}
		/*
	 	0: game_number,
		1: deck_iterator, 
		2: current_players_turn,
		3: player_1’s current score,   will be -1 if player no longer exists in the game;
		4: player_2’s current score, will be -1 if player no longer exists in the game;
		5: player_3’s current score, will be -1 if player no longer exists in the game;
		6: player_4’s current score, will be -1 if player no longer exists in the game; 
		7: current rounds winner
		8: number_of_cards_to_deal_this_round// losing players card value                        
		}
	*/
}
	
	public void get_JSON_Deck(JSONArray d)throws JSONException
	{
		try {
			for (int i = 0; i < JSON_Deck.length; i++)
			{
				JSON_Deck[i] = d.getInt(i);
			}
		}
		catch (JSONException e)
		{
			throw new RuntimeException(e);	
		}
		game_Deck.make_deck(JSON_Deck);
	}
	
	
	public int round_comparator(Card a, Card b){
		  if (a.getCardRank() == b.getCardRank()){
			  System.out.println("case -1 ");
			  return -1;
		  } else if (a.getCardRank() < b.getCardRank()){
			  System.out.println("case 1");  
			  return 1;
		  } else 
			  System.out.println("case 0");
			  return 0;
		  }
	
	  

	
	private int get_num_players(){
		return num_players;
	}
/*
*				Constructors
*/

//			make_player(){}
//			add_player(){}
//			players_this_round(player a, player b){}
//			players_this_round(player a, player b, player c){}
//			players_this_round(player a, player b, player c, player d){}	
	
	 

/*
* 				Setters
*/
	
	public void establish_players(int num_players){ // sets the number of players at the start of a game needs to be added i
		if (num_players == 4)
		{
			player_one.set_existence(1);
			player_two.set_existence(1);
			player_three.set_existence(1);
			player_four.set_existence(1);
		}else if (num_players == 3){
			player_one.set_existence(1);
			player_two.set_existence(1);
			player_three.set_existence(1);
			player_four.set_existence(0);
		}else{
			player_one.set_existence(1);
			player_two.set_existence(1);
			player_three.set_existence(0);
			player_four.set_existence(0);
		}
			
	} 

	public void reset_scores()
	{
	if (player_one.E()){
//		player_one.set_score(0);
		player_one.set_round_loss(0);
		player_one.set_round_wins(0);
	}
	if (player_two.E()){
//		player_two.set_score(0);
		player_two.set_round_loss(0);
		player_two.set_round_wins(0);
	}
	if (player_three.E()){
//		player_three.set_score(0);
		player_three.set_round_loss(0);
		player_three.set_round_wins(0);
	}
	if (player_four.E()){
//		player_four.set_score(0);
		player_four.set_round_loss(0);
		player_four.set_round_wins(0);
	}
	}
	private void set_Cards_this_round(Card a){
		Cards_this_round = a.getCardValue();
	}
	private void set_Cards_this_round(int a){
		Cards_this_round = a;
	}
	private void set_num_players(int i){
		num_players += i;
	}	
	private void remove_player(int i){
		num_players -=i;
	}
	public void set_winning_this_turn(Card a, player p){
		if (a.getCardRank()>=round_winning_Card.getCardRank()){
			player_one.set_round_wins(0);
			player_two.set_round_wins(0);
			player_three.set_round_wins(0);
			player_four.set_round_wins(0);
			round_winning_Card.cloneCard(a);
			p.set_round_wins(1);
		}
		round_disCard.add(a);
	}

	public void set_round_winner(Card a, player p){
		if (a.getCardRank() >= round_winning_Card.getCardRank()){
			player_one.set_round_wins(0);
			player_two.set_round_wins(0);
			player_three.set_round_wins(0);
			player_four.set_round_wins(0);
			round_winning_Card= a;
			p.set_round_wins(1);
		}	
	}

	public Card winners_and_loser(){
		int one = 0, two=0, three=0, four=0;
			//System.out.println("Winners and Losers, Do I get this far");
			if(player_one.E()){one = player_one.lastCard();}
			//System.out.println("howsa");
			if(player_two.E()){two = player_two.lastCard();}
			//System.out.println("Bowser");
			if(player_three.E()){three = player_three.lastCard();}
			//System.out.println("Chowder");
			if(player_four.E()){four = player_four.lastCard();}
			//System.out.println("here too");
			int[] temp = {one, two, three, four};
			Arrays.sort(temp);
			// everything set for no null pointer
			if(temp [3] == one){
				player_one.set_score(player_one.lastCard());
			}
			if(temp [3] == two){
				player_two.set_score(player_two.lastCard());}
			if(temp [3] == three){
				player_three.set_score(player_three.lastCard());}
			if(temp [3] == four){
				player_four.set_score(player_four.lastCard());}
			int i = 0;
			Arrays.sort(temp);
			Card winner = new Card();
			while (temp[i]==0)
			{
				i++;
			}
			// logically this is just in the utra rare instance of a 4 payers ending with the same Card.
			if(one == two && (three == four) && one == three &&(one != 0
					|| three != 0)){ 
					System.out.println("Nobody wins this needs to be taken care of");
				return player_one.get_Card_at(0);
			
			} else {
				if(temp[i] == one)
				{
					player_one.set_round_wins(1);
					System.out.println("Player 1 wins");
					winner= player_one.get_Card_at(0);
				}
				else if(temp[i] == two)
				{
					player_two.set_round_wins(1);
					System.out.println("Player 2 wins");
					winner=player_two.get_Card_at(0);}
				else if(temp[i] == three)
				{
					player_three.set_round_wins(1);
					winner=player_three.get_Card_at(0);
				}
				else if(temp[i] == four)
				{
					player_four.set_round_wins(1);
					winner =player_four.get_Card_at(0);
				}
				else{
					System.out.println("Error in choosing winner");
					
					}
				}
				System.out.println("End of round");
				System.out.println("\n Setting Card number for next round"); 
				Cards_this_round = winner.getCardValue();
				
				while(!(session_disCard.isEmpty())){
					System.out.println("\nB4 trx\t");
				
					game_Deck.push_front(session_disCard.get(0));
					session_disCard.remove(0);
					System.out.println("\nafter trx\t");
				
				 
			
				}
				return winner;		
	} 	
	
	
	private int get_Cards_this_round(){
		return Cards_this_round;
	}






	public void which_Cards_to_swap()
	{
		/*
		 * Scanner swp = new Scanner(System.in);
		 *
		System.out.println("how many Cards do you wish to swap:\n" +
		"use #_#_# format\n");
		print_player_hand(the_player);
		int j = Integer.parseInt(swp.nextLine());
		int[] myIntArray = new int[j];
		int i = 0;
		j = 0;
		System.out.println("Enter number of Card you wish to swap out:\n"+
		"Enter a negative integer when done swapping");
		while (i >= 0)
		{
			i = Integer.parseInt(swp.nextLine());
			myIntArray[j] = i; 
			j++;
		}
		for (j = 0; j < myIntArray.length; j ++)
		{
			the_player.removeFromHand(myIntArray[j]);
			the_player.addToHand(game_Deck.deal_Cards());
			game_Deck.pop_front();
		}

	*/
		
		System.out.println("Add this function to allow Card swap");
	}


	public void recieveCard(player the_player)
	{
		the_player.addToHand(game_Deck.deal_Cards());	
	}

	
/*
* 					getters
*/	
	
/*
 * 					Playing methods
 * 	
 */
	
	private void deal_Cards(int num_Cards){
		for(int i = 0; i < num_Cards; i++){
			if(player_one.E())recieveCard(player_one);
			if(player_two.E())recieveCard(player_two);
			if(player_three.E())recieveCard(player_three);
			if(player_four.E())recieveCard(player_four);
		}
	}

	public void turn(player the_player)
	{
		the_player.sort_hand();
		the_player.show_hand();
		System.out.println("play which Card?\n" + "the_player.get_size_of_hand: " +the_player.get_size_of_hand());
		System.out.println("\nThis player is" + the_player.a());
		boolean play_fair = true;
		while(play_fair == true ){
			int a = Integer.parseInt(std.nextLine());
			if(a >= the_player.get_size_of_hand()|| a < 0){
				System.out.println("error Out of bounds");
				play_fair=true;
			}
			else if (round_disCard.isEmpty()){
				
				set_winning_this_turn(the_player.get_Card_at(a), the_player);
				the_player.play_Card(a);
				round_disCard.add(the_player.remove_from_hand(a));
				play_fair = false;
			}
			else if (the_player.get_rank(a) >= round_winning_Card.getCardRank()
					|| the_player.get_rank(a) == the_player.get_rank(0)){
				
				set_winning_this_turn(the_player.get_Card_at(a), the_player);
				the_player.play_Card(a);
				round_disCard.add(the_player.remove_from_hand(a));
				play_fair=false;
			
			} else {
				System.out.println("The Card is not, your lowest, or is not greater than the previous, try again");
				play_fair =  true;
			}	
		}
		
		
	}


	
/*
 * 					The GAME METHOD	
 */
	public void play_in_order()
	{
	
	
		if (player_one.T()){
			turn(player_one);
			if(player_two.E()){turn(player_two);}
			if(player_three.E()){turn(player_three);}
			if(player_four.E()){turn(player_four);}
		} 
		else if(player_two.T())
		{
			turn(player_two);
			if(player_three.E()){turn(player_three);}
			if(player_four.E()){turn(player_four);}
			if(player_one.E()){turn(player_one);}
		}
		else if (player_three.T())
		{
			turn(player_three);
			if(player_four.E()){turn(player_four);}
			if(player_one.E()){turn(player_one);}
			if(player_two.E()){turn(player_two);}
		}
		else
		{
			turn(player_four);
			if(player_one.E()){turn(player_one);}
			if(player_two.E()){turn(player_two);}
			if(player_three.E()){turn(player_three);}
		}
		
	
	}

public void game(int number_of_players)
{
	player_one.this_player = 1;
	player_two.this_player = 2;
	set_num_players(number_of_players);
//	game_Deck.makeDeck();
//	game_Deck.shuffleDeck();
	establish_players(get_num_players()); //from main
	player_one.set_round_wins(1); // player 1 is default
	set_Cards_this_round(2);// default
			
		set_num_players(num_players);
		
//This is the session while loop		
		while(num_players > 1){
			Round();
		}
}


			
	public void Round()
	{		
		System.out.println("Round 1st: " + Cards_this_round);
		int temp = Cards_this_round;
		game_Deck.shuffleDeck();
		deal_Cards(temp);
		System.out.println("2nd");
		
		System.out.println("\nCards dealt "+ Cards_this_round);
		while (Cards_this_round > 1)
		{
			System.out.println("Lettuce begin this round");
			play_in_order();
			Cards_this_round--;
			System.out.println("\nCards in hand  "+Cards_this_round);
			transfer_from_round_to_session();
		}
		System.out.println("\n do I get this far");
		round_winning_Card = winners_and_loser();
		System.out.println("\n and here");
		
		if(player_one.E()){session_disCard.add(player_one.remove_from_hand(0));}
		if(player_two.E()){session_disCard.add(player_two.remove_from_hand(0));}
		if(player_three.E()){session_disCard.add(player_three.remove_from_hand(0));}
		if(player_four.E()){session_disCard.add(player_four.remove_from_hand(0));}
	}
		
		
	
	
	
	public static void main(String args[]){
	System.out.println("The beginning");
	yogen YOGEN = new yogen();
	YOGEN.game(2);
		 }
	}
	
