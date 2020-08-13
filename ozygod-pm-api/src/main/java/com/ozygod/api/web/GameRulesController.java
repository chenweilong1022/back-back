package com.ozygod.api.web;

import com.ozygod.api.bo.GameRulesBO;
import com.ozygod.api.dto.GameRulesDto;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.utils.Constant;
import com.ozygod.base.utils.IPUtils;
import com.ozygod.base.utils.WeChatUtil;
import com.ozygod.game.service.IGameRuleService;
import com.ozygod.model.common.dto.PlatformDto;
import com.ozygod.model.zdconfig.bo.SysConfigsBO;
import com.ozygod.model.zdconfig.dto.ConfigDto;
import com.ozygod.model.zdmanage.bo.BlackWhiteListBO;
import com.ozygod.model.zdmanage.bo.GameRuleBO;
import com.ozygod.model.zdmanage.dto.BlackWhiteListDto;
import com.ozygod.model.zdmanage.dto.GameRuleDto;
import com.ozygod.platform.service.IPlatformService;
import com.ozygod.player.service.IBlackWhiteListService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/15
 */
@RequestMapping("/api/game/rules")
@RestController
@Slf4j
@Api(value = "/game/rules", description = "游戏规则接口")
public class GameRulesController implements Serializable {
    private static final long serialVersionUID = 3715014842153234830L;

    @Autowired
    private IBlackWhiteListService blackWhiteListService;
    @Autowired
    private IGameRuleService gameRuleService;
    @Autowired
    private IPlatformService platformService;

    @Autowired
    private IPUtils ipUtils;
    @Autowired
    private WeChatUtil weChatUtil;

    /**
     * 获取游戏规则
     * @param gameRulesDto
     * @return
     */
    @RequestMapping(value = "/get", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    @ApiOperation(value = "获取游戏规则列表", response = GameRulesBO.class)
    public GameRulesBO listRules(@RequestBody @ApiParam(value = "查询条件", required = true) GameRulesDto gameRulesDto) {
        GameRulesBO gameRulesBO = new GameRulesBO();

        //获取黑白名单列表
        BlackWhiteListDto blackWhiteListDto = new BlackWhiteListDto();
        blackWhiteListDto.setPartyId(gameRulesDto.getRoomId());
        blackWhiteListDto.setPageSize(-1);
        List<BlackWhiteListBO> blackWhiteListBOS = blackWhiteListService.listBlackWhiteListByQry(blackWhiteListDto);
        gameRulesBO.setBlackWhiteList(blackWhiteListBOS);
        //获取黑白名单规则
        Integer gameId = Integer.valueOf(String.valueOf(gameRulesDto.getRoomId()).substring(0,3));
        GameRuleDto gameRuleDto = new GameRuleDto();
        gameRuleDto.setPartyId(gameId);
        gameRuleDto.setRuleType(1);
        GameRuleBO gameRuleBO = gameRuleService.getGameRuleByQry(gameRuleDto);
        gameRulesBO.setBlackListRule(gameRuleBO.getRule());
        gameRuleDto.setRuleType(2);
        gameRuleBO = gameRuleService.getGameRuleByQry(gameRuleDto);
        gameRulesBO.setWhiteListRule(gameRuleBO.getRule());

        return gameRulesBO;
    }

    /**
     * 查询IP归属地
     * @return
     */
    @RequestMapping(value = "/getIP/{ip}", method = RequestMethod.GET)
    public ResponseBO getIP(@PathVariable String ip) {
        ResponseBO responseBO = new ResponseBO();

        responseBO.setData(ipUtils.getIPAddrCN(ip));
        return responseBO;
    }

    /**
     * 查询IP归属地
     * @return
     */
    @RequestMapping(value = "/domains", method = RequestMethod.GET)
    @ApiOperation(value = "查询IP归属地", response = String.class)
    public ResponseBO listSysDomain() {
        ResponseBO responseBO = new ResponseBO();
        ConfigDto dto = new ConfigDto();
        dto.setEnabled(Constant.YES);
        responseBO.setData(platformService.listSysDomainByQry(dto));
        return responseBO;
    }

    /**
     * 获取短链接
     * @return
     */
    @RequestMapping(value = "/shortUrl", method = RequestMethod.GET)
    @ApiOperation(value = "获取短链接", response = String.class)
    public ResponseBO getShortUrl(@RequestParam("longUrl") String longUrl) {
        ResponseBO responseBO = new ResponseBO();
//        responseBO.setData(weChatUtil.getWeChatShortUrl(longUrl));
        responseBO.setData(longUrl);
        return responseBO;
    }

    /**
     * 获取后台配置文件
     * @return
     */
    @RequestMapping(value = "/configs/{code}", method = RequestMethod.GET)
    public ResponseBO getSysConfigsByCode(@PathVariable String code) {
        ResponseBO responseBO = new ResponseBO();
        SysConfigsBO configsBO = platformService.getSysConfigsByQry(code);
        responseBO.setData(configsBO.getContent());
        return responseBO;
    }

    /**
     * 获取分享图信息
     * @return
     */
    @RequestMapping(value = "/shareImage", method = RequestMethod.GET)
    @ApiOperation(value = "查询IP归属地", response = String.class)
    public ResponseBO getShareImage() {
        PlatformDto dto = new PlatformDto();
        dto.setPageNo(1);
        dto.setPageSize(1);
        dto.setState(1);
        return new ResponseBO(platformService.listShareImageByQry(dto));
    }
}
