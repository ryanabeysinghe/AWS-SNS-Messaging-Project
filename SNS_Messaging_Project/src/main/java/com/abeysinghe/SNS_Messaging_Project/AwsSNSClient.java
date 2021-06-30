package com.abeysinghe.SNS_Messaging_Project;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

/* 
 * This Program sends a Single or Multiple SMS message(s) to user(s). 
 */

public class AwsSNSClient {

	public static final String AWS_ACCESS_KEY_ID = "aws.accessKeyId"; 
	public static final String AWS_SECRET_KEY = "aws.secretKey"; 
	
	static {
		System.setProperty(AWS_ACCESS_KEY_ID, "AKIA4KXGF2IGZZMKC6F5"); 
		System.setProperty(AWS_SECRET_KEY, "Enter Secret Key (Must remain confidential for security purposes)"); 
	}
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); 
		
		System.out.println("Welcome to my program! Please choose an option below");
		System.out.println("Option 1 - Send Single SMS Message");
		System.out.print("Option 2 - Send Multiple SMS Messages \nSelect Option: ");
		int selectOption = scanner.nextInt();
		
		switch(selectOption) {
			case 1: 
				AwsSNSClient singleClient = new AwsSNSClient(); 
				singleClient.sendSingleSMSMessage("How are you doing today?", "+1-3018738012");
				break;
			case 2: 
				AwsSNSClient multipleClients = new AwsSNSClient();
				multipleClients.sendMultipleSMSMessages("My YouTube channel is Ranketsujo.");
				break;
			default: 
				System.out.println("Incorrect option was selected!");
		}
		
		scanner.close();
	}
	
	
	public void sendSingleSMSMessage(String message, String phoneNumber) {
		AmazonSNS snsClient = AmazonSNSClient.builder().withRegion(Regions.US_EAST_1).build(); 
		Map<String, MessageAttributeValue> smsAttributes = new HashMap<>(); 
		smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue().withStringValue("Abeysinghe")
				.withDataType("String"));
		smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue().withStringValue("Transactional")
				.withDataType("String"));
		
		PublishResult result = snsClient.publish(new PublishRequest()
				.withMessage(message)
				.withPhoneNumber(phoneNumber)
				.withMessageAttributes(smsAttributes));
		
		System.out.println("Message Sent Successfully: " + result.getMessageId());
	}
	
	public void sendMultipleSMSMessages(String message) {
		AmazonSNS snsClient = AmazonSNSClient.builder().withRegion(Regions.US_EAST_1).build(); 
		Map<String, MessageAttributeValue> smsAttributes = new HashMap<>(); 
		smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue().withStringValue("Abeysinghe")
				.withDataType("String"));
		smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue().withStringValue("Transactional")
				.withDataType("String"));
		
		PublishResult result = snsClient.publish(new PublishRequest()
				.withTopicArn("arn:aws:sns:us-east-1:847665025549:SNS_Messaging_Project")
				.withMessage(message)
				.withMessageAttributes(smsAttributes));
		
		System.out.println("Message Sent Successfully: " + result.getMessageId());
	}
}
