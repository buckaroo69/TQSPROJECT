package logisticsmarshall.tqs.ua.services;

import logisticsmarshall.tqs.ua.exceptions.*;
import logisticsmarshall.tqs.ua.model.*;
import logisticsmarshall.tqs.ua.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private ReputationRepository reputationRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User getUserByName(String username) {
        return userRepository.findByName(username);
    }
    public List<Driver> getKeylessDrivers(){return driverRepository.findAllByApiKey(null);}
    public List<Company> getKeylessCompanies(){return companyRepository.findAllByApiKey(null);}
    public List<DriverAdminView> getLowRatingDrivers() {
        return reputationRepository.findAllByRatingMaximum(2.5,3l);
    }
    public User save(User newUser) {
        return userRepository.save(newUser);
    }




    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByName(username);
        if (user == null) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();


        grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));



        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), grantedAuthorities);
    }

    public boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }
    public User getUserFromAuth(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return null;
        }


        return userRepository.findByName(authentication.getName());
    }

    public User getUserFromAuthAndCheckCredentials(String role) throws AccessForbiddenException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            throw new AccessForbiddenException("You must log in");
        }
        User user = userRepository.findByName(authentication.getName());
        if (user==null){throw new AccessForbiddenException("You must log in");}
        if (!user.getRole().equals(role)){throw new AccessForbiddenException("Bad Credentials");}
        return  user;
    }
    public void encryptPasswordAndStoreUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    public void grantCompanyKey(Long id) throws AccountDataException {
        Company company = companyRepository.findCompanyById(id);
        if (company==null){throw  new AccountDataException();}
        company.setApiKey(UUID.randomUUID().toString());
        companyRepository.save(company);

    }

    public void grantDriverKey(Long id) throws AccountDataException{
        Driver driver = driverRepository.findDriverById(id);
        if (driver==null){throw new AccountDataException();}
        driver.setApiKey(UUID.randomUUID().toString());
        driverRepository.save(driver);
    }

    public void banDriver(Long id) throws AccountDataException {
        Driver driver = driverRepository.findDriverById(id);
        if (driver==null){throw new AccountDataException();}
        //cascade is a mess
        for (Delivery delivery: driver.getDelivery()){
            delivery.setDriver(null);
        }
        deliveryRepository.saveAll(driver.getDelivery());
        User user = driver.getUser();
        user.setRole("BANNED DRIVER");
        userRepository.save(user);
        driverRepository.delete(driver);

    }
}