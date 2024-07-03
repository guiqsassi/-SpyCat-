package Araiguma.SpyCat.dtos;

import Araiguma.SpyCat.Models.User;

public record UserMessageOutputDTO(
    Long id,
    String username, 
    String icon
) {
    public UserMessageOutputDTO (User user){
        this(user.getId(), user.getUsername(), user.getIcon());
    }
}
