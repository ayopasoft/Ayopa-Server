package com.ayopa.server.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteBucketRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.simpledb.AmazonSimpleDB;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.Attribute;
import com.amazonaws.services.simpledb.model.CreateDomainRequest;
import com.amazonaws.services.simpledb.model.DeleteDomainRequest;
import com.amazonaws.services.simpledb.model.GetAttributesRequest;
import com.amazonaws.services.simpledb.model.GetAttributesResult;
import com.amazonaws.services.simpledb.model.Item;
import com.amazonaws.services.simpledb.model.PutAttributesRequest;
import com.amazonaws.services.simpledb.model.ReplaceableAttribute;
import com.amazonaws.services.simpledb.model.SelectRequest;
import com.amazonaws.services.simpledb.model.SelectResult;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.CreateQueueResult;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.DeleteQueueRequest;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.util.json.JSONObject;


public class AwsFacade {
	private static Log log = LogFactory.getLog(AwsFacade.class);
	
	protected static boolean DELETE_FIRST = false;  
	private static boolean CREATE_RESOURCES = true;

	public static class Table {
		public static final String AUCTION = "ayopa-auctions";
		public static final String MERCHANT = "ayopa-merchants";
		public static final String CATEGORY = "ayopa-categories";
		public static final String BUYER = "ayopa-buyers";
		public static final String PURCHASE = "ayopa-purchases";
	}
	
	public static class Key {
		public static final String ROW_ID = "*row-id*";
		public static final String AUCTION_ID = "auction-id";
		public static final String PRODUCT_ID = "product-id";
		public static final String PRODUCT_NAME = "product-name";
		public static final String PRODUCT_DESCR = "product-descr";
		public static final String PRODUCT_CAT = "product-cat";
		public static final String PRODUCT_IMAGE_URL = "product-img-url";
		public static final String PRODUCT_URL = "product-url";
		public static final String AUCTION_START = "auction-start";
		public static final String AUCTION_END = "auction-end";
		public static final String AUCTION_HIGHLIGHTED = "auction-highlighted";
		public static final String AUCTION_MAXUNITS = "auction-maxunits";
		public static final String AUCTION_STARTPRICE = "auction-startprice";
		public static final String AUCTION_PRICECONFLICT = "auction-priceconflict";
		public static final String AUCTION_SCHEDULE = "auction-schedule";
		public static final String AUCTION_DELETED = "auction-deleted";
		public static final String AUCTION_ENDED = "auction-ended";
		
		public static final String MERCHANT_ID = "merchant-id";
		public static final String MERCHANT_NAME = "merchant-name";
		public static final String MERCHANT_WEBSITE = "merchant-website";
		public static final String MERCHANT_USERNAME = "merchant-username";
		public static final String MERCHANT_PASSWORD = "merchant-password";
		public static final String MERCHANT_ADDRESS1 = "merchant-address1";
		public static final String MERCHANT_ADDRESS2 = "merchant-address2";
		public static final String MERCHANT_CITY = "merchant-city";
		public static final String MERCHANT_STATE = "merchant-state";
		public static final String MERCHANT_COUNTRY = "merchant-country";
		public static final String MERCHANT_POSTALCODE = "merchant-postalcode";
		public static final String MERCHANT_CONTACT = "merchant-contact";
		public static final String MERCHANT_EMAIL = "merchant-email";
		public static final String MERCHANT_FB_PAGE = "merchant-fb-page";
		public static final String MERCHANT_AYOPA_FB = "merchant-ayopa-fb";
		
		public static final String CATEGORY_NAME = "category-name";
		
		public static final String PURCHASE_ID = "purchase-id";
		public static final String PURCHASE_AUCTION_ID = "purchase-auction-id";
		public static final String PURCHASE_BUYER_ID = "purchase-buyer-id";
		public static final String PURCHASE_QUANTITY = "purchase-quantity";
		public static final String PURCHASE_PRICE = "purchase-price";
		public static final String PURCHASE_DATE = "purchase-date";
		
		public static final String BUYER_ID = "buyer-id";
		public static final String BUYER_ACCESS_TOKEN = "buyer-access-token";
		public static final String BUYER_NAME = "buyer-name";
		public static final String BUYER_EMAIL = "buyer-email";
		
	}
	
	public static class Bucket {
		public static final String PRODUCT_IMAGES = "ayopa-product-images";
	}
	
	// Keys
	public static final String FILE_TYPE   = "file-type";
	public static final String FILE_NAME   = "file-name";
	public static final String BUCKET_NAME = "bucket-name";
	public static final String PERIOD      = "period";

	private AmazonSimpleDB sdb;
	private AmazonS3 s3;
	private AmazonSQS sqs;
	
	private Map<String,String> queueUrls = new HashMap<String,String> (); 

	protected AwsFacade () throws IOException {
		AWSCredentials credentials = new PropertiesCredentials(AwsFacade.class.getResourceAsStream("/AwsCredentials.properties"));
		sdb = new AmazonSimpleDBClient(credentials);
		s3  = new AmazonS3Client (credentials);
		sqs = new AmazonSQSClient(credentials);
	
		if (CREATE_RESOURCES) {
			createDomain(Table.AUCTION);
			createDomain(Table.MERCHANT);
			createDomain(Table.CATEGORY);
			createDomain(Table.BUYER);
			createDomain(Table.PURCHASE);
		}
	}
	
	private static AwsFacade _instance = null;

	public static synchronized AwsFacade getInstance() throws IOException {
		if (_instance == null)
			_instance = new AwsFacade ();
		return _instance;
	}
	
	public AmazonSimpleDB getSdb() {
		return sdb;
	}

	public AmazonS3 getS3() {
		return s3;
	}

	public AmazonSQS getSqs() {
		return sqs;
	}

	public boolean fileExists(String bucket, File file) {
		try {
			ObjectMetadata info = s3.getObjectMetadata(bucket, file.getName());
			if (info.getContentLength() == file.length())
				return true;
			return false;
		}
		catch (AmazonServiceException e) {
			if (e.getStatusCode() == 404)
				return false;
			throw e;
		}
	}
	
	public void writeFileToS3(String bucket, File file) {
		writeFileToS3(bucket, file.getName(), file);
	}

	public void writeFileToS3(String bucket, String name, File localfile) {
		log.info("Storing file in S3: " + name);
		s3.putObject(new PutObjectRequest(bucket, name, localfile));
	}

	public void makePublic(String bucket, String name) {
		log.info("Making file public in S3: " + name);
		s3.setObjectAcl(bucket, name, CannedAccessControlList.PublicRead);
	}

	public void makePrivate(String bucket, String name) {
		log.info("Making file private in S3: " + name);
		s3.setObjectAcl(bucket, name, CannedAccessControlList.Private);
	}


	public void readFileFromS3(String bucket, String name, File file) throws IOException {
		log.info("Reading object from S3: " + name);
		S3Object o = s3.getObject(new GetObjectRequest(bucket, name));
		
		OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
		IOUtils.copy (o.getObjectContent(), os);
		os.close();
	}

	public List<Message> getMessages(String queue) {
		String url = getQueueUrl(queue);
		if (url == null)
			return null;
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(url);
        List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
        return messages;
	}

	public void removeMessageFromQueue(String queue, Message message) {
		String url = getQueueUrl(queue);
		if (url == null)
			return;
		String handle = message.getReceiptHandle();
        sqs.deleteMessage(new DeleteMessageRequest(url, handle));
	}

	public void sendMessage(String queue, JSONObject msg) {
		String url = getQueueUrl(queue);
		if (url == null)
			return;
		sqs.sendMessage(new SendMessageRequest(url, msg.toString()));
	}

	private String getQueueUrl(String queue) {
		String url = queueUrls.get(queue);
		if (url == null)
			createQueue (queue);
		url = queueUrls.get(queue);
		if (url == null) {
			log.error("Unable to create message queue or get queue URL for queue: " + queue);
			return null;
		}
		return url;
	}

	private void createDomain(String domain) {
		log.info("(Re-)Creating domain: " + domain);
		if (DELETE_FIRST)
			sdb.deleteDomain(new DeleteDomainRequest(domain));
		sdb.createDomain(new CreateDomainRequest(domain));
	}

	public void createBucket(String bucket) {
		if (s3.doesBucketExist(bucket)) {
			if (DELETE_FIRST) {
				ObjectListing objects = null;
				for (;;) {
					if (objects == null)
						objects = s3.listObjects(bucket);
					else
						objects = s3.listNextBatchOfObjects(objects);
					for (S3ObjectSummary obj: objects.getObjectSummaries()) {
						s3.deleteObject(bucket, obj.getKey());
					}
					if (!objects.isTruncated())
						break;
				}
				s3.deleteBucket(new DeleteBucketRequest(bucket));
			}
			else
				return;
		}
	
		log.info("Creating bucket: " + bucket);
		s3.createBucket (bucket);
	}

	private void createQueue(String queue) {
		if (queueUrls.containsKey(queue))
			return;
		log.info("(Re-)Creating queue: " + queue);
		CreateQueueResult result = sqs.createQueue(new CreateQueueRequest(queue));

		if (DELETE_FIRST) {
			sqs.deleteQueue(new DeleteQueueRequest(result.getQueueUrl()));
			for (;;) {
				try {
					Thread.sleep (100*1000);
					break;
				} catch (InterruptedException e) {
					// ignore ();
				}
			}
			result = sqs.createQueue(new CreateQueueRequest(queue));
		}
		
		queueUrls.put(queue, result.getQueueUrl());
	}

	public void waitForQueue() {
		log.info("Waiting for 10 seconds");
		try {
			Thread.sleep (10*1000);
		}
		catch (InterruptedException e)
		{
			// ignore() ;
		}
	}
	
	public Map<String,String> getRow (String domain, String rowId) {
		Map<String,String> map = new HashMap<String,String> ();
    	GetAttributesResult res;
    	java.util.List<Attribute> attrs;
    	res = sdb.getAttributes(new GetAttributesRequest(domain, rowId));
    	attrs = res.getAttributes();
    	for (Attribute attr : attrs) {
    		map.put(attr.getName(), attr.getValue());
    	}
    	return map;
	}
	
	public void putRow (String domain, String rowId, Map<String,String> map) {
		List<ReplaceableAttribute> replaceableAttributes = new ArrayList<ReplaceableAttribute>();
		for (String key : map.keySet()) {
			String value = map.get(key);
			replaceableAttributes.add(new ReplaceableAttribute(key, value, true));
		}
		sdb.putAttributes(new PutAttributesRequest(domain, rowId, replaceableAttributes));
	}
	
	public List<Map<String,String>> selectRows (String query) {
		List<Map<String,String>> results = new ArrayList<Map<String,String>> ();
		
		SelectResult result = getSdb().select(new SelectRequest(query));
		for (;;) {
			for (Item item : result.getItems()) {
				Map<String,String> map = new HashMap<String,String>();
				for (Attribute attribute : item.getAttributes()) {
					map.put (attribute.getName(), attribute.getValue());
				}
				results.add (map);
			}
			if (result.getNextToken() != null) {
				SelectRequest request = new SelectRequest(query);
				request.setNextToken(result.getNextToken());
				result = getSdb().select(request);
			}
			else
				break;
		}

		return results;
	}

}
