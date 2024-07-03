package Araiguma.SpyCat.Controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Araiguma.SpyCat.Models.Comment;
import Araiguma.SpyCat.Services.CommentService;
import Araiguma.SpyCat.dtos.CommentInputDTO;
import Araiguma.SpyCat.dtos.CommentOutputDTO;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/comments")
@CrossOrigin("*")

public class CommentController {
    @Autowired

    private CommentService service;
    
    @PostMapping
    public ResponseEntity<CommentOutputDTO> create(@Valid @RequestBody CommentInputDTO comment){
        CommentOutputDTO CommentCriado = service.create(comment);
        return new ResponseEntity<CommentOutputDTO>(CommentCriado, HttpStatus.CREATED);
    }
    @PutMapping
        public ResponseEntity<CommentOutputDTO> update(@Valid @RequestBody CommentInputDTO comment){
        CommentOutputDTO commentUpdate = service.update(comment);
        return new ResponseEntity<CommentOutputDTO>(commentUpdate, HttpStatus.OK);
    }
    @GetMapping("/{id}")
        public ResponseEntity<Comment> read (@Valid @PathVariable Long id){
            Comment commentBuscado = service.read(id);
            return new ResponseEntity<Comment>(commentBuscado,HttpStatus.OK);
    }
    @GetMapping
        public ResponseEntity<List<CommentOutputDTO>> list(@RequestParam long id){
            List<CommentOutputDTO> listComments = service.list(id);
            return new ResponseEntity<List<CommentOutputDTO>>(listComments, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
        public ResponseEntity<Comment> delete(@Valid @PathVariable Long id){
            service.delete(id);
            return new ResponseEntity<Comment>(HttpStatus.NO_CONTENT);
        }
}
