package pl.sgnit.springsecurityweek2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestApi {

    private final LoginCounter loginCounter;

    public TestApi(LoginCounter loginCounter) {
        this.loginCounter = loginCounter;
    }

    @GetMapping("admin")
    public String admin(Principal principal) {
        return "Cześć admin " + principal.getName()
            + getLoginCountString(principal.getName());
    }

    @GetMapping("user")
    public String user(Principal principal) {
        return "Cześć user " + principal.getName()
            + getLoginCountString(principal.getName());
    }

    @GetMapping("all")
    public String all(Principal principal) {
        if (principal == null) {
            return "Cześć nieznajomy";
        }
        return "Cześć " + principal.getName();
    }

    @GetMapping("papa")
    public String papa() {
        return "Papa";
    }

    private String getLoginCountString(String userName) {
        return ". Zalogowałeś się już " + loginCounter.getLoginCount(userName) + " razy";
    }
}
