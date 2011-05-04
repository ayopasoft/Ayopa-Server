package com.ayopa.server.model;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class InvoiceTest {

	@Test
	public void testGetInvoicesForMerchants() throws IOException {
		Auction auction = new Auction();
		List<Auction> auctions = new ArrayList<Auction>();
		List<Invoice> invoices = new ArrayList<Invoice>();
		
		//auctions = Auction.getAuctionsforRebate();
		
		//invoices = Invoice.getInvoicesForMerchants(auctions);
		
		assertEquals("invoice auction id incorrect","",invoices.get(0).getAuction_info().get(1).toString());
		
	}

}
