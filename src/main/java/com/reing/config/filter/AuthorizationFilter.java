package com.reing.config.filter;

import com.reing.service.IRouteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {

    private final IRouteService routeService;

    public AuthorizationFilter(IRouteService routeService) {
        this.routeService = routeService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String requestURI = request.getRequestURI();

        Map<String, List<String>> mapRoutes = routeService.getRouteRolMappings();

        List<String> authRoles = mapRoutes.get(requestURI);

        if(authRoles != null) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            if(authentication != null && authentication.isAuthenticated()) {
                boolean accessRol = authentication.getAuthorities().stream()
                        .anyMatch(authorities -> authRoles.contains(authorities.getAuthority()));

                if (!accessRol) {
                    request.getRequestDispatcher("/403.xhtml").forward(request, response);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
