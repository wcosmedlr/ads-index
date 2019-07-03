package hello.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import hello.entity.Ad;
import hello.service.AdService;
import hello.util.Views;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;
import java.util.List;

@RestController
@Api(value="Ad management", description="Allows to index and show ads")
public class AdController {

    @Autowired
    AdService adService;

    @RequestMapping(value = "/index", method = RequestMethod.GET,  produces = "text/plain")
    @ApiOperation(value = "It gives an evaluation to all the ads")
    public String index() {
        adService.processAds(adService.getAllAds());
        return "Ads has been indexed properly at " + new Date();
    }

    @ApiOperation(value = "Shows the ads as an normal user", produces = "application/json")
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user() {
        String result = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper
                    .writerWithView(Views.User.class)
                    .writeValueAsString(adService.getNotIrrelevantAds());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @ApiOperation(value = "Shows the ads as an quality user",  produces = "application/json")
    @RequestMapping(value = "/quality", method = RequestMethod.GET)
    public String quality() {
        String result = "";
        ObjectMapper mapper = new ObjectMapper();

        try {
            result = mapper
                    .writerWithView(Views.Quality.class)
                    .writeValueAsString(adService.getAllAds());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
