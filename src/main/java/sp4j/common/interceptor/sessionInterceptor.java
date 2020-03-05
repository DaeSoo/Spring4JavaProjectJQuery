package sp4j.common.interceptor;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class sessionInterceptor extends HandlerInterceptorAdapter {
	protected Log log = LogFactory.getLog(sessionInterceptor.class);

	@Value("#{sessionUrl}")
	Map<String,Object> server;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		Set<String> set = server.keySet();

		Iterator<String> iterator = set.iterator();
		
		boolean sessionPage = true;
		
		while(iterator.hasNext()){

			  String key = (String)iterator.next();

			  if(key.equals(request.getRequestURI().toString())){
				  sessionPage = false;
				  break;
			  }
			  
		}

		if (sessionPage){
			
			if (session.getAttribute("loginVO") == null) {
				log.debug(
						"====================================           세션만료             =================================================");
				response.sendRedirect("/jcp/loginPage.do");
				return false;

			};
		}else{
			if (session.getAttribute("loginVO") != null) {
				
				response.sendRedirect("/jmp/openPage.do");
				return false;
				
			}
			
		}

		if (log.isDebugEnabled()) {
			log.debug("======================================          START         ======================================");
			log.debug("########################  REQUEST   ############################");
			log.debug("# Host           = {"+ request.getRemoteHost()+"}");
			log.debug("# Method         = {"+ request.getMethod()+"}");
			log.debug("# RequestURL     = {"+ request.getRequestURL().toString()+"}");
			log.debug("# QueryString    = {"+ request.getQueryString()+"}");
			log.debug("# ServerName     = {"+ request.getServerName()+"}");
			log.debug("# RequestURI     = {"+ request.getRequestURI()+"}");
			log.debug("# ContextPath    = {"+ request.getContextPath()+"}");
			log.debug("################################################################");
		}

		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("======================================          END         ======================================");
		}
	}
}