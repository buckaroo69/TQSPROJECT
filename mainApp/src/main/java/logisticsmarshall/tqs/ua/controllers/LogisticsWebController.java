package logisticsmarshall.tqs.ua.controllers;

import logisticsmarshall.tqs.ua.exceptions.*;
import logisticsmarshall.tqs.ua.model.*;
import logisticsmarshall.tqs.ua.services.DeliveryService;
import logisticsmarshall.tqs.ua.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class LogisticsWebController {
    static final String ADMINROLE = "ADMIN";
    static final String COMPANYROLE = "COMPANY";
    static final String DRIVERROLE = "DRIVER";
    @Autowired
    UserServiceImpl userServiceImpl;
    @Autowired
    DeliveryService deliveryService;

    String redirectRoot = "redirect:/";

    @GetMapping("/register")
    public String registration(Model model) {
        if (userServiceImpl.isAuthenticated()) {
            return redirectRoot;
        }
        User user = new User();
        user.setCompany(new Company());
        user.setDriver(new Driver());
        model.addAttribute("user", user);

        return "signUp";
    }

    @PostMapping("/register")
    public String registration(UserDTO userDTO, CompanyDTO companyDTO, DriverDTO driverDTO) throws AccountDataException {
        User user = User.fromDTO(userDTO);
        Company company = Company.fromDTO(companyDTO);
        Driver driver = Driver.fromDTO(driverDTO);

        if (!User.validateNewUser(user, driver, company))
            throw new AccountDataException();
        if (userServiceImpl.isAuthenticated())
            return redirectRoot;

        if (user.getRole().equals(COMPANYROLE))
            user.setCompany(company);
        else if (user.getRole().equals(DRIVERROLE))
            user.setDriver(driver);
        else
            throw new AccountDataException();

        try {
            userServiceImpl.encryptPasswordAndStoreUser(user);
        } catch (DataIntegrityViolationException e) {
            throw new AccountDataException();
        }
        return redirectRoot;
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (userServiceImpl.isAuthenticated() && logout == null) {
            return redirectRoot;
        }
        if (error != null)
            model.addAttribute("error", error);
        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        return "login";
    }

    @GetMapping(path="/")
    public String index() {
        User user = userServiceImpl.getUserFromAuth();
        if (user==null){return "redirect:/info";}
        if (user.getRole().equals(COMPANYROLE)){return "redirect:/companyDash";}
        if (user.getRole().equals(DRIVERROLE)){return "redirect:/driverDash";}
        if (user.getRole().equals(ADMINROLE)){return "redirect:/adminDash";}
        return "redirect:/info";
    }

    @GetMapping(path="/info")
    public String info() {
        return "info";
    }


    @GetMapping(path="/companyDash")
    public String companyDash(Model model) throws AccessForbiddenException {
        User user = userServiceImpl.getUserFromAuthAndCheckCredentials(COMPANYROLE);
        Company company = user.getCompany();
        if(company == null) throw new AccessForbiddenException();
        List<Delivery> delList = new ArrayList<>(deliveryService.getDeliveriesByCompany(company));

        model.addAttribute("profile",user);
        model.addAttribute("company",company);
        model.addAttribute("deliveries",delList);

        return "mainDash";
    }


    @GetMapping(path="/driverDash")
    public String workerDash(Model model) throws AccessForbiddenException {
        User user = userServiceImpl.getUserFromAuthAndCheckCredentials(DRIVERROLE);
        Driver driver = user.getDriver();
        if(driver == null) throw new AccessForbiddenException();
        populateDriverPage(model,user,driver);
        return "mainDash";
    }


    @GetMapping(path="/companyProfile")
    public String companyProfile(Model model) throws AccessForbiddenException {
        User user = userServiceImpl.getUserFromAuthAndCheckCredentials(COMPANYROLE);
        Company company = user.getCompany();
        if(company == null) throw new AccessForbiddenException();
        model.addAttribute("profile",user);
        model.addAttribute("phone_number",company.getPhoneNumber());
        model.addAttribute("delivery_type",company.getDeliveryType());
        model.addAttribute("apikey",company.getApiKey());
        model.addAttribute("address",company.getAddress());
        return "businessOwnerProfile";
    }


    @GetMapping(path="/driverProfile")
    public String driverProfile(Model model) throws AccessForbiddenException {
        User user = userServiceImpl.getUserFromAuthAndCheckCredentials(DRIVERROLE);
        Driver driver = user.getDriver();
        if(driver == null) throw new AccessForbiddenException();
        model.addAttribute("profile_name",user.getName());
        model.addAttribute("phone_number",driver.getPhoneNo());
        model.addAttribute("vehicle",driver.getVehicle().name());
        model.addAttribute("apikey",driver.getApiKey());
        Set<Reputation> repLst = driver.getReputation();
        int avgRep = 0;
        if(!repLst.isEmpty()){
            for(Reputation rep : repLst)avgRep+=rep.getRating();
            avgRep/=repLst.size();
        }
        model.addAttribute("avg_reputation",avgRep);
        return "workerProfile";
    }

    @GetMapping(path="/adminDash")
    public String adminDashboard(Model model) throws AccessForbiddenException {
        User user = userServiceImpl.getUserFromAuthAndCheckCredentials(ADMINROLE);
        return "adminDash";
    }

    @PostMapping(path="/grantApiAccess")
    public String adminDashboard() throws AccessForbiddenException {
        User user = userServiceImpl.getUserFromAuthAndCheckCredentials(ADMINROLE);
        return "redirect:/adminDash";
    }

    @PostMapping(path="/driverDash")
    public String changeDelivery(Model model, @ModelAttribute("action") String action, @ModelAttribute("deliveryId") String deliveryId) throws AccessForbiddenException {
        long deliveryIdValue = Long.parseLong(deliveryId);
        User user = userServiceImpl.getUserFromAuthAndCheckCredentials(DRIVERROLE);
        Driver driver = user.getDriver();
        if(driver == null) throw new AccessForbiddenException();
        //Show driver Available to pickup + delivery history
        populateDriverPage(model,user,driver);


        try {
            switch (action) {
                case "accept":
                    deliveryService.acceptDelivery(user, deliveryIdValue);
                    model.addAttribute("message", "Delivery was successfully accepted");
                    break;
                case "pickup":
                    deliveryService.pickUpDelivery(user, deliveryIdValue);
                    model.addAttribute("message", "Delivery was successfully picked up");
                    break;
                case "finish":
                    deliveryService.finishDelivery(user, deliveryIdValue);
                    model.addAttribute("message", "Delivery was successfully finished");
                    break;
                case "cancel":
                    deliveryService.cancelDelivery(user, deliveryIdValue);
                    model.addAttribute("message", "Delivery was successfully canceled");
                    break;
                default:
                    throw new InvalidDeliveryActionException();
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "mainDash";
    }

    private void populateDriverPage(Model model, User user, Driver driver) {
        List<Delivery> delAvailableList = deliveryService.getDeliveriesByStage(Delivery.Stage.REQUESTED);
        List<Delivery> delDriverList = new ArrayList<>(driver.getDelivery());
        delDriverList.addAll(delAvailableList);
        model.addAttribute("profile",user);
        model.addAttribute("driver",driver);
        model.addAttribute("deliveries",delDriverList);
        double rep = 0;
        if(!driver.getReputation().isEmpty()){
            for(Reputation r : driver.getReputation()) rep+=r.getRating();
            rep/=driver.getReputation().size();
        }
        model.addAttribute("driver_avg_reputation",rep);
    }

}
