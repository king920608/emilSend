
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;


public class FankeTest {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("DESede");//密钥生成器
        keyGen.init(168); //可指定密钥长度为112或168，默认为168
        SecretKey secretKey = keyGen.generateKey();//生成密钥
        System.out.println(secretKey.toString());
        byte[] key = secretKey.getEncoded();//密钥字节数组
        System.out.println();
    }
}