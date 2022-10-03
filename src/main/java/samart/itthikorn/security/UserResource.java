package samart.itthikorn.security;

import io.quarkus.security.credential.Credential;
import io.quarkus.security.identity.SecurityIdentity;
import org.jboss.resteasy.reactive.NoCache;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.Map;
import java.util.Set;

@Path("/api/users")
public class UserResource {

    @Inject
    SecurityIdentity securityIdentity;

    @GET
    @Path("/me")
    @RolesAllowed("user")
    @NoCache
    public User me() {
        return new User(securityIdentity);
    }

    public static class User {

        private final String userName;
        private final String role;
        private final Set<Credential> credential;

        User(SecurityIdentity securityIdentity) {
            this.userName = securityIdentity.getPrincipal().getName();
            this.role = securityIdentity.getRoles().toString();
            this.credential = securityIdentity.getCredentials();
        }

        public String getUserName() {
            return userName;
        }

        public String getRole() {
            return role;
        }

        public Set<Credential> getCredential() {
            return credential;
        }
    }
}
