package PTJ4.transcode.service.impl;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import PTJ4.transcode.service.MailService;
import PTJ4.transcode.util.GenRandomCodeUtil;

@Service
public class MailServiceImpl implements MailService {


    @Autowired
    private JavaMailSender mailSender;
	
	@Override
	public void sendEmail(String to, String subject, String body) {
		SimpleMailMessage email = new SimpleMailMessage();
		
		email.setTo(to);
		email.setSubject(subject);
		email.setText(body);

		mailSender.send(email);
	}

	/* (non-Javadoc)
	 * @see PTJ4.transcode.service.MailService#sendRegisterEmail(java.lang.String)
	 */
	@Override
	public void sendRegisterEmail(String to) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		    try {
		        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		
		        String message = "<h2>欢迎注册Supinfo Video Converter</h2><p><a href='#'>点击此链接激活邮箱(此邮箱可找回密码)</a></p>";
		
		        helper.setFrom("postmaster@supinfotranscode.site");
		        helper.setTo(to);
		        helper.setSubject("欢迎注册Supinfo Video Converter!");
		
		        mimeMessage.setContent(message,"text/html;charset=UTF-8");
		        mailSender.send(mimeMessage);
		        }
		    catch(Exception e)
		    {
		        e.printStackTrace();
		    }
	}

	/* (non-Javadoc)
	 * @see PTJ4.transcode.service.MailService#sendResetPasswordEmail(java.lang.String, java.lang.String)
	 */
	@Override
	public void sendResetPasswordEmail(String username, String to) {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		    try {
		        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		        String tempPassword = GenRandomCodeUtil.genRandomPassword();
		
		        String message = "<h2>"+username+" 这是你的临时密码,请尽快设置新密码!<h3><p style='color:red'>"+tempPassword+"</h3>";
		
		        helper.setFrom("postmaster@supinfotranscode.site");
				helper.setTo(to);
				helper.setSubject("Supinfo Video Converter临时密码");
		
				mimeMessage.setContent(message,"text/html;charset=UTF-8");
				mailSender.send(mimeMessage);
			}
			catch(Exception e)
			{
			    e.printStackTrace();
			}
	}
	
	@Override
	public void sendTranscodeFinishEmail(String username,String videoName,String link, String to) {
			MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		    try {
		        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
		
		        String message = "<h2>"+username+" your uploaded video <p style='color:red'>"+videoName+"</p> has been successfully transcoded,"
		        		+ "click link below to download!: <br /><p>link: <a href=\""+link+"\">"+link+"</a>";
		
		        helper.setFrom("postmaster@supinfotranscode.site");
				helper.setTo(to);
				helper.setSubject("[Supinfo Transcode]Transcode Success!");
		
				mimeMessage.setContent(message,"text/html;charset=UTF-8");
				mailSender.send(mimeMessage);
			}
			catch(Exception e)
			{
			    e.printStackTrace();
			}
	}

}
