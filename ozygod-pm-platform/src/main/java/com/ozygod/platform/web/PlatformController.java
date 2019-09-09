package com.ozygod.platform.web;

import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.enums.ResponseCode;
import com.ozygod.base.utils.Constant;
import com.ozygod.model.zdgame.bo.NoticeBO;
import com.ozygod.model.common.dto.PlatformDto;
import com.ozygod.model.zdlog.dto.PlayerLogDto;
import com.ozygod.model.zdmanage.bo.ShareImageBO;
import com.ozygod.model.zdmanage.bo.SysConfigsBO;
import com.ozygod.model.zdmanage.bo.SysDomainBO;
import com.ozygod.model.zdmanage.bo.VipChannelBO;
import com.ozygod.model.zdmanage.dto.ConfigDto;
import com.ozygod.platform.service.IPlatformService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/19
 */
@RequestMapping("/platform")
@RestController
@Slf4j
@Api(value = "/platform", description = "平台管理")
public class PlatformController {

    @Autowired
    private IPlatformService platformService;

    /**
     * 平台概况查询
     * @return
     */
    @RequestMapping(value = "/overview", method = RequestMethod.GET, headers = Constant.API_VERSION_V1)
    public ResponseBO getPlatformOverview() {
        return new ResponseBO(platformService.getPlatformOverview());
    }

    /**
     * 平台玩家日充值汇总
     * @param dto
     * @return
     */
    @RequestMapping(value = "/rechargeCount", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO getComplexRechargeCountByQry(@RequestBody PlatformDto dto) {
        return new ResponseBO(platformService.getComplexRechargeCountByQry(dto));
    }

    /**
     * 平台玩家近几日统计列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/rechargeCount/recent", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listRecentDaysRechargeCountByQry(@RequestBody PlatformDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(platformService.listRecentDaysRechargeCountByQry(dto));
        return responseBO;
    }

    /**
     * 平台玩家日统计列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/playersCount", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listDailyPlayersCountByQry(@RequestBody PlatformDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(platformService.listDailyPlayersCountByQry(dto));
        responseBO.setTotalCount(platformService.totalDailyPlayersCountByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 平台财富日统计列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/goldCount", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listDailyGoldCountByQry(@RequestBody PlatformDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(platformService.listDailyGoldCountByQry(dto));
        responseBO.setTotalCount(platformService.totalDailyGoldCountByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 平台财富日统计列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/goldCount/sum", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO getDailyGoldCountSumByQry(@RequestBody PlatformDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(platformService.getDailyGoldCountSumByQry(dto));
        return responseBO;
    }

    /**
     * 平台游戏税收日统计列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/game/taxGoldCount", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listDailyGameTaxGoldCountByQry(@RequestBody PlatformDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(platformService.listDailyGameTaxGoldCountByQry(dto));
        responseBO.setTotalCount(platformService.totalDailyGameTaxGoldCountByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 平台注册留存率统计列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/regAliveCount", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listDailyRegAliveCountByQry(@RequestBody PlatformDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(platformService.listDailyRegAliveCountByQry(dto));
        responseBO.setTotalCount(platformService.totalDailyRegAliveCountByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 查询渠道类型
     * @return
     */
    @RequestMapping(value = "/channel/types", method = RequestMethod.GET, headers = Constant.API_VERSION_V1)
    public ResponseBO listAppChannelType() {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(platformService.listAppChannelType());
        return responseBO;
    }

    /**
     * 查询公告列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/notices", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listNoticeByQry(@RequestBody PlatformDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(platformService.listNoticeByQry(dto));
        responseBO.setTotalCount(platformService.totalNoticeByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 发布公告
     * @param noticeId
     * @param bo
     * @return
     */
    @RequestMapping(value = "/notices/{noticeId}/publish", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO publishNotice(@PathVariable Integer noticeId, @RequestBody NoticeBO bo) {
        int result = platformService.publishNotice(noticeId, bo.getManagerId());
        if (result == 2) {
            return new ResponseBO(ResponseCode.P003.getCode(), "该渠道发布消息个数已经达到5个！请先下架过期公告再操作");
        } else if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "发布失败");
        }
        return new ResponseBO("发布成功");
    }

    /**
     * 下架公告
     * @param noticeId
     * @param bo
     * @return
     */
    @RequestMapping(value = "/notices/{noticeId}/unpublish", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO unpublishNotice(@PathVariable Integer noticeId, @RequestBody NoticeBO bo) {
        int result = platformService.unpublishNotice(noticeId, bo.getManagerId());
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "下架失败");
        }
        return new ResponseBO("下架成功");
    }

    /**
     * 删除公告
     * @param noticeId
     * @return
     */
    @RequestMapping(value = "/notices/{noticeId}", method = RequestMethod.DELETE, headers = Constant.API_VERSION_V1)
    public ResponseBO deleteNotice(@PathVariable Integer noticeId) {
        int result = platformService.deleteNotice(noticeId);
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "删除失败");
        }
        return new ResponseBO("删除成功");
    }

    /**
     * 创建公告
     * @param bo
     * @return
     */
    @RequestMapping(value = "/notices/create", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO createNotice(@RequestBody NoticeBO bo) {
        int result = platformService.createNotice(bo);
        if (result == 0) {
            return new ResponseBO(ResponseCode.I001.getCode(), "创建失败");
        }
        return new ResponseBO("创建成功");
    }

    /**
     * 查询VIP充值渠道列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/vipChannel/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listVipChannelByQry(@RequestBody PlatformDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(platformService.listVipChannelByQry(dto));
        responseBO.setTotalCount(platformService.totalVipChannelByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 新增VIP充值渠道
     * @param bo
     * @return
     */
    @RequestMapping(value = "/vipChannel/new", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO saveVipChannel(@RequestBody VipChannelBO bo) {
        int result = platformService.saveVipChannel(bo);
        if (result == 0) {
            return new ResponseBO(ResponseCode.I001.getCode(), "创建失败");
        }
        return new ResponseBO("创建成功");
    }

    /**
     * 更新VIP充值渠道
     * @param bo
     * @return
     */
    @RequestMapping(value = "/vipChannel/modify", method = RequestMethod.PATCH, headers = Constant.API_VERSION_V1)
    public ResponseBO updateVipChannel(@RequestBody VipChannelBO bo) {
        int result = platformService.updateVipChannel(bo);
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "处理失败");
        }
        return new ResponseBO("处理成功");
    }

    /**
     * 新增系统配置
     * @param entity
     * @return
     */
    @RequestMapping(value = "/sysConfigs/add", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO saveSysConfigs(@RequestBody SysConfigsBO entity) {
        int result = platformService.saveSysConfigs(entity);
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "新增失败");
        }
        return new ResponseBO("新增成功");
    }

    /**
     * 查询系统配置列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/sysConfigs/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listSysConfigsByQry(@RequestBody ConfigDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(platformService.listSysConfigsByQry(dto));
        responseBO.setTotalCount(platformService.totalSysConfigsByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 更新系统配置
     * @param entity
     * @return
     */
    @RequestMapping(value = "/sysConfigs/modify", method = RequestMethod.PATCH, headers = Constant.API_VERSION_V1)
    public ResponseBO updateSysConfig(@RequestBody SysConfigsBO entity) {
        int result = platformService.updateSysConfig(entity);
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "处理失败");
        }
        return new ResponseBO("处理成功");
    }

    /**
     * 游戏输赢明细
     * @return
     */
    @RequestMapping(value = "/gameWinningDetail", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listGameWinningDetailByQry(@RequestBody PlayerLogDto dto) {
        return new ResponseBO(platformService.listGameWinningDetailByQry(dto));
    }

    /**
     * 查询财务充值对账单
     * @return
     */
    @RequestMapping(value = "/finance/statement/recharge", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listRechargeStatementByQry(@RequestBody PlatformDto dto) {
        return new ResponseBO(platformService.listRechargeStatementByQry(dto));
    }

    /**
     * 查询财务提现对账单
     * @return
     */
    @RequestMapping(value = "/finance/statement/withdraw", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listWithdrawStatementByQry(@RequestBody PlatformDto dto) {
        return new ResponseBO(platformService.listWithdrawStatementByQry(dto));
    }

    /**
     * 新增系统域名
     * @param entity
     * @return
     */
    @RequestMapping(value = "/sysDomain/add", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO saveSysDomain(@RequestBody SysDomainBO entity) {
        int result = platformService.saveSysDomain(entity);
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "新增失败");
        }
        return new ResponseBO("新增成功");
    }

    /**
     * 查询系统域名列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "/sysDomain/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listSysDomainByQry(@RequestBody ConfigDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(platformService.listSysDomainByQry(dto));
        responseBO.setTotalCount(platformService.totalSysDomainByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 更新系统域名
     * @param entity
     * @return
     */
    @RequestMapping(value = "/sysDomain/modify", method = RequestMethod.PATCH, headers = Constant.API_VERSION_V1)
    public ResponseBO updateSysDomain(@RequestBody SysDomainBO entity) {
        int result = platformService.updateSysDomain(entity);
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "处理失败");
        }
        return new ResponseBO("处理成功");
    }

    /**
     * 新增分享图
     * @param entity
     * @return
     */
    @RequestMapping(value = "/shareImage/add", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO saveShareImage(@RequestBody ShareImageBO entity) {
        int result = platformService.saveShareImage(entity);
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "新增失败");
        }
        return new ResponseBO("新增成功");
    }

    /**
     * 查询分享图
     * @param dto
     * @return
     */
    @RequestMapping(value = "/shareImage/query", method = RequestMethod.POST, headers = Constant.API_VERSION_V1)
    public ResponseBO listShareImageByQry(@RequestBody PlatformDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(platformService.listShareImageByQry(dto));
        responseBO.setTotalCount(platformService.countShareImageByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 更新分享图
     * @param entity
     * @return
     */
    @RequestMapping(value = "/shareImage/modify", method = RequestMethod.PATCH, headers = Constant.API_VERSION_V1)
    public ResponseBO updateShareImage(@RequestBody ShareImageBO entity) {
        int result = platformService.updateShareImage(entity);
        if (result == 0) {
            return new ResponseBO(ResponseCode.U001.getCode(), "处理失败");
        }
        return new ResponseBO("处理成功");
    }
}
