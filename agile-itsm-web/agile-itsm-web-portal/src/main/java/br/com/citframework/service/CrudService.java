package br.com.citframework.service;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;

public interface CrudService extends IService {

    /**
     * Grava o IModel passado como parametro em meio persistente.
     *
     * @param entity
     * @return
     * @throws LogicException
     * @throws ServiceException
     *
     */
    <E extends BaseEntity> E create(final E entity) throws LogicException, ServiceException;

    /**
     * Atualiza o Object passado como parametro em meio persistente.
     *
     * @param entity
     * @return
     * @throws LogicException
     * @throws ServiceException
     *
     */
    <E extends BaseEntity> void update(final E entity) throws LogicException, ServiceException;

    /**
     * Exclui o Object passado como parametro do meio persistente.
     *
     * @param entity
     * @return
     * @throws LogicException
     * @throws ServiceException
     *
     */
    <E extends BaseEntity> void delete(final E entity) throws LogicException, ServiceException;

    /**
     * Recebe um Object com seus atributos chave preenchidos, recupera todos os atributos do meio persistente e retorna o Object Preenchido.
     *
     * @param entity
     * @return
     * @throws LogicException
     * @throws ServiceException
     *
     */
    <E extends BaseEntity> E restore(final BaseEntity entity) throws LogicException, ServiceException;

    <E extends BaseEntity> Collection<E> find(final E entity) throws LogicException, ServiceException;

    <E extends BaseEntity> Collection<E> list() throws LogicException, ServiceException;

}
