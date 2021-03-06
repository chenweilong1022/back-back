package com.ozygod.player.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ozygod.base.bo.ResponseBO;
import com.ozygod.base.enums.Global;
import com.ozygod.base.utils.*;
import com.ozygod.model.common.bo.EmailBO;
import com.ozygod.model.zdconfig.bo.SysConfigsBO;
import com.ozygod.model.zdconfig.dao.SysConfigsEntityMapper;
import com.ozygod.model.zdconfig.vo.PlayersWinLoseVO;
import com.ozygod.model.zdgame.bo.*;
import com.ozygod.model.zdgame.dao.*;
import com.ozygod.model.zdgame.dto.LockPlayerDTO;
import com.ozygod.model.zdgame.dto.PlayerAccountDto;
import com.ozygod.model.zdgame.dto.PlayerOrderDto;
import com.ozygod.model.zdgame.dto.RemitDto;
import com.ozygod.model.zdgame.entity.AccountEntity;
import com.ozygod.model.zdgame.entity.PlayerInfoEntity;
import com.ozygod.model.zdgame.service.TblAccountService;
import com.ozygod.model.zdgame.service.TblOrderService;
import com.ozygod.model.zdlog.bo.GameWinningDetailBO;
import com.ozygod.model.zdlog.bo.RemitDiamondRecordBO;
import com.ozygod.model.zdlog.bo.RemitGoldRecordBO;
import com.ozygod.model.zdlog.dao.*;
import com.ozygod.model.zdlog.dto.BankGoldDto;
import com.ozygod.model.zdlog.dto.PlayerLogDto;
import com.ozygod.model.zdlog.entity.FreezeUserEntity;
import com.ozygod.model.zdlog.service.TblGameGoldService;
import com.ozygod.model.zdmanage.bo.WithdrawOrderBO;
import com.ozygod.model.zdmanage.dao.ManageLogEntityMapper;
import com.ozygod.model.zdmanage.dao.WithdrawOrderEntityMapper;
import com.ozygod.model.zdmanage.dto.BusinessDto;
import com.ozygod.model.zdmanage.entity.WithdrawOrderEntity;
import com.ozygod.model.zdmanage.service.TblWithdrawOrderService;
import com.ozygod.player.service.IPlayerService;
import com.ozygod.player.utils.PlayerConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @title:
 * @description:
 * @author: Joey
 * @email: ozygod@gmail.com
 * @date: 2018/9/14
 */
@Slf4j
@Component
public class PlayerServiceImpl implements IPlayerService {

    @Autowired
    private AccountEntityMapper accountEntityMapper;

    @Autowired
    private BankGoldEntityMapper bankGoldEntityMapper;

    @Autowired
    private OrderEntityMapper orderEntityMapper;

    @Autowired
    private GameGoldEntityMapper gameGoldEntityMapper;

    @Autowired
    private GameDiamondEntityMapper gameDiamondEntityMapper;

    @Autowired
    private FreezeUserEntityMapper freezeUserEntityMapper;

    @Autowired
    private RecordEntityMapper recordEntityMapper;

    @Autowired
    private LogProcedureMapper logProcedureMapper;

    @Autowired
    private WithdrawOrderEntityMapper withdrawOrderEntityMapper;

    @Autowired
    private PlayerInfoEntityMapper playerInfoEntityMapper;

    @Autowired
    private ManagerRemitGoldRecordEntityMapper remitGoldRecordEntityMapper;

    @Autowired
    private ManagerRemitDiamondRecordEntityMapper remitDiamondRecordEntityMapper;

    @Autowired
    private PrivateGameRecordEntityMapper privateGameRecordEntityMapper;

    @Autowired
    private IPUtils ipUtils;

    @Autowired
    private ManageLogEntityMapper manageLogEntityMapper;

    @Autowired
    private SysConfigsEntityMapper sysConfigsEntityMapper;
    @Autowired
    private TblGameGoldService tblGameGoldService;
    @Autowired
    private TblOrderService tblOrderService;
    @Autowired
    private TblWithdrawOrderService tblWithdrawOrderService;
    @Autowired
    private TblAccountService tblAccountService;

    @Value("${game_url}")
    private String gameUrl;

    @Value("${agent_url}")
    private String agentUrl;

    /**
     * 根据玩家id获取玩家信息
     *
     * @param playerId
     * @return
     */
    @Override
    public AccountEntity getPlayerById(Integer playerId) {
        return accountEntityMapper.selectByPrimaryKey((long)playerId);
    }

    /**
     * 查询玩家详情
     *
     * @param playerId
     * @return
     */
    @Override
    public PlayerAccountBO getPlayerAccountById(Integer playerId) {
        PlayerAccountBO accountBO = accountEntityMapper.getPlayerAccountById(playerId);
        if (accountBO != null) {
            // 取IP前三段
            String firstIP = accountBO.getIp().substring(0, accountBO.getIp().lastIndexOf("."));
            accountBO.setIpAttr(ipUtils.getIPAddrCN(firstIP));

            // 玩家如果开通了代理，则增加代理参数
//            if (accountBO.getSpreadUserId() > 0) {
//                PlayerLogDto logDto = new PlayerLogDto();
//                logDto.setPlayerId((long)playerId);
//                // 当日返佣
//                Map<String, Date> todayMap = getDurationBeginEndByType(0);
//                logDto.setStartTime(todayMap.get("begin"));
//                logDto.setEndTime(todayMap.get("end"));
//                accountBO.setTodayRebate(gameGoldEntityMapper.countSpreadMoneyByQry(logDto));
//                // 本周返佣
//                Map<String, Date> thisWeekMap = getDurationBeginEndByType(1);
//                logDto.setStartTime(thisWeekMap.get("begin"));
//                logDto.setEndTime(thisWeekMap.get("end"));
//                accountBO.setThisWeekRebate(gameGoldEntityMapper.countSpreadMoneyByQry(logDto));
//                // 本月返佣
//                Map<String, Date> thisMonthMap = getDurationBeginEndByType(2);
//                logDto.setStartTime(thisMonthMap.get("begin"));
//                logDto.setEndTime(thisMonthMap.get("end"));
//                accountBO.setThisMonthRebate(gameGoldEntityMapper.countSpreadMoneyByQry(logDto));
//            }
        }
        return accountBO;
    }

    /**
     * 查询玩家详情
     *
     * @param showId
     * @return
     */
    @Override
    public PlayerAccountBO getPlayerAccountByShowId(Integer showId) {
        PlayerAccountBO accountBO = accountEntityMapper.getPlayerAccountByShowId(showId);
        if (accountBO != null) {
            // 取IP前三段
            String firstIP = accountBO.getIp().substring(0, accountBO.getIp().lastIndexOf("."));
            accountBO.setIpAttr(ipUtils.getIPAddrCN(firstIP));

            // 玩家如果开通了代理，则增加代理参数
//            if (accountBO.getSpreadUserId() > 0) {
//                PlayerLogDto logDto = new PlayerLogDto();
//                logDto.setPlayerId(accountBO.getUserid());
//                // 当日返佣
//                Map<String, Date> todayMap = getDurationBeginEndByType(0);
//                logDto.setStartTime(todayMap.get("begin"));
//                logDto.setEndTime(todayMap.get("end"));
//                accountBO.setTodayRebate(gameGoldEntityMapper.countSpreadMoneyByQry(logDto));
//                // 本周返佣
//                Map<String, Date> thisWeekMap = getDurationBeginEndByType(1);
//                logDto.setStartTime(thisWeekMap.get("begin"));
//                logDto.setEndTime(thisWeekMap.get("end"));
//                accountBO.setThisWeekRebate(gameGoldEntityMapper.countSpreadMoneyByQry(logDto));
//                // 本月返佣
//                Map<String, Date> thisMonthMap = getDurationBeginEndByType(2);
//                logDto.setStartTime(thisMonthMap.get("begin"));
//                logDto.setEndTime(thisMonthMap.get("end"));
//                accountBO.setThisMonthRebate(gameGoldEntityMapper.countSpreadMoneyByQry(logDto));
//            }
        }
        return accountBO;
    }

    /**
     * 获取指定类型时间段的起始结束日期
     * @param type
     * @return
     */
    private Map<String, Date> getDurationBeginEndByType(int type) {
        Map<String, Date> resultMap = new HashMap<>();
        DateFormat fullDf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try{
            switch (type) {
                // 当日
                case 0: {
                    Date date = new Date();
                    Date beginDate = fullDf.parse(df.format(date) + " 00:00:00");
                    Date endDate = fullDf.parse(df.format(date) + " 23:59:59");
                    resultMap.put("begin", beginDate);
                    resultMap.put("end", endDate);
                }
                break;
                // 当周
                case 1: {
                    Date date = new Date();
                    Date beginDate = fullDf.parse(CommonUtil.getWeekFirstDay(df.format(date)) + " 00:00:00");
                    Date endDate = fullDf.parse( CommonUtil.getWeekLastDay(df.format(date))+ " 23:59:59");
                    resultMap.put("begin", beginDate);
                    resultMap.put("end", endDate);
                }
                    break;
                // 当月
                case 2: {
                    Date date = new Date();
                    Date beginDate = fullDf.parse(CommonUtil.getMonthFirstDay(df.format(date)) + " 00:00:00");
                    Date endDate = fullDf.parse( CommonUtil.getMonthLastDay(df.format(date))+ " 23:59:59");
                    resultMap.put("begin", beginDate);
                    resultMap.put("end", endDate);
                }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultMap;
    }

    /**
     * 查询玩家帐号列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<PlayerAccountBO> listPlayerAccountByQry(PlayerAccountDto dto) {
        List<PlayerAccountBO> resultList = accountEntityMapper.listPlayerAccountByQry(dto);
        for (PlayerAccountBO bo :
                resultList) {
            //修改一个判空bug 如果 bo.getIp为空不处理
            if (CommonUtil.isEmptyStr(bo.getIp())) {
                continue;
            }
            // 取IP前三段
            String firstIP = bo.getIp().substring(0, bo.getIp().lastIndexOf("."));
            bo.setIpAttr(ipUtils.getIPAddrCN(firstIP));


            PlayerLogDto playerLogDto = new PlayerLogDto();
            playerLogDto.setPlayerId(bo.getUserid());
            playerLogDto.setStartTime(DateUtil.beginOfDay(DateUtil.date()));
            playerLogDto.setEndTime(DateUtil.endOfDay(DateUtil.date()));
            PlayersWinLoseVO playersWinLoseVO = this.playersWinLose(playerLogDto);
            if (ObjectUtil.isNotNull(playersWinLoseVO)) {
                playersWinLoseVO.setWinningMoney(playersWinLoseVO.getWin().add(playersWinLoseVO.getLose()));
            }
            bo.setPlayersWinLoseVO(playersWinLoseVO);

        }
        return resultList;
    }

    /**
     * 查询玩家帐号总数
     *
     * @param dto
     * @return
     */
    @Override
    public int totalCountPlayerAccountByQry(PlayerAccountDto dto) {
        return accountEntityMapper.totalCountPlayerAccountByQry(dto);
    }

    /**
     * 给玩家赠送礼金
     *
     * @param bo
     * @return
     */
    @Override
    public int giftMoney(PlayerAccountBO bo) {
        // 获取赠送礼金数额
        // 获取税率配置项
        SysConfigsBO configsBO = sysConfigsEntityMapper.getSysConfigsByQry(Constant.SYS_CONFIGS_GIFT_AMOUNT);
        Map<String, Integer> configsMap = JSON.parseObject(configsBO.getContent(), new TypeReference<Map<String, Integer>>(){});
        Integer giftMoney = configsMap.get(Constant.SYS_CONFIGS_GIFT_AMOUNT);
        String result = HttpRequestUtil.sendGet(gameUrl + "/add_gold?reason=3&user_id=" + bo.getUserid()
                + "&manager_id=" + bo.getManagerId() + "&gold=" + giftMoney * 100);
        log.info("result: " + result);
        if (CommonUtil.ValidateSuccess(result)) {
            // 赠送成功后修改玩家赠送标记
            PlayerInfoEntity entity = new PlayerInfoEntity();
            entity.setUserid(bo.getUserid());
            entity.setSendGift(Constant.YES);
            playerInfoEntityMapper.updateByPrimaryKeySelective(entity);
            return 1;
        }
        return 0;
    }

    /**
     * 设置玩家代理保底
     *
     * @param bo
     * @return
     */
    @Override
    public int setSaleRate(PlayerAccountBO bo) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userid", bo.getUserid());
        paramMap.put("saleRate", bo.getSaleRate());
        String result = HttpRequestUtil.sendPostJson(agentUrl + "/setSaleRate", JSON.toJSONString(paramMap));
        log.info("result: " + result);
        if (!CommonUtil.isEmptyStr(result) && result.indexOf("success") > 0) {
            return 1;
        }
        return 0;
    }

    /**
     * 设置上级代理
     *
     * @param bo
     * @return
     */
    @Override
    public int setUpSpread(PlayerAccountBO bo) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("userid", bo.getUserid());
        paramMap.put("upSpreadId", bo.getSpreadUserId());
        String result = HttpRequestUtil.sendPostJson(agentUrl + "/setUpSpread", JSON.toJSONString(paramMap));
        log.info("result: " + result);
        if (!CommonUtil.isEmptyStr(result) && result.indexOf("success") > 0) {
            return 1;
        }
        return 0;
    }

    /**
     * 发送邮件
     *
     * @param bo
     * @return
     */
    @Override
    public int sendEmail(EmailBO bo) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("from", bo.getSender());
        paramMap.put("to", bo.getReceivers());
        paramMap.put("title", bo.getTitle());
        paramMap.put("msg", bo.getContent());
        log.info("url = {}",agentUrl + "/Mail/sendMail");
        log.info("json = {}",JSON.toJSONString(paramMap));
        String result = HttpRequestUtil.sendPostJson(agentUrl + "/Mail/sendMail", JSON.toJSONString(paramMap));
        log.info("result: " + result);
        if (!CommonUtil.isEmptyStr(result) && result.indexOf("success") > 0) {
            return 1;
        }
        return 0;
    }

    /**
     * 查询在线玩家列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<PlayerAccountBO> listOnlinePlayerQry(PlayerAccountDto dto) {
        /**
         * 今日日期起止
         */
        DateTime beginOfDay = OzygodDateUtil.beginOfDay(DateUtil.date());
        DateTime endOfDay = OzygodDateUtil.endOfDay(DateUtil.date());

        Page page = dto.getPage();
        List<PlayerAccountBO> resultList = accountEntityMapper.listOnlinePlayerQryPage(page, dto, Global.REAL_USER_ID);
        for (PlayerAccountBO bo : resultList) {
            if (StrUtil.isNotBlank(bo.getIp())) {
                // 取IP前三段
                String firstIP = bo.getIp().substring(0, bo.getIp().lastIndexOf("."));
                bo.setIpAttr(ipUtils.getIPAddrCN(firstIP));
            }
            // 充值标识
            bo.setRechargeFlag(this.validateUserRechargeState(bo.getShowId()));
//            /**
//             * 今日输赢
//             */
//            bo.setTodayWinningGold(tblGameGoldService.winningLosing(beginOfDay,endOfDay, CollUtil.newArrayList(bo.getUserid()),CollUtil.newArrayList("0")));
//            /**
//             * 总输赢
//             */
//            bo.setTotalWinningGold(tblGameGoldService.winningLosing(null,null, CollUtil.newArrayList(bo.getUserid()),CollUtil.newArrayList("0")));

            //今日输赢
            PlayerLogDto playerLogDto = new PlayerLogDto();
            playerLogDto.setPlayerId(bo.getUserid());
            PlayersWinLoseVO totalPlayersWinLoseVO = this.playersWinLose(playerLogDto);
            if (ObjectUtil.isNotNull(totalPlayersWinLoseVO)) {
                totalPlayersWinLoseVO.setWinningMoney(totalPlayersWinLoseVO.getWin().add(totalPlayersWinLoseVO.getLose()));
            }
            bo.setTotalPlayersWinLoseVO(totalPlayersWinLoseVO);
            //总输赢
            playerLogDto.setStartTime(DateUtil.beginOfDay(DateUtil.date()));
            playerLogDto.setEndTime(DateUtil.endOfDay(DateUtil.date()));
            PlayersWinLoseVO playersWinLoseVO = this.playersWinLose(playerLogDto);

            if (ObjectUtil.isNotNull(playersWinLoseVO)) {
                playersWinLoseVO.setWinningMoney(playersWinLoseVO.getWin().add(playersWinLoseVO.getLose()));
            }
            bo.setPlayersWinLoseVO(playersWinLoseVO);



            /**
             * 今日充值金额
             */
            bo.setTodayRechargeGold(tblOrderService.recharge(beginOfDay, endOfDay, CollUtil.newArrayList(bo.getUserid())));
            /**
             * 总充值金额
             */
            bo.setTotalRechargeGold(tblOrderService.recharge(null, null, CollUtil.newArrayList(bo.getUserid())));
            /**
             * 今日提现
             */
            bo.setTodayWithdrawGold(tblWithdrawOrderService.totalBack(beginOfDay, endOfDay, CollUtil.newArrayList(bo.getUserid())));
            /**
             * 今日提现
             */
            bo.setTotalWithdrawGold(tblWithdrawOrderService.totalBack(null, null, CollUtil.newArrayList(bo.getUserid())));
        }
        return resultList;
    }

    @Override
    public List<PlayerAccountBO> listOnlineTotalGold(PlayerAccountDto dto) {

        List<PlayerAccountBO> playerAccountBOS = new ArrayList<>();

        PlayerAccountBO playerAccountBO = accountEntityMapper.listOnlinePlayerTotalGold(dto.getPage(), dto, Global.REAL_USER_ID);

        if (ObjectUtil.isNull(playerAccountBO)) {
            playerAccountBO = new PlayerAccountBO();
        }
        playerAccountBO.setType("小计");
        playerAccountBOS.add(playerAccountBO);

        PlayerAccountBO playerAccountBO1 = accountEntityMapper.listOnlinePlayerTotalGold(null, dto, Global.REAL_USER_ID);

        if (ObjectUtil.isNull(playerAccountBO1)) {
            playerAccountBO1 = new PlayerAccountBO();
        }
        playerAccountBO1.setType("合计");

        playerAccountBOS.add(playerAccountBO1);


        return playerAccountBOS;
    }

    /**
     * 查询在线玩家列表总数
     *
     * @param dto
     * @return
     */
    @Override
    public int totalOnlinePlayerByQry(PlayerAccountDto dto) {
        return accountEntityMapper.totalOnlinePlayerByQry(dto);
    }

    /**
     * 查询未结算玩家列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<PlayerAccountBO> listDebtPlayerQry(PlayerAccountDto dto) {
        return playerInfoEntityMapper.listDebtPlayerQry(dto);
    }

    /**
     * 调整玩家幸运值
     *
     * @param bo
     * @return
     */
    @Override
    public int updatePlayerLuckyRatio(PlayerAccountBO bo) {
        String url = gameUrl + "/game_trick?roomid=" + bo.getRoom() +
                "&uid=" + bo.getUserid() + "&val=" + bo.getLuckyRatio();
        log.info("url:" + url);
        String result = HttpRequestUtil.sendGet(url);
        log.info("result: " + result);
        if (CommonUtil.ValidateSuccess(result)) {
            return 1;
        }
        return 0;
    }

    /**
     * 查询未结算玩家列表总数
     *
     * @param dto
     * @return
     */
    @Override
    public int totalDebtPlayerByQry(PlayerAccountDto dto) {
        return playerInfoEntityMapper.totalDebtPlayerByQry(dto);
    }

    /**
     * 查询玩家银行日志列表
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseBO listPlayerBankLogByQry(BankGoldDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(bankGoldEntityMapper.listPlayerBankGoldByQry(dto));
        responseBO.setTotalCount(bankGoldEntityMapper.totalCountPlayerBankGoldByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 查询玩家订单列表
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseBO listPlayerOrderByQry(PlayerOrderDto dto) {
        Page page = dto.getPage();
        List<PlayerOrderBO> playerOrderBOS = orderEntityMapper.listPlayerOrderByQryPage(page, dto);
        return ResponseBO.page(page).setData(playerOrderBOS);
    }

    @Override
    public ResponseBO listPlayerOnlineOrderByQry(PlayerOrderDto dto) {
        Page page = dto.getPage();
        List<Long> userIds = tblAccountService.onlineUserIds();
        dto.setOnlinePlayerIds(userIds);
        List<PlayerOrderBO> playerOrderBOS = orderEntityMapper.listPlayerOrderByQryPage(page, dto);
        return ResponseBO.page(page).setData(playerOrderBOS);
    }

    /**
     * 更新玩家订单
     *
     * @param bo
     * @return
     */
    @Override
    public int updateOrder(PlayerOrderBO bo) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("orderid", bo.getOrderId());
        paramMap.put("result", bo.getResult());
        String result = HttpRequestUtil.sendPostJson(agentUrl + "/bank/payCallback", JSON.toJSONString(paramMap));
        log.info("result:" + result);
        if (!CommonUtil.isEmptyStr(result) && result.indexOf("success") > 0) {
            return 1;
        }
        return 0;
    }

    /**
     * 查询玩家游戏日志列表
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseBO listPlayerGameLogByQry(PlayerLogDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(gameGoldEntityMapper.listPlayerGameLogByQry(dto));
        responseBO.setTotalCount(gameGoldEntityMapper.totalPlayerGameLogByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 查询玩家钻石游戏日志列表
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseBO listPlayerDiamondLogByQry(PlayerLogDto dto) {
        ResponseBO responseBO = new ResponseBO();
        responseBO.setData(gameDiamondEntityMapper.listPlayerGameLogByQry(dto));
        responseBO.setTotalCount(gameDiamondEntityMapper.totalPlayerGameLogByQry(dto));
        responseBO.setPageNo(dto.getPageNo());
        responseBO.setPageSize(dto.getPageSize());
        return responseBO;
    }

    /**
     * 重置玩家登录密码
     *
     * @param playerId
     * @param managerId
     * @return
     */
    @Override
    public int resetPlayerPassword(Integer playerId, Integer managerId) {
        String result = HttpRequestUtil.sendGet(gameUrl + "/change_password?user_id=" + playerId
                + "&password=" + PlayerConstant.DEFAULT_PLAYER_PASSWORD + "&manager_id=" + managerId);
        log.info("result: " + result);
        if (CommonUtil.ValidateSuccess(result)) {
            return 1;
        }
        return 0;
    }

    /**
     * 重置玩家银行密码
     *
     * @param playerId
     * @param managerId
     * @return
     */
    @Override
    public int resetPlayerBankPassword(Integer playerId, Integer managerId) {
        String result = HttpRequestUtil.sendGet(gameUrl + "/change_second_pwd?user_id=" + playerId
                + "&password=" + PlayerConstant.DEFAULT_PLAYER_BANK_PASSWORD + "&manager_id=" + managerId);
        log.info("result: " + result);
        if (CommonUtil.ValidateSuccess(result)) {
            return 1;
        }
        return 0;
    }

    /**
     * 查询玩家冻结帐号记录列表
     *
     * @param playerId
     * @return
     */
    @Override
    public List<FreezeUserEntity> listFreezeUserRecordById(Integer playerId) {
        return freezeUserEntityMapper.listFreezeUserRecordById(playerId);
    }

    /**
     * 冻结玩家帐号
     *
     * @param bo
     * @return
     */
    @Override
    public int freezePlayer(PlayerAccountBO bo) {
        String result = HttpRequestUtil.sendGet(gameUrl + "/freeze_user?user_id_start=" + bo.getUserid()
                + "&user_id_end=" + bo.getUserid() + "&manager_id=" + bo.getManagerId() + "&reason=" + bo.getReason());
        log.info("result: " + result);

        //新增发送到 agent
        LockPlayerDTO lockPlayerDTO = new LockPlayerDTO();
        lockPlayerDTO.setStart_uid(bo.getUserid());
        lockPlayerDTO.setEnd_uid(bo.getUserid());
        lockPlayer(lockPlayerDTO);
        if (CommonUtil.ValidateSuccess(result)) {
            return 1;
        }
        return 0;
    }

    /**
     * 冻结玩家帐号
     *
     * @param bo
     * @return
     */
    @Override
    public int batchFreezePlayer(PlayerAccountBO bo) {
        String result = HttpRequestUtil.sendGet(gameUrl + "/freeze_user?user_id_start=" + bo.getStartPlayerId()
                + "&user_id_end=" + bo.getEndPlayerId() + "&manager_id=" + bo.getManagerId() + "&reason=" + bo.getReason());
        log.info("result: " + result);

        //新增发送到 agent
        LockPlayerDTO lockPlayerDTO = new LockPlayerDTO();
        lockPlayerDTO.setStart_uid(bo.getStartPlayerId());
        lockPlayerDTO.setEnd_uid(bo.getEndPlayerId());
        lockPlayer(lockPlayerDTO);

        if (CommonUtil.ValidateSuccess(result)) {
            return 1;
        }
        return 0;
    }

    private void lockPlayer(LockPlayerDTO lockPlayerDTO) {
        String post = HttpUtil.post(agentUrl + "/lockPlayer", JSONUtil.toJsonStr(lockPlayerDTO));
        log.info(post);
    }

    /**
     * 解冻玩家帐号
     *
     * @param bo
     * @return
     */
    @Override
    public int unfreezePlayer(PlayerAccountBO bo) {
        String result = HttpRequestUtil.sendGet(gameUrl + "/unfreeze_user?user_id=" + bo.getUserid()
                + "&manager_id=" + bo.getManagerId() + "&reason=" + bo.getReason());
        log.info("result: " + result);
        if (CommonUtil.ValidateSuccess(result)) {
            return 1;
        }
        return 0;
    }

    /**
     * 查询玩家输赢记录
     *
     * @param dto
     * @return
     */
    @Override
    public List<RecordBO> listPlayerGameRecordByQry(PlayerAccountDto dto) {
        return recordEntityMapper.listPlayerGameRecordByQry(dto);
    }

    /**
     * 玩家私人房游戏记录统计
     *
     * @param dto
     * @return
     */
    @Override
    public List<PrivateGameRecordBO> listPrivateGameStatisticsByQry(PlayerAccountDto dto) {
        return privateGameRecordEntityMapper.listPrivateGameStatisticsByQry(dto);
    }

    /**
     * 玩家私人房游戏记录统计总数
     *
     * @param dto
     * @return
     */
    @Override
    public int totalPrivateGameStatisticsByQry(PlayerAccountDto dto) {
        return privateGameRecordEntityMapper.totalPrivateGameStatisticsByQry(dto);
    }


    /**
     * 查询玩家私人房游戏记录列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<PrivateGameRecordBO> listPlayerPrivateGameByQry(PlayerAccountDto dto) {
        return privateGameRecordEntityMapper.listPlayerPrivateGameByQry(dto);
    }

    /**
     * 查询玩家私人房游戏记录总数
     *
     * @param dto
     * @return
     */
    @Override
    public int totalPlayerPrivateGameByQry(PlayerAccountDto dto) {
        return privateGameRecordEntityMapper.totalPlayerPrivateGameByQry(dto);
    }

    /**
     * 查询相同IP地址的玩家列表
     *
     * @param playerId
     * @return
     */
    @Override
    public List<PlayerAccountBO> listSameAddressPlayerById(Integer playerId) {
        return logProcedureMapper.listSameAddressPlayerById(playerId);
    }

    /**
     * 查询玩家财富排行列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<PlayerInfoBO> listPlayerWealthRankByQry(PlayerAccountDto dto) {
        return playerInfoEntityMapper.listPlayerWealthRankByQry(dto);
    }

    /**
     * 查询玩家财富排行列表总数
     *
     * @param dto
     * @return
     */
    @Override
    public int totalPlayerWealthRankByQry(PlayerAccountDto dto) {
        return playerInfoEntityMapper.totalPlayerWealthRankByQry(dto);
    }

    /**
     * 给指定玩家划账
     *
     * @param dto
     * @return
     */
    @Override
    public int remitGold(RemitDto dto) {
        log.info(gameUrl + "/add_gold?reason=99&user_id=" + dto.getPlayerId()
                + "&manager_id=" + dto.getManagerId() + "&gold=" + dto.getRemitGold() * 100);
        String result = HttpRequestUtil.sendGet(gameUrl + "/add_gold?reason=99&user_id=" + dto.getPlayerId()
                + "&manager_id=" + dto.getManagerId() + "&gold=" + dto.getRemitGold() * 100);
        log.info("result: " + result);
        if (CommonUtil.ValidateSuccess(result)) {
            return 1;
        }
        return 0;
    }

    /**
     * 给指定玩家划账
     *
     * @param dto
     * @return
     */
    @Override
    public int remitDiamond(RemitDto dto) {
        log.info(gameUrl + "/add_diamond?user_id=" + dto.getPlayerId()
                + "&manager_id=" + dto.getManagerId() + "&diamond=" + dto.getRemitDiamond());
        String result = HttpRequestUtil.sendGet(gameUrl + "/add_diamond?user_id=" + dto.getPlayerId()
                + "&manager_id=" + dto.getManagerId() + "&diamond=" + dto.getRemitDiamond());
        log.info("result: " + result);
        if (CommonUtil.ValidateSuccess(result)) {
            // TODO 人工划账提现逻辑

            return 1;
        }
        return 0;
    }

    /**
     * 查询划账日志列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<RemitGoldRecordBO> listRemitGoldRecordByQry(PlayerLogDto dto) {
        return remitGoldRecordEntityMapper.listRemitGoldRecordByQry(dto);
    }

    /**
     * 查询划账日志列表
     *
     * @param dto
     * @return
     */
    @Override
    public ResponseBO listRemitGoldRecordByQryPage(PlayerLogDto dto) {
        Page page = dto.getPage();
        List<RemitGoldRecordBO> remitGoldRecordBOS = remitGoldRecordEntityMapper.listRemitGoldRecordByQryPage(page, dto);
        return ResponseBO.page(page).setData(remitGoldRecordBOS);
    }

    @Override
    public ResponseBO listRemitGoldRecordOnlineByQryPage(PlayerLogDto dto) {
        Page page = dto.getPage();
        List<Long> longs = tblAccountService.onlineUserIds();
        dto.setOnlineUserIds(longs);
        List<RemitGoldRecordBO> remitGoldRecordBOS = remitGoldRecordEntityMapper.listRemitGoldRecordByQryPage(page, dto);
        return ResponseBO.page(page).setData(remitGoldRecordBOS);
    }


    /**
     * 查询划账日志列表总数
     *
     * @param dto
     * @return
     */
    @Override
    public int totalRemitGoldRecordByQry(PlayerLogDto dto) {
        return remitGoldRecordEntityMapper.totalRemitGoldRecordByQry(dto);
    }

    /**
     * 获取划账总额
     *
     * @param dto
     * @return
     */
    @Override
    public long getTotalRemitGoldByQry(PlayerLogDto dto) {
        if (dto.isOnline()) {
            List<Long> longs = tblAccountService.onlineUserIds();
            dto.setOnlineUserIds(longs);
        }
        return remitGoldRecordEntityMapper.getTotalRemitGoldByQry(dto);
    }

    /**
     * 查询划账日志列表(钻石)
     *
     * @param dto
     * @return
     */
    @Override
    public List<RemitDiamondRecordBO> listRemitDiamondRecordByQry(PlayerLogDto dto) {
        return remitDiamondRecordEntityMapper.listRemitDiamondRecordByQry(dto);
    }

    /**
     * 查询划账日志列表总数(钻石)
     *
     * @param dto
     * @return
     */
    @Override
    public int totalRemitDiamondRecordByQry(PlayerLogDto dto) {
        return remitDiamondRecordEntityMapper.totalRemitDiamondRecordByQry(dto);
    }

    /**
     * 获取划账总额(钻石)
     *
     * @param dto
     * @return
     */
    @Override
    public int getTotalRemitDiamondByQry(PlayerLogDto dto) {
        return remitDiamondRecordEntityMapper.getTotalRemitDiamondByQry(dto);
    }

    /**
     * 创建玩家提现订单
     *
     * @param bo
     * @return
     */
    @Override
    @Transactional("zdmanageTransactionManager")
    public int createWithdrawOrder(WithdrawOrderBO bo) {
        WithdrawOrderEntity entity = new WithdrawOrderEntity();
        try {
            PropertyUtils.copyProperties(entity, bo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        // 获取税率配置项
        SysConfigsBO configsBO = sysConfigsEntityMapper.getSysConfigsByQry(Constant.SYS_CONFIGS_WITHDRAW_RATIO);
        Map<String, Double> configsMap = JSON.parseObject(configsBO.getContent(), new TypeReference<Map<String, Double>>(){});
        entity.setTax((int)(bo.getAmount() * configsMap.get(Constant.SYS_CONFIGS_WITHDRAW_RATIO)));
        entity.setState(Constant.WITHDRAW_STATE_DEFAULT);
        entity.setCreateTime(new Date());
        int count = withdrawOrderEntityMapper.insertSelective(entity);
//        if (count > 0) {
//            String url = gameUrl + "/exchange?userid=" + bo.getUserid()
//                    + "&value=" + bo.getAmount();
//            log.info("url:" + url);
//            String result = HttpRequestUtil.sendGet(url);
//            log.info("result: " + result);
//            if (CommonUtil.ValidateSuccess(result)) {
//                return 1;
//            } else {
//                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//                return 0;
//            }
//        }
        return count;
    }

    /**
     * 查询玩家提现列表
     *
     * @param dto
     * @return
     */
    @Override
    public List<WithdrawOrderBO> listWithdrawOrderByQry(BusinessDto dto) {
        return withdrawOrderEntityMapper.listWithdrawOrderByQry(dto);
    }

    /**
     * 查询玩家提现总数
     *
     * @param dto
     * @return
     */
    @Override
    public int totalWithdrawOrderbyQry(BusinessDto dto) {
        return withdrawOrderEntityMapper.totalWithdrawOrderbyQry(dto);
    }

    /**
     * 更新玩家订单
     *
     * @param bo
     * @return
     */
    @Override
    @Transactional("zdmanageTransactionManager")
    public int updateWithdrawOrder(WithdrawOrderBO bo) {
        WithdrawOrderEntity entity = new WithdrawOrderEntity();
        try {
            PropertyUtils.copyProperties(entity, bo);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        if (bo.getState() == Constant.WITHDRAW_STATE_COMPLETE) {
            entity.setCompletetime(new Date());
        }
        int result = withdrawOrderEntityMapper.updateByPrimaryKeySelective(entity);
        if (result > 0) {
            WithdrawOrderEntity orderEntity = withdrawOrderEntityMapper.selectByPrimaryKey(entity.getId());
            if (bo.getState() == Constant.WITHDRAW_STATE_CANCEL) {
                // 退还金币
                String resp = HttpRequestUtil.sendGet(gameUrl + "/add_gold?reason=5&note="+ bo.getNote() +"&user_id=" + bo.getUserid()
                        + "&manager_id=0&gold=" + bo.getAmount());
                if (!CommonUtil.ValidateSuccess(resp)) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return 0;
                }
            }

            // 推送钉钉
            String pushMsg = "";
            switch (bo.getState()) {
                case 1:
                    pushMsg = "会员：" + orderEntity.getRealname() + " 提现已经审核通过进入发放环节啦，快去看看吧";
                    break;
                case 2:
                    pushMsg = "会员：" + orderEntity.getRealname() + " 提现已经发放(手动)完成啦！";
                    break;
                case 3:
                    pushMsg = "会员：" + orderEntity.getRealname() + " 提现审核失败，原因：" + bo.getNote();
                    break;
                default:
                    break;
            }

            this.pushDingTalk(pushMsg);
        }
        return result;
    }

    /**
     * 验证玩家是否充值
     *
     * @param showId
     * @return
     */
    @Override
    public int validateUserRechargeState(Long showId) {
        // 玩家充值订单
        PlayerOrderDto orderDto = new PlayerOrderDto();
        orderDto.setShowId(showId);
        orderDto.setState(3);
        int orderCount = orderEntityMapper.totalCountPlayerOrderByQry(orderDto);
        // 玩家划账
        PlayerLogDto logDto = new PlayerLogDto();
        logDto.setShowId(showId);
        int count = remitGoldRecordEntityMapper.totalRemitGoldRecordByQry(logDto);
        if (count > 0 || orderCount > 0) {
            return 1;
        }
        return 0;
    }

    /**
     * 查询玩家输赢明细
     *
     * @param dto
     * @return
     */
    @Override
    public List<GameWinningDetailBO> listUserGameDetailByQry(PlayerLogDto dto) {
        Integer maxId = gameGoldEntityMapper.getGameRecordMaxId(dto.getEndTime());
        Integer minId = gameGoldEntityMapper.getGameRecordMinId(dto.getStartTime());
        dto.setStartId(minId);
        dto.setEndId(maxId);
        return gameGoldEntityMapper.listUserGameDetailByQry(dto);
    }



    /**
     * 查询玩家输赢明细总数
     *
     * @param dto
     * @return
     */
    @Override
    public int totalUserGameDetailByQry(PlayerLogDto dto) {
        Integer maxId = gameGoldEntityMapper.getGameRecordMaxId(dto.getEndTime());
        Integer minId = gameGoldEntityMapper.getGameRecordMinId(dto.getStartTime());
        dto.setStartId(minId);
        dto.setEndId(maxId);
        return gameGoldEntityMapper.totalUserGameDetailByQry(dto);
    }

    @Override
    public PlayersWinLoseVO  playersWinLose(PlayerLogDto dto) {
        return gameGoldEntityMapper.playersWinLose(dto);
    }

    /**
     * 推送钉钉消息
     * @param msg
     */
    private int pushDingTalk(String msg) {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("content", msg);
        SysConfigsBO configsBO = sysConfigsEntityMapper.getSysConfigsByQry(Constant.SYS_CONFIGS_DING_TALK_GROUP);
        if (configsBO != null) {
            Map<String, String> configsMap = JSON.parseObject(configsBO.getContent(), new TypeReference<Map<String, String>>(){});
            String webhook = configsMap.get("webhook");
            if (!CommonUtil.isEmptyStr(webhook)) {
                Map<String, Object> pushData = new HashMap<>();
                pushData.put("msgtype", "text");
                pushData.put("text", dataMap);
                String result = HttpRequestUtil.sendPostJson(webhook, JSON.toJSONString(pushData));

                Map<String, String> resultMap = JSON.parseObject(result, new TypeReference<Map<String, String>>(){});
                log.info("result: " + resultMap.toString());
                if (CommonUtil.ValidateSuccess(resultMap.get("errmsg"))) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
