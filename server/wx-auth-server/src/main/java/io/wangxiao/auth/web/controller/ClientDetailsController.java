package io.wangxiao.auth.web.controller;

import io.wangxiao.auth.domain.dto.OauthClientDetailsDto;
import io.wangxiao.auth.oauth.OauthClientDetailsDtoValidator;
import io.wangxiao.auth.service.OauthService;
import org.beetl.json.JsonTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Handle 'client_details' management
 *
 * @author Shengzhao Li
 */
@Controller
public class ClientDetailsController {


    @Autowired
    private OauthService oauthService;

    @Autowired
    private OauthClientDetailsDtoValidator clientDetailsDtoValidator;


    @RequestMapping("client_details")
    public String clientDetails(Model model) {
        List<OauthClientDetailsDto> clientDetailsDtoList = oauthService.loadAllOauthClientDetailsDtos();
        model.addAttribute("clientDetailsDtoList", clientDetailsDtoList);

        JsonTool jsonTool = new JsonTool();
//        System.out.println(jsonTool.serialize(clientDetailsDtoList));
        return "clientdetails/client_details";
    }


    /*
    * Logic delete
    * */
    @RequestMapping("archive_client/{clientId}")
    public String archiveClient(@PathVariable("clientId") String clientId) {
        oauthService.archiveOauthClientDetails(clientId);
        return "redirect:../client_details";
    }

    /*
    * Test client
    * */
    @RequestMapping("test_client/{clientId}")
    public String testClient(@PathVariable("clientId") String clientId, Model model) {
        OauthClientDetailsDto clientDetailsDto = oauthService.loadOauthClientDetailsDto(clientId);
        model.addAttribute("clientDetailsDto", clientDetailsDto);
        return "clientdetails/test_client";
    }


    /*
    * Register client
    * */
    @RequestMapping(value = "register_client", method = RequestMethod.GET)
    public String registerClient(Model model) {
        model.addAttribute("formDto", new OauthClientDetailsDto());
        return "clientdetails/register_client";
    }


    /*
    * Submit register client
    * */
    @RequestMapping(value = "register_client", method = RequestMethod.POST)
    public String submitRegisterClient(@ModelAttribute("formDto") OauthClientDetailsDto formDto, BindingResult result) {
        clientDetailsDtoValidator.validate(formDto, result);
        if (result.hasErrors()) {
            return "clientdetails/register_client";
        }
        oauthService.registerClientDetails(formDto);
        return "redirect:client_details";
    }


}