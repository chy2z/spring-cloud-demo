package org.chy.guard;


import java.util.Optional;

/**
 * Created by chy on 2022/2/14.
 */
public interface UserContext {

    Optional<Integer> getPlatform();

    Optional<String> getAppId();

    Optional<String> getPhone();

    Optional<Long> getAccountId();

    Boolean isValid();
}
