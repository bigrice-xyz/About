package xyz.bigrice.wx.gw;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 服务处理器.
 * 
 * @author tangxiucai
 *
 */
public interface Handler {
	/**
	 * 处理请求
	 * @param req
	 * @param res
	 * @throws Exception
	 */
	void process(HttpServletRequest req, HttpServletResponse res) throws Exception;
}
