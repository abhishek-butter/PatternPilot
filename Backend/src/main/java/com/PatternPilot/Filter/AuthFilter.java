package com.PatternPilot.Filter;

/**
 * @author Abhishek V S
 **/


import com.PatternPilot.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.io.IOException;

/**
 * @author Abhishek
 **/
public class AuthFilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String authHeader = req.getHeader("Authorization");
        if(authHeader != null) {
            String[] authHeaderArr=authHeader.split("Bearer ");
            if(authHeaderArr.length>1 && authHeaderArr[1]!=null) {
                String token=authHeaderArr[1];
                try{
                    Claims claims= Jwts.parser().setSigningKey(Constants.API_KEY)
                            .parseClaimsJws(token).getBody();
                    req.setAttribute("userId",Integer.parseInt(claims.get("userId").toString()));


                }
                catch(Exception e) {
                    resp.sendError(HttpStatus.FORBIDDEN.value(),"Invalid/expired token");
                    return;
                }
            }
            else{
                resp.sendError(HttpStatus.FORBIDDEN.value(),"Authorization token must be a Bearer [token]");
                return;
            }

        }
        else{
            resp.sendError(HttpStatus.FORBIDDEN.value(),"Authorization token must be provided");
            return;

        }
        chain.doFilter(request, response);
    }
}

