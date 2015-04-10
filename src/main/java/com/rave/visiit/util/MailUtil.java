package com.rave.visiit.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.rave.visiit.entity.TripVoucher;

@Component
public class MailUtil {

	private String SMTP_HOST = "smtp.gmail.com";
	private String TRUE = "true";
	private String PORT = "587";

	@Autowired
	private JavaMailSender mailSender;

	// @Asynchronous
	@Async
	public boolean sendMail(final String[] recipients,
			final String[] bccRecipients, final String subject,
			final String message) {
		Runnable thread = new Runnable() {
			public void run() {

				try {
					// Thread.sleep(5000);
					Message msg = setMailMessage(recipients, bccRecipients,
							subject, message);
					Transport.send(msg);

					// return true;

				} catch (UnsupportedEncodingException ex) {
					Logger.getLogger(MailUtil.class.getName()).log(
							Level.SEVERE, null, ex);
					// return false;
				} catch (MessagingException ex) {
					Logger.getLogger(MailUtil.class.getName()).log(
							Level.SEVERE, null, ex);
				} catch (Exception ex) {
					Logger.getLogger(MailUtil.class.getName()).log(
							Level.SEVERE, null, ex);
				}

			}

		};

		new Thread(thread).start();
		return false;
	}

	private Message setMailMessage(final String[] recipients,
			final String[] bccRecipients, final String subject,
			final String message) throws IOException,
			UnsupportedEncodingException, MessagingException, AddressException {
		Properties props = new Properties();

		props.put("mail.smtp.auth", TRUE);
		props.put("mail.smtp.starttls.enable", TRUE);
		props.put("mail.smtp.host", SMTP_HOST);
		props.put("mail.smtp.port", PORT);

		Properties prop = new Properties();
		InputStream input = Helper.class
				.getResourceAsStream("mail_config.properties");
		prop.load(input);
		Session session = Session.getInstance(props, new SocialAuth());
		Message msg = new MimeMessage(session);

		InternetAddress from = new InternetAddress(
				prop.getProperty("fromMailId"),
				prop.getProperty("fromMailName"));
		msg.setFrom(from);

		InternetAddress[] toAddresses = new InternetAddress[recipients.length];
		for (int i = 0; i < recipients.length; i++) {
			toAddresses[i] = new InternetAddress(recipients[i]);

		}
		msg.setRecipients(Message.RecipientType.TO, toAddresses);

		InternetAddress[] bccAddresses = new InternetAddress[bccRecipients.length];
		for (int j = 0; j < bccRecipients.length; j++) {
			bccAddresses[j] = new InternetAddress(bccRecipients[j]);
		}
//		InternetAddress bccAddr = new InternetAddress(
//				"narayanan.hariraman@gmail.com");
//		msg.setRecipient(Message.RecipientType.BCC, bccAddr);
		 msg.setRecipients(Message.RecipientType.BCC, bccAddresses);

		msg.setSubject(subject);
		msg.setContent(message, "text/html; charset=ISO-8859-1");
		return msg;
	}

	public void sendEmailWithVoucherAttachment(final String[] recipients,
			final String[] bccRecipients, final String subject,
			final String message, final TripVoucher file) throws AddressException,
			MessagingException {
		// sets SMTP server properties
		Runnable thread = new Runnable() {
			public void run() {
				try {
					Message msg = setMailMessage(recipients, bccRecipients,
							subject, message);

					// creates message part
					MimeBodyPart messageBodyPart = new MimeBodyPart();
					messageBodyPart.setContent(message, "text/html");

					// creates multi-part
					Multipart multipart = new MimeMultipart();
					multipart.addBodyPart(messageBodyPart);

					MimeBodyPart attachPart = new MimeBodyPart();

//					attachPart.attachFile(attachFiles);
					
					

//					multipart.addBodyPart(attachPart);
					
					  BodyPart attachmentBodyPart = new MimeBodyPart();
			          String filename = file.getFileName();
						DataSource source = new ByteArrayDataSource(file.getData(),
								file.getMimeType());
			          attachmentBodyPart.setDataHandler(new DataHandler(source));
			         attachmentBodyPart.setFileName(filename);
				          multipart.addBodyPart(attachmentBodyPart);

					// sets the multi-part as e-mail's content
					
					msg.setContent(multipart);
//					msg.setContent((Multipart) file);

					// sends the e-mail
					Transport.send(msg);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (AddressException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};

		new Thread(thread).start();

	}

	class SocialAuth extends Authenticator {

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {

			Properties prop = new Properties();
			InputStream input = Helper.class
					.getResourceAsStream("mail_config.properties");
			try {
				prop.load(input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new PasswordAuthentication(prop.getProperty("fromMailId"),
					prop.getProperty("fromMailpassword"));
		}

	}

	public String parseFile(String fileName, Map nameValues)
			throws IOException, URISyntaxException {
		Properties prop = new Properties();
		InputStream input = Helper.class
				.getResourceAsStream("config.properties");
		prop.load(input);
		nameValues.put("$IMAGECONTEXT$", prop.getProperty("imagecontext"));
		URL fileURL = this.getClass().getResource(fileName);
		File revReadfile = new File(fileURL.toURI().toString().replaceFirst("vfs:", ""));
		BufferedReader br = new BufferedReader(new FileReader(revReadfile));
		StringBuffer buf = new StringBuffer();
		String line = "";
		while ((line = br.readLine()) != null) {
			buf.append(parseReading(line, nameValues));
			buf.append("\n");
		}
		br.close();
		return buf.toString();
	}

	private String parseReading(String line, Map nameValues) throws IOException {
		for (Iterator iter = nameValues.keySet().iterator(); iter.hasNext();) {
			String key = (String) iter.next();
			line = StringUtils.replace(line, key, (String) nameValues.get(key));
		}
		return line;
	}

	public static String getAdminMail() {
		Properties prop = new Properties();
		InputStream input = Helper.class
				.getResourceAsStream("mail_config.properties");
		String mail = "";
		try {
			prop.load(input);
			mail = prop.getProperty("adminMailId");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mail;
	}
	
	@Async
	public boolean sendNewsLetter(final String[] recipients,
			final String[] bccRecipients, final String subject,
			final String message) {
		Runnable thread = new Runnable() {
			public void run() {

				try {
//					Thread.sleep(5000);
					Properties props = new Properties();

					props.put("mail.smtp.auth", TRUE);
					props.put("mail.smtp.starttls.enable", TRUE);
					props.put("mail.smtp.host", SMTP_HOST);
					props.put("mail.smtp.port", PORT);
					Properties prop = new Properties();
					InputStream input = Helper.class
							.getResourceAsStream("mail_config.properties");
					prop.load(input);
					Session session = Session.getInstance(props,
							new SocialAuth());
					Message msg = new MimeMessage(session);

					InternetAddress from = new InternetAddress(
							prop.getProperty("fromMailId"),
							prop.getProperty("fromMailName"));
					msg.setFrom(from);
					if(recipients != null && recipients.length > 0){
						InternetAddress[] toAddresses = new InternetAddress[recipients.length];
						for (int i = 0; i < recipients.length; i++) {
							toAddresses[i] = new InternetAddress(recipients[i]);
	
						}
						msg.setRecipients(Message.RecipientType.TO, toAddresses);
					}
					
					if(bccRecipients != null && bccRecipients.length > 0){
						InternetAddress[] bccAddresses = new InternetAddress[bccRecipients.length];
						for (int j = 0; j < bccRecipients.length; j++) {
							bccAddresses[j] = new InternetAddress(bccRecipients[j]);
						}
						msg.setRecipients(Message.RecipientType.BCC, bccAddresses);
					}
					msg.setSubject(subject);
					msg.setContent(message, "text/html; charset=ISO-8859-1");
					Transport.send(msg);
					
					// return true;

				} catch (UnsupportedEncodingException ex) {
					Logger.getLogger(MailUtil.class.getName()).log(
							Level.SEVERE, null, ex);
					// return false;
				} catch (MessagingException ex) {
					Logger.getLogger(MailUtil.class.getName()).log(
							Level.SEVERE, null, ex);
					//return false;
				} catch (Exception ex) {
					Logger.getLogger(MailUtil.class.getName()).log(
							Level.SEVERE, null, ex);
					//return false;
				}

			}
		};

		new Thread(thread).start();
		return false;
	}
}
