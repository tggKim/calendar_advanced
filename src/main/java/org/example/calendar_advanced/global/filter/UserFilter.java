package org.example.calendar_advanced.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.example.calendar_advanced.global.error.ErrorCode;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String requestURI = httpServletRequest.getRequestURI();
        String method = httpServletRequest.getMethod();

        String[] strs = requestURI.split("/");

        if(!strs[strs.length-1].equals("users")){
            if(method.equals("POST") || method.equals("PATCH")){
                HttpSession session = httpServletRequest.getSession(false);
                if(checkSession(session, httpServletResponse)){
                    return;
                }

//                if(checkSameUser(session, httpServletResponse, strs)){
//                    return;
//                }
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private boolean checkSession(HttpSession session, HttpServletResponse response){
        ErrorCode errorCode = ErrorCode.LOGIN_REQUIRED;

        response.setStatus(errorCode.getHttpStatus().value());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Map<String, String> errorMessage = new HashMap<>();
        errorMessage.put("message", errorCode.getMessage());

        if(session == null || session.getAttribute("userId") == null){
            try{
                String json = new ObjectMapper().writeValueAsString(errorMessage);
                response.getWriter().write(json);
                return true;
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }

        return false;
    }

//    private boolean checkSameUser(HttpSession session, HttpServletResponse response, String[] strs){
//        ErrorCode errorCode = ErrorCode.USER_ACCESS_DENIED;
//
//        response.setStatus(errorCode.getHttpStatus().value());
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//
//        Map<String, String> errorMessage = new HashMap<>();
//        errorMessage.put("message", errorCode.getMessage());
//
//        Long userId = (Long)session.getAttribute("userId");
//        if(userId != Long.parseLong(strs[strs.length-1])){
//            try{
//                String json = new ObjectMapper().writeValueAsString(errorMessage);
//                response.getWriter().write(json);
//                return true;
//            }catch (Exception e){
//                log.error(e.getMessage());
//            }
//        }
//
//        return false;
//    }
}
