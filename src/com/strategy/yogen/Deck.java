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
import java.util.Scanner; 
import java.util.Random;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.Random;
import java.util.Collections;


public class Deck extends Card
{
	
	private List<Card> CardDeck= new ArrayList<Card>();

	private int itr;// the iterator of the deck.
	
	public void make_deck(int JSON_Deck[])
	{
	for (int i = 0; i < 52; i++){
		CardDeck.add(i,create_card(JSON_Deck[i]));
	}	
	itr = 0;
	}
	public int get_itr(){return this.itr;}
	public void modify_itr(int i){this.itr += i;}
	
	
	public void update_itr(int i){this.itr = i;}
	public void update_deck(int i)
	{
		for (int j = 0; j < i; j++)
		{
			CardDeck.remove(0);
		}
	modify_itr(i);
	}
	

	public void push_front(Card a){
		this.CardDeck.add(a);
	}
	public void pop_front(int i)
	{
		for(int j = 0; j < i; j++)
		this.CardDeck.remove(0);	
	}  
	public void viewDeck()
	{
		int counter =0 ;
		for (int i = 0; i < CardDeck.size(); i++)
		{
			System.out.format("Value: %i\tRank: %i\t num: %i\t\n", CardDeck.get(i).getCardValue(), CardDeck.get(i).getCardRank(), CardDeck.get(i).getCardNum());
		}
	}
	public void shuffleDeck()
	{
		Collections.shuffle(CardDeck);
	}
	
	public Card deal_Cards()
	{
		  	Card temp = CardDeck.get(0);
		  	CardDeck.remove(0);
		  	itr += 1;
		  	return temp;
	}

}
