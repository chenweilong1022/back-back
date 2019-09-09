package com.ozygod.game.web;

import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.utils.Constant;
import com.ozygod.game.service.IGameService;
import com.ozygod.model.zdgame.dto.GameDto;
import io.swagger.annotations.Api;
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
 * @date: 2018/9/26
 */
@RequestMapping("/games")
@RestController
@Api(value = "/games", description = "游戏管理")
public class GameController {
    @Autowired
    private IGameService gameService;

    /**
     * 查询全部游戏列表
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET, headers = Constant.API_VERSION_V1)
    public ResponseBO listAllGame() {
        return new ResponseBO(gameService.listAllGame());
    }

    /**
     * 查询游戏列表及在线玩家人数
     * @return
     */
    @RequestMapping(value = "/rooms/onlineCount", method = RequestMethod.GET, headers = Constant.API_VERSION_V1)
    public ResponseBO listGameAndOnlineCount() {
        return new ResponseBO(gameService.listGameAndOnlineCount());
    }

    /**
     * 查询游戏房间列表
     * @return
     */
    @RequestMapping(value = "/rooms/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listGameRoomByQry(@RequestBody GameDto dto) {
        return new ResponseBO(gameService.listGameRoomByQry(dto));
    }
}
