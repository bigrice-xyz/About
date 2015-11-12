package xyz.bigrice.wx;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xyz.bigrice.util.HttpUtil;

import com.alibaba.fastjson.JSONObject;

/**
 * 访问票据.
 * 第一步先通过appid+scretId获取票据
 * 第二步票据存在时效，需要定时刷新以及统一获取渠道.
 * <p>
   <code>接口调用请求说明
	
	http请求方式: GET
	https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
	参数说明
	
	参数	是否必须	说明
	grant_type	是	获取access_token填写client_credential
	appid	是	第三方用户唯一凭证
	secret	是	第三方用户唯一凭证密钥，即appsecret
	返回说明
	
	正常情况下，微信会返回下述JSON数据包给公众号：
	
	{"access_token":"ACCESS_TOKEN","expires_in":7200}
	参数	说明
	access_token	获取到的凭证
	expires_in	凭证有效时间，单位：秒   (实际有效期为服务器指定有效期-本地提前失效期)
</code>
</p>
 * @author tangxiucai
 *
 */
public class Token {
	private static final Logger logger = LoggerFactory.getLogger(Token.class);
	
	/**
	 * 票据参数化API地址.
	 */
	public static String TOKEN_URL = WXService.WX_SERVICE_BASE_URL+
				"/token?grant_type=client_credential&appid=${APPID}&secret=${APPSECRET}";
	
	/**
	 * 票据获取后本地提前失效间隔 ，默认10分钟, 即规则：票据的服务器端指定失效时间+获取的时间点 < 当前时间+本地失效时间.
	 */
	public static int EXPIRE_INTERVAL = 10*60*1000;
	
	/**
	 * 获取票据异常时候重试次数.
	 */
	public static int RETRY_TIME = 3;
	
	/**
	 * 获取登录凭证.
	 * @param appid 应用程序id
	 * @param secret 应用程序密钥
	 * @return 如果应用程序id或密钥失败，或网络错误则返回null
	 */
	public static String getToken(String appid, String secret){
		return CacheManager.getToken(appid, secret);
	}
	
	/**
	 * 凭证缓存管理器.
	 * @author tangxiucai
	 *
	 */
	private static class CacheManager{
		private static Map<String, Cache> caches = new HashMap<String, Cache>();
		
		public synchronized static String getToken(String appid, String secret){
			if(caches.containsKey(appid)){
				Cache c = caches.get(appid);
				if(c.isValid()){
					logger.debug("返回缓存的token, appid={}, token={}", appid, c.token);
					return c.token;
				}
			}
			
			Cache cc = obtainToken(appid, secret);
			caches.put(appid, cc);
			return cc.token;
		}
		
		private static Cache obtainToken(String appid, String secret){
			Map<String ,String> params = new HashMap<String, String>();
			params.put("APPID", appid);
			params.put("APPSECRET", secret);
			String url = HttpUtil.getRealUrl(TOKEN_URL, params);
			
			String content = HttpUtil.getString(url);
			//访问存在问题时候进行重试三次操作
			int retry = 0;
			while(content == null && retry++ < RETRY_TIME){
				content = HttpUtil.getString(url);
			}
			
			if(content == null)
				return null;
			
			JSONObject json = JSONObject.parseObject(content);
			String token = json.getString("access_token");
			int expire = json.getIntValue("expires_in");
			logger.debug("获取的Token返回报文{}", content);
			
			Cache c = new Cache(appid, secret);
			c.setToken(token);
			c.setExpire(expire);//Default 7200
			return c;
		}
	}
	
	/**
	 * 凭证缓存.
	 * @author tangxiucai
	 *
	 */
	private static class Cache{
		/**
		 * 应用程序id.
		 */
		@SuppressWarnings("unused")
		private String appid;
		
		@SuppressWarnings("unused")
		private String secret;
		
		/**
		 * 应用程序凭证.
		 */
		private String token;
		/**
		 * 过期时间：获取到的系统时间+微信设置的超时长度.
		 */
		private long expire;
		
		public Cache(String appid,String secret){
			this.appid = appid;
			this.secret = secret;
		}
		
		public void setToken(String token){
			this.token = token;
		}
		
		public void setExpire(long expire){
			this.expire = (System.currentTimeMillis() + expire*1000);
		}
		
		public boolean isValid(){
			return (expire - EXPIRE_INTERVAL) > System.currentTimeMillis();
		}
	}
}
