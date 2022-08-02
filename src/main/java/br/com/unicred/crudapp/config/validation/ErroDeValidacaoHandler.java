package br.com.unicred.crudapp.config.validation;

import br.com.unicred.crudapp.exception.FieldValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice                                                                                        //fará os tratamentos de erros quando tiver uma exceção no caso de validação de formulário
public class ErroDeValidacaoHandler {

    private final MessageSource messageSource;                                                               //classe do spring que auxilia a pegar mensagens de erro

    @Autowired
    public ErroDeValidacaoHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)                                                 //anotação e (parametro) para o spring direcionar para esse método quando tiver uma exceção dentro de algum controller
    public List<ErroDeFormularioResponse> handle(MethodArgumentNotValidException exception) {                //método que fará o tratamento dos erros
        List<ErroDeFormularioResponse> errosDeFormResp = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        fieldErrors.forEach(erro -> {                                                                        //para cada erro fazer
            String mensagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
            ErroDeFormularioResponse erroForm = new ErroDeFormularioResponse(erro.getField(), mensagem );    //passado como parametro campo e erro
            errosDeFormResp.add(erroForm);
        });

        return errosDeFormResp;
    }

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FieldValidationException.class)           //exceção de validação de campo
    public ErroDeFormularioResponse businessExceptionHandler(FieldValidationException exception) {
        return new ErroDeFormularioResponse(exception.getCampo(), exception.getMessage());
    }

}