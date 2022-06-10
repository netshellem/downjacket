package com.samplemgt.downjacket.control;

import com.samplemgt.downjacket.service.JacketTypeService;
import com.samplemgt.downjacket.service.ManPowerService;
import com.samplemgt.downjacket.service.ManPowerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;
import java.time.LocalDate;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.samplemgt.downjacket.entity.ManPowerType;

@Controller
public class WebMainControl {

    @Autowired
    JacketTypeService jacketTypeService;

    @Autowired
    ManPowerService manPowerService;

    @Autowired
    ManPowerTypeService manPowerTypeService;


    @RequestMapping(value = {"/home","/home/{offset}"}, method = RequestMethod.GET)
    public String homePage(Model model, @PathVariable(value="offset",required = false) String offset) {
        LocalDate previousDate;
        LocalDate currentDate = LocalDate.now();
        if (offset == null) {
            previousDate = currentDate.minusDays(300);
        }else{
            previousDate = currentDate.minusDays(Long.parseLong(offset));
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String today =  currentDate.format(formatter);
        String startDate = previousDate.format(formatter);

        List<String> jacketTypes = jacketTypeService.findAllJacketType().stream()
                                    .map(x -> x.getType()).collect(Collectors.toList());

        List<ManPowerType> manPowerTypes = manPowerTypeService.findAllManPowerType();

        List<String> editors = null, designers = null, tailors = null;
        for( ManPowerType manPowerType : manPowerTypes)
        {
            switch (manPowerType.index){
            case 1:
                editors = manPowerService.findAllManPowerByType(manPowerType.getType())
                        .stream().map(x -> x.name)
                        .collect(Collectors.toList());
                break;
            case 2:
                designers = manPowerService.findAllManPowerByType(manPowerType.getType())
                        .stream().map(x -> x.name)
                        .collect(Collectors.toList());
                break;
            case 3:
                tailors = manPowerService.findAllManPowerByType(manPowerType.getType())
                        .stream().map(x -> x.name)
                        .collect(Collectors.toList());
                    break;
            }
        }

        List<String> manPowerTypeStrings = manPowerTypes.stream()
                                            .map(x -> x.getType()).collect(Collectors.toList());

        model.addAttribute("manPowerTypes",manPowerTypeStrings);
        model.addAttribute("endDate", today);
        model.addAttribute("startDate", startDate);
        model.addAttribute("jacketTypes", jacketTypes);
        model.addAttribute("editors", editors);
        model.addAttribute("designers", designers);
        model.addAttribute("tailors", tailors);

        return "home";
    }


//    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
//    public String loginPage(Model model) {
//        //model.addAttribute("title", "Welcome");
//        //model.addAttribute("message", "This is welcome page!");
//        return "home";
//    }
}
