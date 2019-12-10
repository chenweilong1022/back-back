package com.ozygod.base.redis.auth;

import com.ozygod.base.auth.AccessToken;
import com.ozygod.base.redis.AbstractRedisDao;
import org.springframework.stereotype.Repository;

@Repository
public class AccessTokenRedisDao extends AbstractRedisDao<AccessToken> {
}
