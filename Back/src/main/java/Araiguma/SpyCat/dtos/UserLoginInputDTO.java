package Araiguma.SpyCat.dtos;


public record UserLoginInputDTO (
    String email,
    String password
){

    // public UserLoginInputDTO(User user){
    //     this(user.getEmail(), user.getPassword());
    // }
    
}
