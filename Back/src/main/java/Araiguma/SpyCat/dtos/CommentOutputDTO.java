package Araiguma.SpyCat.dtos;

import java.time.LocalDateTime;

import Araiguma.SpyCat.Models.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentOutputDTO(
    @NotNull
    Long id,
    @NotNull
    UserMessageOutputDTO user,
    @NotBlank
    String text,
    @NotNull
    LocalDateTime date

) {
    public CommentOutputDTO(Comment comment){
        this(comment.getId(), new UserMessageOutputDTO(comment.getUser()), comment.getText(), comment.getDate());
    }
}
