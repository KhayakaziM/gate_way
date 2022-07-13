package com.poc.gateway.amq.resource;

import com.poc.gateway.model.User;
import com.poc.gateway.service.ServiceConveter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import javax.jms.Queue;

@RestController
@RequestMapping("/rest/publish")
public class ProducerResource {

    @Autowired
    JmsTemplate jmsTemplate;
    @Autowired
    Queue queue;
    private final ServiceConveter serviceConveter;


    @Autowired
    public ProducerResource(ServiceConveter serviceConveter) {
        this.serviceConveter = serviceConveter;
    }
    @GetMapping("/{message}")
    public String publish(@PathVariable("message") final String message) {
        jmsTemplate.convertAndSend(queue, message);
        return "Published Successfully";
    }
    //ToDO:
    @PostMapping("/{message}")
    public String sendJaxb(@PathVariable("message")
                          final String message) {
        serviceConveter.mapUsers();
        return "marshalled objects to XML Successfully";
    }

    @GetMapping("/AllUsers")
    public ResponseEntity<User> getUsers(){
        return ResponseEntity.ok().body(this.serviceConveter.jaxbXMLToObject());
    }
}

