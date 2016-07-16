package PTJ4.transcode.service;

public interface MailService {

    /**
     * @param to
     * @param subject
     * @param body
     */
    void sendEmail(String to,String subject,String body);

    /**
     *
     * @param to 注册用户邮箱
     */
    void sendRegisterEmail(String to);


    /**
     * 发送重置密码邮件
     * @param username
     * @param to
     */
    void sendResetPasswordEmail(String username ,String to);

	/**
	 * 发送转码完成邮件
	 * @param username
	 * @param srcVideoName
	 * @param link
	 * @param to
	 */
	void sendTranscodeFinishEmail(String username, String srcVideoName, String link, String to);
}
