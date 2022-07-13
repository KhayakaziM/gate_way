package com.poc.gateway.service;

import com.poc.gateway.model.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
@Service
public class ServiceConveter {

    public ServiceConveter() {
    }

    private static final String FILE_NAME = "users.xml";
    public void mapUsers(){
        User user = new User();
        user.setId(1);
        user.setAge(25);
        user.setFirstName("Cateline");
        user.setGender("Female");
        user.setLastName("Jane - Test");
        jaxbObjectToXML(user);

    }

    public static void jaxbObjectToXML(User user) {

        try {
            JAXBContext context = JAXBContext.newInstance(User.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(user, System.out);
            m.marshal(user, new File(FILE_NAME));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public static User jaxbXMLToObject() {
        try {
            JAXBContext context = JAXBContext.newInstance(User.class);
            Unmarshaller un = context.createUnmarshaller();
            User user = (User) un.unmarshal(new File(FILE_NAME));
            return user;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

}
