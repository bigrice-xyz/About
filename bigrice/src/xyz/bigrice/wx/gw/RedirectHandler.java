package xyz.bigrice.wx.gw;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 服务器端跳转.
 * @author tangxiucai
 *
 */
public class RedirectHandler implements Handler {
	/**
	 * 目标地址.
	 */
	private String targetUrl;
	
	public RedirectHandler(String targetUrl){
		this.targetUrl = targetUrl;
	}

	@Override
	public void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String appid = req.getParameter("appid");
		if("forward".equals(appid) || "f".equals(appid)){ //forward跳转
			targetUrl = req.getParameter("url");
			req.getRequestDispatcher(targetUrl).forward(req, res);
		}else if("redirect".equals(appid) || "r".equals(appid)){ //redirect跳转
			targetUrl = req.getParameter("url");
			res.sendRedirect(targetUrl);
		}else{
			res.sendRedirect(targetUrl);
		}
	}

}
