package com.ozygod.model.zdconfig.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.ozygod.base.bo.ResponseBO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ozygod.model.zdconfig.entity.TblGameRoomEntity;
import com.ozygod.model.zdconfig.dto.TblGameRoomListDto;
import com.ozygod.model.zdconfig.service.TblGameRoomService;



/**
 * 
 *
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-02-03 10:27:37
 */
@RestController
@RequestMapping("zdconfig/tblgameroom")
public class TblGameRoomController {
    @Autowired
    private TblGameRoomService tblGameRoomService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public ResponseBO list(@RequestBody TblGameRoomListDto tblGameRoom){
        return tblGameRoomService.queryPage(tblGameRoom);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{roomid}")
    public ResponseBO info(@PathVariable("roomid") Integer roomid){
			TblGameRoomEntity tblGameRoom = tblGameRoomService.getById(roomid);

        return ResponseBO.data(tblGameRoom);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public ResponseBO save(@RequestBody TblGameRoomEntity tblGameRoom){
			tblGameRoomService.save(tblGameRoom);

        return ResponseBO.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public ResponseBO update(@RequestBody TblGameRoomEntity tblGameRoom){
			tblGameRoomService.updateById(tblGameRoom);

        return ResponseBO.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public ResponseBO delete(@RequestBody Integer[] roomids){
			tblGameRoomService.removeByIds(Arrays.asList(roomids));

        return ResponseBO.ok();
    }

}
