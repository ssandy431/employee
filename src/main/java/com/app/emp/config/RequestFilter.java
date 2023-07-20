package com.app.emp.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.emp.service.conf.RedisServiceImpl;
import com.app.emp.util.ErrorResponseBean;
import com.app.emp.util.JwtUtil;
import com.app.emp.util.exception.CustomException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService detailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private RedisServiceImpl serviceImpl;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");
		String jwtToken = null;
		String username = null;
		String uri = request.getRequestURI();
		System.out.println("Uri :: " + uri);
		if (uri.equals("/login/register") || uri.equals("/login/authenticate")) {
			filterChain.doFilter(request, response);
		} else {

			try {
				if (header != null && header.startsWith("Bearer ")) {
					jwtToken = header.substring(7);
					username = jwtUtil.getUsername(jwtToken);
					System.out.println("Username:: "+username);
				}

				if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
					UserDetails userDetails = this.detailsService.loadUserByUsername(username);

					if (jwtUtil.isTokenValid(jwtToken, userDetails)) {
						if (serviceImpl.isKeyExists(jwtToken) || serviceImpl.getValue(jwtToken) != null) {
							System.out.println("blacklist:: ");
							errorJsonResponse(response, null);
							return;
//							response.send
						}
						UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
								userDetails, null, userDetails.getAuthorities());
						authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(authenticationToken);
					}
					filterChain.doFilter(request, response);
				} else {
					errorJsonResponse(response, null);
				}

			} catch (CustomException e) {
				System.out.println("inside custom ex");
//				e.printStackTrace();
				errorJsonResponse(response, e);
				return;
			}
			catch (Exception e) {
				System.out.println("inside ex ex");
				e.printStackTrace();
				errorJsonResponse(response, e);
				return;
			}
		}
	}

//	private HttpServletResponse errorJsonResponse(HttpServletResponse response, CustomException ex) throws IOException {
//		ErrorResponseBean bean = new ErrorResponseBean();
//		if (ex != null) {
//			bean.setErrCode(Integer.parseInt(ex.getErrorCode()));
//			bean.setMessage(ex.getErrorMessage());
//		} else {
//			bean.setErrCode(401);
//			bean.setMessage("You are not authorized to access the data.");
//		}
//
//		byte[] resToSend = restResponseByte(bean);
//		response.setHeader("Content-type", "application/json");
//		response.setStatus(bean.getErrCode());
//		response.getOutputStream().write(resToSend);
//		return response;
//
//	}
	private HttpServletResponse errorJsonResponse(HttpServletResponse response, Exception ex) throws IOException {
		ErrorResponseBean bean = new ErrorResponseBean();
		System.out.println((ex instanceof RedisConnectionFailureException));
		if(ex instanceof ExpiredJwtException)
		{
			bean.setErrCode(401);
			bean.setMessage("Your session has been expired. Please login again.");
		}
		else if(ex instanceof CustomException e)
		{
			bean.setErrCode(Integer.parseInt(e.getErrorCode()));
			bean.setMessage(e.getErrorMessage());
			bean.setErrCode(401);
			bean.setMessage("You are not authorized to access the data.");
		}
		else
		{
			bean.setErrCode(500);
			bean.setMessage("Some problems arises. Please try after somtimes.");
		}
		byte[] resToSend = restResponseByte(bean);
		response.setHeader("Content-type", "application/json");
		response.setStatus(bean.getErrCode());
		response.getOutputStream().write(resToSend);
		return response;
		
	}

	private byte[] restResponseByte(ErrorResponseBean bean) throws IOException {
		String serialized = new ObjectMapper().writeValueAsString(bean);
		return serialized.getBytes();
	}

}
