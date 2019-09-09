package com.ozygod.base.redis.auth;

import com.ozygod.base.redis.AbstractRedisDao;
import org.springframework.stereotype.Repository;

import java.util.HashSet;

/**
 * 类描述：
 *
 * @author by zhanggl on 2017/6/14.
 */
@Repository
public class AuthSetRedisDao extends AbstractRedisDao<HashSet<String>> {
}
