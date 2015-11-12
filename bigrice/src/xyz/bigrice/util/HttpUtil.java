package xyz.bigrice.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import xyz.bigrice.wx.NameValuePair;


/**
 * Http网页交互.
 * @author tangxiucai
 *
 */
public class HttpUtil {
	/**
	 * 通过Get获取指定URL内容.
	 * @param url 指定网址.
	 * @return 网址内容，如果产生异常返回null.
	 */
	public static String getString(String url){
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url);
		try {
			return EntityUtils.toString(client.execute(get).getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			client.getConnectionManager().shutdown();
		}
		return null;
	}
	
	/**
	 * 获取实际地址，替换参数为实际内容.
	 * @param parameterUrl 通过${paramName}方式指定参数 , e.g. http://ip:port/xxx?name=${name}&pass=${pass}
	 * @param params
	 * @return
	 */
	public static String getRealUrl(String parameterUrl, Map<String, String> params){
		if((parameterUrl== null || params == null)||(!parameterUrl.contains("${"))){
			return parameterUrl;
		}else{
			for(String param : params.keySet()){
				parameterUrl = parameterUrl.replace("${"+param+"}", params.get(param));
			}
			return parameterUrl;
		}
	}
	
	/**
	 * 获取实际地址，替换参数为实际内容.
	 * @param parameterUrl 通过${paramName}方式指定参数 , e.g. http://ip:port/xxx?name=${name}&pass=${pass}
	 * @param params 键值对对象数组
	 * @return
	 */
	public static String getRealUrl(String parameterUrl, NameValuePair...params){
		if((parameterUrl== null || params == null)||(!parameterUrl.contains("${"))){
			return parameterUrl;
		}else{
			Map<String, String> tmp = new HashMap<String, String>();
			for(NameValuePair param : params){
				tmp.put(param.getName(), param.getValue());
			}
			return getRealUrl(parameterUrl, tmp);
		}
	}
}
