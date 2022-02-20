package com.wen.weaving.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wen.weaving.entity.NoteList;
import com.wen.weaving.service.NoteListService;
import com.wen.weaving.utils.HttpUtil;
import com.wen.weaving.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = {"NoteList相关接口"},value = "NoteList相关接口")
@RestController
@RequestMapping("/noteList")
public class NoteListController {
    @Resource
    private NoteListService noteListService;

    @Resource
    private HttpUtil httpUtil;

    private static final String url = "https:";
    @ApiOperation(value = "从阿里云获取信息并存放在数据库")
    @PutMapping
    private R<?> updateNotes(){
        String res = httpUtil.doGet(url);
        JSONObject jsonObject = JSONObject.fromObject(res);
        JSONArray message = jsonObject.getJSONArray("message");
        for (Object o : message) {
            JSONObject note = JSONObject.fromObject(o);
            NoteList noteList = new NoteList();
            noteList.setId(note.getInt("id"));
            noteList.setDes(note.getString("des"));
            noteList.setVid(note.getBoolean("vid"));
            if (note.getBoolean("vid")){
                noteList.setDur(note.getString("dur"));
            }else {
                noteList.setDur("");
            }
            noteList.setSrc(note.getString("src"));
            System.out.println(noteList+"------");
            NoteList noteList_R = noteListService.getBaseMapper().selectById(noteList.getId());
            if (noteList_R==null){
                int insert = noteListService.getBaseMapper().insert(noteList);
                if (insert!=1){
                    return R.error();
                }
            }else {
                int update = noteListService.getBaseMapper().updateById(noteList);
                if (update!=1){
                    return R.error();
                }
            }
        }
        return R.ok();
    }

    @ApiOperation(value = "从数据库获取所有的noteList信息")
    @GetMapping()
    public R<?> getAllNoteList(){
        QueryWrapper<NoteList> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        List<NoteList> noteLists = noteListService.getBaseMapper().selectList(wrapper);
        return R.ok().data(noteLists);
    }

    @ApiOperation(value = "通过标题查询nodeList")
    @GetMapping("/{des}")
    public R<?> SelectNoteList(@PathVariable String des){
        System.out.println("查询");
        QueryWrapper<NoteList> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.like("des",des);
        List<NoteList> noteLists = noteListService.getBaseMapper().selectList(wrapper);
        return R.ok().data(noteLists);
    }
}
