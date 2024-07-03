package Araiguma.SpyCat.dtos;

public record UserUpdateInputDTO(
    Long id,
    String username,
    String email,
    String city, 
    String state,
    String icon,
    String password
){

}