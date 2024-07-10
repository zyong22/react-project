package shop.mtcoding.blog.board;

import lombok.Data;
import org.springframework.data.domain.Page;
import shop.mtcoding.blog.reply.Reply;
import shop.mtcoding.blog.user.User;

import java.util.ArrayList;
import java.util.List;

public class BoardResponse {

    @Data
    public static class DTO {
        private int id;
        private String title;
        private String content;

        public DTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
        }
    }

    // 게시글 상세보기 화면
    @Data
    public static class DetailDTO {
        private int id;
        private String title;
        private String content;
        private int userId;
        private String username; // 게시글 작성자 이름
        private boolean isOwner;
        private List<ReplyDTO> replies = new ArrayList<>();

        public DetailDTO(Board board, User sessionUser) {
            this.id = board.getId();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.userId = board.getUser().getId();
            this.username = board.getUser().getUsername(); // join 해서 가져왔음
            this.isOwner = false;
            if(sessionUser != null){
                if(sessionUser.getId() == userId) isOwner = true;
            }

            this.replies = board.getReplies().stream().map(reply -> new ReplyDTO(reply, sessionUser)).toList();
        }

        @Data
        public class ReplyDTO {
            private int id;
            private String comment;
            private int userId; // 댓글 작성자 아이디
            private String username; // 댓글 작성자 이름
            private boolean isOwner;

            public ReplyDTO(Reply reply, User sessionUser) {
                this.id = reply.getId(); // lazy loading 발동
                this.comment = reply.getComment();
                this.userId = reply.getUser().getId();
                this.username = reply.getUser().getUsername(); // lazy loading 발동 (in query)
                this.isOwner = false;
                if(sessionUser != null){
                    if(sessionUser.getId() == userId) isOwner = true;
                }
            }
        }
    }

    // 게시글 목록보기 화면
    @Data
    public static class MainDTO {
        private int id;
        private String title;

        public MainDTO(Board board) {
            this.id = board.getId();
            this.title = board.getTitle();
        }
    }

    // 게시글 목록보기 화면
    @Data
    public static class MainV2DTO {
        private Integer totalPage;
        private Integer number; // 현재 페이지
        private Boolean isFirst;
        private Boolean isLast;

        private List<BoardDTO> boards;

        public MainV2DTO(Page<Board> boardPG) {
            this.totalPage = boardPG.getTotalPages();
            this.number = boardPG.getNumber();
            this.isFirst = boardPG.isFirst();
            this.isLast = boardPG.isLast();
            this.boards = boardPG.getContent().stream().map(BoardDTO::new).toList();
        }

        @Data
        public class BoardDTO {
            private int id;
            private String title;

            public BoardDTO(Board board) {
                this.id = board.getId();
                this.title = board.getTitle();
            }
        }

    }
}