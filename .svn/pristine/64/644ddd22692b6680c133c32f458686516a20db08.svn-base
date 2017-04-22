package com.mongorest.olahtek.service;

//import java.io.File;
//import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongorest.olahtek.model.User;

@Service("resetPassword")
@Transactional
public class ResetPasswordImpl implements ResetPassword {
	

	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	DecimalFormat decimalFloat = new DecimalFormat("#.##");

	@Override
	public boolean sendFromGMail(String usemailId, String password) {
		Properties props = System.getProperties();
		String RECIPIENT = usemailId;
		String[] to = { RECIPIENT };
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		//System.out.println("olahtekmail..."+MongoDBConnection.olahtekmailusername);
		//System.out.println("password..."+MongoDBConnection.olahtekmailpassword);
		props.put("mail.smtp.user", MongoDBConnection.olahtekmailusername);
		props.put("mail.smtp.password",MongoDBConnection.olahtekmailpassword);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(MongoDBConnection.olahtekmailusername));
			InternetAddress[] toAddress = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++) {
				toAddress[i] = new InternetAddress(to[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}
			message.setSubject("Olahtek reset password");
			String body = "your new Olahtek password is......" + password + "";
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, MongoDBConnection.olahtekmailusername, MongoDBConnection.olahtekmailpassword);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			return true;
		} catch (AddressException ae) {
			ae.printStackTrace();
			return false;
		} catch (MessagingException me) {
			me.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean insertRandomPassword(User user, String password) {
		MongoDBConnection.userColl.update("{'email': '" + user.getEmail() + "'}").upsert().multi()
		.with("{$set: {'password':'" + password + "','modified_ts':'" + dateFormat.format(date) + "'}}");
		return true;
	}

	@Override
	public boolean killingSession(User user) {
		try {
			User userData = MongoDBConnection.userColl.findOne("{email:#}", user.getEmail()).as(User.class);
			String user_id = userData.get_id();
			com.mongorest.olahtek.model.Session sessionDetails = MongoDBConnection.sessionColl.findOne("{user_id:#}", user_id).as(
					com.mongorest.olahtek.model.Session.class);
			if (sessionDetails != null) {
				MongoDBConnection.sessionColl.remove("{_id:#}", sessionDetails.get_id());
				//System.out.println("user logout in rest after reset password ");
				return true;
			} else {
				//System.out.println("user not have any info in session ");
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	// ==================admin mailing list===================
	public boolean sendGMailFromAdmin(String usemailId, String password) {
		//System.out.println("inside reser impl");
		Properties props = System.getProperties();
		String RECIPIENT = usemailId;
		String[] to = { RECIPIENT };
		String host = "smtp.gmail.com";
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		//System.out.println("olahtekmail..."+MongoDBConnection.olahtekmailusername);
		//System.out.println("password..."+MongoDBConnection.olahtekmailpassword);
		props.put("mail.smtp.user", MongoDBConnection.olahtekmailusername);
		props.put("mail.smtp.password", MongoDBConnection.olahtekmailpassword);
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
      
		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(MongoDBConnection.olahtekmailusername));
			InternetAddress[] toAddress = new InternetAddress[to.length];
			for (int i = 0; i < to.length; i++) {
				//System.out.println("hello in address");
				toAddress[i] = new InternetAddress(to[i]);
			}

			for (int i = 0; i < toAddress.length; i++) {
				//System.out.println("hello rcvr in address");
				message.addRecipient(Message.RecipientType.TO, toAddress[i]);
			}
			message.setSubject("Olahtek  password");
			String body = "your new Olahtek password is......" + password + "";
			message.setText(body);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, MongoDBConnection.olahtekmailusername, MongoDBConnection.olahtekmailpassword);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			//System.out.println("inside reser success");
			return true;
		} catch (AddressException ae) {
			ae.printStackTrace();
			return false;
		} catch (MessagingException me) {
			me.printStackTrace();
			return false;
		}
	}

}
