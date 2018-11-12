package com.pars.entity;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

@Data
@Builder
/*@Check(constraints = "ATIVO IN ('S', 'N')")*/
@Table(name = "USER", indexes = @Index(name = "UNQ_USER_LOGIN", columnList = "LOGIN", unique = true))
@Entity(name = "User")
public class User implements UserDetails {

    @Id
    private int codigo;

    @NotNull(message = "O Login do Usuário não pode ser vazio.")
    @Length(max = 30, message = "O Login do Usuário deve conter até 30 dígitos.")
    @Column(name = "LOGIN", length = 30, nullable = false, unique = true)
    private String login;

    @NotNull(message = "A Senha do Usuário não pode ser vazia.")
    @Length(max = 100, message = "A Senha do Usuário deve conter até 100 dígitos.")
    @Column(name = "SENHA", length = 100, nullable = false)
    private String senha;

    @NotNull(message = "O Nome do Usuário não pode ser vazio.")
    @Length(max = 120, message = "O Nome do Usuário deve conter até 120 dígitos.\n" +
            "Caso seu nome contenha mais que isto, favor, utilize abreviações.")
    @Column(name = "NOMEUSU", length = 120, nullable = false)
    private String nome;

    @Email(message = "O E-mail do Usuário está em formato inválido.")
    @NotNull(message = "O E-mail do Usuário não pode ser vazio.")
    @Length(max = 100, message = "O E-mail do Usuário deve conter até 100 dígitos.\n" +
            "Caso seu e-mail contenha mais que isto, favor utilize outro ou contate o suporte.")
    @Column(name = "EMAILUSU", length = 100, nullable = false)
    private String email;

    @NotNull(message = "A Data/Hora de Cadastro do Usuário não pode ser vazia.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "DHCADASTRO", nullable = false)
    private Date dhCadastro = new Date();

    @NotNull(message = "O Ativo do Usuário não pode ser vazio.")
    @Column(name = "ATIVO", nullable = false)
    private char ativo = 'S';

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.getSenha();
    }

    @Override
    public String getUsername() {
        return this.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return (this.getAtivo() == 'S');
    }

    @Override
    public boolean isAccountNonLocked() {
        return (this.getAtivo() == 'S');
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return (this.getAtivo() == 'S');
    }

    @Override
    public boolean isEnabled() {
        return (this.getAtivo() == 'S');
    }
}
