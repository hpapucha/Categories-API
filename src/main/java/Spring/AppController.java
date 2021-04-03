package Spring;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    //When the user visits http:/localhost/9092/hello
    @GetMapping("/hello")
    public String helloWorld(){
        return "Hello world";
    }
}
