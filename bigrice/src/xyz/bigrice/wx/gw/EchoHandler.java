package xyz.bigrice.wx.gw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务器报文响应.
 * 
   /wx/gw?appid=echo
 * @author tangxiucai
 *
 */
public class EchoHandler implements Handler{
	private static Logger logger = LoggerFactory.getLogger(EchoHandler.class);

	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		PrintWriter out = new PrintWriter(System.err);
		out.println("---- Request :");
		out.println("Req Encoding:"+req.getCharacterEncoding());
		out.println("Req ContentType:"+req.getContentType());
		out.println("Req Length"+req.getContentLength());
		out.println("Req info:");
		
		
		Map<String, Object> param = req.getParameterMap();
		for(String s : param.keySet()){
			Object value = param.get(s);
			logger.info("***********=========== ");
			if(value instanceof String[]){
				out.print("\t"+s+"=");
				logger.info(s+"=");
				for(String v : (String[])value){
					out.print(v+",");
					logger.info(v+",");
				}
			}
			logger.info("***********=========== ");
		}
		out.flush();
		
		resp.getWriter().write("");
	}

}
