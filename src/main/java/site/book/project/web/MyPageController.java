package site.book.project.web;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.book.project.domain.Order;
import site.book.project.domain.User;
import site.book.project.dto.UserSecurityDto;
import site.book.project.repository.UserRepository;
import site.book.project.service.OrderService;
import site.book.project.service.UserService;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MyPageController {
    
    private final UserRepository userRepository;
    private final OrderService orderService;
    private final UserService userService;
    
    
    // (하은) 마이페이지 연결
    @GetMapping("/myPage")
    public String myPage(@AuthenticationPrincipal UserSecurityDto u, Model model) {
        
        User user = userRepository.findById(u.getId()).get();
        
        // 주문내역 확인 리스트로 가져옴. 날짜별로  최근순 
        List<Order> orderList = orderService.readByUserId(user.getId());
        
        model.addAttribute("orderList", orderList);
        model.addAttribute("user", user);
        
        return "/book/myPage";
    }
    
    
    // (은정)
    @PostMapping("/mypage/modify")
    public String modify(@AuthenticationPrincipal UserSecurityDto u,
                      String userImage , String nickName, String email, String phone) {
        log.info("귀찬항,,, {} {} {} {}" , userImage, nickName, email, phone);
        
        
        
        return "redirect:/myPage";
    }
    
    
    
    
}
