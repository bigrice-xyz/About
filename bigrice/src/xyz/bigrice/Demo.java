package xyz.bigrice;

import java.util.HashMap;
import java.util.Map;

import xyz.bigrice.util.HttpUtil;
import xyz.bigrice.wx.Token;
import xyz.bigrice.wx.WXService;

public class Demo {
	public static void main(String[] args) {
		
		Map<String ,String> params = new HashMap<String, String>();
		params.put("APPID", "123");
		params.put("APPSECRET", "lyg");
		String s = HttpUtil.getRealUrl(Token.TOKEN_URL, params);
		System.out.println(s);
		
		
		
		System.out.println(Token.getToken("wx0d138813942e62bd", "d4624c36b6795d1d99dcf0547af5443d"));
		System.out.println(Token.getToken("wx0d138813942e62bd", "d4624c36b6795d1d99dcf0547af5443d"));
		System.out.println(Token.getToken("wx0d138813942e62bd", "d4624c36b6795d1d99dcf0547af5443d"));
		
		String token = Token.getToken("wx0d138813942e62bd", "d4624c36b6795d1d99dcf0547af5443d");
		for(String server : WXService.getServerList(token+1))
			System.out.println(server);
	}
}
