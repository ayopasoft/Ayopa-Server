package com.ez.server.utils;

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
		public static final String UNIT = "e-unit";
		public static final String INSTITUTION = "ez-institutions";
		public static final String METADATA = "ez-metadata";
		public static final String BRANDING = "ez-branding";
		public static final String DEPARTMENT = "ez-department";
		public static final String INDEPENDENT_DEPT = "ez-independent-dept";
		public static final String SEARCH = "ez-search";
		public static final String PROCEDURE = "ez-procedure";
		public static final String USER = "ez-user";
	}
	
	public static class Key {
		
		
		public static final String UNIT_ID = "unit_id";
		public static final String UNIT_PARENT_ID = "unit_parent_id";
		public static final String UNIT_NAME = "unit_name";
		public static final String UNIT_SHORT_NAME = "unit_short_name";
		public static final String UNIT_UNIQUE_ID = "unit_unique_id";
		public static final String UNIT_URL = "unit_url";
		public static final String UNIT_DESCRIPTION = "unit_description";
		public static final String UNIT_ASK_DEMOGRAPHICS = "unit_ask_demographics";
		public static final String UNIT_HR_SEARCH = "unit_hr_search";
		public static final String UNIT_HR_REVIEW = "unit_hr_review";
		public static final String UNIT_INFO_URLS = "unit_info_urls";
		public static final String UNIT_LOGO = "unit_logo";
		
		// will become unit
		public static final String INSTITUTION_ID = "institution_id";
		public static final String INSTITUTION_NAME = "institution_name";
		public static final String INSTITUTION_SHORTNAME = "institution_shortname";
		public static final String INSTITUTION_URL = "institution_url";
		public static final String INSTITUTION_DESCRIPTION = "institution_description";
		public static final String INSTITUTION_ASK_DEMOGRAPHIC = "institution_ask_demographics";
		public static final String INSTITUTION_HR_SEARCH = "institution_hr_search";
		public static final String INSTITUTION_HR_REVIEW = "institution_hr_review";
		public static final String INSTITUTION_INFO_URLS = "institution_info_urls";
		
		public static final String METADATA_UNIT_ID = "metadata_unit_id";
		public static final String METADATA_COUNTRY = "metadata_country";
		public static final String METADATA_STATE = "metadata_state";
		public static final String METADATA_CITY = "metadata_city";
		public static final String METADATA_LOC_TYPE = "metdata_loc_type";
		public static final String METADATA_UNIT_TYPE = "metadata_unit_type";
		public static final String METADATA_ENROLL = "metadata_enroll";
		public static final String METADATA_TENURE = "metadata_tenure";
		
		public static final String BRANDING_UNIT_ID = "branding_unit_id";
		public static final String BRANDING_BACK_COLOR = "branding_back_color";
		public static final String BRANDING_FRONT_COLOR = "branding_front_color";
		public static final String BRANDING_HEAD_COLOR = "branding_head_color";
		public static final String BRANDING_HEAD_FONT = "branding_head_font";
		public static final String BRANDING_HEAD_SIZE = "branding_head_size";
		public static final String BRANDING_TEXT_COLOR = "branding_text_color";
		public static final String BRANDING_TEXT_FONT = "branding_text_font";
		public static final String BRANDING_TEXT_SIZE = "branding_text_size";
		
		// will become unit
		public static final String DEPARTMENT_ID = "department_id";
		public static final String DEPARTMENT_PARENT_ID = "department_parent_id";
		public static final String DEPARTMENT_NAME = "department_name";
		public static final String DEPARTMENT_UNIQUE_ID = "department_unique_id";
		public static final String DEPARTMENT_URL = "department_url";
		public static final String DEPARTMENT_DESCRIPTION = "department_description";
		public static final String DEPARTMENT_LOGO = "department_logo";
		
		// will become unit
		public static final String INDEPENDENT_DEPT_ID = "independent_dept_id";
		public static final String INDEPENDENT_DEPT_NAME = "independent_dept_name";
		public static final String INDEPENDENT_DEPT_UNIQUE_ID = "independent_dept_unique_id";
		public static final String INDEPENDENT_DEPT_URL = "independent_dept_url";
		public static final String INDEPENDENT_DEPT_DESCRIPTION = "independent_dept_description";
		public static final String INDEPENDENT_DEPT_LOGO = "independent_dept_logo";
		public static final String INDEPENDENT_DEPT_INFO_URLS = "independent_dept_info_urls";

		public static final String SEARCH_ID = "search_id";
		public static final String SEARCH_PARENT_ID = "search_parent_id";
		public static final String SEARCH_POSITION = "search_position";
		public static final String SEARCH_REF_MIN = "search_ref_min";
		public static final String SEARCH_REF_MAX = "search_max_ref";
		public static final String SEARCH_MAIL = "search_mail";
		public static final String SEARCH_PDF = "search_pdf";
		public static final String SEARCH_WORD = "search_word";
		public static final String SEARCH_DESCRIPTION = "search_description";
		public static final String SEARCH_CITIZEN_INFO = "search_citizen_info";
		public static final String SEARCH_FOUND = "search_found";
		public static final String SEARCH_AD_PLACES = "search_ad_places";
		public static final String SEARCH_OFFLINE = "search_offline";
		public static final String SEARCH_ONLINE = "search_online";
		public static final String SEARCH_REVIEW = "search_review";
		
		public static final String PROCEDURE_ID = "procedure_id";
		public static final String PROCEDURE_SEARCH_ID = "procedure_search_id";
		public static final String PROCEDURE_NAME = "procedure_name";
		public static final String PROCEDURE_MIN = "procedure_min";
		public static final String PROCEDURE_MAX = "procedure_max";
		public static final String PROCEDURE_INSTRUCTIONS = "procedure_instructions";
		public static final String PROCEDURE_PDF = "procedure_pdf";
		public static final String PROCEDURE_WORD = "procedure_word";
		
		public static final String USER_ID = "user_id";
		public static final String USER_UNIT_ID = "user_unit_id";
		public static final String USER_FIRST = "user_first";
		public static final String USER_LAST = "user_list";
		public static final String USER_ROLE = "user_role";
		
		
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
			createDomain(Table.UNIT);
			createDomain(Table.INSTITUTION);		// will become unit
			createDomain(Table.BRANDING);
			createDomain(Table.METADATA);
			createDomain(Table.DEPARTMENT);			// will become unit
			createDomain(Table.INDEPENDENT_DEPT);	// will become unit
			createDomain(Table.USER);
			
			
			
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
