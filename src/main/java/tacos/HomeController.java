package tacos;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // HomeController 클래스가 컴포넌트로 식별되게 함.
public class HomeController {

    @GetMapping("/")  // 루트경로인 /의 웹 요청을 처리.
    public String home() {
        return "home"; // 뷰 이름 반환
    }
}
