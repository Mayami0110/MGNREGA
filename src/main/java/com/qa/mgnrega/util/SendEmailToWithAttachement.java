package com.qa.mgnrega.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmailToWithAttachement {
	private String from;

	private List<String> to;

	private List<String> cc;
	
	/*
	private String to;

	private String cc;*/

	private String subject;

	private String messageBody;

	private String fileName1;
	private String fileName2;

//test comments again
	
	private String host;

	private String port;

	String strDataFileName = this.getClass().getSimpleName();
	
	public String strAbsolutepath = (new File("")).getAbsolutePath();

	private String strDataPath = this.strAbsolutepath + "\\src\\main\\java\\com\\qa\\mgnrega\\testdata\\";

	private String strFilePath = this.strAbsolutepath + "/TestResults/";
	ReadDataFile readData = new ReadDataFile();
	int rownumber = 1;

	private Properties properties;

	private MimeMessage message;

	private BodyPart messageBodyPart;

	private Multipart multipart;

	private Authenticator authenticator;
	
	String strWebPortalExecutionTime = "";
	 String  strMisReportExecutionTime ="";

	public void SendMailMethod(String WebPortalExecutionTime, String MisReportExecutionTime , String finalStatus ) {
		
		
		
		String name = new Object(){}.getClass().getEnclosingMethod().getName();
		
		this.from = "mayank_mishra@cms.co.in";
		
		to = FetchEmails(strDataFileName, "EMailForTo",  name);
		cc = FetchEmails(strDataFileName, "EMailForCC", name);

		
		/*this.to = "mayami0110@gmail.com";
		this.cc = "mayank_mishra@cms.co.in";*/
		String dateTime = (new SimpleDateFormat("dd-MM-yyyy hh:mm a")).format(Calendar.getInstance().getTime());
		
		
		//String newfinalstatusText = "Working";
		
		
		
		this.subject = "Check Status of WebPortal/MIS Portal  - " + dateTime + "  STATUS : " + finalStatus ; ;
			
		this.messageBody = "<html><body> <h4>Hi All, &nbsp; </h4></body></html>";
		this.messageBody += "<html><body><h4>Please find the Status of Web-Portal :</br></br></br></h4></body></html>";
	//	this.messageBody += "<html><body><h3><u> Total Applications Pending - Application Wise  :</u><h3> </body></html>";
		this.messageBody += "<html> <head> </head> <body> <table border=\"1\" cellpadding=\"1\" cellspacing=\"1\"> <tr style=\"background-color: #a9c2e8;\">";
		this.messageBody += "<td><strong>S No &nbsp;&nbsp;</strong></td><td><strong>Test Step &nbsp;&nbsp;</strong></td><td><strong>Expected Conditons  </strong></td><td><strong>Execution Time &nbsp;&nbsp;&nbsp; </strong></td><tr>";
		this.messageBody += "<td>1.&nbsp;</td><td>Open Web Portal URL	&nbsp;</td><td>Navigate to Web Portal Page &nbsp;</td><td>&nbsp;" + WebPortalExecutionTime +"&nbsp; </td><tr>";
		this.messageBody += "<td>2.&nbsp;</td><td>Click on MIS Reports &nbsp;</td><td>Navigate to MIS Reports &nbsp;</td><td>&nbsp;" + MisReportExecutionTime +"&nbsp; </td><tr></body></html>";
	
		if (finalStatus.equalsIgnoreCase("Working")) {
		this.messageBody += "<html><body><h1><u> STATUS :&nbsp;&nbsp;  <font color=\"Green\">" + finalStatus
				+ "</u> <h1> </body></html>";
	} else {
		this.messageBody += "<html><body><h1><u> STATUS : &nbsp;&nbsp; <font color=\"Red\">" + finalStatus
				+ " </u><h1> </body></html>";
	}

				
		this.messageBody += "<html><body><h4><font color=\"blue\">Note: This is an auto-generated mail. </br></br></br></font> </h4></body></html>";
		this.messageBody += "<html><body><h3> Thanks & Regards,</h3></body></html>";
		this.messageBody += "<html><body><h3> QA Team </h3></body></html>";
		//this.fileName2 = "WebPortalPage";
		this.fileName1 = "MISReportPage";
		
		
		
		
		this.host = "mail.cms.co.in";
		this.port = "587";
		this.authenticator = new SMTPAuthenticator();
		this.properties = System.getProperties();
		this.properties.put("mail.smtp.host", this.host);
		this.properties.put("mail.smtp.starttls.enable", "false");
		this.properties.put("mail.smtp.port", this.port);
		this.properties.put("mail.smtp.auth", "true");
		
		performTask();

	}
	
	
	public List<String> FetchEmails(String strDataFileName, String strElement,String Methodname) 
	{
		List<String> array= new ArrayList<String>();

		try {
			int columnCount = 0;
			String[] Emails = new String[0];
			int rowNumber = 1;
			String strEmails = readData.readDataFile(strDataFileName, rowNumber, strElement , Methodname);

			Emails = strEmails.split(";");
			List<String> emailIds= Arrays.asList(Emails);

			columnCount = emailIds.size();

			for (int cnt = 0; cnt < columnCount; cnt++) 
			{
				String excelEmailValues = emailIds.get(cnt);
				array.add(excelEmailValues);

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return array;
	}



	public void sendMail(String from, List<String> to2, List<String> cc2, String subject, String messageBody, String fileName1,String fileName2) {
		try {
			Session session = Session.getDefaultInstance(this.properties, this.authenticator);
			this.message = new MimeMessage(session);
			this.message.setFrom((Address) new InternetAddress(from));
			
		/*	this.message.addRecipient(Message.RecipientType.TO, (Address) new InternetAddress(to2));
			this.message.addRecipient(Message.RecipientType.CC, (Address) new InternetAddress(cc2));*/

			for (String string : to2) 
			{
				message.addRecipient ( Message.RecipientType.TO,
						new InternetAddress ( string ) );            }

			for (String string : cc2) 
			{
				message.addRecipient ( Message.RecipientType.CC,
						new InternetAddress ( string ) );            }

			
			
			this.message.setSubject(subject);
			this.multipart = (Multipart) new MimeMultipart();
			this.messageBodyPart = (BodyPart) new MimeBodyPart();
			this.messageBodyPart.setContent(messageBody, "text/html");
			this.multipart.addBodyPart(this.messageBodyPart);
			 messageBodyPart = (BodyPart) new MimeBodyPart();
	         DataSource fds = new FileDataSource(strAbsolutepath + "\\Screenshot\\" + fileName1 + ".png");

	         messageBodyPart.setDataHandler(new DataHandler(fds));
	         messageBodyPart.setHeader("Content-ID", "<image>");
			messageBodyPart.setFileName ( fileName1 );
	         multipart.addBodyPart(messageBodyPart);
			this.message.setContent(this.multipart);
			
		//	this.multipart.addBodyPart(this.messageBodyPart);
	/*		 messageBodyPart = (BodyPart) new MimeBodyPart();
	         DataSource fds1 = new FileDataSource(strAbsolutepath + "\\Screenshot\\" + fileName2 + ".png");

	         messageBodyPart.setDataHandler(new DataHandler(fds1));
	         messageBodyPart.setHeader("Content-ID", "<image>");
			messageBodyPart.setFileName ( fileName2 );
	         multipart.addBodyPart(messageBodyPart);
			this.message.setContent(this.multipart);*/
			
			
			
			Transport.send((Message) this.message);
			System.out.println("Message send successfully....");
		} catch (Exception me) {
			me.printStackTrace();
		}
	}

	public void performTask() {
		sendMail(this.from, this.to, this.cc, this.subject, this.messageBody, this.fileName1,this.fileName2);
	}

	/*public void SendMail() {
		SendMailMethod();
		performTask();
	}*/

	class SMTPAuthenticator extends Authenticator {
		private static final String SMTP_AUTH_USER = "mayank_mishra";

		private static final String SMTP_AUTH_PASSWORD = "Cms@123456";

		public PasswordAuthentication getPasswordAuthentication() {
			String username = "mayank_mishra";
			String password = "Cms@123456";
			return new PasswordAuthentication(username, password);
		}
	}
}
