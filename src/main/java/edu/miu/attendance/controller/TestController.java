package edu.miu.attendance.controller;

import edu.miu.attendance.config.JPAPersonDetailsService;
import edu.miu.attendance.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestController {
    @Autowired
    JwtUtil jwtUtils;

    @Autowired
    JPAPersonDetailsService personDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    //@RequestMapping("/")
    public String root() {
        return "redirect:/index";
    }

    //@RequestMapping("/index")
    public String index() {
        return "index.html";
    }

    //@RequestMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {
        boolean error_status = (error != null?true:false);
        //System.out.println("Error: "+error);
        model.addAttribute("loginError",error_status);
        return "login";
    }
    /*@RequestMapping("/signin")
    public ResponseEntity<?> loginUser(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception {
        System.out.println("Post Login....");
        authenticate(username, password);
        final UserDetails userDetails = personDetailsService.loadUserByUsername(username);
        final String token = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }*/

    /**
     *  Get the username password from the client and use the secret key to encrypt it into json web token
     * */
    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    //@RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }
    //@RequestMapping(value = "/access-denied", method = RequestMethod.GET)
    public String accessDenied() {
        return "403.html";
    }
}
