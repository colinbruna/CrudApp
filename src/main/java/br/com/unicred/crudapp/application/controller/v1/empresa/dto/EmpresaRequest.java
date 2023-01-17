package br.com.unicred.crudapp.application.controller.v1.empresa.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EmpresaRequest {

    @NotBlank(message = "Nome é obrigatório")
    @Size(max = 50)
    @Schema(example = "Mercado X")
    private String nome;

    @CNPJ
    @NotBlank(message = "CNPJ é obrigatório")
    @Schema(example = "36194570000107", maxLength = 14, description = "somente números")
    private String cnpj;

    @NotBlank(message = "E-mail é obrigatório")
    //@Email //passa sem . e não passa sem @
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email inválido")
    //não aceita sem @, ., aceita .qualquercoisa
    @Schema(example = "aaa@email.com")
    private String email;

    @NotBlank(message = "Telefone é obrigatório")
    @Pattern(regexp = "^\\d{11}$", message = "Telefone inválido")
    @Schema(example = "51999999999", maxLength = 11, description = "DDD + telefone")
    private String telefone;

    private EnderecoRequest endereco;

    public EmpresaRequest() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(final String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(final String telefone) {
        this.telefone = telefone;
    }

    public EnderecoRequest getEndereco() {
        return endereco;
    }

    public void setEndereco(final EnderecoRequest endereco) {
        this.endereco = endereco;
    }
}
