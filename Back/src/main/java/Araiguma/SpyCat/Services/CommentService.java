package Araiguma.SpyCat.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Araiguma.SpyCat.Models.Comment;
import Araiguma.SpyCat.Repositories.CommentRepository;
import Araiguma.SpyCat.dtos.CommentInputDTO;
import Araiguma.SpyCat.dtos.CommentOutputDTO;
import jakarta.transaction.Transactional;

@Service
public class CommentService {
    @Autowired

    private CommentRepository repository;

    @Transactional
    public CommentOutputDTO create(CommentInputDTO dto){
        Comment comment = new Comment(dto); 
        repository.save(comment);
        CommentOutputDTO commentAuxiliar = new CommentOutputDTO(comment);
        return commentAuxiliar;
    }

    @Transactional
    public CommentOutputDTO update(CommentInputDTO comment){
        if(repository.existsById(comment.id())){
            Comment commentAuxiliar = new Comment(comment);
            return new CommentOutputDTO(repository.save(commentAuxiliar));
            
        }
        else{
            return null;
        }
    } 
    
    public List<CommentOutputDTO> list(long id){
        List<CommentOutputDTO> list = repository.findByUser_id(id).stream().map(comment -> new CommentOutputDTO(comment)).toList();
        return list;
    }

    public Comment read(Long id){
        if(repository.existsById(id)){
            return repository.findById(id).get();
        }
        else{
            return null;
        }
          }


    public void delete(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
        }
    }
}
