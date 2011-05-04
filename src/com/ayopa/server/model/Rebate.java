package com.ayopa.server.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Rebate {

	public void getRebatesforConsumers() throws IOException
	{
		List<Auction> auctions = new ArrayList<Auction>();
		List<Purchase> purchases = new ArrayList<Purchase>();
		auctions = Auction.getAuctionsforRebate();
		
		for (int i = 0; i < auctions.size(); i++){
			purchases = 
		}
	}
	
}
