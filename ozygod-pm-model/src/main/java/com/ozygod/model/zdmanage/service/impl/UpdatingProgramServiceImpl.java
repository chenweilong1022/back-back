package com.ozygod.model.zdmanage.service.impl;

import com.ozygod.base.utils.ShellUtil;
import com.ozygod.model.zdmanage.service.UpdatingProgramService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-18 16:02
 */
@Service("updatingProgramService")
public class UpdatingProgramServiceImpl implements UpdatingProgramService {


    @Override
    @Async
    public void run(String run) {
        ShellUtil.run(run);
    }
}
