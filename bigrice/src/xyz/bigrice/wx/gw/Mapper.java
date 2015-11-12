package xyz.bigrice.wx.gw;

import java.util.HashMap;
import java.util.Map;

/**
 * 服务地址映射.
   /wx/gw?appid=${appid}
       执行服务器端跳转
 * @author tangxiucai
 *
 */
public class Mapper {
	private static Map<String, Handler> handlers = null;
	
	public static void init(){
		handlers = new HashMap<String, Handler>();
		
		//预定义服务.
		handlers.put("echo", new EchoHandler());  //wx/gw/appid=echo
		handlers.put("forward", new RedirectHandler(null)); //wx/gw/appid=forward&url=xxx  同一个应用内跳转
		handlers.put("f", new RedirectHandler(null)); //wx/gw/appid=f&url=xxx  同一个应用内跳转
		handlers.put("redirect", new RedirectHandler(null)); //wx/gw/appid=redirect&url=xxx  跳转到不同应用
		handlers.put("r", new RedirectHandler(null)); //wx/gw/appid=r&url=xxx  跳转到不同应用
		
		
		//TODO 可以从数据库加载配置
		handlers.put("baidu", new RedirectHandler("http://www.baidu.com"));
	}
	
	public static boolean contain(String appid){
		if(handlers == null)
			init();
		return handlers.containsKey(appid);
	}
	
	public static Handler getHandler(String appid){
		if(handlers == null)
			init();
		return handlers.get(appid);
	}
}
