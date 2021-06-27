package com.epam.gameshop.util.creator;

import com.epam.gameshop.entity.Address;
import com.epam.gameshop.entity.User;
import com.epam.gameshop.util.constants.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class UserCreator implements Creator<User>, Constants {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final Logger logger = Logger.getLogger(UserCreator.class);

    @Override
    public User create(HttpServletRequest request) {
        User user = new User();
        Address address = new Address();

        try {
            user.setId(Long.parseLong(request.getParameter(ID_COLUMN)));
        }catch (NumberFormatException e){
            logger.info("new user");
        }

        user.setName(request.getParameter(NAME_COLUMN));
        user.setsName(request.getParameter(SNAME_COLUMN));
        user.setLogin(request.getParameter(LOGIN));
        user.setPassword(request.getParameter(PASSWORD));
        try {
            user.setDateOfBirthday(simpleDateFormat.parse(request.getParameter(DATE_OF_BIRTHDAY_COLUMN)));
        } catch (ParseException e) {
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
        }
        user.seteMail(request.getParameter(EMAIL_COLUMN));

        try {
            address.setId(Long.parseLong(request.getParameter("address_id")));
        }catch (NumberFormatException e){
            logger.info("new address");
        }

        address.setCountry(request.getParameter(COUNTRY_COLUMN));
        address.setCity(request.getParameter(CITY_COLUMN));
        address.setStreet(request.getParameter(STREET_COLUMN));
        address.setNumberOfHouse(request.getParameter(NUMBER_COLUMN));
        user.setAddress(address);

        return user;
    }
}
