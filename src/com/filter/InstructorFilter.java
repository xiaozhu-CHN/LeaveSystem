package com.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.shiti.DbTeacher;

/**
 * Servlet Filter implementation class InstructorFilter
 */
@WebFilter("/InstructorFilter")
public class InstructorFilter implements Filter {

    /**
     * Default constructor. 
     */
    public InstructorFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO 辅导员类型拦截
				HttpServletRequest requ = (HttpServletRequest)request;
				HttpServletResponse resp = (HttpServletResponse)response;
				HttpSession session = requ.getSession(true);
				DbTeacher dbteacher=(DbTeacher) session.getAttribute("teacher");
				if(session.getAttribute("teacher")==null) {
					resp.sendRedirect("/LeaveSystem/teacher/login.jsp");
				}else {
						if(dbteacher.getJurisdiction()!=1)
						{	
							requ.getSession().removeAttribute("student");
							resp.sendRedirect("/LeaveSystem/teacher/login.jsp");}
						else{chain.doFilter(request, response);}
				}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
