package com.yeung.api;

import com.bte.exchange.db.base.pager.Pager;
import com.bte.exchange.db.po.YeungClientInfo;
import com.bte.exchange.db.po.YeungClientNote;
import com.bte.exchange.db.po.YeungClientRepair;
import com.bte.exchange.db.po.YeungClientRepairGroup;
import com.bte.exchange.db.service.YeungClientInfoService;
import com.bte.exchange.db.service.YeungClientNoteService;
import com.bte.exchange.db.service.YeungClientRepairService;
import com.yeung.bean.ResponseData;
import com.yeung.bean.StatusCode;
import com.yeung.utils.JsonUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("yeung/client")
public class YeungApi extends BaseApi{
    @Autowired
    YeungClientInfoService yeungClientInfoService;

    @Autowired
    YeungClientRepairService yeungClientRepairService;

    @Autowired
    YeungClientNoteService yeungClientNoteService;

    //获取所有客户
    @GetMapping("/getAll")
    public Object infos(int type, String keyword, int pageNo, int pageSize) {
        Map<String, Object> condition = new HashMap<>();

        if(type == 1)
            condition.put("email",keyword);
        if(type == 2)
            condition.put("cell",keyword);

        condition.put("sortColumns", "id desc");

        Pager<YeungClientInfo> yeungClientInfoPager = yeungClientInfoService.findPageList(pageNo, pageSize, condition);
        Map<String, Object> result = new HashMap<>();
        result.put("list", CollectionUtils.isEmpty(yeungClientInfoPager.getList()) ? new ArrayList<>() : yeungClientInfoPager.getList());
        result.put("nextPage", CollectionUtils.isEmpty(yeungClientInfoPager.getList()) ? -1 : yeungClientInfoPager.getCurrePageNumber() < yeungClientInfoPager.getPageCount() ? yeungClientInfoPager.getCurrePageNumber() : -1);

        return result;
    }

    //添加一个客户
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseData add(HttpServletRequest request, HttpServletResponse response, @RequestBody String req) {
        YeungClientInfo yeungClientInfo = JsonUtils.jsonToObject(req,YeungClientInfo.class);

        if(null == yeungClientInfo) return ResponseData.getInstance(StatusCode.FAILURE);
        yeungClientInfo.setCreateTime(new Date());
        yeungClientInfo.setIsEnable(1);
        yeungClientInfoService.save(yeungClientInfo);

        return ResponseData.getInstance(StatusCode.SUCCESS);
    }

    //更新
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseData update(HttpServletRequest request, HttpServletResponse response, @RequestBody String req) {
        YeungClientInfo yeungClientInfo = JsonUtils.jsonToObject(req,YeungClientInfo.class);
        if(null == yeungClientInfo) return ResponseData.getInstance(StatusCode.FAILURE);
        yeungClientInfoService.update(yeungClientInfo);

        return ResponseData.getInstance(StatusCode.SUCCESS);
    }

    //获取某个客户repair记录，按天分组
    @GetMapping("/repair/getGroup")
    public ResponseData repairsGroup(String clientId) {
        if(StringUtils.isBlank(clientId)) return null;

        List<YeungClientRepairGroup> yeungClientRepairGroups = yeungClientRepairService.findClientRepairByGroup
                (Integer.valueOf(clientId));

        YeungClientInfo yeungClientInfo = yeungClientInfoService.getByID(clientId);
        Map<String, Object> result = new HashMap<>();
        result.put("list", yeungClientRepairGroups);
        result.put("client", yeungClientInfo);

        return ResponseData.getInstance(StatusCode.SUCCESS, result);
    }

    //获取某个客户的所有repair记录
    @GetMapping("/repair/getAll")
    public Object repairs(String clientId, int pageNo, int pageSize) {
        if(StringUtils.isBlank(clientId)) return null;
        Map<String, Object> condition = new HashMap<>();

        condition.put("clientId",clientId);
        Pager<YeungClientRepair> yeungClientRepairPager = yeungClientRepairService.findPageList(pageNo,pageSize,condition);

        BigDecimal total = new BigDecimal(0);
        for(YeungClientRepair y:yeungClientRepairPager.getList()){
            total = total.add(y.getPrice());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("list", CollectionUtils.isEmpty(yeungClientRepairPager.getList()) ? new ArrayList<>() : yeungClientRepairPager.getList());
        result.put("nextPage", CollectionUtils.isEmpty(yeungClientRepairPager.getList()) ? -1 : yeungClientRepairPager.getCurrePageNumber() < yeungClientRepairPager.getPageCount() ? yeungClientRepairPager.getCurrePageNumber() : -1);
        result.put("totalPrice", total);

        return result;
    }

    //按ids获取repair记录
    @GetMapping("/repair/getByIds")
    public ResponseData repairsByIds(String ids) {
        if(StringUtils.isBlank(ids)) return ResponseData.getInstance(StatusCode.REQUEST_PARAMETER_ERROR);

        List<Integer> idsList = new ArrayList<>();
        for(String s : ids.split(",")){
            idsList.add(Integer.valueOf(s));
        }

        List<YeungClientRepair> yeungClientRepairs = yeungClientRepairService.findClientRepairByIds(idsList);

        return ResponseData.getInstance(StatusCode.SUCCESS,yeungClientRepairs);
    }

    @GetMapping("/repair/getByTime")
    public ResponseData repairsByTime(String clientId,Long timestamp) {
        if(StringUtils.isBlank(clientId) || timestamp==null ) return ResponseData.getInstance(StatusCode
                .REQUEST_PARAMETER_ERROR);

        if(timestamp ==0){
            Calendar c = Calendar.getInstance();
            Date d = new Date();
            c.setTimeInMillis(d.getTime());
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);
            timestamp = c.getTimeInMillis();
        }

        Map<String, Object> condition = new HashMap<>();
        condition.put("timestamp",timestamp);
        condition.put("clientId",clientId);

        List<YeungClientRepair> yeungClientRepairs = yeungClientRepairService.findListByCondition(condition);

        return ResponseData.getInstance(StatusCode.SUCCESS,yeungClientRepairs);
    }

    //添加某个客户的repairs记录
    @RequestMapping(value = "/repair/add", method = RequestMethod.POST)
    public ResponseData repairAdd(HttpServletRequest request, HttpServletResponse response, @RequestBody String req) {
        List<YeungClientRepair> yeungClientRepairs = JsonUtils.jsonToList(req, YeungClientRepair.class);
        if(CollectionUtils.isEmpty(yeungClientRepairs))
            return ResponseData.getInstance(StatusCode.REQUEST_PARAMETER_ERROR);

        Calendar c = Calendar.getInstance();
        Date d = new Date();
        c.setTimeInMillis(d.getTime());
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        for(YeungClientRepair y: yeungClientRepairs) {
            y.setTimestamp(c.getTimeInMillis());
            y.setCreateTime(d);
            y.setIsEnable(1);

            yeungClientRepairService.save(y);
        }

        Map<String, Object> condition = new HashMap<>();
        condition.put("clientId",yeungClientRepairs.get(0).getClientId());
        condition.put("timestamp",c.getTimeInMillis());

        YeungClientNote yeungClientNoteCheck = yeungClientNoteService.getByCondition(condition);

        if(yeungClientNoteCheck == null) {
            String str_date = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
            YeungClientNote yeungClientNote = new YeungClientNote();
            yeungClientNote.setClientId(yeungClientRepairs.get(0).getClientId());
            yeungClientNote.setCreateDate(str_date);
            yeungClientNote.setTimestamp(c.getTimeInMillis());
            yeungClientNote.setCreateTime(d);
            yeungClientNote.setIsEnable(1);

            yeungClientNoteService.save(yeungClientNote);
        }

        return ResponseData.getInstance(StatusCode.SUCCESS);
    }

    //修改某个客户的repair记录
    @RequestMapping(value = "/repair/update", method = RequestMethod.POST)
    public ResponseData updateRepair(HttpServletRequest request, HttpServletResponse response, @RequestBody Map<String,Object> req){
        if(null == req.get("repairId")) return ResponseData.getInstance(StatusCode.FAILURE);

        YeungClientRepair yeungClientRepair = new YeungClientRepair();
        yeungClientRepair.setId(Integer.valueOf(req.get("repairId").toString()));
        yeungClientRepair.setDescription(req.get("description").toString());
        yeungClientRepair.setPrice(new BigDecimal(req.get("price").toString()));

        yeungClientRepairService.update(yeungClientRepair);

        return ResponseData.getInstance(StatusCode.SUCCESS);

    }

    @RequestMapping(value = "/note/save", method = RequestMethod.POST)
    public ResponseData noteSave(HttpServletRequest request, HttpServletResponse response, @RequestBody String req){
        YeungClientNote yeungClientNote = JsonUtils.jsonToObject(req,YeungClientNote.class);

        if(null == yeungClientNote) return ResponseData.getInstance(StatusCode.FAILURE);
        if(yeungClientNote.getDeposit() == null)yeungClientNote.setDeposit(new BigDecimal(0.00));

        yeungClientNoteService.update(yeungClientNote);

        return ResponseData.getInstance(StatusCode.SUCCESS);
    }

    @GetMapping("/note/getByClientId")
    public ResponseData noteGetByClientId(String clientId,int pageNo, int pageSize){
        if(StringUtils.isBlank(clientId)) return ResponseData.getInstance(StatusCode.REQUEST_PARAMETER_ERROR);

        Map<String, Object> condition = new HashMap<>();

        condition.put("clientId",clientId);
        condition.put("sortColumns", "id desc");
        Pager<YeungClientNote> yeungClientNotePager = yeungClientNoteService.findPageList(pageNo,pageSize,condition);

        List<YeungClientRepairGroup> yeungClientRepairGroups = yeungClientRepairService.findClientRepairTotalPrice
                (Integer
                        .valueOf(clientId));

        Map<Long, BigDecimal> map = new HashMap<>();
        for (YeungClientRepairGroup y:yeungClientRepairGroups){
            map.put(y.getTimestamp(),y.getTotalPrice());
        }

        for(YeungClientNote y:yeungClientNotePager.getList()){
            BigDecimal totalPrice = (BigDecimal) MapUtils.getObject(map, y.getTimestamp(),new BigDecimal(0));
            y.setTotalPrice(totalPrice);
            y.setTotalPriceIncludeTax(totalPrice.multiply(new BigDecimal(1.13)).setScale(2, BigDecimal.ROUND_HALF_UP));
        }

        YeungClientInfo yeungClientInfo = yeungClientInfoService.getByID(clientId);

        Map<String, Object> result = new HashMap<>();
        result.put("list", CollectionUtils.isEmpty(yeungClientNotePager.getList()) ? new ArrayList<>() : yeungClientNotePager.getList());
        result.put("nextPage", CollectionUtils.isEmpty(yeungClientNotePager.getList()) ? -1 : yeungClientNotePager.getCurrePageNumber() < yeungClientNotePager.getPageCount() ? yeungClientNotePager.getCurrePageNumber() : -1);

        Map<String, Object> res = new HashMap<>();
        res.put("list", result);
        res.put("client", yeungClientInfo);

        return ResponseData.getInstance(StatusCode.SUCCESS, res);
    }

    //删除某个客户的repair记录
    @GetMapping("/repair/del")
    public ResponseData delRepair(String repairId){
        if(StringUtils.isBlank(repairId)) return ResponseData.getInstance(StatusCode.FAILURE);

        yeungClientRepairService.delete(repairId);
        return ResponseData.getInstance(StatusCode.SUCCESS);
    }

    @GetMapping("/bill/del")
    public ResponseData delBill(String clientId,Long timestamp,String pw){
        if (StringUtils.isBlank(clientId) || StringUtils.isBlank(pw) || timestamp == null) return ResponseData
                .getInstance(StatusCode.REQUEST_PARAMETER_ERROR);


        if(pw.equals("yeung_zhang")){
            yeungClientNoteService.updateByTime(Integer.valueOf(clientId),timestamp);
            yeungClientRepairService.updateByTime(Integer.valueOf(clientId),timestamp);
        }

        return ResponseData.getInstance(StatusCode.SUCCESS);
    }

    @GetMapping("/test/issue")
    public ResponseData issue() {
        return ResponseData.getInstance(StatusCode.SUCCESS);
    }

    @GetMapping("/test/get")
    public ResponseData test() {
        return ResponseData.getInstance(StatusCode.SUCCESS);
    }

    @GetMapping("/test/get2")
    public ResponseData test2() {
        return ResponseData.getInstance(StatusCode.SUCCESS);
    }

    @GetMapping("/test/gitchangeuser")
    public ResponseData gitchangeuser() {
        String s = "iussue";
        return ResponseData.getInstance(StatusCode.SUCCESS);
    }

    @GetMapping("/test/get3")
    public ResponseData test3() {
        int i = 0;
        return ResponseData.getInstance(StatusCode.SUCCESS);
    }

}
