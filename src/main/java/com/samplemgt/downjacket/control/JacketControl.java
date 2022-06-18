package com.samplemgt.downjacket.control;

import com.samplemgt.downjacket.dto.CustomJacketReq;
import com.samplemgt.downjacket.dto.JacketAttributeResp;
import com.samplemgt.downjacket.dto.JacketStatusResp;
import com.samplemgt.downjacket.dto.ValidateResp;
import com.samplemgt.downjacket.entity.Jacket;
import com.samplemgt.downjacket.entity.JacketAttribute;
import com.samplemgt.downjacket.entity.JacketStatus;
import com.samplemgt.downjacket.entity.JacketType;

import com.samplemgt.downjacket.service.JacketAttributeService;
import com.samplemgt.downjacket.service.impl.JacketCustomRepositoryImpl;
import com.samplemgt.downjacket.service.JacketService;
import com.samplemgt.downjacket.service.JacketStatusService;
import com.samplemgt.downjacket.service.JacketTypeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class JacketControl {

    @Autowired
    JacketService jacketService;

    @Autowired
    JacketStatusService jacketStatusService;

    @Autowired
    JacketAttributeService jacketAttributeService;

    @Autowired
    JacketTypeService jacketTypeService;

    @Autowired
    JacketCustomRepositoryImpl jacketCustomRepository;

    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @ApiOperation(value = "Add one Sample DownJacket")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/jacket/AddJacket", method = RequestMethod.POST,
            consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Jacket save(@RequestBody Jacket jacket) {

        if (jacketService.existsByJacketId(
                jacket.jacketId.toUpperCase().replace(" ", ""))) {
            return null;
        }
        Date today = new Date();
        if (jacket.createDate == null)
            jacket.createDate = today;
        if (jacket.status == null)
        if (jacket.status == null)
            jacket.status = jacketStatusService.getDefaultStatus().status;
        jacket.jacketId = jacket.jacketId.toUpperCase();
        return jacketService.save(jacket);
    }


    @ApiOperation(value = "ValidateJacketId")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/jacket/ValidateJacketId", method = RequestMethod.POST)
    public ValidateResp validateJacketId(String jacketId) {
        ValidateResp v = new ValidateResp();
        v.valid = false;
        if (!jacketService.existsByJacketId(jacketId.toUpperCase().replace(" ", ""))) {
            v.valid = true;
        }
        return v;
    }

    @ApiOperation(value = "get all DownJacket type")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/jacket/GetJacketType", method = RequestMethod.GET)
    @ResponseBody
    public List<String> GetAllJacketType() {
        List<JacketType> types = jacketTypeService.findAllJacketType();
        return types.stream().map(x -> x.getType()).collect(Collectors.toList());
    }


    @ApiOperation(value = "get all DownJacket status")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/admin/GetJacketStatus", method = RequestMethod.GET)
    @ResponseBody
    public List<JacketStatusResp> GetAllJacketStatus() {

        List<JacketStatus> statusList = jacketStatusService.findAllJacketStatus();
        return statusList.stream()
                .map(x -> x.getStatus())
                .map(x -> new JacketStatusResp(x, x))
                .collect(Collectors.toList());
    }


    @ApiOperation(value = "Add one man")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/admin/AddJacketAttribute", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public JacketAttribute save(@RequestBody JacketAttribute attribute) {
        return jacketAttributeService.save(attribute);
    }


    @ApiOperation(value = "get all DownJacket Attribute")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/admin/GetJacketAttribute", method = RequestMethod.GET)
    @ResponseBody
    public List<JacketAttributeResp> GetAllJacketAttribute() {

        List<JacketAttribute> attributeList = jacketAttributeService.findAllJacketAttribute();
        return attributeList.stream()
                .map(x -> x.getAttribute())
                .map(x -> new JacketAttributeResp(x, x))
                .collect(Collectors.toList());
    }




    @ApiOperation(value = "Update Down Jacket")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/admin/UpdateJacket", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Jacket UpdateJacket(@RequestBody Jacket jacket) {

        return jacketService.save(jacket);
    }

    @ApiOperation(value = "Get Down Jacket")
    @ApiResponses(
            value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/jacket/GetJacket", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<Jacket> GetJacket(@RequestBody CustomJacketReq request) {

        return jacketCustomRepository.findJacketByProperties(request);

    }



    @RequestMapping(value = "/jacket/DeleteJacket", method = RequestMethod.POST)
    public boolean DeleteJacket(@RequestParam(value = "jacketId", required = true) String ids) {
        String[] jacketIds = ids.split(",");
        for (String jacketId : jacketIds) {
            jacketService.delete(jacketId);
        }

        return true;
    }


    @RequestMapping("/logout")
    void logout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        request.logout();
        //http://localhost:8180/auth/realms/{realm-name}/protocol/openid-connect/logout?redirect_uri=encodedRedirectUri
        String logoutUrl = authServerUrl + "/realms/"+realm+"/protocol/openid-connect/logout";
        response.sendRedirect(logoutUrl);
    }

}
