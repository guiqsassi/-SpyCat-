package Araiguma.SpyCat.Comment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import Araiguma.SpyCat.Models.Comment;
import Araiguma.SpyCat.Models.Pet;
import Araiguma.SpyCat.Models.User;
import Araiguma.SpyCat.Repositories.CommentRepository;
import Araiguma.SpyCat.Services.CommentService;
import Araiguma.SpyCat.dtos.CommentInputDTO;
import Araiguma.SpyCat.dtos.CommentOutputDTO;
import Araiguma.SpyCat.dtos.LocationInputDTO;
import Araiguma.SpyCat.dtos.PetInputDTO;
import Araiguma.SpyCat.dtos.UserInputDTO;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {
    @InjectMocks
    private CommentService service;

    @Mock
    private CommentRepository repository;

    @Test
    public void SuccessfullycreateComment() throws IOException{

        User user = new User();
        user.setId(1);
        Pet pet = new Pet();
        pet.setId(1l);
        CommentInputDTO dtoComment = new CommentInputDTO(0l,user, "text", LocalDateTime.now(), pet);

        Comment comment = new Comment(dtoComment);

        when(repository.save(comment)).thenReturn(comment);

        CommentOutputDTO result = service.create(dtoComment);

        assertNotNull(result);
        assertEquals(result.text(), dtoComment.text());
    }
    
    @Test 
    public void SuccesfulDeleteComment() throws IOException{

        Comment commentDelete = new Comment();
        commentDelete.setId(1l);

        when(repository.existsById(anyLong())).thenReturn(true);

        service.delete(commentDelete.getId());

        verify(repository, times(1)).deleteById(1l);
    }

    @Test 
    public void ErrorDeleteComment() throws IOException{

        Comment commentDelete = new Comment();
        commentDelete.setId(1l);

        when(repository.existsById(anyLong())).thenReturn(false);

        service.delete(commentDelete.getId());

        verify(repository, times(0)).deleteById(1l);
    }
    @Test
    public void SuccessfulCommentUpdate(){
    User user = new User();
    user.setId(1);   
     Pet pet = new Pet();
     pet.setId(1l);

     CommentInputDTO dtoComment = new CommentInputDTO(0l,user, "text", LocalDateTime.now(), pet);
     Comment comment = new Comment(dtoComment);
 
     when(repository.existsById(anyLong())).thenReturn(true);
     when(repository.save(any())).thenReturn(comment);
     
    CommentOutputDTO commentAtualizado = service.update(dtoComment); // n sei pq ta dando errooo eu sou burro peço perdão 

    assertEquals(comment.getId(), commentAtualizado.id());
    assertNotNull(commentAtualizado);
    }

    @Test
    public void ErrorCommentUpdate(){
        User user = new User();
        user.setId(1);   
        
        Pet pet = new Pet();
        pet.setId(1l);

        CommentInputDTO dtoComment = new CommentInputDTO(0l, user, "text", LocalDateTime.now(), pet);

   
     when(repository.existsById(anyLong())).thenReturn(false);
     
    CommentOutputDTO petAtualizado = service.update(dtoComment);// msm problema do outro n sei fazer a conversão peço perdão

    assertNull(petAtualizado);
    }


}
