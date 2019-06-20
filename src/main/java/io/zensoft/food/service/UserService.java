package io.zensoft.food.service;

import io.zensoft.food.model.Role;
import io.zensoft.food.model.User;
import io.zensoft.food.repository.RoleRepository;
import io.zensoft.food.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


@Service("userService")
public class UserService{

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Qualifier("roleRepository")
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public User findUserById(int id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user,String role) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(1);
        Role userRole = roleRepository.findByRole(role);
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return userRepository.save(user);
    }


    public ArrayList<User> getAllByStatus(String status){
        ArrayList<User> list=(ArrayList<User>)userRepository.getAllByStatus(status);
        ArrayList<User> finalist=new ArrayList<User>();
        if(!list.isEmpty() && list.size()>2){
        finalist.add(list.get(0));
        finalist.add(list.get(1));
        finalist.add(list.get(2));
        finalist.add(list.get(3));
        finalist.add(list.get(4));
        finalist.add(list.get(5));

        return finalist;
        }

        return list;
    }

    public ArrayList<User> getAll()
    {
        return (ArrayList) userRepository.findAll();
    }

    public User save(User user){
        return userRepository.save(user);
    }


}