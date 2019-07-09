package io.zensoft.food.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "email"
        })
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 25)
    private String firstname;

    @NotBlank
    @Size(max = 25)
    private String lastname;

    @NaturalId
    @NotBlank
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "manager", orphanRemoval = true)
    @JsonIgnore
    private List<CompanyOrder> companyOrder;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    @JsonIgnore
    private List<Payment> userPayments = new ArrayList<>();

    @OneToMany(mappedBy = "manager", orphanRemoval = true)
    @JsonIgnore
    private List<Payment> managerPayments = new ArrayList<>();

    private BigDecimal balance = new BigDecimal(0);

    public User(@NotBlank @Size(max = 25) String firstname,String lastname, @NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(max = 100) String password) {
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.lastname = lastname;
    }

    public User(@NotBlank @Size(max = 25) String firstname, @NotBlank @Size(max = 25) String lastname, @NotBlank @Size(max = 40) @Email String email, @NotBlank @Size(max = 100) String password, Set<Role> roles) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public void addBalance(BigDecimal amount){
        setBalance(this.balance.add(amount));
    }

    public void offBalance(BigDecimal amount){
        setBalance(this.balance.subtract(amount));
    }
}