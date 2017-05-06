package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import trainapp.TableSeat;

@Controller
public class StationController {

    public StationController() {
    }

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/stations/all")
    @SendTo("/topic/allStations")
    public Greeting allStations(HelloMessage message) throws Exception {
        System.out.println("I AM HERE in all stations");
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

    @MessageMapping("/stations/update")
    @SendTo("/topic/updateStations")
    public Greeting updateStations(HelloMessage message) throws Exception {
        System.out.println("I AM HERE in update stations");
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

    public void updateStationMessage() {
        try {
            this.template.convertAndSend("/topic/updateStations", new Greeting("Fire"));
        } catch(Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
