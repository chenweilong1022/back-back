package com.ozygod.spread.web;

import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.enums.ResponseCode;
import com.ozygod.base.utils.Constant;
import com.ozygod.model.zdspread.bo.SpreadUserBO;
import com.ozygod.model.zdspread.dto.SpreadUserDto;
import com.ozygod.spread.service.ISpreadUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/22
 */
@RequestMapping("/spread/users")
@RestController
@Api(value = "/spread/users", description = "推广用户管理")
public class SpreadUserController implements Serializable {
    private static final long serialVersionUID = 2831047582449414725L;

    @Autowired
    private ISpreadUserService spreadUserService;

    /**
     * 新增推广员
     * @param spreadUserBO
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO addSpreadUser(@RequestBody SpreadUserBO spreadUserBO) {
        int result = spreadUserService.addSpreadUser(spreadUserBO);
        if (result == 0) {
            return new ResponseBO(ResponseCode.I001.getCode(), "添加推广员失败");
        } else if (result == -1) {
            return new ResponseBO(ResponseCode.I001.getCode(), "父推广员已经是第三级推广员，添加失败！");
        } else if (result == -2) {
            return new ResponseBO(ResponseCode.I003.getCode(), "推广员ID已经存在");
        } else if (result == -3) {
            return new ResponseBO(ResponseCode.I001.getCode(), "子推广员提成比率不能高过上级推广员");
        } else if (result == -4) {
            return new ResponseBO(ResponseCode.I001.getCode(), "父推广员ID输入错误！");
        }
        return new ResponseBO("添加成功");
    }

    /**
     * 推广员登录
     * @param spreadUserDto
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO spreadUserLogin(@RequestBody SpreadUserDto spreadUserDto) {
        return spreadUserService.spreadUserLogin(spreadUserDto);
    }

    /**
     * 修改密码
     * @param spreadUserBO
     * @return
     */
    @RequestMapping(value = "/changePassword", method = RequestMethod.PATCH, headers = Constant.API_VERSION_V1)
    public ResponseBO changePassword(@RequestBody SpreadUserBO spreadUserBO) {
        return spreadUserService.changePassword(spreadUserBO);
    }

    /**
     * 重置密码
     * @param spreadUserBO
     * @return
     */
    @RequestMapping(value = "/resetPassword", method = RequestMethod.PATCH, headers = Constant.API_VERSION_V1)
    public ResponseBO resetPassword(@RequestBody SpreadUserBO spreadUserBO) {
        int result = spreadUserService.resetPassword(spreadUserBO);
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "密码重置失败");
        }
        return new ResponseBO("重置成功");
    }

    /**
     * 取消推广员
     * @param spreadUserId
     * @return
     */
    @RequestMapping(value = "/cancel/{spreadUserId}", method = RequestMethod.PATCH, headers = Constant.API_VERSION_V1)
    public ResponseBO cancelSpreadUserById(@PathVariable Integer spreadUserId) {
        int result = spreadUserService.cancelSpreadUserById(spreadUserId);
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "取消推广员资格失败");
        } else if (result == -1) {
            return new ResponseBO(ResponseCode.U001.getCode(), "下级推广员不为空,不能撤销！");
        }

        return new ResponseBO("撤销成功");
    }


    /**
     * 重置提成比率
     * @param spreadUserBO
     * @return
     */
    @RequestMapping(value = "/resetRatio", method = RequestMethod.PATCH, headers = Constant.API_VERSION_V1)
    public ResponseBO resetSpreadUserRatio(@RequestBody SpreadUserBO spreadUserBO) {
        int result = spreadUserService.resetSpreadUserRatio(spreadUserBO);
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "提成比率重置失败");
        }
        return new ResponseBO("重置成功");
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping(value = "/{spreadUserId}/logout", method = RequestMethod.DELETE, headers = Constant.API_VERSION_V1)
    public ResponseBO logOut(@PathVariable Integer spreadUserId, HttpServletRequest request) {
        String token = request.getHeader("X-Token");

        spreadUserService.logout(token, spreadUserId);

        return new ResponseBO("退出成功");
    }

    /**
     * 查询推广员详情
     * @param spreadUserId
     * @return
     */
    @RequestMapping(value = "/{spreadUserId}", method = RequestMethod.GET, headers = Constant.API_VERSION_V1)
    public ResponseBO getSpreadUserByQry(@PathVariable Integer spreadUserId) {
        SpreadUserDto spreadUserDto = new SpreadUserDto();
        spreadUserDto.setSpreadUserId(spreadUserId);
        return spreadUserService.getSpreadUserByQry(spreadUserDto);
    }

    /**
     * 查询推广用户列表
     * @param spreadUserDto
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listSpreadUserByQry(@RequestBody SpreadUserDto spreadUserDto) {
        return spreadUserService.listSpreadUserByQry(spreadUserDto);
    }

    /**
     * 削减推广员钻石
     * @param spreadUserBO
     * @return
     */
    @RequestMapping(value = "/reduceDiamond", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO reduceSpreadUserDiamond(@RequestBody SpreadUserBO spreadUserBO) {
        if(spreadUserBO.getDiamond() < 0) {
            return new ResponseBO(ResponseCode.U001.getCode(),"推广员钻石不足");
        }
        int result = spreadUserService.changeSpreadUserDiamond(spreadUserBO);
        if (result == 0) {
            return new ResponseBO("削减失败");
        }
        return new ResponseBO("削减成功");
    }

    /**
     * 修改推广员信息
     * @param spreadUserBO
     * @return
     */
    @RequestMapping(value = "/modify", method = RequestMethod.PATCH, headers = Constant.API_VERSION_V1)
    public ResponseBO updateSpreadUser(@RequestBody SpreadUserBO spreadUserBO) {
        int result = spreadUserService.updateSpreadUser(spreadUserBO);
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "修改失败");
        }
        return new ResponseBO("修改成功");
    }
}
