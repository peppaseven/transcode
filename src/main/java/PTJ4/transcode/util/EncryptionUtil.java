package PTJ4.transcode.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2016/2/1.
 * 密码加密类
 */
public class EncryptionUtil {


    
    /**
     * 密码算法
     */
    public static final String hashType = "SHA";


    /**
     * 获取加密密码
     * @param source
     * @return
     */
    public static String getHash(String source)
    {
        StringBuilder sb = new StringBuilder();
        MessageDigest md;
        try {
            md = MessageDigest.getInstance(hashType);
            md.update(source.getBytes());
            for (byte b : md.digest()) {
            	//不足两位前面补0
                sb.append(String.format("%02X", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;

    }




}
