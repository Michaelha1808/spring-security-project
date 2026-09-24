package mihchaelha1808.example.spring_security_project.controller;

import jakarta.annotation.security.RolesAllowed;
import org.jspecify.annotations.Nullable;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/admin")
@RolesAllowed("ADMIN")

public class AdminController {

    //@PostAuthorize("hasIpAddress("1111"))
    @GetMapping("/vip")
    public String zoneVip(){
        return "zoneVip";
    }

    @GetMapping("/normal")
    public String zoneNormal(){
        return "zoneNormal";
    }

    @GetMapping("/info")
    public  Authentication getInfoUser(){
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
