package xyz.bigrice.wx;

/**
 * 微信服务接口.
 * @author tangxiucai
 *
 */
public class WXService {
	public static final String WX_SERVICE_BASE_URL =  "https://api.weixin.qq.com/cgi-bin";
	
	/**
	 * 获取登录凭证.
	 * @param appid 应用程序id
	 * @param secret 应用程序密钥
	 * @return 如果应用程序id或密钥失败，或网络错误则返回null
	 */
	public String getToken(String appid, String secret){
		return Token.getToken(appid, secret);
	}
	
	/**
	 * 获取服务器列表.
	 * @param token 凭证.
	 * @return  服务器地址列表，如果访问失败则返回空的列表.
	 */
	public static String[] getServerList(String token){
		String[] result = ServerList.getServerList(token);
		if(result == null)
			return new String[0];
		return result;
	}
}
