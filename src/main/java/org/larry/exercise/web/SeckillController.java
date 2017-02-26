package org.larry.exercise.web;

import org.larry.exercise.dto.Exposer;
import org.larry.exercise.dto.SeckillExecution;
import org.larry.exercise.dto.SeckillResult;
import org.larry.exercise.entity.Seckill;
import org.larry.exercise.enums.SeckillEnums;
import org.larry.exercise.exeception.ReapeatkillExeception;
import org.larry.exercise.exeception.SeckillCloseExeception;
import org.larry.exercise.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Larry on 2017/2/23.
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService ;

    @RequestMapping(value = "/list" ,method = RequestMethod.GET)
    public String list(Model model){
        List<Seckill> seckillList = seckillService.getAll() ;
        model.addAttribute("list",seckillList) ;
        return "list" ;
    }

    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detial(@PathVariable("seckillId")Long seckillId, Model model){
        if(seckillId == null)
            return "redirect:/seckill/list" ;
        Seckill seckill = seckillService.getById(seckillId) ;
        if (seckill == null)
            return "forward:/seckill/list" ;
        model.addAttribute("seckill",seckill) ;
        return "detail" ;
    }

    @ResponseBody
    @RequestMapping(value = "/{seckillId}/exposer",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
        SeckillResult<Exposer> result ;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId) ;
            result = new SeckillResult(true,exposer) ;
        }catch (Exception e){
            result = new SeckillResult(false,e.getMessage()) ;
        }
        return result ;
    }

    @ResponseBody
    @RequestMapping(value = "/{seckillId}/{md5}/execution",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    public SeckillResult<SeckillExecution> seckillExecution(@PathVariable("seckillId") Long seckillId, @PathVariable("md5") String md5, @CookieValue(value = "killPhone",required = false)Long userPhone){
        SeckillResult<SeckillExecution> result ;
        if(userPhone == null)
            return new SeckillResult<SeckillExecution>(false,"未注册");
        try {
            // SeckillExecution execution = seckillService.executeSeckill(seckillId,userPhone,md5) ;
            SeckillExecution execution = seckillService.executeSeckillByProcedure(seckillId,userPhone,md5) ;
            result = new SeckillResult(true,execution) ;
        }catch (ReapeatkillExeception e){
            SeckillExecution execution = new SeckillExecution(SeckillEnums.REPEAT_KILL,seckillId);
            result = new SeckillResult(false,execution) ;
        }catch (SeckillCloseExeception e) {
            SeckillExecution execution = new SeckillExecution(SeckillEnums.END, seckillId);
            result = new SeckillResult(false,execution);
        }catch (Exception e) {
            SeckillExecution execution = new SeckillExecution(SeckillEnums.INNER_ERROR, seckillId);
            result = new SeckillResult(false,execution);
        }
        return result ;
    }

    @ResponseBody
    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    public SeckillResult<Long> time(){
        Date now = new Date() ;
        return new SeckillResult<Long>(true,now.getTime()) ;
    }
}
