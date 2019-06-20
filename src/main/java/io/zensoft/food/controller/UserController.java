package io.zensoft.food.controller;

import io.zensoft.food.model.Role;
import io.zensoft.food.model.User;
import io.zensoft.food.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    @GetMapping("/login")
//    public ResponseEntity<User> login(@RequestBody User newUser) {
//        User userExists = userService.findUserByEmail(newUser.getEmail());
//        if (userExists!=null) {
//            return new ResponseEntity(userExists, HttpStatus.OK);
//        }
//        return new ResponseEntity(HttpStatus.BAD_REQUEST);
//    }

    @GetMapping("/login")
    public ResponseEntity login() {
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/user/{status}")
    public List<User> retrieveUser(@PathVariable String status) {
        return userService.getAllByStatus(status);
    }

    @GetMapping("/user/s")
    private List<User> getAllUsers(){
        return userService.getAll();
    }

//    @GetMapping("/user/{user_is}")
//    private User retrieveUserById(@PathVariable int id){
//        return userService.findUserById(id);
//    }

    @PostMapping("/user/register")
    public ResponseEntity<Void> registerUserAccount(@RequestBody User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "*There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        } else {
            user.setStatus("user");
            userService.saveUser(user, "USER");
            modelAndView.addObject("user", new User());
            return ResponseEntity.ok().build();
        }

        }

}