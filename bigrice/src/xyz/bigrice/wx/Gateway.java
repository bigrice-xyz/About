package xyz.bigrice.wx;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xyz.bigrice.wx.gw.Mapper;

/**
 * 服务网关.
 * 微信服务器调用的URL地址.
 * @author tangxiucai
 *
 */
@SuppressWarnings("serial")
public class Gateway extends HttpServlet{
	private static Logger logger = LoggerFactory.getLogger(Gateway.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String appid = req.getParameter("appid");
		if(Mapper.contain(appid)){
			try {
				Mapper.getHandler(appid).process(req, resp);
			} catch (Exception e) {
				logger.error("WX Gateway info:", e);
			} 
		} else{
			resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
			resp.getWriter().write("Your Request Is Invalid .  (BigRice)");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}

}
