package org.seckill.web;

import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.dto.SeckillResult;
import org.seckill.entity.Seckill;
import org.seckill.enums.SeckillStateEnum;
import org.seckill.exception.RepectSeckillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by xudong on 2017/3/26.
 */
@Controller
@RequestMapping(value = "/seckill")
public class SeckillController {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model)
    {
        List<Seckill> list=seckillService.getSeckillList();
        model.addAttribute("list",list);
        return "list";
    }
    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    //@PathVariable接收路径传递来的参数
    public String detail(@PathVariable("seckillId") Long seckillId,Model model)
    {
        if (seckillId==null)
            return "redirect:/seckill/list";
        Seckill seckill=seckillService.getById(seckillId);
        if (seckill==null)
            return "forward:/seckill/list";
        model.addAttribute("seckill",seckill);
        return "detail";
    }
    //ajax json数据请求处理
    //method = RequestMethod.POST,post方式只能添加和修改信息，对浏览框中修改的地址无效
    @RequestMapping(value = "/{seckillId}/expser",
            method = RequestMethod.POST
            )//produces = {"application/json;charset=UTF-8"}
    @ResponseBody//告诉spring我是返回json类型的，并且 produces = {"application/json;charset=UTF-8"}
    public SeckillResult<Exposer> exposer(Long seckillId)//json的数据封装，不用Model了
    {
        SeckillResult<Exposer> result;
         try {
             //try catch的使用，若是报异常，我把信息捕获给你显示出来，有两种可能
                 Exposer exposer=seckillService.exportSeckillUrl(seckillId);
                 result=new SeckillResult<Exposer>(true,exposer);
                   logger.info(" exposer ={}", exposer );
               } catch (Exception e) {
                   logger.error(e.getMessage(),e);
                 result=new SeckillResult<Exposer>(false,e.getMessage());
               }
        return result;
    }
    @RequestMapping(value = "/{seckillId}/{md5}/execution",
                    method = RequestMethod.POST
                    )
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value = "killPhone",required = false) Long phone)
    {                                               //required = false意思是不是必要的，防止没有手机号报错
        SeckillResult<SeckillExecution> execution;
        if (phone==null)
            return new SeckillResult<SeckillExecution>(false,"未注册");
         try {
                SeckillExecution seckillExecution=seckillService.executeSeckill(seckillId,phone,md5);
                return new SeckillResult<SeckillExecution>(true,seckillExecution);
         } catch (SeckillCloseException e) {
                SeckillExecution seckillExecution=new SeckillExecution(seckillId, SeckillStateEnum.END);
                return new SeckillResult<SeckillExecution>(false,seckillExecution);
         } catch (RepectSeckillException e) {
             SeckillExecution seckillExecution=new SeckillExecution(seckillId, SeckillStateEnum.REPEAT_KILL);
             return new SeckillResult<SeckillExecution>(false,seckillExecution);
         }catch (SeckillException e){
                logger.error(e.getMessage(),e);
                SeckillExecution seckillExecution=new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
                return new SeckillResult<SeckillExecution>(false,seckillExecution);
               }

    }
    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    public SeckillResult<Long> time()
    {
        Date now=new Date();
        return new SeckillResult<Long>(true,now.getTime());
    }

}
