package com.tts.UsersAPI.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserV2 {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
	
    @NotEmpty(message = "Please provide your First name")
    @Length(max = 20, message = "First name must be a maximum of 20 characters in length")
    @ApiModelProperty(notes = "User's first name  ")
    private String firstName;
	
    @NotEmpty(message = "Please provide your Last name")
    @Length(min = 2, message = "Last name must be a minimum of 2 characters in length")
    @ApiModelProperty(notes = "User's last name  ")
    private String lastName;
    
//    @Size(min = 2, max = 30, message =  "State must be between 4 and 20 characters in length")
    @Pattern(regexp = "[A-Za-z ]{4,20}", message = "State must be between 4 and 20 characters in length")
    @ApiModelProperty(notes = "User's location (State)")
    private String state;
    
    
    
    
    
    
    
}

