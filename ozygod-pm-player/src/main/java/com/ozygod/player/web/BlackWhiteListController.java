package com.ozygod.player.web;

import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.enums.ResponseCode;
import com.ozygod.base.utils.Constant;
import com.ozygod.model.zdgame.bo.PlayerAccountBO;
import com.ozygod.model.zdmanage.bo.BlackWhiteListBO;
import com.ozygod.model.zdmanage.dto.BlackWhiteListDto;
import com.ozygod.player.service.IBlackWhiteListService;
import com.ozygod.player.service.IPlayerService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/13
 */
@RequestMapping("/players/blackWhiteList")
@RestController
@Slf4j
@Api(value = "/players/blackWhiteList", description = "玩家黑白名单管理")
public class BlackWhiteListController {

    @Autowired
    private IBlackWhiteListService blackWhiteListService;
    @Autowired
    private IPlayerService playerService;

    private static final Integer FLAG_DETETED = 1;


    /**
     * 查询黑白名单列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listBlackWhiteListByQry(@RequestBody BlackWhiteListDto dto) {
        List<BlackWhiteListBO> resultList = blackWhiteListService.listBlackWhiteListByQry(dto);
        int total = blackWhiteListService.totalBlackWhiteListByQry(dto);

        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(resultList);
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        responseBO.setTotalCount(total);
        return responseBO;
    }

    /**
     * 将玩家加入黑白名单
     * @param bo
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO saveBlackWhiteList(@RequestBody BlackWhiteListBO bo) {

        //查询该玩家详情
        PlayerAccountBO playerAccountBO = playerService.getPlayerAccountById(bo.getPlayerId());
        if (playerAccountBO == null) {
            return new ResponseBO(ResponseCode.L002.getCode(), "当前玩家不存在");
        }

        // 莫莫要求黑白名单只能有一个玩家，故加上限制
        BlackWhiteListDto existDto = new BlackWhiteListDto();
        existDto.setPartyId(bo.getPartyId());
        int count = blackWhiteListService.totalBlackWhiteListByQry(existDto);
        if (count >= 1) {
            return new ResponseBO(ResponseCode.I003.getCode(), "黑白名单已存在玩家");
        }

        //判断玩家在当前房间的黑白名单里是否已存在
        BlackWhiteListDto dto = new BlackWhiteListDto();
        dto.setPartyId(bo.getPartyId());
        dto.setPlayerId(bo.getPlayerId());
        int exist = blackWhiteListService.existInList(dto);
        if (exist > 0) {
            return new ResponseBO(ResponseCode.I003.getCode(), "该玩家已在黑白名单");
        }

        bo.setPlayerNickname(playerAccountBO.getNickName());
        int result = blackWhiteListService.saveBlackWhiteListBO(bo);
        if (result == 0) {
            return new ResponseBO(ResponseCode.I001.getCode(), ResponseCode.I001.getTitle());
        }
        return new ResponseBO("加入成功");
    }


    /**
     * 转换名单类型
     * @param bo
     * @return
     */
    @RequestMapping(value ="/transform", method = RequestMethod.PATCH, headers = Constant.API_VERSION_V1)
    public ResponseBO transformBlackWhiteListBO(@RequestBody BlackWhiteListBO bo) {

        int result = blackWhiteListService.updateBlackWhiteListBO(bo);
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), ResponseCode.U001.getTitle());
        }

        return new ResponseBO("加入成功");
    }

    /**
     * 删除黑白名单
     * @param listId
     * @return
     */
    @RequestMapping(value = "/{listId}", method = RequestMethod.DELETE, headers = Constant.API_VERSION_V1)
    public ResponseBO deleteBlackWhiteListBO(@PathVariable Integer listId) {

        BlackWhiteListBO bo = new BlackWhiteListBO();
        bo.setId(listId);
        bo.setDeleted(FLAG_DETETED);
        int result = blackWhiteListService.updateBlackWhiteListBO(bo);
        if (result == 0) {
            return new ResponseBO(ResponseCode.D001.getCode(), ResponseCode.D001.getTitle());
        }

        return new ResponseBO("删除成功");
    }
}
