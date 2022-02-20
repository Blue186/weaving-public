package com.wen.weaving.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wen.weaving.entity.*;
import com.wen.weaving.entity.vo.CollectionColl.*;
import com.wen.weaving.service.*;
import com.wen.weaving.utils.HttpUtil;
import com.wen.weaving.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Api(tags = {"收藏相关接口"})
@RestController
@RequestMapping("/collection")
public class CollectionController {
    /**
     * 包括ColorList,ColorListImgs,FocusCard,LgList,XsList,XsListImgs在内
     * type设计，FocusCard 1,LgList 2,ColorList 3,XsList 4
     */
    @Resource
    private ColorListService colorListService;
    @Resource
    private ColorListImgsService colorListImgsService;
    @Resource
    private FocusCardService focusCardService;
    @Resource
    private LgListService lgListService;
    @Resource
    private XsListService xsListService;
    @Resource
    private XsListImgsService xsListImgsService;
    @Resource
    private HttpUtil httpUtil;
    @Resource
    private FocusCollService focusCollService;
    @Resource
    private MaterialCollService materialCollService;

    private static final String url_color_list = "https:";
    private static final String url_focus_list = "https:";
    private static final String url_lg_list = "https:";
    private static final String url_xs_list = "https:";

    @ApiOperation(value = "用户更新所有的数据库信息，从阿里云到数据库")
    @PostMapping("/updateAll")
    public R<?> updateAllCollInfos(){
        String colorListJson = httpUtil.doGet(url_color_list);
        JSONObject json_colorList = JSONObject.fromObject(colorListJson);
        JSONArray message_color = json_colorList.getJSONArray("message");
        for (Object o : message_color) {
            JSONObject colorJson = JSONObject.fromObject(o);
            ColorList colorList = new ColorList();
            colorList.setId(colorJson.getInt("name_id"));
            colorList.setSkImg(colorJson.getString("sk_img"));
            colorList.setSkName(colorJson.getString("sk_name"));
            JSONArray lists = colorJson.getJSONArray("List");
            ColorList colorList_R = colorListService.getBaseMapper().selectById(colorJson.getInt("name_id"));
            if (colorList_R==null){
                colorListService.getBaseMapper().insert(colorList);//这里将colorList插入到数据库
                for (Object list : lists) {
                    JSONObject list_img = JSONObject.fromObject(list);
                    ColorListImgs colorListImgs = new ColorListImgs();
                    colorListImgs.setId(list_img.getInt("id"));
                    colorListImgs.setColorId(colorJson.getInt("name_id"));
                    colorListImgs.setImg(list_img.getString("img"));
                    colorListImgs.setStatus(list_img.getInt("status"));
                    colorListImgs.setType(3);
                    colorListImgsService.getBaseMapper().insert(colorListImgs);//这里将对应的图片插入数据库
                }
            }else {
                colorListService.getBaseMapper().updateById(colorList);
                Set<Integer> set = new HashSet<>();
                QueryWrapper<ColorListImgs> wrapper = new QueryWrapper<>();
                wrapper.eq("color_id",colorJson.getInt("name_id"));
                List<ColorListImgs> colorListImgs_R = colorListImgsService.getBaseMapper().selectList(wrapper);
                for (ColorListImgs colorListImgs : colorListImgs_R) {
                    set.add(colorListImgs.getId());
                }//这里将数据库中对应color图片的id都添加进去
                for (Object list : lists) {
                    JSONObject list_img = JSONObject.fromObject(list);
                    int id = list_img.getInt("id");
                    QueryWrapper<ColorListImgs> select = new QueryWrapper<>();
                    select.eq("id",id);
                    select.eq("color_id",colorJson.getInt("name_id"));
                    ColorListImgs img = colorListImgsService.getBaseMapper().selectOne(select);
                    if (img==null){
                        ColorListImgs colorListImgs = new ColorListImgs();
                        colorListImgs.setId(list_img.getInt("id"));
                        colorListImgs.setColorId(colorJson.getInt("name_id"));
                        colorListImgs.setImg(list_img.getString("img"));
                        colorListImgs.setStatus(list_img.getInt("status"));
                        colorListImgs.setType(3);
                        colorListImgsService.getBaseMapper().insert(colorListImgs);
                    }else {
                        set.remove(id);
                        img.setId(id);
                        img.setImg(list_img.getString("img"));
                        img.setColorId(colorJson.getInt("name_id"));
                        img.setStatus(list_img.getInt("status"));
                        img.setType(3);
                        colorListImgsService.getBaseMapper().update(img,select);//这里将对应的图片插入数据库
                    }
                }
                for (Integer integer : set) {
                    QueryWrapper<ColorListImgs> delete = new QueryWrapper<>();
                    delete.eq("id",integer);
                    delete.eq("color_id",colorJson.getInt("name_id"));
                    colorListImgsService.getBaseMapper().delete(wrapper);
                }
            }

        }

        String focusListJson = httpUtil.doGet(url_focus_list);
        JSONObject json_focusList = JSONObject.fromObject(focusListJson);
        JSONArray message_focus = json_focusList.getJSONArray("message");
        for (Object messageFocus : message_focus) {
            JSONObject focusList = JSONObject.fromObject(messageFocus);
            FocusCard focusCard = new FocusCard();
            focusCard.setId(focusList.getInt("id"));
            focusCard.setStatus(focusList.getInt("status"));
            focusCard.setCap(focusList.getString("cap"));
            focusCard.setBgUrl(focusList.getString("bg_url"));
            focusCard.setLink(focusList.getString("link"));
            focusCard.setType(1);
            FocusCard focusCard_R = focusCardService.getBaseMapper().selectById(focusList.getInt("id"));
            if (focusCard_R==null){
                focusCardService.getBaseMapper().insert(focusCard);//这里插入focusList相应信息到数据库中
            }else {
                focusCardService.getBaseMapper().updateById(focusCard);
            }
        }

        String lgListJson = httpUtil.doGet(url_lg_list);
        JSONObject json_lgList = JSONObject.fromObject(lgListJson);
        JSONArray message_lgList = json_lgList.getJSONArray("message");
        for (Object messageLgList : message_lgList) {
            JSONObject m_LgList = JSONObject.fromObject(messageLgList);
            LgList lgList = new LgList();
            lgList.setId(m_LgList.getInt("id"));
            lgList.setStatus(m_LgList.getInt("status"));
            lgList.setLgImg(m_LgList.getString("lg_img"));
            lgList.setLgCap(m_LgList.getString("lg_cap"));
            lgList.setLgDes(m_LgList.getString("lg_des"));
            lgList.setLink(m_LgList.getString("link"));
            lgList.setType(2);
            LgList lgList_R = lgListService.getBaseMapper().selectById(m_LgList.getInt("id"));
            if (lgList_R==null){
                lgListService.getBaseMapper().insert(lgList);//如果不存在就插入
            }else {
                lgListService.getBaseMapper().updateById(lgList);
            }
        }

        String xsListJson = httpUtil.doGet(url_xs_list);
        JSONObject json_xsList = JSONObject.fromObject(xsListJson);
        JSONArray message_xsList = json_xsList.getJSONArray("message");
        for (Object messageXsList : message_xsList) {
            JSONObject jsonXsList = JSONObject.fromObject(messageXsList);
            XsList xsList = new XsList();
            xsList.setId(jsonXsList.getInt("name_id"));
            xsList.setXsImg(jsonXsList.getString("xs_img"));
            xsList.setXsName(jsonXsList.getString("xs_name"));
            JSONArray lists = jsonXsList.getJSONArray("List");
            XsList xsList_R = xsListService.getBaseMapper().selectById(jsonXsList.getInt("name_id"));
            if (xsList_R==null){
                xsListService.getBaseMapper().insert(xsList);//插入xsList信息
                for (Object list : lists) {
                    JSONObject jsonList = JSONObject.fromObject(list);
                    XsListImgs xsListImgs = new XsListImgs();
                    xsListImgs.setId(jsonList.getInt("id"));
                    xsListImgs.setXsId(jsonXsList.getInt("name_id"));
                    xsListImgs.setStatus(jsonList.getInt("status"));
                    xsListImgs.setImg(jsonList.getString("img"));
                    xsListImgs.setType(4);
                    xsListImgsService.getBaseMapper().insert(xsListImgs);//插入图片信息
                }
            }else {
                xsListService.getBaseMapper().updateById(xsList);
                Set<Integer> set = new HashSet<>();
                QueryWrapper<XsListImgs> wrapper = new QueryWrapper<>();
                wrapper.eq("xs_id",jsonXsList.getInt("name_id"));
                List<XsListImgs> xsListImgs_R = xsListImgsService.getBaseMapper().selectList(wrapper);
                for (XsListImgs xsListImgs : xsListImgs_R) {
                    set.add(xsListImgs.getId());
                }//这里先将数据库中图片的id全部添加到set集合中
                for (Object list : lists) {
                    JSONObject jsonList = JSONObject.fromObject(list);
                    int id = jsonList.getInt("id");
                    QueryWrapper<XsListImgs> select = new QueryWrapper<>();
                    select.eq("id",id);
                    select.eq("xs_id",jsonXsList.getInt("name_id"));
                    XsListImgs xsListImgs = xsListImgsService.getBaseMapper().selectOne(select);//去数据库中查是否存在该id
                    if (xsListImgs==null){
                        XsListImgs xsListImgs1 = new XsListImgs();
                        xsListImgs1.setId(jsonList.getInt("id"));
                        xsListImgs1.setXsId(jsonXsList.getInt("name_id"));
                        xsListImgs1.setStatus(jsonList.getInt("status"));
                        xsListImgs1.setImg(jsonList.getString("img"));
                        xsListImgs1.setType(4);
                        xsListImgsService.getBaseMapper().insert(xsListImgs1);//如果这张图片是新的就插入图片信息
                    }else {
                        set.remove(id);//如果这id已存在在数据库中就从set中移除
                        xsListImgs.setId(jsonList.getInt("id"));
                        xsListImgs.setXsId(jsonXsList.getInt("name_id"));
                        xsListImgs.setStatus(jsonList.getInt("status"));
                        xsListImgs.setImg(jsonList.getString("img"));
                        xsListImgs.setType(4);
                        xsListImgsService.getBaseMapper().update(xsListImgs,select);//存在就更新该图片信息
                    }
                }
//                剩下的就是需要删除的内容
                for (Integer integer : set) {
                    QueryWrapper<XsListImgs> delete = new QueryWrapper<>();
                    delete.eq("id",integer);
                    delete.eq("xs_id",jsonXsList.getInt("name_id"));
                    xsListImgsService.getBaseMapper().delete(wrapper);
                }

            }
        }

        return R.ok();
    }

    @ApiOperation(value = "获取colorList信息")
    @GetMapping("/colorList/{uid}")
    public R<?> getColorList(@PathVariable Integer uid){
        QueryWrapper<ColorList> wrapper_color = new QueryWrapper<>();
        wrapper_color.orderByAsc("id");
        List<ColorList> colorLists = colorListService.getBaseMapper().selectList(wrapper_color);
        List<ColorListInfo> allColorList = colorListService.getAllColorList(colorLists,uid);
        System.out.println("调用了");
        return R.ok().data(allColorList);
    }

    @ApiOperation(value = "获取FocusCard信息")
    @GetMapping("/focusCard/{uid}")
    public R<?> getFocusCard(@PathVariable Integer uid){
        QueryWrapper<FocusCard> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("id");
        List<FocusCard> focusCards = focusCardService.getBaseMapper().selectList(wrapper);
        FocusCardInfo allFocusCard = focusCardService.getAllFocusCard(focusCards,uid);
        return R.ok().data(allFocusCard);
    }

    @ApiOperation(value = "获取lgList信息")
    @GetMapping("/lgList/{uid}")
    public R<?> getLgList(@PathVariable Integer uid){
        QueryWrapper<LgList> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("id");
        List<LgList> lgLists = lgListService.getBaseMapper().selectList(wrapper);
        LgListInfo allLgList = lgListService.getAllLgList(lgLists,uid);
        return R.ok().data(allLgList);
    }

    @ApiOperation(value = "获取xsList信息")
    @GetMapping("/xsList/{uid}")
    public R<?> getXsList(@PathVariable Integer uid){
        QueryWrapper<XsList> wrapper = new QueryWrapper<>();
        wrapper.orderByAsc("id");
        List<XsList> xsLists = xsListService.getBaseMapper().selectList(wrapper);
        List<XsListInfo> allXsList = xsListService.getAllXsList(xsLists,uid);
        return R.ok().data(allXsList);
    }
    @ApiOperation(value = "收藏接口")
    @PostMapping("/collection")
    public R<?> putCollection(@ApiParam(name = "collR",value = "收藏时传送的信息",required = true)@RequestBody CollR collR){
        Integer type = collR.getType();
        Integer id = collR.getId();
        Integer uid = collR.getUid();
        Integer nameId = collR.getName_id();
        if (uid==-1){
            return R.error().code(101).message("用户未登录，请先登录。");
        }
        if (id==null){
            return R.error().message("id为空");
        }
        if (type==null){
            return R.error().message("type为空");
        }
        if (type==1){//focus
            FocusCard focusCard = focusCardService.getBaseMapper().selectById(id);
            FocusColl focusColl_F = new FocusColl();
            System.out.println(focusCard+"..........");
            focusColl_F.setUid(uid);
            focusColl_F.setStatus(1);
            focusColl_F.setFlId(id);
            focusColl_F.setType(focusCard.getType());
            focusCollService.getBaseMapper().insert(focusColl_F);
        }
        if (type==2){//lg
            LgList lgList = lgListService.getBaseMapper().selectById(id);
            FocusColl focusColl_Lg = new FocusColl();
            System.out.println(lgList+"..........");
            focusColl_Lg.setUid(uid);
            focusColl_Lg.setStatus(1);
            focusColl_Lg.setFlId(id);
            focusColl_Lg.setType(lgList.getType());
            focusCollService.getBaseMapper().insert(focusColl_Lg);
        }
        if (type==3){//colorList
            QueryWrapper<ColorListImgs> wrapper = new QueryWrapper<>();
            wrapper.eq("id",id);
            wrapper.eq("color_id",nameId);
            ColorListImgs colorListImgs = colorListImgsService.getBaseMapper().selectOne(wrapper);
            MaterialColl materialColl_C = new MaterialColl();
            materialColl_C.setUid(uid);
            materialColl_C.setNameId(nameId);
            materialColl_C.setCxId(id);
            materialColl_C.setType(colorListImgs.getType());
            materialColl_C.setStatus(1);
            materialCollService.getBaseMapper().insert(materialColl_C);
        }
        if (type==4){//xsList
            QueryWrapper<XsListImgs> wrapper = new QueryWrapper<>();
            wrapper.eq("id",id);
            wrapper.eq("xs_id",nameId);
            XsListImgs xsListImgs = xsListImgsService.getBaseMapper().selectOne(wrapper);
            MaterialColl materialColl_X = new MaterialColl();
            materialColl_X.setCxId(id);
            materialColl_X.setNameId(nameId);
            materialColl_X.setUid(uid);
            materialColl_X.setType(xsListImgs.getType());
            materialColl_X.setStatus(1);
            materialCollService.getBaseMapper().insert(materialColl_X);
        }
        return R.ok();
    }

    @ApiOperation(value = "取消收藏接口")
    @PostMapping("/deleteColl")
    public R<?> deleteColl(@ApiParam(name = "collR",value = "收藏信息",required = true)@RequestBody CollR collR){
        Integer type = collR.getType();
        Integer id = collR.getId();
        Integer uid = collR.getUid();
        Integer nameId = collR.getName_id();
        System.out.println("collR+++++++++"+collR);
        if (type==1||type==2){//focus,lg
            QueryWrapper<FocusColl> wrapper = new QueryWrapper<>();
            wrapper.eq("fl_id",id);
            wrapper.eq("uid",uid);
            wrapper.eq("type",type);
            focusCollService.getBaseMapper().delete(wrapper);
        }
        if (type==3||type==4){//color,xs
            QueryWrapper<MaterialColl> wrapper = new QueryWrapper<>();
            wrapper.eq("cx_id",id);
            wrapper.eq("uid",uid);
            wrapper.eq("type",type);
            wrapper.eq("name_id",nameId);
            materialCollService.getBaseMapper().delete(wrapper);
        }
        return R.ok();
    }

    @ApiOperation(value = "获取Focus中收藏的信息，每个人不同")
    @GetMapping("/focusColl/{uid}")
    public R<?> getFocusColl(@ApiParam(name = "uid",value = "用户id",required = true)@PathVariable Integer uid){
        if (uid==-1){
            return R.error().code(101).message("用户未登录，请先登录。");
        }
        QueryWrapper<FocusColl> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);
        wrapper.orderByAsc("id");
        List<FocusColl> focusColls = focusCollService.getBaseMapper().selectList(wrapper);
        List<FocusCollInfo> allFocusCollInfo = focusCollService.getAllFocusCollInfo(focusColls);
        return R.ok().data(allFocusCollInfo);
    }

    @ApiOperation(value = "获取Material中的收藏信息，每个人不同")
    @GetMapping("/materialColl/{uid}")
    public R<?> getMaterialColl(@ApiParam(name = "uid",value = "用户id",required = true)@PathVariable Integer uid){
        if (uid==-1){
            return R.error().code(101).message("用户未登录，请先登录。");
        }
        QueryWrapper<MaterialColl> wrapper = new QueryWrapper<>();
        wrapper.eq("uid",uid);
        wrapper.orderByAsc("id");
        List<MaterialColl> materialColls = materialCollService.getBaseMapper().selectList(wrapper);
        List<MaterialCollInfo> allMaterialCollInfo = materialCollService.getAllMaterialCollInfo(materialColls);
        return R.ok().data(allMaterialCollInfo);
    }
}
