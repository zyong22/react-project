package shop.mtcoding.blog.reply;


import lombok.Data;

public class ReplyResponse {

    @Data
    public static class SaveDTO {
        private Integer boardId;
        private String comment;

        public SaveDTO(Integer boardId, Reply reply) {
            this.boardId = boardId;
            this.comment = reply.getComment();
        }
    }
}
