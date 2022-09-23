package com.xxc.homework.controller;

import com.xxc.homework.entity.Message;
import com.xxc.homework.entity.User;
import com.xxc.mvc.annotations.controller.Controller;
import com.xxc.mvc.annotations.mapping.GetMapping;
import com.xxc.mvc.annotations.mapping.Mapping;
import com.xxc.mvc.annotations.mapping.PostMapping;
import com.xxc.mvc.annotations.param.RequestParams;
import com.xxc.mvc.model.ModelAndView;

import java.util.List;

import javax.servlet.http.HttpSession;

/**
 * @author 夏旭晨
 * @version 1.1.1
 * @class MessageController
 * @Description
 * @createTime 2119-15-29 21:22
 */
@Controller
@Mapping(url = "/msg")
public class MessageController {

    @GetMapping(url = "/show")
    public ModelAndView showMsg(@RequestParams(value = "page",nullable = true) Integer page){
        if(page == null)
            page = 1;
        final List<Message> messages = Message.getMessages(Message.PAGE_SIZE*(page-1));
        ModelAndView mv = new ModelAndView();
        mv.addAttribute("messages",messages);
        mv.addAttribute("pages",Message.getSize());
        mv.addAttribute("curPage",page);
        mv.addAttribute("isMine",false);
        mv.setView("/page/msg/msg_board.jsp");
        return mv;
    }

    @GetMapping(url = "/show/mine")
    public ModelAndView showMineMsg(@RequestParams(value = "page",nullable = true) Integer page, HttpSession session){
        if(page == null)
            page = 1;
        final List<Message> messages = Message.getMineMessages((
                (User)session.getAttribute("user")).getUsername(),
                Message.PAGE_SIZE*(page-1));
        ModelAndView mv = new ModelAndView();
        mv.addAttribute("messages",messages);
        mv.addAttribute("pages",Message.getSize());
        mv.addAttribute("curPage",page);
        mv.addAttribute("isMine",true);
        mv.setView("/page/msg/msg_board.jsp");
        return mv;
    }

    @PostMapping(url = "/alter")
    public ModelAndView alterMsg(HttpSession session,@RequestParams(value = "isMine")Boolean isMine,@RequestParams(value = "id")Integer id,@RequestParams(value = "page") Integer page,@RequestParams(value = "content")String content){
        Message msg = new Message();
        msg.setId(id);
        msg.setContent(content);
        msg.updateMessage();
        if(isMine)
            return showMineMsg(page, session);
        else
            return showMsg(page);
    }

    @PostMapping(url = "/add")
    public ModelAndView addMsg(@RequestParams(value = "isMine")Boolean isMine,HttpSession session,@RequestParams(value = "content") String content)
    {
        Message msg = new Message();
        msg.setUser((User) session.getAttribute("user"));
        msg.setContent(content);
        msg.leaveMessage();
        if(isMine)
            return showMineMsg(1, session);
        else
            return showMsg(1);
    }

    @GetMapping(url = "/delete")
    public ModelAndView deleteMsg(HttpSession session,@RequestParams(value = "isMine")Boolean isMine,@RequestParams("id")Integer id){
        Message msg = new Message();
        msg.setId(id);
        msg.deleteMessage();
        if(isMine)
            return showMineMsg(1, session);
        else
            return showMsg(1);
    }

}
