package anubahv.insuracne.insuranceagency.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
    private AuthenticationManager authenticationManager;
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public SecurityServiceImpl(AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(userDetails instanceof UserDetails) {
            return ((UserDetails)userDetails).getUsername();
        }
        return null;
    }

    @Override
    public void autoLogin(String username, String password) {
        System.out.println("getting details");
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        System.out.println("authentication");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails,password,userDetails.getAuthorities()
        );
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }
}
