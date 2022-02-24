package org.chy.guard;

import java.util.Optional;

/**
 * Created by chy on 2022/2/14.
 */
public class AccessContext implements UserContext {
    private static String USER_CONTEXT_PREFIX = "certificate-2.0-";

    @Override
    public Optional<Integer> getPlatform() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getAppId() {
        return Optional.empty();
    }

    @Override
    public Optional<String> getPhone() {
        return Optional.empty();
    }

    @Override
    public Optional<Long> getAccountId() {
        return Optional.empty();
    }


    @Override
    public Boolean isValid() {
        return this.getAccountId().isPresent() && this.getAppId().isPresent();
    }
}
