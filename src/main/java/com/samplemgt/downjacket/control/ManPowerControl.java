package com.samplemgt.downjacket.control;

import com.samplemgt.downjacket.dto.ValidateResp;
import com.samplemgt.downjacket.entity.ManPower;
import com.samplemgt.downjacket.entity.ManPowerType;
import com.samplemgt.downjacket.service.ManPowerService;
import com.samplemgt.downjacket.service.ManPowerTypeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ManPowerControl {

    @Autowired
    ManPowerTypeService manPowerTypeService;

    @Autowired
    ManPowerService manPowerService;

    @ApiOperation(value = "get all ManPower type")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/manpower/GetManPowerType", method = RequestMethod.GET)
    @ResponseBody
    public List<String> GetAllManPowerType() {

        List<ManPowerType> types = manPowerTypeService.findAllManPowerType();
        return types.stream().map(x -> x.getType()).collect(Collectors.toList());
    }

    @ApiOperation(value = "get all ManPower")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/manpower/GetManPower", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public List<ManPower> GetAllManpower() {

        return manPowerService.findAllManPower();
    }

    @ApiOperation(value = "get ManPower by Type")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/manpower/GetManPowerByType", method = RequestMethod.GET)
    @ResponseBody
    public List<ManPower> GetManpowerByType(String type) {

        return manPowerService.findAllManPowerByType(type);
    }

    @ApiOperation(value = "Delete ManPower")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/manpower/DeleteManPower", method = RequestMethod.POST)
    public boolean deleteManPowerByName(@RequestParam(value = "names", required = true) String names) {
        String[] nameArray = names.split(",");
        for (String name : nameArray){
            manPowerService.deleteByName(name);
        }

        return true;
    }

    @ApiOperation(value = "Validate if man exists")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/manpower/ValidateMan", method = RequestMethod.POST)
    public ValidateResp ValidateMan(String name) {
        ValidateResp v = new ValidateResp();
        v.valid = false;
        if(!manPowerService.existsByName(name.replace(" ","")))
            v.valid = true;

        return v;
    }

    @ApiOperation(value = "Add one man")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success"), @ApiResponse(code = 500, message = "InternalServerError")})
    @RequestMapping(value = "/manpower/AddManPower", method = RequestMethod.POST, consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ManPower save(@RequestBody ManPower manPower) {
        Date today = new Date();
        manPower.createDate = today;
        return manPowerService.save(manPower);
    }
}
