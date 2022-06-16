package com.samplemgt.downjacket.control;

import com.samplemgt.downjacket.service.JacketStatusService;
import com.samplemgt.downjacket.service.JacketTypeService;
import com.samplemgt.downjacket.service.ManPowerService;
import com.samplemgt.downjacket.service.ManPowerTypeService;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import com.samplemgt.downjacket.entity.ManPowerType;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebMainControl {

    @Autowired
    JacketStatusService jacketStatusService;
    @Autowired
    ManPowerService manPowerService;

    @Autowired
    ManPowerTypeService manPowerTypeService;

    @Autowired
    JacketTypeService jacketTypeService;


    @RequestMapping(value = {"/home","/home/{offset}"}, method = RequestMethod.GET)
    public String homePage(Principal principal, Model model, @PathVariable(value="offset",required = false) String offset) {
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

        List<String> jacketStatus = jacketStatusService.findAllJacketStatus().stream()
                                    .map(x -> x.getStatus()).collect(Collectors.toList());

        List<String> jacketTypes = jacketTypeService.findAllJacketType().stream()
                                    .map(x-> x.getType()).collect(Collectors.toList());

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

        model.addAttribute("principal", principal);

        model.addAttribute("manPowerTypes",manPowerTypeStrings);
        model.addAttribute("jacketTypes",jacketTypes);
        model.addAttribute("endDate", today);
        model.addAttribute("startDate", startDate);
        model.addAttribute("jacketStatus", jacketStatus);
        model.addAttribute("editors", editors);
        model.addAttribute("designers", designers);
        model.addAttribute("tailors", tailors);
        configCommonRoleAttributes(model);
        return "home";
    }

    private void configCommonRoleAttributes(Model model) {
        KeycloakAuthenticationToken authentication = (KeycloakAuthenticationToken) SecurityContextHolder.getContext()
                .getAuthentication();
        Collection<? extends GrantedAuthority> authorities =  authentication.getAuthorities();
        boolean isAdmin = false;
        for (GrantedAuthority grantedAuthority : authorities){
            if (grantedAuthority.getAuthority().toLowerCase().equals("admin")) {
                isAdmin = true;
                break;
            }
        }

        model.addAttribute("admin", isAdmin);
    }


//    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
//    public String loginPage(Model model) {
//        //model.addAttribute("title", "Welcome");
//        //model.addAttribute("message", "This is welcome page!");
//        return "home";
//    }
}
