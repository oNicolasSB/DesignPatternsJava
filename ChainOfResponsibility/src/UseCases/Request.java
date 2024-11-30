package UseCases;

public class Request {
    private boolean authenticated;
    private boolean rateLimitExceeded;
    private boolean cached;

    public Request(boolean authenticated, boolean rateLimitExceeded, boolean cached) {
        this.authenticated = authenticated;
        this.rateLimitExceeded = rateLimitExceeded;
        this.cached = cached;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public boolean isRateLimitExceeded() {
        return rateLimitExceeded;
    }

    public boolean isCached() {
        return cached;
    }

    public void sanitize() {
        System.out.println("Sanitizing data algorithm...");
    }
}
