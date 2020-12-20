package pl.sgnit.springsecurityweek2;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LoginCounter {

    private Map<String, Integer> loginCounter = new ConcurrentHashMap<>();

    @EventListener(InteractiveAuthenticationSuccessEvent.class)
    protected void logged(InteractiveAuthenticationSuccessEvent event) {
        loginCounter.computeIfPresent(event.getAuthentication().getName(),
            (userName, counter) -> counter + 1);
        loginCounter.putIfAbsent(event.getAuthentication().getName(), 1);
    }

    public Integer getLoginCount(String userName) {
        return loginCounter.get(userName);
    }
}
