package com.ozygod.robot.service.impl;

import com.ozygod.base.utils.CommonUtil;
import com.ozygod.model.zdmanage.bo.RobotMenuBO;
import com.ozygod.model.zdmanage.dao.RobotMenuEntityMapper;
import com.ozygod.model.zdmanage.dto.RobotMenuDto;
import com.ozygod.model.zdmanage.entity.RobotMenuEntity;
import com.ozygod.robot.service.IRobotMenuService;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/13
 */
@Component
public class RobotMenuServiceImpl implements IRobotMenuService {
    @Autowired
    private RobotMenuEntityMapper robotMenuEntityMapper;

    @Override
    public List<RobotMenuBO> listAllRobotMenu() {
        //查出全部父级菜单
        List<RobotMenuBO> superMenus = robotMenuEntityMapper.listRobotMenuByQry(new RobotMenuDto());
        getAllChildren(superMenus);
        return superMenus;
    }

    /**
     * 新增机器人菜单
     *
     * @param bo
     * @return
     */
    @Override
    @Transactional(transactionManager = "zdmanageTransactionManager")
    public int saveRobotMenu(RobotMenuBO bo) throws Exception {
        RobotMenuEntity entity = new RobotMenuEntity();
        PropertyUtils.copyProperties(entity, bo);
        return robotMenuEntityMapper.insertSelective(entity);
    }

    /**
     * 查询指定级别的菜单列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<RobotMenuBO> listRobotMenuByQry(RobotMenuDto dto) {
        return robotMenuEntityMapper.listRobotMenuByQry(dto);
    }

    /**
     * 更新机器人菜单信息
     *
     * @param bo
     * @return
     */
    @Override
    @Transactional(transactionManager = "zdmanageTransactionManager")
    public int updateRobotMenu(RobotMenuBO bo) throws Exception {
        RobotMenuEntity entity = new RobotMenuEntity();
        PropertyUtils.copyProperties(entity, bo);
        return robotMenuEntityMapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 获取全部子菜单
     *
     * 当存在多层级时，可以使用递归方式，例如：
     *
     * if (!CommonUtil.isEmpty(children)) {
     *     getAllChildren(children);
     *
     *     superBO.setChildren(children);
     * }
     *
     * @param superMenus
     */
    private void getAllChildren(List<RobotMenuBO> superMenus){
        if (!CommonUtil.isEmpty(superMenus)) {
            for (RobotMenuBO superBO :
                    superMenus) {
                RobotMenuDto menuDto = new RobotMenuDto();
                menuDto.setSuperId(superBO.getId());
                List<RobotMenuBO> children = robotMenuEntityMapper.listRobotMenuByQry(menuDto);
                if (!CommonUtil.isEmpty(children)) {
                    superBO.setChildren(children);
                }
            }
        }

    }
}
