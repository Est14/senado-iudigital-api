package com.est14.senadoiudigital.security.jwt;

import com.est14.senadoiudigital.security.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
// Se ejecuta por cada tequest y prueba si el token es valido
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
                                    throws ServletException, IOException {

        try {
            String token = getToken(request);
            log.info("Token -> " + token);
            if (token != null && jwtProvider.validateToken(token)){
                String userEmail = jwtProvider.getEmailFromToken(token);
                log.info("email --> " + userEmail );
                UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch (Exception e){
            log.error("Fallo el metodo doFilter " + e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    // Obtenemos el token del request
    private String getToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");

        if(header != null && header.startsWith("Bearer ")){
            return header.replace("Bearer ", "");
        }
        return null;
    }
}
