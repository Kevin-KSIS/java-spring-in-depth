package lowk.learning.moneyMana.config;

import lombok.extern.slf4j.Slf4j;
import lowk.learning.moneyMana.model.UserPrinciple;
import lowk.learning.moneyMana.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /*
    * Inject to a filter chain and add an authentication from jwt string to SecurityContext
    * */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestTokenHeader = request.getHeader("Authorization");
        String jwtToken = jwtTokenProvider.parse(requestTokenHeader);
        // Get username from jwt token
        if (StringUtils.hasText(jwtToken)) {
            //jwtToken = requestTokenHeader.substring(7);
            String username = jwtTokenProvider.getUsernameFromToken(jwtToken);

            // loading from DB
            UserPrinciple userPrinciple = userDetailsService.loadUserByUsername(username);

            // Validate the Jwt token and set an authentication token to security context
            if (jwtTokenProvider.validateToken(jwtToken, userPrinciple)) {

                UsernamePasswordAuthenticationToken upAuthToken = new UsernamePasswordAuthenticationToken(
                        userPrinciple, null, userPrinciple.getAuthorities()
                );
                upAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(upAuthToken);
            }else{
                logger.debug("Token invalid");
            }
        }

        filterChain.doFilter(request, response);
    }
}
