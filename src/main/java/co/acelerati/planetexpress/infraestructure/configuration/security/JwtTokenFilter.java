package co.acelerati.planetexpress.infraestructure.configuration.security;

import co.acelerati.planetexpress.application.exception.EmptyHeaderAutorizationException;
import co.acelerati.planetexpress.infraestructure.configuration.security.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    private List<String> excludedPrefixes = Arrays.asList("/auth/**", "/swagger-ui/**");
    private AntPathMatcher pathMatcher = new AntPathMatcher();
    @Autowired
    private JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain)
      throws ServletException, IOException {
        try {
            String token = getToken(req);
            if (jwtProvider.validateToken(token) && jwtProvider.getUserNameFromToken(token) != null) {
                UserDetails userDetails = getUserDetails(token);
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                  userDetails, null, userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (Exception e) {
            logger.error(String.format("doFilter error: %s", e));
            res.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
        }
        filterChain.doFilter(req, res);
    }

    private UserDetails getUserDetails(String token) {
        return new User(0L, jwtProvider.getUserNameFromToken(token), jwtProvider.getRole(token));
    }

    private String getToken(HttpServletRequest request) {
        try {
            return request.getHeader("Authorization").replace("Bearer", "");
        } catch (NullPointerException exception) {
            throw new EmptyHeaderAutorizationException("The Authorization header is empty");
        }
    }
}
