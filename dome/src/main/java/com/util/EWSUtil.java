package com.util;

import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Properties;
import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.enumeration.property.WellKnownFolderName;
import microsoft.exchange.webservices.data.core.service.folder.Folder;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.MessageBody;

public class EWSUtil {

	public static void sendEmail(String subject,String body,List<String> toRecipients,List<String> attachments) throws Exception{

		try {
			ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);

			ExchangeCredentials credentials = new WebCredentials(getProperty("EWS_USER_ID"),getProperty("EWS_USER_PASS"));
			service.setCredentials(credentials);
			service.setTraceEnabled(true);
			service.setUrl(new URI(getProperty("EWS_USER_DOMAIN")));
			EmailMessage msg= new EmailMessage(service);
			
			msg.setSubject(subject); 
			msg.setBody(MessageBody.getMessageBodyFromText(body));
			if(null!= toRecipients && toRecipients.size() > 0){
				for (String toRecipient : toRecipients) {
					msg.getToRecipients().add(toRecipient);

				}
			}else {
				throw new Exception("收件人为空！");

			}
			if(null!= attachments && attachments.size() > 0){
				for (String attachment : attachments) {
					msg.getAttachments().addFileAttachment(attachment);
				}
			}
	
			msg.send();
		} catch ( Exception e) {
			throw new Exception("邮件发送错误 "+ e.getMessage());
		}
		
	}
	
	public static String getProperty(String key){
	    	Properties properties = new Properties(); 
	    	InputStream is = null;
	    	String code="";
	    	try { 
	    		is = EWSUtil.class.getClassLoader().getResourceAsStream("ews.properties"); 
	    		properties.load(is); 		
	    		code = properties.getProperty(key);
	    	}catch(Exception e){
	    		
	    	}
	    	return code;
	 }

	public static void main(String[] args) {
		ExchangeService service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);

		ExchangeCredentials credentials = new WebCredentials(getProperty("EWS_USER_ID"),getProperty("EWS_USER_PASS"));
		service.setCredentials(credentials);
		service.setTraceEnabled(true);
		        try {
		service.setUrl(new URI(getProperty("EWS_USER_DOMAIN")));
		 Folder inbox = Folder.bind(service, WellKnownFolderName.Outbox);
	       System.out.println(inbox.getDisplayName());
		EmailMessage msg= new EmailMessage(service);
		
		msg.setSubject("Hellworld2!"); 
		msg.setBody(MessageBody.getMessageBodyFromText(
		"Sent using the EWS Managed API."));
		msg.getToRecipients().add("yunfei.li3@pactera.com");
		msg.getToRecipients().add("761157164@qq.com");

		msg.getAttachments().addFileAttachment("D://aaa//lyu.txt");
		msg.send();
		} catch ( Exception e) {
		e.printStackTrace();
		}
		System.out.println(getProperty("EWS_USER_ID"));
	}
}
