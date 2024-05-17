package shop.mtcoding.blog.reply;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import shop.mtcoding.blog._core.utils.ApiUtil;
import shop.mtcoding.blog.user.SessionUser;
import shop.mtcoding.blog.user.User;

@RequiredArgsConstructor
@RestController
public class ReplyController {
    private final ReplyService replyService;
    private final HttpSession session;

    @DeleteMapping("/api/replies/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        User sessionUser = (User) session.getAttribute("sessionUser");
        replyService.댓글삭제(id, sessionUser.getId());
        return ResponseEntity.ok(new ApiUtil(null));
    }

    @PostMapping("/api/replies")
    public ResponseEntity<?> save(@Valid @RequestBody ReplyRequest.SaveDTO reqDTO, Errors errors){
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        ReplyResponse.SaveDTO respDTO = replyService.댓글쓰기(reqDTO, sessionUser);
        return ResponseEntity.ok(new ApiUtil(respDTO));
    }
}
