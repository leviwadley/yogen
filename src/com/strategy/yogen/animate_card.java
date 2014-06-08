package com.strategy.yogen;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.Log;
import java.lang.InterruptedException;
import java.lang.Runnable;
import java.lang.Thread;
import java.lang.Math;
import java.util.ArrayList;

public class animate_card {

	
		final float ppf = 40;
		protected yogenView theView;
	//	private ArrayList<card> theCard;
		private card_anchor theAnchor;
		private int count;
		private int frames;
		private float Cx;
		private float Cy;
		private boolean animate;
		private Runnable Callback;
		
		
		
		public animate_card(yogenView v)
		{
			theView = v;
			animate = false;
			theCard = new ArrayList(52);
			Callback = null;
		} 
		
private void draw_cards(Resources r){
	
	
	
}
}
