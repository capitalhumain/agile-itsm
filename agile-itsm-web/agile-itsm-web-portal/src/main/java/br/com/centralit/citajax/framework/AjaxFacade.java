package br.com.centralit.citajax.framework;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class AjaxFacade {

    protected HttpServletRequest request;
    protected HttpServletResponse response;

}
