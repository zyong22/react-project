package shop.mtcoding.blog.reply;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import shop.mtcoding.blog.board.Board;
import shop.mtcoding.blog.user.User;

public class ReplyRequest {

    @Data
    public static class SaveDTO {
        @NotNull(message = "빈 값이 들어올 수 없습니다.")
        private Integer boardId;
        @NotEmpty(message = "댓글을 작성하여 주십시오.")
        private String comment;

        public Reply toEntity(User sessionUser, Board board){
            return Reply.builder()
                    .comment(comment)
                    .board(board)
                    .user(sessionUser)
                    .build();
        }
    }
}
