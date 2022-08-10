package br.com.unicred.crudapp.application.config;

import br.com.unicred.crudapp.domain.service.exception.BusinessException;
import br.com.unicred.crudapp.application.controller.v1.exception.EntityNotFoundException;
import br.com.unicred.crudapp.application.controller.v1.exception.erros.ErroDeResposta;
import br.com.unicred.crudapp.application.controller.v1.exception.erros.ErroDeFormulario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice     //fará os tratamentos de erros quando tiver uma exceção no caso de validação de formulário
public class ApplicationControllerAdvice {

    private final MessageSource messageSource;

    @Autowired
    public ApplicationControllerAdvice(final MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)  //erros de validação de formulário
    @ResponseStatus(code = HttpStatus.BAD_REQUEST) //prenchimento incorreto do formulário de criar/alterar empresa
    public List<ErroDeFormulario> handle(final MethodArgumentNotValidException exception) {
        List<ErroDeFormulario> dto = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroDeFormulario erro = new ErroDeFormulario(e.getField(), mensagem);
            dto.add(erro);
        });

        return dto;
    }

    @ExceptionHandler(BusinessException.class)  //erros de negócio - validações de cep
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErroDeFormulario handleBusinessException(final BusinessException e) {
        return new ErroDeFormulario(e.getCampo(), e.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class) //entidade não encontrada
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ResponseEntity<ErroDeResposta> handleEntityNotFoundException(final EntityNotFoundException ex) {
        ErroDeResposta erroDeResposta = new ErroDeResposta(ex.getMessage());
        return new ResponseEntity<>(erroDeResposta, HttpStatus.NOT_FOUND);
    }
}
