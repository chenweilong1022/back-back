package com.ozygod.game.web;

import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.enums.ResponseCode;
import com.ozygod.base.utils.Constant;
import com.ozygod.game.service.IGameRuleService;
import com.ozygod.model.zdmanage.bo.GameRuleBO;
import com.ozygod.model.zdmanage.dto.GameRuleDto;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/16
 */
@Slf4j
@RequestMapping("/game/rules")
@RestController
@Api(value = "/game/rules", description = "游戏规则管理")
public class GameRuleController {


    @Autowired
    private IGameRuleService gameRuleService;

    /**
     * 查询游戏规则
     * @param gameRuleDto
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO getGameRuleByQry(@RequestBody GameRuleDto gameRuleDto) {

        GameRuleBO gameRuleBO = gameRuleService.getGameRuleByQry(gameRuleDto);

        return new ResponseBO(gameRuleBO);
    }

    /**
     * 保存游戏规则
     * @param gameRuleBO
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO saveGameRule(@RequestBody GameRuleBO gameRuleBO) {
        int result = gameRuleService.saveGameRule(gameRuleBO);
        if (result == 0) {
            return new ResponseBO(ResponseCode.S001.getCode(), ResponseCode.S001.getTitle());
        }
        return new ResponseBO("保存成功");
    }
}
