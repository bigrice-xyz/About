package xyz.bigrice.wx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import xyz.bigrice.util.HttpUtil;

/**
 * 获取微信服务器列表.
   <p><code>
	接口调用请求说明
	
	http请求方式: GET
	https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN
	参数说明
	
	参数	是否必须	说明
	access_token	是	公众号的access_token
	返回说明
	
	正常情况下，微信会返回下述JSON数据包给公众号：
	
	{
		"ip_list":["127.0.0.1","127.0.0.1"]
	}
	参数	说明
	ip_list	微信服务器IP地址列表
	</code></p>
 * @author tangxiucai
 *
 */
public class ServerList {
	private static final Logger logger = LoggerFactory.getLogger(ServerList.class);
	
	/**
	 * 服务器列表参数化API地址.
	 */
	public static String SERVER_LIST_URL = WXService.WX_SERVICE_BASE_URL+
				"/getcallbackip?access_token=${ACCESS_TOKEN}";
	
	
	/**
	 * 获取服务器列表.
	 * @param token 凭证.
	 * @return  服务器地址列表，如果访问失败则返回null.
	 */
	public static String[] getServerList(String token){
		String url = HttpUtil.getRealUrl(SERVER_LIST_URL, new NameValuePair("ACCESS_TOKEN", token));
		
		String content = HttpUtil.getString(url);
		logger.debug("获取微信服务器列表返回报文：{}", content);
		
		JSONObject json = JSONObject.parseObject(content);
		return json.getObject("ip_list", String[].class);
	}
}
